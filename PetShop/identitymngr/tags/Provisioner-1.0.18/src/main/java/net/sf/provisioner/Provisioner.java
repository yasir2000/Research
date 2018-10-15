package net.sf.provisioner;

import java.util.*;
import java.io.*;

import net.sf.provisioner.config.Configuration;
import net.sf.provisioner.core.Producer;
import net.sf.provisioner.utils.I18n;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import sun.misc.Signal;
import sun.misc.SignalHandler;




/**
 * This class is the main class. It sets general configuration  
 * and starts a producer.
 *             
 * @author     <a href="mailto:gespert@users.sourceforge.net">Gonzalo Espert</a>
 * @version $ $
 * @created    Sep 21, 2006
 */

public class Provisioner {
	
	static String versionNumber = "1.0.18";
	static Producer producer;
	static long timeInterval = 60000;
	static String configPath = "src/config/";
	static int maxThreads = 4;
	/**
     * .
     * 
     * 
     * 
     * @param args 
     *                      
     *           
     */
	public static void main(String[] args) {
		
		/** Logger for this class and subclasses */
		Logger logger = Logger.getLogger("Provisioner");
		
		if (args.length > 0) {
		    try {
		        timeInterval = Long.parseLong(args[0])* 1000;
		    } catch (NumberFormatException e) {
		    	System.out.println("Argument #1, (INTERVAL_TIME), has to be expressed in number of seconds");
		    	System.exit(0);
		    }
		    try {
		        maxThreads = Integer.parseInt(args[1]);
		    } catch (NumberFormatException e) {
		    	System.out.println("Argument #2, (MAX_CONSUMERS), has to be expressed in number of threads");
		    	System.exit(0);
		    }
		    try {
		        configPath = args[2];
		    } catch (Exception e) {
		    	System.out.println("Argument #3, (CONFIG_PATH), has to be expressed as a string");
		    	System.exit(0);
		    }
		}
				
		//set looger config
		PropertyConfigurator.configure(configPath + "log4j.properties");
		
		Properties p = System.getProperties();
		
		// Internationalization
		String language, country;
		if (args.length < 5) {
			language = new String("en");
			country = new String("US");
		} else {
			language = new String(args[3]);
			country = new String(args[4]);
		}
		I18n.setLocale(language, country);
		
		logger.info(I18n.getLogString("Provisioner.main.version") + versionNumber);
		logger.debug(p.toString());
		
		
		// set signal handler
		Signal.handle(new Signal("TERM"), handlerTerm);
		
		// Unix or Linux
		// We create PID file
		String OS = new String();
		OS = p.getProperty("os.name");
		if (OS.equalsIgnoreCase("Linux")) {
			writePID();
			Signal.handle(new Signal("HUP"), handlerHup);
		}
		
		// There is no PID in Windows vista
		if (OS.equalsIgnoreCase("Windows Vista")) {
			
			createPIDfile("1234");
			
			//deletePIDfile();
			
		}
		
		//Construct producer
		producer = new Producer(timeInterval, configPath, maxThreads);
		
		Object[] i18nArguments = {(timeInterval/1000)};
		logger.info(I18n.getFormattedLogString("Provisioner.main.interval", i18nArguments));
		
		/* Start producer */
		try { producer.Start(new Configuration(configPath)); }
		catch (Exception e) {e.printStackTrace(); Stop();}
		
	}
	
	static void writePID() {
		
		/** Logger for this class and subclasses */
		Logger logger = Logger.getLogger("Provisioner");
		
		String[] cmd = { "/bin/bash", "-c", "echo $PPID" };
		
		try // obtain process id                
		{                        
			Process myProcess = Runtime.getRuntime().exec( cmd ) ;
			BufferedReader stdout = new BufferedReader( new InputStreamReader( myProcess.getInputStream() ) ) ;
			String line = stdout.readLine() ;
			// write it to a file
			createPIDfile(line);
			while ( line != null )
			{                                
				logger.info( line ) ;
				line = stdout.readLine() ;
			}                        
			int exitVal = myProcess.waitFor() ;
			logger.debug(I18n.getLogString("Provisioner.writePID.exitValue") + exitVal ) ;
		} catch (Exception e) {e.printStackTrace();}
	}
	
	static void createPIDfile(String pid) {
		
		try
		{
		
			PrintStream out = new PrintStream(
					new BufferedOutputStream(
						new FileOutputStream("provisioner.pid")));
	
			out.println(pid);
			out.flush();
			out.close();
			
		} catch (Exception e) {e.printStackTrace();}
	}
	
	static void deletePIDfile() {
		File f = new File("provisioner.pid");
		if(f.exists()) {
			f.delete();
		}
	}
	
	// Delete PID file and exit if SIGTERM is received
	static SignalHandler handlerTerm = new SignalHandler () {
	      public void handle(Signal sig) {
	    	  
	    	  /** Logger for this class and subclasses */
	    	  Logger logger = Logger.getLogger("Provisioner");
	  		
	          logger.info(I18n.getLogString("Provisioner.receivedSignal") + sig);
	          
	          Stop();   
	      }
	};

	// Re-start producer in order to re-load configuration 
	// when SIGHUP is received
	static SignalHandler handlerHup = new SignalHandler () {
	      public void handle(Signal sig) {
	    	  
	    	  /** Logger for this class and subclasses */
	    	  Logger logger = Logger.getLogger("Provisioner");
	  		
	          logger.info(I18n.getLogString("Provisioner.receivedSignal") + sig);
	   
	          logger.info(I18n.getLogString("Provisioner.reloadingConfiguration"));
	          producer.Stop();
	        	  
	          /* Start producer */
	      	  try { producer.Start(new Configuration(configPath)); }
	      	  catch (Exception e) {e.printStackTrace(); Stop();}
	          
	      }
	};
	
	// Exit in a gracefull maner
	static void Stop() {
		
		/** Logger for this class and subclasses */
		Logger logger = Logger.getLogger("Provisioner");
  	  
		logger.info(I18n.getLogString("Provisioner.deletingPIDFile"));
		deletePIDfile();
		System.exit(0);
	}
}
