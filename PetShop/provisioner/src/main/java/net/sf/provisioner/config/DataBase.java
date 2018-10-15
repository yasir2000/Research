package net.sf.provisioner.config;

public class DataBase {
	
	public String name;
	
	public String user;
	
	public String password;
	
	public class Server{
		
		public String type;
		
		public String name;
		
		Server() {};
	}
	
	public class Driver{
		
		public String className;
		
		public String type;
		
		Driver() {};
	}

	public Server server = new Server();
	
	public Driver driver = new Driver();
	
	public void Database() {};
}
