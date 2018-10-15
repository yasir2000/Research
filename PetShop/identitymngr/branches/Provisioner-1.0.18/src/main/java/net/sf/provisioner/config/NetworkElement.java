package net.sf.provisioner.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import net.sf.provisioner.responses.Response;
import net.sf.provisioner.utils.PathHelper;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


/**
 * Esta clase representa a un elemento de red y sus datos
 * de acceso. Tiene un nombre que puede ser el numero de IP, 
 * un tipo que sirve para determinar que interfaz se debe 
 * utilizar para conectarse al mismo, usuario y password
 * para aquellos elementos de red que requieran iniciar una
 * sesion, clave de autenticacion para aquellos elementos de 
 * red que utilicen este mecanismo de seguridad, y patrones
 * de exito o fracaso para los elementos de red basados en 
 * protocolo Telnet.
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:22 $
 * @author Gonzalo Espert
 */
public class NetworkElement {
	
	
	/** Logger for this class and subclasses */
	Logger logger = Logger.getLogger(getClass());
	
	public String name = new String();
	public String port = new String();
	public String type = new String();
	public String user = "user";
	public String password = "password";
	public String authKey = "authKey";
	public String bin = "command";
	public String create = "create";
	public String delete = "delete";
	public String enable = "enable";
	public String disable = "disable";
	public String loginPrompt = "ogin username:";
	public String systemPrompt = ">";
	public String sudoPassword = "";
	public String sudoPasswordPrompt = "Password:";
	public String loginErrorSecuence = "Failed";
	public boolean sendInitialCRLF = false;
	public String parameter1 = "";
	public String parameter2 = "";
	public String parameter3 = "";
	public String parameter4 = "";
	public String parameter5 = "";
	
	public Hashtable responses = new Hashtable();
	
	String configPath;
	
	/**
     * Constructor de un elemento de red en blanco.
     * 
     */
	public NetworkElement() {}
	
	/**
     * Creates a NetworkElement with its properties populated using
     * information in an XML file.
     * 
     * @param neName a name used to construct a reference to an XML file
     * in Provisioner's config. file location.
     * e.g. "intraway" is translated to "config/intraway.xml".
     * 
     */
	public NetworkElement(String neName) throws FileNotFoundException {
		this(new File(neName + ".xml"));
	}

    /**
     * Creates a NetworkElement with its properties populated using 
     * information in an XML file.
     * 
     * @param configFile an XML configuration file (see asterisk.xml and 
     * intraway.xml for examples of the format.) 
     */
    public NetworkElement(File xmlConfig) throws FileNotFoundException {
    	this(new FileInputStream(xmlConfig));    	    
    }
    
    public NetworkElement(InputStream xmlConfig) {
    	
    	try {
    	    Document d = new SAXBuilder().build(xmlConfig); 
            List children = d.getContent();  
            Iterator iterator = children.iterator();
            while (iterator.hasNext()) {
                Element child = (Element) iterator.next();
                getParameters(child);
            }
        } catch (Exception e) {
            logger.fatal("Error reading network element configuration file " + name);
            e.printStackTrace();
        }            
    }

	/**
     * Metodo utilizado para obtener los parametros del elemento de red.
     * 
     * @param current
     * 			Elemento del nodo del archivo xml
     * 
     * @param tipoOperacion
     * 			               
     */
	void getParameters(Element current) {
		
	    for (Element child : filterChildParameters(current)) {
            String paramName = child.getAttributeValue("name").toLowerCase();
            String value     = child.getAttributeValue("value");
            
	    	if (paramName.equals("host"      )) name     = value; 
	    	if (paramName.equals("type"      )) type     = value;
	    	if (paramName.equals("username"  )) user     = value;
	    	if (paramName.equals("password"  )) password = value;
	    	if (paramName.equals("password_sudo")) {
	    		sudoPassword = value;
	    	}
	    	if (paramName.equals("password_authorisation")) {
	    		authKey = value;
	    	}

	    	if (paramName.equals("bin"       )) bin      = value;
	    	if (paramName.equals("create"    )) create   = value;
	    	if (paramName.equals("delete"    )) delete   = value;
	    	if (paramName.equals("enable"    )) enable   = value;
	    	if (paramName.equals("disable"   )) disable  = value;
	    	if (paramName.equals("errorlogin")) loginErrorSecuence = value;
	    	
	    	/* Cargamos los parametros a enviar */
	    	if (paramName.equals("$1")) parameter1 = value;
	    	if (paramName.equals("$2")) parameter2 = value;
	    	if (paramName.equals("$3")) parameter3 = value;
	    	if (paramName.equals("$4")) parameter4 = value;
	    	if (paramName.equals("$5")) parameter5 = value;
	    	
	    	/* Cargamos las respuestas posibles del elemento de red de esta ruta */
	    	if (paramName.equals("responses")) {
	    		storePossibleResponses(child.getAttributeValue("value"));
	        }
	    }  
	}
	
