package net.sf.provisioner.utils;

import java.util.HashMap;
import java.util.Map;

import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

public class LDAPUtils {

    public static BasicAttributes buildAttrs(String className, Map<String, Object> attrs) {
    	return buildAttrs(
    			new String[] { className }, 
    			attrs
    	);
    }
    
    public static BasicAttributes buildAttrs(String className, String attrName, Object attrValue) {
    	Map<String, Object> attrs = new HashMap<String, Object>(1);
    	attrs.put(attrName, attrValue);
    	
    	return buildAttrs(className, attrs);
    }
    
	public static BasicAttributes buildAttrs(String[] classNames, Map<String, Object> attrs) {
		BasicAttributes result = new BasicAttributes();
		Attribute       classes = new BasicAttribute("objectClass");
		classes.add("top");
		for (String className : classNames) {
			if (className.equals("top")) continue;
			classes.add(className);
		}
		result.put(classes);
		
		for (String key : attrs.keySet()) {			
			result.put(key, attrs.get(key));
		}
		return result;
	}

}
