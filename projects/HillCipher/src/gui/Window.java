package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;


import eukleidian.MatrixOperator;

import analysis.InputReader;
import analysis.PositionCounterLetter;

import static javax.swing.GroupLayout.Alignment.*;

public class Window extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -503408349638454675L;
	JFileChooser fc;
	JTextField matrixTextField;
	JTextField nField;
	JTextField inputField;
	JTextField outputField;
	JTextField inverseMatrixField;
	JButton matrixFindButton;
	JButton inputFindButton;
	JButton outputFindButton;
	JButton inverseMatrixButton;
	JButton inverseMatrixFindButton;
	JButton encryptButton;
	JButton JTextFieldButton;
	JCheckBox decodeCheckBox;
	JCheckBox encodeCheckBox;
	String filename;
	String inputFilename;
	String outputFilename;
	String inverseMatrixFilename;
	int[][] matrix;
	List<int[]> blocks;
	FileWriter fw;
	InputReader reader;
	MatrixOperator operator;
	List<int[]> encryptedBlocks;
	int[][] inverseMatrix;
	JMenuItem item1;
	JMenuItem item2;
	
    public Window() {
    	fc = new JFileChooser();
    	reader = new InputReader(PositionCounterLetter.countPosition());
    	operator = new MatrixOperator(reader.getPositions().size());
    	encryptedBlocks = new ArrayList<int[]>();
    	
        JLabel matrixLabel = new JLabel("1. Select matrix file");
        matrixTextField = new JTextField(15);
        matrixFindButton = new JButton("Browse");
        matrixFindButton.addActionListener(this);
        
        JLabel nLabel = new JLabel("n =");
        nField = new JTextField(4);
        
        JLabel inputLabel = new JLabel("2. Select input file");
        inputField = new JTextField(15);
        inputFindButton = new JButton("Browse");
        inputFindButton.addActionListener(this);
        decodeCheckBox = new JCheckBox("Decode output string");
        decodeCheckBox.addActionListener(this);
        
        JLabel outputLabel = new JLabel("3. Select output file");
        outputField = new JTextField(15);
        outputFindButton = new JButton("Browse");
        outputFindButton.addActionListener(this);
        encodeCheckBox = new JCheckBox("Encode input string");
        encodeCheckBox.addActionListener(this);
        
        JLabel inverseMatrixLabel = new JLabel("4. Select IM output file");
        inverseMatrixField = new JTextField(15);
        inverseMatrixFindButton = new JButton("Browse");
        inverseMatrixFindButton.addActionListener(this);

        
        inverseMatrixButton = new JButton("Inv. Matrix");
        inverseMatrixButton.setAlignmentX(RIGHT_ALIGNMENT);
        encryptButton = new JButton("Encrypt");
        encryptButton.setAlignmentX(RIGHT_ALIGNMENT);
        inverseMatrixButton.addActionListener(this);
        encryptButton.addActionListener(this);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Information");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        item1 = new JMenuItem("Help");
        item1.addActionListener(this);
        item2 = new JMenuItem("Credits");
        item2.addActionListener(this);
        menu.add(item1);
        menu.add(item2);
        setJMenuBar(menuBar);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
        		.addGroup(layout.createParallelGroup(Alignment.LEADING)
            .addComponent(matrixLabel).addComponent(inputLabel).addComponent(outputLabel).addComponent(inverseMatrixLabel).addGap(30))
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(matrixTextField).addGroup(layout.createParallelGroup(LEADING).addComponent(inputField).addComponent(encodeCheckBox)).addGroup(layout.createParallelGroup(LEADING).addComponent(outputField).addComponent(decodeCheckBox)).addComponent(inverseMatrixField).addGap(30))
                
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addComponent(matrixFindButton).addComponent(nLabel).addComponent(nField)).addComponent(inputFindButton).addComponent(outputFindButton).addComponent(inverseMatrixFindButton).
                addGroup(layout.createSequentialGroup().addComponent(inverseMatrixButton).addComponent(encryptButton))
                )
        );
       
        layout.linkSize(SwingConstants.HORIZONTAL, matrixFindButton, inputFindButton, outputFindButton, inverseMatrixFindButton);

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(matrixLabel)
                .addComponent(matrixTextField)
                .addGroup(layout.createParallelGroup(BASELINE).addComponent(matrixFindButton).addComponent(nLabel).addComponent(nField)))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(inputLabel)
                        .addGroup(layout.createSequentialGroup().addComponent(inputField).addComponent(encodeCheckBox))
                        .addComponent(inputFindButton))
                    
                        .addGroup(layout.createParallelGroup(BASELINE)
                                .addComponent(outputLabel)
                                .addGroup(layout.createSequentialGroup().addComponent(outputField).addComponent(decodeCheckBox))
                                .addComponent(outputFindButton))
                        .addGroup(layout.createParallelGroup(BASELINE)
                                .addComponent(inverseMatrixLabel)
                                .addComponent(inverseMatrixField)
                                .addComponent(inverseMatrixFindButton))
                        .addGap(40)
                        .addGroup(layout.createParallelGroup(LEADING)
                        		.addGap(30)
                        		.addGap(30)
                                .addGroup(layout.createParallelGroup(BASELINE)
                                .addComponent(inverseMatrixButton)
                                .addComponent(encryptButton)))        
                
        );
        
        

        setTitle("Hill Cipher");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(nField.getText()!=null && nField.getText().length()>0)
		{
			int n=26;
			try
			{
				n=Integer.parseInt(nField.getText());
			}catch(Exception e1)
			{
				JOptionPane.showMessageDialog(this, "Please fill in field 'n' a number", "Modulo n number", JOptionPane.ERROR_MESSAGE);
			}
			operator.setN(n);
		}
		else
			operator.setN(26);
		
		if((encodeCheckBox.isSelected() || decodeCheckBox.isSelected()) && operator.getN()!=26)
		{
			JOptionPane.showMessageDialog(this, "Please leave the n blank (default n=26) while you want to either encode or decode text", "Modulo n number", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (e.getSource() == matrixFindButton) {
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                filename=file.getPath();
                matrix = reader.readMatrix(filename);
                matrixTextField.setText(optimalizer(filename));
            } 
        }
            
		else if (e.getSource() == inputFindButton) {
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                inputFilename=file.getPath();
                
                inputField.setText(optimalizer(inputFilename));
            } 
        }
		else if (e.getSource() == outputFindButton) {
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                outputFilename=file.getPath();
                outputField.setText(optimalizer(outputFilename));
            } 
        }
		else if (e.getSource() == inverseMatrixFindButton) {
            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                inverseMatrixFilename=file.getPath();
                inverseMatrixField.setText(optimalizer(inverseMatrixFilename));
            } 
        }
		else if (e.getSource() == encryptButton) {
			if(matrix == null ||
					inputFilename == null ||
					outputFilename == null)
			{
				String message="";
				if(matrix == null)
				{
					message+="Please choose file with matrix (field 1.)\n";
					
				}
				if(inputFilename == null)
				{
					message+="Please choose file with input to be encrypted (field 2.)\n";
					
				}
				if(outputFilename == null)
				{
					message+="Please choose file for result of encryption (field 3.)\n";
					
				}
				
				    JOptionPane.showMessageDialog(this, message, "Encrypting", JOptionPane.WARNING_MESSAGE);
				    return;
			}
			OutputStream osw =null;
			blocks = reader.convertInputToPositions(inputFilename,encodeCheckBox.isSelected());
			if(blocks==null)
			{
				JOptionPane.showMessageDialog(this, "Encoding failed, input file probably not contains English word(s)", "Encoding failed", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				osw = new FileOutputStream(outputFilename);
			} catch (FileNotFoundException e3) {
				e3.printStackTrace();
			}
			HashMap<Integer, String> letterByPosition = PositionCounterLetter.countLetter();
			encryptedBlocks = new ArrayList<int[]>();
			for(int i=0; i<blocks.size(); i++)
			{
				encryptedBlocks.add(operator.multiplyMatrixes(matrix, blocks.get(i)));
				int [] m =encryptedBlocks.get(i);
				for(int j=0; j<m.length; j++)
				{
					try {
						String s="";
						if(decodeCheckBox.isSelected())
							s=letterByPosition.get(m[j]);
						else
							s=String.valueOf(m[j])+" ";
						if(s==null)
						{
							JOptionPane.showMessageDialog(this, "Decoding failed, input file probably not contains English word(s)", "Dencoding failed", JOptionPane.ERROR_MESSAGE);
							return;
						}
						osw.write(s.getBytes());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				
			} 
			JOptionPane.showMessageDialog(this, "Encryption of input matrix was successful", "Help", JOptionPane.INFORMATION_MESSAGE);
        }
		else if (e.getSource() == inverseMatrixButton) {
			if(matrix == null ||
					inverseMatrixFilename == null)
			{
				String message="";
				if(matrix == null)
				{
					message+="Please choose file with matrix (field 1.)\n";
					
				}
				if(inverseMatrixFilename == null)
				{
					message+="Please choose file for result of inversion (field 4.)\n";
					
				}
				
				    JOptionPane.showMessageDialog(this, message, "Inverse matrix", JOptionPane.WARNING_MESSAGE);
				    return;
			}
			OutputStream osw =null;
			try {
				osw = new FileOutputStream(inverseMatrixFilename);
			} catch (FileNotFoundException e3) {
				e3.printStackTrace();
			}
			/*You can choose two options for counting inverse matrix
			 * first one uses extended euclidean algorithm shown on http://introcs.cs.princeton.edu/78crypto/ExtendedEuclid.java.html
			 * the second one uses extended euclidean algorithm written by me
			 * both algorithms are described in classes InverseFinder.java and AnotherInverseFinder.java */
			//inverseMatrix = operator.inverseMatrix(matrix);
			inverseMatrix = operator.concurentInverseMatrix(matrix);
			if(inverseMatrix==null)
			{
				String message="Matrix is not squared or has less than 2 rows or does not exists inverse for one or more numbers on the diagonal";
				JOptionPane.showMessageDialog(this, message, "Inverse matrix", JOptionPane.ERROR_MESSAGE);
			    return;
			}
			for(int i=0; i<inverseMatrix.length; i++)
			{
				for(int j=0; j<inverseMatrix[0].length; j++)
				{
					try {
						osw.write((String.valueOf(inverseMatrix[i][j])).getBytes());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if(j<inverseMatrix[0].length-1)
					{
						try {
							osw.write(" ".getBytes());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				if(i<inverseMatrix.length-1)
				{
					try {
						osw.write("\r\n".getBytes());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} 
			JOptionPane.showMessageDialog(this, "Creating inverse matrix was successful", "Help", JOptionPane.INFORMATION_MESSAGE);
        }
		else if (e.getSource() == item1) {
			String message="For using encryption please:\n"+
			"1. choose file with matrix (field 1.)\n"+
			"-> n is kongruent modulo number (by default n=26 -> English alphabet)\n"+
			"2. choose file with input to be encrypted (field 2.)\n"+
			"-> Encode input string - if input is in English alphabet to encode it as numbers (n=26)\n"+
			"3. choose file for result of encryption (field 3.)\n"+
			"-> Decode output string - if output is in numbers to decode it as English string (n=26)\n\n"+
			"For generating inverse matrix please:\n"+
			"1. choose file with matrix (field 1.)\n"+
			"-> n is kongruent modulo number (by default n=26 -> English alphabet)\n"+
			"2. choose file for result of inversion (field 4.)"
			;
			JOptionPane.showMessageDialog(this, message, "Help", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getSource() == item2) {
			String message="\u00a9 Martin Kozeny, University of New Orleans, 2011"
			;
			JOptionPane.showMessageDialog(this, message, "Credits", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
    private String optimalizer(String s)
    {
    	s=s.substring(s.length()-25,s.length());
    	return "..."+s;
    }
    
}

