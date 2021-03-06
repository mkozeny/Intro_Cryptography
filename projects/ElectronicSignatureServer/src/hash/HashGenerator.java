package hash;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class HashGenerator {

	private static final int HASHLENGTH = 30;

	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "abcdefghijklmnopqrstuvwxyz" + "0123456789" + "+/";

	public static String computeHash(byte[] fileBytes, byte[] fileName) {

		byte[] hashInput = concatenateByteArrays(fileBytes, fileName);
		int remainder = hashInput.length % HASHLENGTH;
		if (remainder != 0)
			hashInput = appendBeginning(hashInput, remainder);

		int constant = hashInput.length / HASHLENGTH;
		System.out.println("Const: " + constant);
		System.out.println("Rem: " + remainder);

		StringBuffer sbForward = new StringBuffer(HASHLENGTH);
		int znak = 0;
		for (int i = 0; i < HASHLENGTH; i++) {
			znak += hashInput[i * constant];
			for (int j = 0; j < constant; j++) {
				znak = (znak + hashInput[i * constant + j]) % ALPHABET.length();
			}
			znak = znak % ALPHABET.length();
			sbForward.append(ALPHABET.charAt(znak));
		}

		//System.out.println("hash after 1st iteration is: " + sbForward);
		StringBuffer sbBackward = new StringBuffer(HASHLENGTH);
		znak = 0;
		for (int i = HASHLENGTH - 1; i >= 0; i--) {
			znak += hashInput[i * constant];
			for (int j = 0; j < constant; j++) {
				znak = (znak + hashInput[i * constant + j]) % ALPHABET.length();

			}
			znak = znak % ALPHABET.length();
			sbBackward.append(ALPHABET.charAt(znak));
		}
		//System.out.println("hash after 2nd iteration is: " + sbBackward);
		StringBuffer result = new StringBuffer(HASHLENGTH);
		for (int i = 0; i < HASHLENGTH; i++) {
			result.append(ALPHABET.charAt((sbForward.charAt(i) + sbBackward
					.charAt(i))
					% ALPHABET.length()));
		}

		return result.toString();

	}

	private static byte[] appendBeginning(byte[] hashInput, int remainder) {
		int difference = HASHLENGTH - remainder;
		byte[] result = null;
		if (hashInput.length >= difference)
			result = concatenate(hashInput, Arrays
					.copyOf(hashInput, difference));
		else {
			int hashInputLength = hashInput.length;
			result = hashInput;
			while (HASHLENGTH - result.length < hashInputLength) {
				result = concatenate(result, Arrays.copyOf(hashInput,
						hashInput.length));
			}
			return appendBeginning(result, (result.length % HASHLENGTH));
		}
		return result;
	}

	private static byte[] concatenateByteArrays(byte[] fileBytes,
			byte[] fileName) {
		int fileLengthLength = (int) Math
				.ceil(((Math.log(fileBytes.length) / Math.log(2.0)) / 8));
		System.out.println("filelength: " + fileBytes.length);

		byte[] fileLengthBytes = ByteBuffer.allocate(
				(fileLengthLength < 4) ? 4 : fileLengthLength).putInt(
				fileBytes.length).array();

		byte[] result = new byte[fileBytes.length + fileName.length
				+ fileLengthBytes.length];
		result = concatenate(fileBytes, fileName);
		result = concatenate(result, fileLengthBytes);
		return result;

	}

	private static final byte[] concatenate(byte[] s1, byte[] s2) {
		byte[] target = new byte[s1.length + s2.length];
		System.arraycopy(s1, 0, target, 0, s1.length);
		System.arraycopy(s2, 0, target, s1.length, s2.length);
		return target;
	}
}
