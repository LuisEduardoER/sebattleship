package Networking;
/**
 * 
 * @author Josh
 *
 */
public class BattleshipClient extends Client {

	private String serverName = null;
	private String clientName = null;
	
	public BattleshipClient(String serverIP, int serverPort, String clientName){
		super(serverIP, serverPort);
		this.clientName=clientName;
	}
	
	
	public String GetClientName(){
		return clientName;
	}
	
	
	public String GetServerName(){
		return serverName;
	}
	
	
}