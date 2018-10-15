<?xml version="1.0" ?>
<Concern name="InformationRetrieval">
<Goal id="1">
<!-- The following support focuses on semantics like requires and 
features sections inside task definition these are mapped to an abstract 
of feature tree model  of the target application -->

<Neptune Support><![CDATA
define 
// It should be possible to retrieve information from the system.
.]]>
</Neptune Support>
< Goal id="1.1">
<![CDATA
[It should be possible to access
information about the attractions.]]>
</Goal>
< Goal id="1.2">
<![CDATA[page 1.2]]>
It should be possible to access
information about the current location. </Goal>
</Goal>
< Goal id="1.3">
<![CDATA[  
It should be possible to obtain a list of available preset 
tours
]]>
</Goal>
</Concern>
<!-- InformationRetrival concern in XML -->
<?xml version="1.0" ?>
<!-- Mobility concern in XML -->
<Concern name="Mobility">
< Goal id="1">
<![CDATA[  
The system will be accessed on the move
]]>
< Goal id="1.1">The system will be accessed from within
a limited area</Goal>
</Goal>
</Concern>
<!-- Mobility concern in XML -->
<Concern name="Availability">
< Goal id="2.1.1">The CPU Load cycles is below 60</Goal>
<AND>
< Goal id="2.1.2">Memory is less than 2000 bytes  
<![CDATA[  

define situation RegenerationTest
{
if (Concept ["Concepts\HeavyLoad")
{
return true;
}
else
{
<!--
The following statements shows how fuzziness of concepts can determine level of certainty in decision making process.  
Fuzzification of concept HeavyLoad can determine how much its close to main logic rule or 
goal achievement percentage as induction for yes or no decision unfearfully. 
Its also obvious that AND’s minimizes certainty ratios, minimum (conjunction), OR’s maximum (disjunction), NOT’s complement (negation).  
This is a good example to show how to locate placeholder’s strategically in the problem space. 
Location and schedule of cross-cutting within multi-layered static logging. 
Temporal state machine can be used like formalism of problem (formal methods) ie; state machine, automata, petri-net’s,…etc, 
but all have to be solidly bounded by logic to narrow problem. Problem space slicing can be approached healthily through 
strategic decision placeholder inside code (annotation location). This will solve scalability issues. 
A multi-objectiveness decision problem solving provides expert decision making of pervasiveness inside 
complex role based environments, it also helps in processing multi decision profiles in XCAML, 
which also bases an intuit for secure multi-tenant’s cloud architecture. 
The advanced logging for system on conceptual model level will represent the meta model for 
problem space or the shared memory in constraint logic programming (CLP). 
Using CLP will be helpful in spreading the computational effort of solving logical problem between threaded acting objects. 
XACML is very much useful in rule/logic based application composition. 
The match and bind filters can be used as concept based weaving. 
So by introducing new concepts and define fluent variables using CA-SPA now we may add any vocab to the programming model at 
runtime as it will be tested to evaluate logical constraints between intentions (user based temporal states) and  goals 
(problem domain based states). These states are presented to the runtime world via 
concerns at the user level and as aspects and subjects at the meta-level/levels.
-->   
returun concept HeavyLoad as boolean
{
if (instrument
["Resource/CPULoad".value > 60
&&
["Resource/AvailableMemory"].value > 2000
}
define action Regenerate using RegenerationTest
{
Services=Regenerate(me, machineID);
ReRoute(s);
}
define situation pRegenerate using RegenerationTest
if !(Concept["Concepts\HeavyLoad")
{
return true;
}
else
{
return false;
}
]
]]>
</Goal>
</Concern>



?
Meta data layer support for Moods Model
<?xml version="1.0" ?>
<MetaConcern name="InformationRetrieval">
<Description>The operation of accessing information from a
computer system </Description>
<Concepts>Database retrieval, Multimedia retrieval</Concepts>
<Aspects> ‘Availability’, ‘|’, ‘Mobility’, ‘|’, ‘InformationUpdate’
</Aspects>
</MetaConcern>

// InformationRetrival meta concern meta data layer

<?xml version="1.0" ?>
 <MetaConcern name="Mobility">
<Description>The quality of moving freely </Description>
<Concepts>Wireless networks, Mobile phones, Context aware
systems </Concepts>
<Aspects> ‘Availability’, ‘|’, ‘Portability’ ,‘|’, ‘Context’ </Aspects>
</MetaConcern>
