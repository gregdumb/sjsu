package slider;

import slider.ScalableShape;

import javax.swing.*;
import java.awt.*;

/**
   An icon that contains a moveable shape.
   Modified from Horstman's shapeicon
*/
public class ShapeIcon implements Icon
{
   public ShapeIcon(slider.ScalableShape shape,
					int width, int height)
   {
      this.shape = shape;
      this.width = width;
      this.height = height;
   }
   
   public int getIconWidth()
   {
      return width;
   }

   public int getIconHeight()
   {
      return height;
   }

   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      Graphics2D g2 = (Graphics2D)g;
      shape.draw(g2);
   }

   private int width;
   private int height;
   private ScalableShape shape;
}


