package analysis;

import java.util.HashMap;

public class PositionCounterLetter {

	/* map every letter of English alphabet to number -> used for encryption */
	public static HashMap<String, Integer> countPosition()
	{
		HashMap<String, Integer> charPositions = new HashMap<String, Integer>();
		
		for ( char ch = 'A';  ch <= 'Z';  ch++ )
        	charPositions.put(String.valueOf(ch), ch-65);
		//charPositions.put(" ", 26);
		return charPositions;

	}
	/* map number to every letter of English alphabet -> used for decryption */
	public static HashMap<Integer, String> countLetter()
	{
		HashMap<Integer, String> letterByPosition = new HashMap<Integer, String>();
		HashMap<String, Integer> charPositions =countPosition();
		for (String s:charPositions.keySet())
        	letterByPosition.put(charPositions.get(s), s);
		
		return letterByPosition;

	}
}
