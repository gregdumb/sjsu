package slider;

import java.awt.*;

/**
 * A shape that can be scaled.
 * Modified from Horstmann's movableshape
 */
public interface ScalableShape
{
	/**
	 * Draws the shape.
	 *
	 * @param g2 the graphics context
	 */
	void draw(Graphics2D g2);
	
	/**
	 * Sets shape scale
	 *
	 * @param newScale scale amount
	 */
	//void translate(double dx, double dy);
	void setScale(double newScale);
}
