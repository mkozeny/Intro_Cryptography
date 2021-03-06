package gui;


import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import main.Equation;
import main.Field;

public class Window extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -503408349638454675L;
	JTextField byte1Field;
	JTextField byte2Field;
	JButton multiplyButton;
	JButton addSubButton;
	JButton inverseButton;
	Field field;
	JTextField result;
	JMenuItem item1;
	JMenuItem item2;
	
    public Window() {
    	field = new Field();

        JLabel byte1Label = new JLabel("byte 1");
        byte1Field = new JTextField(15);
        
        JLabel byte2Label = new JLabel("byte 2");
        byte2Field = new JTextField(15);
        
        JLabel resultLabel = new JLabel("result");
        
        byte1Field.setInputVerifier(new StrictInputVerifier());
        byte2Field.setInputVerifier(new StrictInputVerifier());
        
        addSubButton = new JButton("Add/sub");
        addSubButton.addActionListener(this);

        multiplyButton = new JButton("Multiply");
        multiplyButton.addActionListener(this);
        
        inverseButton = new JButton("byte1^{-1}");
        inverseButton.addActionListener(this);
        
        result = new JTextField(2);
        result.setEditable(false);
        
        
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
        
        
        
        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout(); 
        panel.setLayout(flowLayout);
        flowLayout.setAlignment(FlowLayout.TRAILING);


        
        panel.add(byte1Label);
        panel.add(byte1Field);
        panel.add(byte2Label);
        panel.add(byte2Field);
        panel.setComponentOrientation(
                ComponentOrientation.LEFT_TO_RIGHT);
        
        
        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());

        controls.add(addSubButton);
        controls.add(multiplyButton);
        controls.add(inverseButton);

        
        JPanel resultPanel = new JPanel();
        controls.setLayout(new FlowLayout());
        resultPanel.add(resultLabel);
        resultPanel.add(result);
        
        
        add(panel, BorderLayout.NORTH);
        add(controls, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        setTitle("GF 256");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private class StrictInputVerifier extends InputVerifier {
        

        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            if (textField.getText().matches("[0-9a-fA-F]{1,2}")) {
                return true;
            } else {
            	textField.setText("");
                return false;
            }
        }
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addSubButton && byte1Field.getText()!=null 
				&& byte1Field.getText().length()>0 && byte2Field.getText()!=null && byte2Field.getText().length()>0)
		{
			result.setText(Field.binaryToHex(field.addPolynoms(Field.hexToBinary(byte1Field.getText()), Field.hexToBinary(byte2Field.getText()))));
		}
		else if(e.getSource()==multiplyButton && byte1Field.getText()!=null 
				&& byte1Field.getText().length()>0 && byte2Field.getText()!=null && byte2Field.getText().length()>0)
		{
			result.setText(Field.binaryToHex(field.multiplyPolynoms(Field.hexToBinary(byte1Field.getText()), Field.hexToBinary(byte2Field.getText()))));
		}
		else if(e.getSource()==inverseButton && byte1Field.getText()!=null 
				&& byte1Field.getText().length()>0)
		{
			Equation equation = field.doExtendedEucleid(field.getModuloPolynom(),Field.hexToBinary(byte1Field.getText()));
			result.setText(Field.binaryToHex(Field.isPolynomZero(equation.getRemainder())?equation.getLeftOperand():equation.getK()));
		}
		else if (e.getSource() == item1) {
			String message="Please type byte1 and byte into appropriate input fields and choose operation."
			;
			JOptionPane.showMessageDialog(this, message, "Help", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getSource() == item2) {
			String message="\u00a9 Martin Kozeny, University of New Orleans, 2011"
			;
			JOptionPane.showMessageDialog(this, message, "Credits", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			result.setText("");
		}
	}
    
}

