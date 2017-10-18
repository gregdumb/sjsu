import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by Greg on 10/17/2017.
 */
public class ClockFrame
{
	public ClockFrame()
	{
		JFrame frame = new JFrame();

		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel iconTester = new JLabel("Clock");
		Icon icon = new ClockIcon();
		iconTester.setIcon(icon);

		frame.getContentPane().add(iconTester);

		frame.setVisible(true);

		java.util.Timer timer = new java.util.Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				iconTester.repaint();
			}
		}, 1000, 1000);
	}
}