package main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiThread extends Thread{
	
	private Socket socket;
	
	public void communication(Socket socket){
		
		this.socket= socket;
		this.start();
	}
	
	
	 @Override
     public void run()
	{
		
		/*java.io.InputStream in;
		try {
			in = socket.getInputStream();
			DataInputStream input = new DataInputStream(socket.getInputStream());
	
		BufferedReader inFromClient =new BufferedReader(new InputStreamReader(in));
		//DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

		
		//get filename
		//String fileName = inFromClient.readLine();
		String fileName = getReceivedText(input);
		System.out.println("Filename: "+fileName);
		
		
		//sending ACK
		//outToClient.writeBytes("ACK\r\n");
		out.print("ACK\r\n");
        out.flush();
		
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("/home/kozenym/Desktop/key_1.txt"));
        byte [] receivedData = new byte[1024];		
		int num = 0;
        while ( (num = bis.read(receivedData)) > 0){
            bos.write(receivedData,0,num);
          }
          //System.out.println(counter);
          bos.close();
          bis.close();

          //File receivedFile = new File(filename);


		FileOutputStream fos = new FileOutputStream(new File("/home/kozenym/Desktop/key_1.txt"));
		
		byte [] buffer = new byte[1024];		
		int read = 0;	
        while( (read = in.read(buffer)) != -1){
        	
        	fos.write(buffer, 0, read);
        }
        System.out.println("File received");
        
        
        //sending ACK
		//outToClient.writeBytes("ACK\r\n");
        out.print("ACK\r\n");
        out.flush();
		
		//get encryptedHash
		String encryptedHash = inFromClient.readLine();
		//getKey();
		//String hash = RSAEncryptUtil.decrypt(encryptedHash, publicKey);
		System.out.println("Received hash: "+encryptedHash);
		
		//sending ACK
		outToClient.writeBytes("ACK\r\n");
		
        //fos.close();
        input.close();
        inFromClient.close();
        out.close();
        //outToClient.close();
        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 DataInputStream input = null;
		DataOutputStream output = null;
		
		 try {
			    input = new DataInputStream(socket.getInputStream());
			    output = new DataOutputStream(socket.getOutputStream());
			}
			catch(UnknownHostException e) {
			    System.out.println("No server found on the specified host.");
			    System.exit(1);
			}
			catch(IOException e) {
			    System.err.println("No server is listening on the specified port.");
			    System.exit(1);
			}
			
			receiveFile("/home/kozenym/Desktop/output", input, output);
			

		
	}
	 
	 private String getReceivedText(InputStream in) throws IOException
	 {
		 char znak;
		 StringBuffer fromServer = new StringBuffer();
         while ((znak = (char)in.read()) != -1)
             {
                 fromServer.append(znak);
                 if (fromServer.toString().endsWith("\r\n"))
                	 break;
                 
             }
         return fromServer.substring(0, fromServer.length()-2).toString();
	 }
	 static private void receiveFile(String receive, DataInputStream input, DataOutputStream output) {

			String ack=null;              // response from server (ACK/NACK)
			String username=null;
			String filename=null;
			System.out.println("Preparing to receive file...");

			try {
				username = input.readUTF();  
				System.out.println("Username is: "+username);
				
				output.writeUTF("ACK");
				System.out.println("ACK sent");
				
				filename = input.readUTF();  
				System.out.println("Filename is: "+filename);
				
				output.writeUTF("ACK");
				System.out.println("ACK sent");
				
			    /*output.writeUTF("RECEIVE");   // RECEIVE command,
			    //output.writeUTF(receive);     // ...filename to server
			    ack = input.readUTF();        // get server response
			    if (! ack.equals("SEND")) {
				System.out.println("Server didn't acknowledge receive attempt.  Aborted.");
			    }
			    else {*/
				// get size of file from server
				int togo = input.readInt();
				System.out.println("Filesize sent: "+togo);
				
				output.writeUTF("ACK");
				System.out.println("ACK sent");

				// receive file contents
				File file = new File(receive+"/"+username+"/"+filename);
				DataOutputStream filestream = new DataOutputStream(new FileOutputStream(file));

				sendInChunks(input, filestream, togo, 1024);
				filestream.close();
				System.out.println("File received.");
				
				output.writeUTF("ACK");   // SEND command,
				
			    //}
			}
			catch (Exception e) {
			    System.err.println("File receive failed.");
			}
		    }

	 
	 static public void sendInChunks(DataInputStream input, DataOutputStream output, int togo, int chunksize) throws IOException {
			
			// copy bytes from input to output in chunksize-sized chunks

			byte bytes[] = new byte[chunksize];
			int writecount;
			while (togo > 0) {
			    writecount = (togo >= chunksize ? chunksize : togo);
			    input.read(bytes, 0, writecount);
			    output.write(bytes, 0,  writecount);
			    togo = togo - writecount;
			}
			
			output.flush();
		    }
}
