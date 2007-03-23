/**
 * 
 */

import java.io.*;

/**
 * @author Josh
 *
 */
public class BattleshipGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		
/* Temporary Driver for Josh's Stuff 
 * -- DELETE ME
 */
			// BattleshipServer is threaded so it won't stall
		if(args[0].equalsIgnoreCase("s")){
			System.out.println("Starting Server");
			BattleshipServer server= new BattleshipServer(7780, "Server");
		}
	//	Thread serverT = new Thread(server,"Server Thread");
		if(args[0].equalsIgnoreCase("c"))
		{
			System.out.println("Starting Client");
		BattleshipClient client = new BattleshipClient("127.0.0.1", 7780, "Client");
		}
		System.out.println("Do I Make it here?");

	//	server.Host();
	//	client.Join();
		
		
/*
 * End Temporary Driver
 */
		

	}

}
