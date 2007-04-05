package Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author Josh
 *
 */
public class ThreadedXmitter extends Thread {
	private BattleshipServer server = null;
	private BattleshipClient client = null;
	private boolean wait = false;
	
	public ThreadedXmitter (BattleshipServer server){
		this.server = server;
		
		this.start();
		System.out.println("ThreadedXmitter Thread Started...");
	}
	
	public ThreadedXmitter (BattleshipClient client){
		this.client = client;
		this.start();
		System.out.println("ThreadedXmitter Thread Started...");
	}
	
	public void Stall(){
		this.wait=true;
	}
	
	public void Continue(){
		synchronized (this) {
			this.notify();
		}		
	}
	
	// dedicated for chatting
	public void run(){
		BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
		String data=null;
		if(server != null){
	    	try {
	    		while(true){
	    			synchronized(this){
		    			if(wait){
		    				try {
								this.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
		    			}
	    			}
	    			
		    		// See if message is ready
					if (stdin.ready() ){
							// Get message
						data=stdin.readLine();
							// send it
						String temp = "M";
						temp = temp.concat(data);
						server.Send(temp);
						System.out.println(server.GetServerName() + ": " + data);
					}
	    		}
	        } catch (IOException e) {
	        	return;
	//			System.out.println( "IO: "+e.getMessage() );
	        }				
		}
		else if(client != null){
	    	try {
	    		while(true){
	    			synchronized(this){
		    			if(wait){
		    				try {
								this.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							} // end try-catch
		    			}// end if(wait)
	    			}// end synchronized
	    			
		    		// See if message is ready
					if (stdin.ready()){
							// Get message
						data=stdin.readLine();
						String temp = "M";	// affix the message type indicator
						temp = temp.concat(data);
						client.Send(temp);
						System.out.println(client.GetClientName() + ": " + data);
					}// end if
	    		}// end while
	    	} catch (IOException e) {
	        	return;
		//		System.out.println( "IO: "+e.getMessage() );
	    	}// end try-catch
	    }// end else-if
	}
}
