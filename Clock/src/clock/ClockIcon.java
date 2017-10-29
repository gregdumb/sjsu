package clock;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Greg on 10/17/2017.
 */
public class ClockIcon implements Icon
{
	public ClockIcon()
	{
	}

	public void paintIcon(Component c, Graphics g, int x, int y)
	{
		// Center of the clock
		double centerx = x + getIconWidth() / 2;
		double centery = y + getIconHeight() / 2;
		
		// Length of the hands
		double minuteHandLength = getIconWidth() / 2;
		double hourHandLength = minuteHandLength * 0.5;
		double secondHandLength = minuteHandLength * 0.75;
		
		// Calendar with current time
		GregorianCalendar nowCal = new GregorianCalendar();
		
		// Extract time from calendar
		int second = nowCal.get(GregorianCalendar.SECOND);
		int minute = nowCal.get(GregorianCalendar.MINUTE);
		int hour = nowCal.get(GregorianCalendar.HOUR);
		
		// Log time to the console
		System.out.println("Current time: " + hour + ":" + minute + ":" + second);
		
		// Get rotation in radians of each hand
		double rotSecond = (second / 60.0) * Math.PI * 2 - (Math.PI / 2);
		double rotMinute = (minute / 60.0) * Math.PI * 2 - (Math.PI / 2);
		double rotHour = ((hour + minute / 60.0) / 12.0) * Math.PI * 2 - (Math.PI / 2);
		
		// Calculate coordinates of hand ends
		double hourx = centerx + Math.cos(rotHour) * hourHandLength;
		double houry = centery + Math.sin(rotHour) * hourHandLength;

		double minutex = centerx + Math.cos(rotMinute) * minuteHandLength;
		double minutey = centery + Math.sin(rotMinute) * minuteHandLength;

		double secondx = centerx + Math.cos(rotSecond) * secondHandLength;
		double secondy = centery + Math.sin(rotSecond) * secondHandLength;
		
		// Create hand lines
		Shape secondHand = new Line2D.Double(centerx, centery, secondx, secondy);
		Shape minuteHand = new Line2D.Double(centerx, centery, minutex, minutey);
		Shape hourHand = new Line2D.Double(centerx, centery, hourx, houry);
		
		// Create 2D graphics object
		Graphics2D g2 = (Graphics2D) g;
		
		// Create clock circle
		Shape ellipse = new Ellipse2D.Double(x, y, getIconWidth(), getIconHeight());
		
		// Draw elements
		g2.draw(ellipse);
		g2.draw(secondHand);
		g2.draw(minuteHand);
		g2.draw(hourHand);

		g2.setColor(Color.BLACK);
	}

	public int getIconWidth() {
		return 400;
	}

	public int getIconHeight() {
		return 400;
	}
}
