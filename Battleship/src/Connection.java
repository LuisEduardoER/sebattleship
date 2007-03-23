import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


/**
 * @class Connection
 * 
 * Establishes a threaded I/O connection to the client.
 * This connection continually listens for text, receives it, and relays it back
 * Useful for chatting.
 */
class Connection{
	
	private DataInputStream in = null;
	private DataOutputStream out = null;
	private Socket socket = null;
	private String msg;

	public Connection( Socket socket) {

		try {
			this.socket = socket;
			in = new DataInputStream( this.socket.getInputStream() );
			out = new DataOutputStream( this.socket.getOutputStream() );

			// Starts the thread which calls the run function
//			this.start();

		} catch( IOException e ) {
			e.printStackTrace();
		} // try_catch
//	} // Connection

//	public void run() {

		try {
			String data;
			while( (data=in.readUTF()) != null );
			msg=data;
        } catch (IOException e) {
			System.out.println( "IO: "+e.getMessage() );
        } // try_catch
	} // run
	
	
	public String GetMsg(){
		return msg;
	}
}
