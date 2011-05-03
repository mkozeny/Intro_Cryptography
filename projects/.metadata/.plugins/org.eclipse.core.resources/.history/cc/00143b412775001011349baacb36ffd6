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
		// TODO Auto-generated method stub
		/*String text = "Hi Nino";
		
		
		KeyPairGenerator keyGen =
		    KeyPairGenerator.getInstance("RSA");
		SecureRandom random =
		    SecureRandom.getInstance("SHA1PRNG", "SUN");
		keyGen.initialize(1024, random);
		KeyPair pair = keyGen.generateKeyPair();
		PrivateKey priv = pair.getPrivate();
		PublicKey pub = pair.getPublic();

		ObjectOutputStream oos =
			new ObjectOutputStream(
			new FileOutputStream( "pub.txt" ) );
			oos.writeObject( pub );
			oos.close();

		
			oos =
				new ObjectOutputStream(
				new FileOutputStream( "priv.txt" ) );
				oos.writeObject( priv );
				oos.close();	
			
			ObjectInputStream ois =
			new ObjectInputStream(
			new FileInputStream( "pub.txt" ) );
			PublicKey desPub = (PublicKey) ois.readObject();
			ois.close();

				ois =
					new ObjectInputStream(
					new FileInputStream( "priv.txt" ) );
					PrivateKey desPriv = (PrivateKey) ois.readObject();
					ois.close();
		
		
		String encryptedText = RSAEncryptUtil.encrypt(text, desPub);
		System.out.println(encryptedText);
		String decryptedText = RSAEncryptUtil.decrypt(encryptedText, desPriv);
		System.out.println(decryptedText);*/
		
		
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
			
			
			
			
			
//			System.out.println("server started listening on: "+port);
//			while(true)
//			{
//				//waiting on this line for client to connect
//				Socket connectionSocket = listenSocket.accept();
//				
//				//connection is established
//				//we pass the socket into the thread as parameter of the constructor,
//				//so that this main thread can continue in waiting 
//				//for another connection and handle it
//				MessageRequest request = new MessageRequest(connectionSocket);
//				Thread thread = new Thread(request);
//				
//				//starting new thread - app is now really multithreading
//				thread.start();
//			}
			
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}
	}
	/*public static void getKey() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				publicKeyFileName));
		publicKey = (PublicKey) ois.readObject();
		ois.close();
	}*/
}
