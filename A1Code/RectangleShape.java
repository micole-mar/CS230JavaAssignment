/**
 *	===============================================================================
 *	RectangleShape.java : A shape that is a rectangle.
 *  YOUR UPI: mmar250
 *	=============================================================================== */
import java.awt.*;
//Complete the RectangleShape

class RectangleShape extends Shape {
	
	// Q8: default and 2 overloaded constructors of RectangleShape
	
    public RectangleShape() {
        super(); // Call default constructor of parent class Shape
    }

    public RectangleShape(Color c, Color bc, PathType pt) {
        super(c, bc, pt); // Call constructor of parent class Shape with color, border color, and path type
    }

    public RectangleShape(int x, int y, int width, int height, int panelWidth, int panelHeight, Color c, Color bc, PathType pt) {
        super(x, y, width, height, panelWidth, panelHeight, c, bc, pt); // Call constructor of parent class Shape with all properties
    }

    // Q8: overridden method which 'draws' a rectangle shape
    @Override
    public void draw(Graphics g) {
        // Print fill color and border color
        System.out.println(getColor());
        System.out.println(getBorderColor());
        // Print rectangle information using toString() method
        System.out.println(toString());
    }
    
    // Q8: overridden method which checks if a given mousePt point is inside the rectangle
    @Override
    public boolean contains(Point mousePt) {
        // Check if the given mouse point is inside the rectangle
        int mouseX = mousePt.x;
        int mouseY = mousePt.y;    	
        // returns true if point is inside the rectangle, false otherwise
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

}

