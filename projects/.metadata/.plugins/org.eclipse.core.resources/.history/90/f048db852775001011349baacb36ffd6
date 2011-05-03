package main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NotMultithread {
	public static void communication(Socket socket) throws IOException{
		
		
		java.io.InputStream in = socket.getInputStream();
		BufferedReader inFromClient =new BufferedReader(new InputStreamReader(in));
		DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());

		
		String requestLine = inFromClient.readLine();
		
		System.out.println(requestLine);
		
		outToClient.writeBytes("ahoj kliente\r\n");
		
		FileOutputStream fos = new FileOutputStream(new File("/home/kozenym/Desktop/key_1.txt"));
		
		byte [] buffer = new byte[1024];		
		int read = 0;	
        while( (read = in.read(buffer)) != -1){
        	
        	fos.write(buffer, 0, read);
        }
        System.out.println("soubor zapsan");
        fos.close();
        inFromClient.close();
        outToClient.close();
		
		
	}

}
