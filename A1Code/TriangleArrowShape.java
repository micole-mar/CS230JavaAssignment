/**
 *	===============================================================================
 *	StarShape.java : A shape that is a triangle arrow (i.e. upside down triangle).
 *  YOUR UPI: mmar250
 *	=============================================================================== */
import java.awt.*;
import java.util.*;
//Complete the TriangleArrowShape
class TriangleArrowShape extends Shape {
	
	// Q9: default and 2 overloaded constructors of TriangleArrowShape

	public TriangleArrowShape() {
        super(); // Call default constructor of parent class Shape
    }

    public TriangleArrowShape(Color c, Color bc, PathType pt) {
        super(c, bc, pt); // Call constructor of parent class Shape with color, border color, and path type
    }

    public TriangleArrowShape(int x, int y, int width, int height, int panelWidth, int panelHeight, Color c, Color bc, PathType pt) {
        super(x, y, width, height, panelWidth, panelHeight, c, bc, pt); // Call constructor of Shape with all properties
    }

    // Q9: overridden method which 'draws' a triangle arrow shape
    @Override
    public void draw(Graphics g) {
        // Print fill color and border color
        System.out.println(getColor());
        System.out.println(getBorderColor());
    
        // Print triangle arrow shape information using toString() method
        System.out.println(toString());
    
        // Print the coordinates of the triangle arrow shape
        int[] xCoords = { x, x + width, x + width / 2 };
        int[] yCoords = { y, y, y + height };
        System.out.println(Arrays.toString(xCoords) + ", " + Arrays.toString(yCoords));
    }
    
    // Q9: overridden method which checks if a given mousePt point is inside the triangle shape
    @Override
    public boolean contains(Point mousePt) {
        // Check if the given point is inside the triangle shape
        Polygon triangle = new Polygon(
                new int[]{x, x + width, x + width / 2},
                new int[]{y, y, y + height},
                3);
        // returns true if point is within triangle shape
        return triangle.contains(mousePt);
    }
}