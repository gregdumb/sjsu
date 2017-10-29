package mvc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Greg on 10/29/2017.
 */
public class View
{
	private Model model;
	
	private JTextArea textArea;
	
	public View(Model newModel) {
		
		model = newModel;
		
		// Frame
		JFrame frame = new JFrame();
		frame.setSize(600, 800);
		
		// Panel
		JPanel panel = new JPanel();
		panel.setSize(600, 800);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		// Text area
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setSize(100, 400);
		
		// Text field
		JTextField textField = new JTextField();
		
		// Button
		JButton addButton = new JButton();
		addButton.setText("Add");
		addButton.addActionListener(new ActionListener() {
			// THIS IS THE CONTROLLER
			public void actionPerformed(ActionEvent e) {
				String newLine = textField.getText();
				textField.setText("");
				
				model.addLine(newLine);
				
				updateView();
			}
		});
		
		panel.add(textArea);
		panel.add(textField);
		panel.add(addButton);
		frame.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.show();
		
		updateView();
	}
	
	public void updateView() {
	
		textArea.setText("");
		String newText = "";
		
		for(String s : model.getLines()) {
			newText += s + "\n";
		}
		
		textArea.setText(newText);
	}
}
