package hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Arrays;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	private static final int HASHLENGTH = 30;
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "abcdefghijklmnopqrstuvwxyz" + "0123456789" + "+/";
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File sendingFile = new File("/home/kozenym/Desktop/hash-files/Main.java");
		byte[] byteArray = getBytesFromFile(sendingFile);
		//String test = "AhojiAhojiAhojiAhojiAhojiAhoji";
		String hash = computeHash(byteArray, sendingFile.getName().getBytes("UTF-8"));
		
		System.out.println("hash is: "+hash);
		
	}
	
	private static String computeHash(byte[] fileBytes, byte[] fileName){
		
		byte [] hashInput = concatenateByteArrays(fileBytes, fileName);
		int remainder = hashInput.length % HASHLENGTH;
		if(remainder != 0)
			hashInput = appendBeginning(hashInput, remainder);
		

		int constant = hashInput.length / HASHLENGTH;
		System.out.println("Const: "+constant);
		System.out.println("Rem: "+remainder);
		
		
		StringBuffer sbForward = new StringBuffer(HASHLENGTH);
		int znak = 0;
		for (int i=0; i<HASHLENGTH; i++){
			znak += hashInput[i*constant];
			for (int j=0; j<constant; j++){
				znak = ( znak + hashInput[i*constant + j] ) % ALPHABET.length();
			}	
			znak = znak  % ALPHABET.length();
			sbForward.append(ALPHABET.charAt(znak));
		}
		
		System.out.println("hash after 1st iteration is: "+sbForward);
		StringBuffer sbBackward = new StringBuffer(HASHLENGTH);
		znak = 0;
		for (int i=HASHLENGTH -1; i>=0; i--){
			znak += hashInput[i*constant];
			for (int j=0; j<constant; j++){
				znak = ( znak + hashInput[i*constant + j] ) % ALPHABET.length();
				
			}	
			znak = znak  % ALPHABET.length();
			sbBackward.append(ALPHABET.charAt(znak));
		}
		System.out.println("hash after 2nd iteration is: "+sbBackward);
		StringBuffer result = new StringBuffer(HASHLENGTH);
		for (int i=0; i<HASHLENGTH; i++){
			result.append(ALPHABET.charAt((sbForward.charAt(i)+sbBackward.charAt(i)) % ALPHABET.length()));
		}

		return result.toString();
		
	}
	
	private static byte [] appendBeginning(byte [] hashInput, int remainder)
	{
		int difference = HASHLENGTH - remainder;
		byte [] result = null;
		if(hashInput.length>=difference)
			result = concatenate(hashInput,Arrays.copyOf(hashInput, difference));
		else
		{
			int hashInputLength = hashInput.length;
			result = hashInput;
			while(HASHLENGTH - result.length < hashInputLength)
			{
				result = concatenate(result,Arrays.copyOf(hashInput, hashInput.length));
			}
			return appendBeginning(result, (result.length % HASHLENGTH));
		}
		return result;	
	}
	private static byte [] concatenateByteArrays(byte [] fileBytes,byte [] fileName)
	{
		int fileLengthLength = (int) Math.ceil(((Math.log(fileBytes.length)/Math.log(2.0))/8));
		System.out.println("filelength: "+fileBytes.length);
		
		byte[] fileLengthBytes = ByteBuffer.allocate((fileLengthLength<4)?4:fileLengthLength).putInt(fileBytes.length).array();

		byte [] result = new byte [fileBytes.length+fileName.length+fileLengthBytes.length];
		result =  concatenate(fileBytes,fileName);
		result =  concatenate(result,fileLengthBytes);
		return result;

	}

	
	private static final byte [] concatenate(byte [] s1,  byte[] s2) {
		byte [] target= new byte [s1.length+s2.length];   
	   System.arraycopy(s1, 0, target, 0, s1.length);
	   System.arraycopy(s2, 0, target, s1.length, s2.length);
	   return target;
}

	
	private static byte[] getBytesFromFile(File file) throws IOException {
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
	    byte[] bytes = new byte[(int)length];

	    // Read in the bytes
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    // Ensure all the bytes have been read in
	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    // Close the input stream and return bytes
	    is.close();
	    return bytes;
	}
	
	private static boolean [] getBits(byte b)
	//private static String getBits(byte b)
	{
		//int i = Integer.parseInt(hex,16);
	    String binary = Integer.toBinaryString((int)b);
	    int binaryLength = binary.length();
	    boolean [] result = new boolean [8];
		for(int i = 0; i < 8; i++)
		{
			if(i<binaryLength)
				result[(8-binaryLength)+i] = (binary.charAt(i)=='1');
		
		}
	    return result;
	}
	
	private static byte getByte(boolean [] array)
	//private static String getBits(byte b)
	{
		//int i = Integer.parseInt(hex,16);
		reverse(array);
		int sum = 0;
		for(int i = 0; i < 8; i++)
		{
			if(array[i])
				sum += Math.pow(2.0, i);
		
		}
	    return (byte)sum;
	}
	
	private static void reverse(boolean[] p) {
		  int left  = 0;          
		  int right = p.length-1; 
		  
		  while (left < right) {
		     boolean temp = p[left]; 
		     p[left]  = p[right]; 
		     p[right] = temp;
		     left++;
		     right--;
		  }
	}
	
	private static boolean [] [] parseString(byte [] input)
	{
		boolean [][] binaryString = new boolean [input.length][8];
		for(int i = 0; i < input.length; i++)
		{
			copyArray(binaryString, getBits(input[i]), i);
		}
		return binaryString;
	}
	
	private static void copyArray(boolean [][] binaryString, boolean [] input, int row)
	{
		for(int i = 0; i < input.length; i++)
		{
			binaryString[row][i] = input[i];
		}
	}
	
	private static void copyTwoDimArrayToOneDimArray(boolean [][] binaryString, boolean [] input, int row)
	{
		for(int i = 0; i < input.length; i++)
		{
			 input[i] = binaryString[row][i];
		}
	}
	
	private static boolean [][] reduceBytes(boolean [] [] binaryString)
	{
		int length = binaryString.length/2;
		boolean [][] result = new boolean [length] [8]; 
		for(int i = 0; i < length; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				result [i][j] = xorBits(binaryString [2*i][j],binaryString [2*i+1][j]);
			}
		}
		if(result.length > 16)
			return reduceBytes(result);
		return result;
	}
	
	private static boolean xorBits(boolean a, boolean b)
	{
		return a!=b;
	}
	
	private static String calculateHash(byte [] input)
	{
		boolean [] [] matrix = parseString(input);
		matrix = reduceBytes(matrix);
		//byte [] result = new byte [matrix.length];
		StringBuffer s = new StringBuffer();
		for(int i = 0 ; i < matrix.length; i++)
		{
			boolean [] row = new boolean[8];
			copyTwoDimArrayToOneDimArray(matrix, row, i);
			s.append(ALPHABET.charAt(((int)getByte(row))% ALPHABET.length()));
		}
		return s.toString();
	}
}
