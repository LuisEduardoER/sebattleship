

import java.io.*;

import Networking.*;
import Gameplay.*;
import menuPack.*;



/**
 * @author Josh
 *
 */
public class BattleshipGame{

	private static BattleshipServer server;
	private static BattleshipClient client;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		StartMenu start_menu = new StartMenu();
		
		
		start_menu.PrintMenu();
		int choice = start_menu.Input();
		String name=null;
		String server_ip=null;	
		int server_port=7777;
		
		switch(choice){
		case 1:
			System.out.println("Enter your name: ");
			try {
				name = stdin.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Enter the server port: ");
			try {
				server_port = Integer.parseInt(stdin.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			server = new BattleshipServer(server_port, name);
			break;
		case 2:
			System.out.println("Enter your name: ");
			try {
				name = stdin.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Enter the server IP: ");
			try {
				server_ip = stdin.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			System.out.println("Enter the server port: ");
			try {
				server_port = Integer.parseInt(stdin.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			client = new BattleshipClient(server_ip, server_port, name);
			break;
		case 3:
			
			break;
		}
		
		
		
/* Temporary Driver for Josh's Stuff 
 * -- DELETE ME
 */

		
/*
		BattleshipServer server = null;
		BattleshipClient client = null;
		
		if(args[0].equalsIgnoreCase("s")){
			System.out.println("Starting Server");
			server= new BattleshipServer(7780, "Server");
		}
	//	Thread serverT = new Thread(server,"Server Thread");
		if(args[0].equalsIgnoreCase("c"))
		{
			System.out.println("Starting Client");
			client = new BattleshipClient("127.0.0.1", 7780, "Client");
		}
		System.out.println("Do I Make it here?");
	
		

		ThreadedReceiver listen = null;
		ThreadedXmitter send = null;
		
			if(args[0].equalsIgnoreCase("s")){

				// create a receiving thread
				listen = new ThreadedReceiver(server);
				// create a transmitting thread
				send = new ThreadedXmitter(server);
			}
			else if(args[0].equalsIgnoreCase("c")){
				
				listen = new ThreadedReceiver(client);
				send = new ThreadedXmitter(client);
			}

		while (true);
		
    //	System.out.println("Quitting...");
		// NEED:
		//	- Top Level Module in a top-level class to be threaded and handle listening
		//    - ability to send messages at same time
		
		
	//	server.Host();
	//	client.Join();
	
		
*/
		
/*
 * End Temporary Driver
 */
		

	}

}
