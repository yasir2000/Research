/**
 * 
 */
package net.sf.provisioner.adapters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat; 
import java.util.*;

import net.sf.provisioner.config.DataBase;
import net.sf.provisioner.connectors.JDBCConnector;
import net.sf.provisioner.core.Operation;


/**
 * .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:24 $
 * @author Gonzalo Espert
 */
public class SQLAdapter extends Adapter {
	
	JDBCConnector db;
	
	/**
     * .
     * <p>
     * 
     * .
     * <p>
     * 
     * 
     * 
     * @param 
     *            
     * @throws 
     *            
     *           
     */
	public SQLAdapter(DataBase db) { this.db = new JDBCConnector(db); }

	/**
     * .
     * <p>
     * 
     * .
     * <p>
     * 
     * 
     * 
     * @param 
     *            
     * @throws 
     *            
     *           
     */
	public String ExecuteCommand(String command) { return new String(); }


	/**
     * .
     * <p>
     * 
     * .
     * <p>
     * 
     * 
     * 
     * @param 
     *            
     * @throws 
     *            
     *           
     */
	public Operation[] getPendingOperations(int maxRows) throws Exception{
		
		Operation[] operations = {new Operation() , new Operation(), new Operation(), new Operation()};
		
		ResultSet rs = null;
		
		try {
			this.db.statement.setMaxRows(maxRows); // fixed array size
			rs = this.db.statement.executeQuery("SELECT * FROM operations_queue WHERE \"status\" in ('PEND','RETR') ORDER BY id, insert_date");
		} catch (SQLException se) {
		  throw se;
		}
		
		try {
			
				if (rs.next()){
			
						int i = 0;
						boolean doContinue = true;
						while (doContinue) {
							
							if (rs.isLast()) doContinue = false;
							
							Operation operation = new Operation();
							operation.setId(rs.getString("id"));
							operation.setType(rs.getString("type"));
							operation.setParameters(rs.getString("xml_string"));
							
							operations[i] = operation;
							
							rs.next();
							i++;
						}
					
				} else { logger.info("No pending operations"); }
			
		} catch (Exception ex) { throw ex; }
		   
		return operations;
	}
	
	
	/**
     * .
     * <p>
     * 
     * .
     * <p>
     * 
     * 
     * 
     * @param 
     *            
     * @throws 
     *            
     *           
     */
	public void registerResponse(String response, String operationID) throws Exception {
		String fecha;
		PreparedStatement ps = null;

		//escape special characters (http://www.jguru.com/faq/view.jsp?EID=8881)
		response = response.replaceAll("'", "''");
		
  		try {
  			// F.Hevia: date format in compliance with postgreSQL
  			fecha = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
  			
  			//update operation's response
	    	ps = db.connection.prepareStatement("UPDATE operations_queue SET \"response\" = '" +
	    		                              response + "', \"execution_date\" = '" + fecha +
	    		                              "' WHERE id = " + operationID);
	    	
  			//Windows Vista
	    	//ps = db.connection.prepareStatement("UPDATE cola_operaciones SET \"descripcion_respuesta\" = '" +
            //        response +"' WHERE id =" + operationID);

  		} catch (SQLException se) {
  			throw se;
  		}

   		try {
	    	  ps.executeUpdate();
   		} catch (SQLException se) {
   			throw se;
   		}
   		fecha = null;
	}
	
	/**
     * .
     * <p>
     * 
     * .
     * <p>
     * 
     * 
     * 
     * @param 
     *            
     * @throws 
     *            
     *           
     */
	public void updateOperationStatus(String operationID, String status) throws Exception {
		
		PreparedStatement ps = null;

  		try {
	    		  ps = db.connection.prepareStatement("UPDATE operations_queue SET \"status\" = '" +
	    				                              status +"' WHERE id ="+ operationID);
	    		  
  		} catch (SQLException se) {
  			throw se;
  		}

   		try {
	    	  ps.executeUpdate();
   		} catch (SQLException se) {
   			throw se;
   		}

	}
}
