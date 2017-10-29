package observer;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * A class that implements an Observer object that displays a barchart view of
 * a data model.
 */
public class BarFrame extends JFrame implements ChangeListener
{
	/**
	 * Constructs a observer.BarFrame object
	 *
	 * @param dataModel the data that is displayed in the barchart
	 */
	public BarFrame(DataModel dataModel)
	{
		this.dataModel = dataModel;
		a = dataModel.getData();
		
		this.setLocation(0, 200);
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				
				g2.setColor(Color.red);
				
				double max = ((Double) (a.get(0))).doubleValue();
				for (int i = 1; i < a.size(); i++)
				{
					double val = ((Double) (a.get(i))).doubleValue();
					if (val > max)
						max = val;
				}
				
				double barHeight = getHeight() / a.size();
				
				for (int i = 0; i < a.size(); i++)
				{
					double value = ((Double) (a.get(i))).doubleValue();
					
					double barLength = getWidth() * value / max;
					
					Rectangle2D.Double rectangle = new Rectangle2D.Double
							(0, barHeight * i, barLength, barHeight);
					
					g2.fill(rectangle);
				}
			}
		};
		
		panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		contentPane.add(panel, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.show();
		
		panel.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				float yPercent = (e.getY() / (float) panel.getSize().height);
				float xPercent = (e.getX() / (float) panel.getSize().width);
				
				int section = (int) Math.floor(yPercent * a.size());
				
				double max = ((Double) (a.get(0))).doubleValue();
				for (int i = 1; i < a.size(); i++)
				{
					double val = ((Double) (a.get(i))).doubleValue();
					if (val > max)
						max = val;
				}
				
				double newVal = max * xPercent;
				
				dataModel.update(section, newVal);
				
				System.out.println(newVal);
			}
		});
	}
	
	/**
	 * Calledwhen the data in the model is changed.
	 *
	 * @param e the event representing the change
	 */
	public void stateChanged(ChangeEvent e)
	{
		a = dataModel.getData();
		this.repaint();
	}
	
	private ArrayList a;
	private DataModel dataModel;
	
	private static final int PANEL_WIDTH = 200;
	private static final int PANEL_HEIGHT = 200;
}
