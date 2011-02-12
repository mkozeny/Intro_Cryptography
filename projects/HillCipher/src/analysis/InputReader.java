package analysis;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class InputReader {
	
	private HashMap<String, Integer> positions;
	
	

	public InputReader(HashMap<String, Integer> positions) {
		super();
		this.positions = positions;
	}

	private String readFile(String fileName)
	{
		String result="";
		try
		{
			 FileInputStream fstream = new FileInputStream(fileName);
			 DataInputStream in = new DataInputStream(fstream);
			 BufferedReader br = new BufferedReader(new InputStreamReader(in));
			 String strLine;
			 while ((strLine = br.readLine()) != null)   {
			    result+=strLine;
			 }
			 in.close();
		 }catch (Exception e){
		   System.err.println("Error: " + e.getMessage());
		 }
		 return result;
	}
	/* read input file a then convert it to blocks of 2*/
	public List<int []> convertInputToPositions(String fileName, boolean convert)
	{
		String result=readFile(fileName);
		int [] convertedText;
		if(convert)
		{
			convertedText = new int[result.length()];
			for(int i=0;i<convertedText.length;i++)
				try
				{
					convertedText[i]=positions.get(String.valueOf(result.charAt(i)));
				}catch(Exception e)
				{
					return null;
				}
		}
		else
		{
			StringTokenizer tokenizer=new StringTokenizer(result," ");
			List<Integer> row = new ArrayList<Integer>();
			while(tokenizer.hasMoreElements())
				row.add(Integer.parseInt(tokenizer.nextToken()));
			convertedText = new int[row.size()];
			for(int i=0;i<row.size();i++)
				convertedText[i]=row.get(i);
		}
		return create2Blocks(convertedText);
	}
	
	private List<int []> create2Blocks(int [] convertedText)
	{
		int [] block;
		List<int []> blocks = new ArrayList<int[]>();
		for(int i =0; i< convertedText.length;i+=2)
		{
			block = new int[2];
			block[0]=convertedText[i];
			block[1]=convertedText.length>i+1?convertedText[i+1]:(convertedText[i]+1 % 27);
			blocks.add(block);
		}
		return blocks;
	}
	
	
	/* read input matrix*/
	public int[][] readMatrix(String fileName)
	{
		int [][] matrix = null;
		try
		{
			 FileInputStream fstream = new FileInputStream(fileName);
			 DataInputStream in = new DataInputStream(fstream);
			 BufferedReader br = new BufferedReader(new InputStreamReader(in));
			 String strLine;
			 StringTokenizer tokenizer;
			 List<List<Integer>> mat = new ArrayList<List<Integer>>();
			 while ((strLine = br.readLine()) != null)   {
				tokenizer=new StringTokenizer(strLine," ");
				List<Integer> row = new ArrayList<Integer>();
				while(tokenizer.hasMoreElements())
					row.add(Integer.parseInt(tokenizer.nextToken()));
			   mat.add(row); 
			 }
			 
			 in.close();
			 matrix = new int[mat.size()][mat.get(0).size()];
			 for(int i=0; i < mat.size(); i++)
			 {
				 for(int j=0; j< mat.get(0).size(); j++)
					 matrix[i][j]=mat.get(i).get(j);
			 }
		 }catch (Exception e){
		   System.err.println("Error: " + e.getMessage());
		 }
		 return matrix;
	}

	public HashMap<String, Integer> getPositions() {
		return positions;
	}

	public void setPositions(HashMap<String, Integer> positions) {
		this.positions = positions;
	}
	
	
}
