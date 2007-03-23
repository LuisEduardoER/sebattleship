/**
 * 
 * @author Josh
 *
 */
public class BattleshipClient extends Client {

	private String serverName;
	private String clientName;
	private int serverPort;
	private String serverIP;
	
	public BattleshipClient(String serverIP, int serverPort, String clientName){
		super(serverIP, serverPort);
		this.clientName=clientName;
		this.serverPort=serverPort;
		this.serverIP=serverIP;
	}
	
	
	public String GetClientName(){
		return clientName;
	}
	
	
	public String GetServerName(){
		return serverName;
	}
	
	
}