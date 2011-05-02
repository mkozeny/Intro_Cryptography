package main;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.StringTokenizer;

import rsa.RSAEncryptUtil;

public class Main {

	/**
	 * @param args
	 * @throws Exception
	 * 
	 */
	private static PublicKey publicKey;

	private static PrivateKey privateKey;

	private static BufferedReader inFromServer;
	
	private static DataOutputStream outToServer;
	
	private final static String publicKeyFileName = "/home/kozenym/Desktop/pubKey.txt";
	
	private final static String privateKeyFileName = "/home/kozenym/Desktop/privKey.txt";
	
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.doCommunication();
	}

	public void doCommunication() throws Exception {
		/*Socket socket = null;
		try
		{
			// communication
			socket = new Socket("localhost", 16789);
			outToServer = new DataOutputStream(socket
					.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			FileInputStream fis = null;
			boolean fileExists = true;
			try	{
				fis = new FileInputStream("/home/kozenym/Desktop/key.txt");			
			}
			catch (FileNotFoundException e) {
				fileExists = false;
			}
			//File sendingFile = new File("/home/kozenym/Desktop/key.txt");
			//byte[] fileContent = getBytesFromFile(sendingFile);
			String hash = "74832huhr32ry92";
			
			// sending filename
//			System.out.println("Sending file name: "+sendingFile.getName());
//			outToServer.writeBytes(sendingFile.getName()+"\r\n");
			System.out.println("Sending file name: ");
			outToServer.writeBytes("key.txt\r\n");
	
			// waiting for ack
			waitingForACK();
			System.out.println("Received ACK for sending file name");
	
			// sending file content
//			System.out.println("Sending content for file: "+sendingFile.getName());
			System.out.println("Sending content for file: ");
			if(fileExists){
				//method will go over the bytes of the file and write the into output buffer
				System.out.println("Sending...");
				sendBytes(fis, outToServer);
				System.out.println("File sent");
				fis.close();
			}
			else{
				
			}
			//outToServer.write(fileContent, 0, fileContent.length);
	
			// waiting for ack
			waitingForACK();
			System.out.println("Received ACK for sending file content");
	
			// sending hash
			System.out.println("Sending hash: "+hash);
			getKeys();
			String encryptedHash = RSAEncryptUtil.encrypt(hash, privateKey);
			System.out.println("Sending encrypted hash: "+encryptedHash);
			outToServer.writeBytes(encryptedHash+"\r\n");
	
			// waiting for ack
			waitingForACK();
			System.out.println("Received ACK for sending hash");
			
			inFromServer.close();
			outToServer.close();
			socket.close();
		}catch(Exception e)
		{
			System.err.println("Socket error");
			e.printStackTrace();
			inFromServer.close();
			outToServer.close();
			socket.close();
		}*/
		/*
		
		 * function version from Dave
		 
		Socket socket = new Socket("localhost", 16789);
		java.io.InputStream in = socket.getInputStream();
		BufferedReader inFromClient =new BufferedReader(new InputStreamReader(in));
		DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
		
		outToClient.writeBytes("ahoj servere\r\n");
		
		String reply = inFromClient.readLine();
		System.out.println("S: "+reply);
		FileOutputStream fos = new FileOutputStream("/home/kozenym/Desktop/key_1.txt");
		
		byte [] buffer = new byte[1024];		
		int read = 0;	
        while( (read = in.read(buffer)) != -1){
        	
        	fos.write(buffer, 0, read);
        }
        System.out.println("soubor zapsan");
        fos.close();
        inFromClient.close();
        outToClient.close();
		
		 * end of function version
		 
        */
		
		
/*
		
		
		 * function version from Dave
		 
		*/
		/*//reserving resources
		//allocating in and out buffers and mapping them tcp socket
		Socket socket = new Socket("localhost", 16789);
		InputStream in = socket.getInputStream();
		DataInputStream input = new DataInputStream(socket.getInputStream());

		inFromServer =new BufferedReader(new InputStreamReader(in));
		//outToServer = new DataOutputStream(socket.getOutputStream());
		BufferedOutputStream outToServer = new BufferedOutputStream(socket.getOutputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		File sendingFile = new File("/home/kozenym/Desktop/key.txt");
		System.out.println("File name: "+sendingFile.getName());
		DataInputStream fis = null;
		boolean fileExists = true;
		try	{
			fis = new DataInputStream(new FileInputStream(sendingFile));			
		}
		catch (FileNotFoundException e) {
			fileExists = false;
		}
		String hash = "74832huhr32ry92";
		
		System.out.println("Sending file name: "+sendingFile.getName());
		//outToServer.writeBytes(sendingFile.getName()+"\r\n");
		out.print(sendingFile.getName()+"\r\n");
        out.flush();
		
		
		// waiting for ack
		waitingForACK(input);
		System.out.println("Received ACK for sending file name");
		
		
		//creating file buffer, so that we can send the file into socket
		//but first we have to decide whether the file exists (we will send it)
		//or not (we will send file-not-found message)
		
		//sending into the socket is done simply with writing bytes into output buffer
		if(fileExists){
			//method will go over the bytes of the file and write the into output buffer
			
//			sendBytes(fis, outToServer);
			sendBytes(fis, outToServer);
			System.out.println("file sent");
			fis.close();
			
			//in.close();
			outToServer.flush();
		}
		else{
			
		}
		//Thread.sleep(1000);
		//in.reset();
//		/in.notify();
		// waiting for ack
		waitingForACK(input);
		System.out.println("Received ACK for sending file content");
		
		// sending hash
		System.out.println("Sending hash: "+hash);
		getKeys();
		String encryptedHash = RSAEncryptUtil.encrypt(hash, privateKey);
		System.out.println("Sending encrypted hash: "+encryptedHash);
		outToServer.writeBytes(encryptedHash+"\r\n");
		
		// waiting for ack
		waitingForACK();
		System.out.println("Received ACK for sending hash");
		
		
		
		//debugging output
		
		//System.out.println("closing connection");
		System.out.println("------------------");
		
		
		//before closing the socket, we have to close buffers
		//so that they are unalocated by GC
		inFromServer.close();
		//outToServer.close();
		input.close();
		out.close();*/
		
		 /* end of function version
		 
		*/
		Socket socket = null;
		DataInputStream input = null;
		DataOutputStream output = null;
		try {
			socket = new Socket("localhost", 16789);
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
		
		// get filename to send if this is a send

		sendFile("/home/kozenym/Desktop/key.txt", input, output);
		   
		

	}

	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

	public boolean parseReply(String reply, String comparedString) {
		StringTokenizer tokenizer = new StringTokenizer(reply, " ");
		if (tokenizer.nextToken().equals(comparedString))
			return true;
		return false;
	}

	public void waitingForACK(InputStream in) throws Exception {
//		String responseLine = inFromServer.readLine();
		String responseLine = getReceivedText(in);
		if (!parseReply(responseLine, "ACK"))
			throw new Exception("ACK awaited");
	}

	public void generateKey() throws NoSuchAlgorithmException,
			NoSuchProviderException, FileNotFoundException, IOException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		keyGen.initialize(1024, random);
		KeyPair pair = keyGen.generateKeyPair();
		PrivateKey priv = pair.getPrivate();
		PublicKey pub = pair.getPublic();

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				publicKeyFileName));
		oos.writeObject(pub);
		oos.close();

		oos = new ObjectOutputStream(new FileOutputStream(privateKeyFileName));
		oos.writeObject(priv);
		oos.close();
	}

	public void getKeys() throws FileNotFoundException, IOException,
			ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				publicKeyFileName));
		publicKey = (PublicKey) ois.readObject();
		ois.close();

		ois = new ObjectInputStream(new FileInputStream(privateKeyFileName));
		privateKey = (PrivateKey) ois.readObject();
		ois.close();
	}
	
	//private void sendBytes(FileInputStream fis, DataOutputStream outToClient) throws Exception {
	private void sendBytes(DataInputStream fis, BufferedOutputStream outToClient) throws Exception {
		byte[] buffer = new byte[1024];
		int bytes = 0;
		
		while((bytes = fis.read(buffer)) != -1)
		{
			System.out.println("Bytes: "+buffer[0]+" "+buffer[1]+" "+buffer[2]+" "+buffer[3]+" "+buffer[4]+" "+buffer[5]+" "+buffer[6]);
			outToClient.write(buffer, 0, bytes);
			outToClient.flush();
		}
		
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
	
	static private void sendFile(String send, DataInputStream input, DataOutputStream output) {

		String ack=null;              // response from server (ACK/NACK)
			
		System.out.println("Preparing to transfer file " + send + "...");

		try {
			output.writeUTF("username");   // SEND command,
			System.out.println("Username sent");
			
			ack = input.readUTF();     // get server response
		    if (! ack.equals("ACK")) {
		    	System.out.println("Server didn't acknowledge username.  Aborted.");
		    }
		    else
		    	System.out.println("ACK received");
			
		    File file = new File(send);
			DataInputStream filestream=new DataInputStream(new FileInputStream(file));
			int togo = getBytesFromFile(file).length;
			
		    
		    output.writeUTF(file.getName());   // SEND command,
		    System.out.println("Filename sent");
		    //output.writeUTF(send);     // ...filename to server
		    
		    ack = input.readUTF();     // get server response
		    if (! ack.equals("ACK"))System.out.println("Server didn't acknowledge filename.  Aborted.");
		    
		    else
		    	System.out.println("ACK received");
			
			
			
			
		    /*output.writeUTF("SEND");   // SEND command, 
		    //output.writeUTF(send);     // ...filename to server
		    ack = input.readUTF();     // get server response
		    
		    if (! ack.equals("RECEIVE")) {
			System.out.println("Server didn't acknowledge send.  Aborted.");
		    }
		    else {*/
			

			// push length of file to server
			output.writeInt(togo);
			System.out.println("Size of file: "+togo+" sent");

			ack = input.readUTF();     // get server response
		    if (! ack.equals("ACK"))System.out.println("Server didn't acknowledge file size.  Aborted.");
		    
		    else
		    	System.out.println("ACK received");
			// transfer file contents

		    
			sendInChunks(filestream, output, togo, 1024);
			filestream.close();
			System.out.println("File content sent");

			// is the server happy with the transfer?

			ack = input.readUTF();
			if (! ack.equals("ACK"))
				System.out.println("Server failed to store the file.");
			else
				System.out.println("ACK received");
			
		    //} 
		}
		catch (Exception e) {
		    System.out.println("Server failed to store the file.");
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
