package animation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This program implements an animation that moves
 * a plane shape.
 */
public class AnimationTest
{
	/**
	 * Construct a frame and animate a plane in it.
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		
		final MoveableShape shape
				= new PlaneShape(0, 0, CAR_WIDTH);
		
		final ShapeIcon icon = new ShapeIcon(shape,
				ICON_WIDTH, ICON_HEIGHT);
		
		final JLabel label = new JLabel(icon);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new FlowLayout());
		contentPane.add(label);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.show();
		
		final int DELAY = 10;
		// milliseconds between timer ticks
		Timer t = new Timer(DELAY, new
				ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						shapePosition++;
						
						if (shapePosition > frame.getWidth() + CAR_WIDTH)
						{
							shape.translate(shapePosition * -1, 0);
							label.repaint();
							shapePosition = 0;
							System.out.println("Toobaig");
						}
						else
						{
							shape.translate(1, 0);
							label.repaint();
						}
					}
				});
		t.start();
	}
	
	private static final int ICON_WIDTH = 400;
	private static final int ICON_HEIGHT = 100;
	private static final int CAR_WIDTH = 100;
	
	private static int shapePosition = CAR_WIDTH;
}
