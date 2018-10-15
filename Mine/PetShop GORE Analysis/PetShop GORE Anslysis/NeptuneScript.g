grammar NeptuneScript;

options {
	language = CSharp2;
}

@header {
using System.Collections.Generic;
}

@namespace { NeptuneScript }

script returns [ScriptDefinitions scriptDef]
	:	{ $scriptDef = new ScriptDefinitions(); }
		(definition {
			if ($definition.d is Task) $scriptDef.addTask((Task)$definition.d);
			else if ($definition.d is Process) $scriptDef.addProcess((Process)$definition.d);
			else $scriptDef.addNBLO((NBLO)$definition.d);
		})+
	;

definition returns [Object d]
	:	task { $d = $task.t; }
	|	process { $d = $process.p; }
	|	nblo { $d = $nblo.nblo; }
	;

// process is not defined in NeptuneScript EBNF so defining it basing on samples
// in 'thesis'
process returns [Process p]
	:	'process' name '{' 'tasks' '{' tasks[new LinkedList<ExecutionStatement>()] '}' '}' {
			$p = new Process($name.text);
			foreach (ExecutionStatement es in $tasks.ess) {
				$p.AddExecutionStatement(es);
			}
		}
	;

// tasks is not defined in NeptuneScript EBNF so defining it basing on samples
// in 'thesis'
tasks[LinkedList<ExecutionStatement> essBase] returns [LinkedList<ExecutionStatement> ess]
	:	{ $ess = $essBase; }
		(tc1=task_call ';' { $ess.AddLast($tc1.et); }
		| 'if' '(' expression ')' tc2=task_call ';' { $ess.AddLast(new ExecutedIf($expression.e, $tc2.et)); }
		)
		(tasks[ess])*
	;
	
task_call returns [ExecutedTask et]
	:	name { $et = new ExecutedTask($name.text); }
	|	name '(' variable_reference ')' { $et = new ExecutedTask($name.text, $variable_reference.text); }
	;

task returns [Task t]
	:	'task' n=name ('with' tp=name o=name)? '{'
		'requirements' '{' requirement[new LinkedList<Requirement>()]? '}'
		('actions' '{' action[new LinkedList<Action>()] '}')? '}'
		{ $t = new Task($n.text, $tp.text, $o.text, $requirement.rs, $action.actions); }
	;

requirement[LinkedList<Requirement> rsBase] returns [LinkedList<Requirement> rs]
	:	(n=name ':' 'require' an_object '.' a_feature
//	 ('(' a=name ')' )? not implemented for PoC
		';' {
			$rs = rsBase;
			$rs.AddLast(new Requirement($n.text, $an_object.text, $a_feature.text/*, $a.text*/));
		}
//		| 'when' expression '{' requirement '} // switching off for PoC
		)
		(requirement[rsBase])*
	;

action[LinkedList<Action> asBase] returns [LinkedList<Action> actions]
	:	(n1=name {
				actions=asBase;
				actions.AddLast(new Action($n1.text));
			}
			(',' n2=name {
				$actions.Last.Value.AddRequirement($n2.text);
			})*
			'via' n3=name ';'
			{
				$actions.Last.Value.SetNBLOName($n3.text);
			}
		)
		(action[asBase])*
        ;

name : NAME
     ;

nblo returns [NBLO nblo]
	:	('nblo' | 'define') name
		'with' a_neptune_object a_variable_string_literal_value
		'{' 'purpose' '{' feature[new LinkedList<Feature>()] '}' 'actuation' '{' actuation '}' '}'
		{
			$nblo = new NBLO($name.text, $a_neptune_object.text, $a_variable_string_literal_value.text,
								$feature.fs, $actuation.a);
		}
	;

// evaluate is not supported for PoC
feature[LinkedList<Feature> fsBase] returns [LinkedList<Feature> fs]
	:	('feature' a_feature 'to' an_object ';') {
			$fs = fsBase;
			$fs.AddLast(new Feature($an_object.text, $a_feature.text));
		}
		(feature[fsBase])*
	;

actuation returns [ActuationStatement a]
	:	'BaseLanguage' '.' language_connector '('
		location ',' method_name ',' variable_data ','
		synchronized_value ')' ';'
		{
			$a = new ActuationStatement($language_connector.text, $location.text,
										$method_name.text, $variable_data.text, $synchronized_value.text);
		}
	;

expression returns [Expression e]
	:	(vr1=variable_reference { $e = new Expression($vr1.text); }
//		| '(' expression ')' // switching off for PoC
//		| UNARYOPERATOR expression)
//		(BINARYOPERATOR expression)*
		| '!' vr2=variable_reference { $e = new Expression($vr2.text, false); }
		)
	;

variable_reference
	:	(NAME) ('.' NAME)*
	;

a_neptune_object 
	:	NAME
	;

a_variable_string_literal_value 
	:	NAME
	;

an_object
	:	NAME
	;

a_feature 
	:	NAME ('.' NAME)*
	;

language_connector 
	:	NAME
	;

location 
	:	STRING
	;

method_name
	:	STRING
	;

variable_data
	:	NAME
	;
	
// For now meaning of synchronized_value is not clear, samples in 'thesis' even show
// that it may be ommited
synchronized_value
	:	NAME
	;

NAME 	:	('a'..'z' | 'A'..'Z') ('a'..'z' | 'A'..'Z' | '0'..'9')+ ;

STRING 	:
	'"' ( EscapeSequence | ~('\u0000'..'\u001f' | '\\' | '\"' ) )* '"'
	;

fragment EscapeSequence
    	:   '\\' (UnicodeEscape |'b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    	;

fragment UnicodeEscape
	: 'u' HexDigit HexDigit HexDigit HexDigit
	;

fragment HexDigit
	: '0'..'9' | 'A'..'F' | 'a'..'f'
	;

BINARYOPERATOR
	:	'==' | '!='
	;

UNARYOPERATOR
	:	'!'
	;

COMMENT	:	'//' (~'\n')* '\n' { $channel = HIDDEN; } ;

WS	:	(' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;
