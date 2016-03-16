import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FakeCalculator extends JFrame
{
	public static void main(String [] args)
	{
		new Calculator();
	}
    
	private JTextField resultJText;

	public FakeCalculator() 
	{
		JButton[] numberButtons = new JButton[10];
		for ( int i = 9; i >= 0; i--)
   			numberButtons[i] = new JButton(Integer.toString(i));
		JButton enterButton = new JButton("=");
		JButton cButton = new JButton("C");
    		JButton multiplyButton = new JButton("*");
    		JButton divideButton = new JButton("/");
		JButton addButton = new JButton("+");
		JButton substractButton = new JButton("-");

		resultJText = new JTextField();
		resultJText.setPreferredSize(new Dimension(160, 20));
		resultJText.setBackground(Color.WHITE);
		resultJText.setEnabled(false);
		resultJText.setHorizontalAlignment(4);
		resultJText.setDisabledTextColor(Color.BLACK);

		JPanel motherPanel = new JPanel();
		motherPanel.setLayout(new BoxLayout(motherPanel, BoxLayout.Y_AXIS));

		JPanel textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(160, 20));
		textPanel.add(resultJText);

		JPanel numberButtonsPanel = new JPanel();
		numberButtonsPanel.setPreferredSize(new Dimension(160, 100));

		for(int i = 9; i>=0; i--)
			numberButtonsPanel.add(numberButtons[i]);

		JPanel functionButtonPanel = new JPanel();
		functionButtonPanel.setPreferredSize(new Dimension(160, 35));
		functionButtonPanel.add(enterButton);
		functionButtonPanel.add(cButton);
		functionButtonPanel.add(multiplyButton);
		functionButtonPanel.add(divideButton);
		functionButtonPanel.add(addButton);
		functionButtonPanel.add(substractButton);

		motherPanel.add(textPanel);
		motherPanel.add(numberButtonsPanel);
		motherPanel.add(functionButtonPanel);
		add(motherPanel);

		setTitle("Calculator");
		setSize(180, 290);
		setResizable(false);
		setLocationByPlatform(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
}