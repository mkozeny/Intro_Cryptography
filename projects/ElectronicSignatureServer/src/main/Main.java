package main;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	final static int port = 16789;
	
	public static void main(String[] args) throws Exception {
		
		try {
			//TCP to state LISTENING
			ServerSocket listenSocket = new ServerSocket(port);
			
			System.out.println("server started listening on: "+port);
            while(true)
            {
            	Socket connectionSocket = listenSocket.accept();
    			MultiThread mult = new MultiThread();
    			mult.communication(connectionSocket);
                
            }
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}
	}
}
