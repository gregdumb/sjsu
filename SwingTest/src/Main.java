import javax.swing.*;

/**
 * Created by Greg on 10/3/2017.
 */
public class Main
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();

		JButton button = new JButton("click");
		button.setBounds(130, 100, 100, 40);

		frame.add(button);

		frame.setSize(400, 500);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
