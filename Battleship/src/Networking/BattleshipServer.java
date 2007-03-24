package Networking;



public class BattleshipServer extends Server{

	private String serverName = null;
	private String clientName = null;
	
	public BattleshipServer(int port, String name) {
		super(port);
		serverName=name;
			// Wait for a connection
		super.Connect();
		System.out.println("We have Connection!");
	}
	
	
	public String GetClientName(){
		return clientName;
	}
	
	
	public String GetServerName(){
		return serverName;
	}
}
