package slider;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import slider.ScalableShape;
import slider.ShapeIcon;

import java.awt.*;

/**
 * Created by Greg on 10/28/2017.
 */
public class SliderTester
{
	public static void main(String args[]) {
		
		JFrame frame = new JFrame();
		
		JPanel panel = new JPanel();
		
		JLabel label = new JLabel();
		JSlider slider = new JSlider();
		
		final ScalableShape carShape = new CarShape(0, 0, 100);
		//ShapeIcon car = new ShapeIcon(carShape, 200, 200);
		final ShapeIcon carIcon = new ShapeIcon(carShape, 200, 200);
		
		label.setIcon(carIcon);
		
		slider.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				double percent = ((double) slider.getValue() / 100) * 2;
				
				carShape.setScale(percent);
				label.repaint();
				
				System.out.println(percent);
			}
		});
		
		panel.add(label);
		panel.add(slider);
		
		frame.getContentPane().add(panel);
		
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.show();
	}
}
