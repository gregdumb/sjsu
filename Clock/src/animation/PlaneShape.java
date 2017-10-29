package animation;

import java.awt.*;
import java.awt.geom.*;

/**
 * A plane that can be moved around.
 */
public class PlaneShape implements MoveableShape
{
	/**
	 * Constructs a plane item.
	 *
	 * @param x     the left of the bounding rectangle
	 * @param y     the top of the bounding rectangle
	 * @param width the width of the bounding rectangle
	 */
	public PlaneShape(int x, int y, int width)
	{
		this.x = x;
		this.y = y;
		this.width = width;
	}
	
	public void translate(double dx, double dy)
	{
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g2)
	{
		Rectangle2D.Double body
				= new Rectangle2D.Double(x + width / 3, y + width / 3,
				width, width / 6);
		
		// the bottom left of left wing
		Point2D.Double l1
				= new Point2D.Double(x + width / 2, y + width / 3);
		// the top of left wing
		Point2D.Double l2
				= new Point2D.Double(x + width / 2, y + width / 6);
		// the bottom right of left wing
		Point2D.Double l3
				= new Point2D.Double(x + width / 1, y + width / 3);
		
		
		// Left wing
		Line2D.Double backLW
				= new Line2D.Double(l1, l2);
		Line2D.Double frontLW
				= new Line2D.Double(l2, l3);
		
		// the top left of right wing
		Point2D.Double r1
				= new Point2D.Double(x + width / 2, y + width / 3 + width / 6);
		// the bottom of right wing
		Point2D.Double r2
				= new Point2D.Double(x + width / 2, y + 3 * width / 6 + width / 6);
		// the top right of right wing
		Point2D.Double r3
				= new Point2D.Double(x + width / 1, y + width / 3 + width / 6);
		
		
		// Right wing
		Line2D.Double backRW
				= new Line2D.Double(r1, r2);
		Line2D.Double frontRW
				= new Line2D.Double(r2, r3);
		
		// the top left of front
		Point2D.Double f1
				= new Point2D.Double(x + width / 3 + width,
				y + width / 3);
		// the right of front
		Point2D.Double f2
				= new Point2D.Double(x + width / 3 + width + width / 6,
				y + width / 3 + width / 12);
		// the bottom left of front
		Point2D.Double f3
				= new Point2D.Double(x + width / 3 + width,
				y + width / 3 + width / 6);
		
		
		// Bottom
		Line2D.Double topFront
				= new Line2D.Double(f1, f2);
		Line2D.Double bottomFront
				= new Line2D.Double(f2, f3);
		
		g2.draw(body);
		
		g2.draw(backLW);
		g2.draw(frontLW);
		
		g2.draw(backRW);
		g2.draw(frontRW);
		
		g2.draw(topFront);
		g2.draw(bottomFront);
	}
	
	private int x;
	private int y;
	private int width;
}
