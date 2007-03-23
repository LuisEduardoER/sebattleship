
public class BattleshipServer extends Server{

	private String serverName;
	private String clientName;
	private int port;
	
	public BattleshipServer(int port, String name) {
		super(port);
		serverName=name;
		this.port=port;
			// Wait for a connection
		this.run();
	}
	
	
	public String GetClientName(){
		return clientName;
	}
	
	
	public String GetServerName(){
		return serverName;
	}
	
	public void run(){
		super.Connect();
		System.out.println("We have Connection!");
	}
	
}