	protected Collection<Element> filterChildParameters(Element currentElement) {
		List          children = currentElement.getChildren();
		List<Element> params   = new ArrayList<Element>();
        for (Object c : children) {
		    Element child = (Element) c;
		    if (child.getName().equalsIgnoreCase("parameter")) {
		    	params.add(child);
		    }
        }
        return params;
	}
	
	/**
     * Metodo utilizado para obtener las respuestas posibles del elemento de red.
     * 
     * @param archivo
     * 			Nombre del archivo donde residen las respuestas posibles
     * 			para este tipo de elemento de red
     * 			               
     */
	void storePossibleResponses(String responses) {
		
		/*File configFile = new File(responses);        
		if (!configFile.exists()) {
            configFile = new File("config/" + responses + ".xml");
        }
        if (!configFile.exists()) {
      	    Exception e = new FileNotFoundException("NetworkElement's config. not found: " + configFile);
      	    logger.fatal(e);
        	// TODO: returning isn't the best course of action here.
        }
        if (!configFile.canRead()) {
        	Exception e = new IOException("NetworkElement's config. is not readable: " + configFile);
        	logger.fatal(e);
        	// TODO: returning isn't the best course of action here. 
        	return;
        }*/
        
		InputStream configFile = null;
		try {
			configFile = PathHelper.pathToStream(responses);
		} catch (IOException ioe) {
			/* Failure is acceptable here.  It may be the 
			 * user wants the system to use the default
			 * config. dir. */ 
		}
		
		if (configFile == null) {
			try {
				configFile = PathHelper.pathToStream(this.configPath + responses + ".xml");
			} catch (IOException ioe) {
				logger.fatal("Couldn't find responses for '" + responses + "'", ioe);
				// TODO: is returning enough?  Should we throw something?
				return;
			}
		}

		if (configFile == null) {
			logger.fatal("Couldn't find responses for '" + responses + "'");
			// TODO: is returning enough?  Should we throw something?
			return;
		}
		
		/* Recorremos el archivo xml y cargamos cada una de las
		 * respuestas posibles
		 */
		try {
			  
		      Document d = new SAXBuilder().build(configFile); 
		      List children = d.getContent();  
			  Iterator iterator = children.iterator();
			  while (iterator.hasNext()) {
			      Element child = (Element) iterator.next();
			      getResponses(child, "");
			  }
	    } catch (Exception e) {
	    	Exception wrapper = new Exception(
	    			"Error reading possible responses from configuration file "+ responses,
	    			e
	    	);
	    	logger.fatal(wrapper);	    	
	    }
	}
    
	/**
     * Metodo utilizado para obtener las respuestas posibles del elemento de red.
     * 
     * @param current
     * 			Elemento del nodo del archivo xml
     * @param operationType
     * 			               
     */
	void getResponses(Element current, String operationType) {
		
	    List children = current.getChildren();
	    Iterator iterator = children.iterator();
	    while (iterator.hasNext()) {
	      Element child = (Element) iterator.next();
	      if (child.getName().equalsIgnoreCase("operation"))
	    	  operationType = child.getAttributeValue("type");;
	      if (child.getName().equalsIgnoreCase("response")) {
	    	  
	    	  Response respuestaPosible = new Response();
	    	  respuestaPosible.tipoOperacion = operationType; 	    	  
	    	  respuestaPosible.result = child.getAttributeValue("result");
	    	  if (child.getAttributeValue("success").equalsIgnoreCase("yes")) respuestaPosible.successfull = true;
	    	  respuestaPosible.errorStr = child.getAttributeValue("respond");
	    	  if (child.getAttributeValue("retry").equalsIgnoreCase("yes")) respuestaPosible.retry = true;
			  
	    	  int i = this.responses.size();
	    	  this.responses.put(new Integer(i++), respuestaPosible);
	    	  
	      }  
	      getResponses(child, operationType);
	    }   
	}
	
	public String toString() {
		return "host=" + name + ":" + port + ", type=" + type + ", user=" + user;
	}

	/**
	 * @return the configPath
	 */
	public String getConfigPath() {
		return configPath;
	}

	/**
	 * @param configPath the configPath to set
	 */
	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}
}
