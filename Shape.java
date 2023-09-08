/**
 *    ===============================================================================
 *    Shape.java : The superclass of all shapes.

 *    A shape defines various properties, including selected, colour, width and height.
 *    YOUR UPI: mmar250
 *    ===============================================================================
 */
import java.awt.*;
abstract class Shape {
	public static final ShapeType DEFAULT_SHAPETYPE = ShapeType.RECTANGLE; // Q3: defines the default shape type which is RECTANGLE from shape type enum
	public static final PathType DEFAULT_PATHTYPE = PathType.BOUNCING; // Q3: defines the default path type which is BOUNCING from path type enum
    public static final int DEFAULT_X = 0, DEFAULT_Y = 0, DEFAULT_WIDTH=50, DEFAULT_HEIGHT=50, DEFAULT_PANEL_WIDTH=400, DEFAULT_PANEL_HEIGHT=600;
    public static final Color DEFAULT_COLOR=Color.red, DEFAULT_BORDER_COLOR=Color.blue;

	//Complete the default shape type and the default path type

    public int x, y, width=DEFAULT_WIDTH, height=DEFAULT_HEIGHT, panelWidth=DEFAULT_PANEL_WIDTH, panelHeight=DEFAULT_PANEL_HEIGHT; // the bouncing area
    protected Color color = DEFAULT_COLOR;
    protected boolean selected = false;    // draw handles if selected
    protected MovingPath path = new BouncingPath(1, 2);

	// Q5: Complete the default constructor
    public Shape() {
        this.x = DEFAULT_X;
        this.y = DEFAULT_Y;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.panelWidth = DEFAULT_PANEL_WIDTH;
        this.panelHeight = DEFAULT_PANEL_HEIGHT;
        this.color = DEFAULT_COLOR;
        this.path = new BouncingPath(1, 2);
    }
	// Q5: Complete 2 abstract methods
    public abstract void draw(Graphics g);
    public abstract boolean contains(Point mousePt);

	// Q6: data field border color for the border of the shape
    protected Color borderColor = DEFAULT_BORDER_COLOR;
	// Q6: Complete the getBorderColor()
    public Color getBorderColor() {
        return borderColor;
    }
	// Q6: Complete the setBorderColor()
    public void setBorderColor(Color bc) {
        borderColor = bc;
    }

	// Q7: Complete the two overloaded constructors
    public Shape(Color c, Color bc, PathType pt) {
        // Constructor with 3 parameters
        this.color = c;
        this.borderColor = bc;
        this.path = (pt == PathType.BOUNCING) ? new BouncingPath(1, 2) : null;
        // Q14 modifications
        if (pt == PathType.BOUNCING) {
            this.path = new BouncingPath(1, 2);
        } else if (pt == PathType.DOWN_AND_UP) {
            this.path = new DownAndUpPath(1, 2);
        } else {
            this.path = null;
        }
    }
    
    public Shape(int x, int y, int width, int height, int panelWidth, int panelHeight, Color c, Color bc, PathType pt) {
        // Constructor with 9 parameters
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.color = c;
        this.borderColor = bc;
        // Q14 modifications
        if (pt == PathType.BOUNCING) {
            this.path = new BouncingPath(1, 2);
        } else if (pt == PathType.DOWN_AND_UP) {
            this.path = new DownAndUpPath(1, 2);
        } else {
            this.path = null;
        }
    }
    
    
    
	// Q7: Complete the toString() method
    public String toString() {
        String className = getClass().getSimpleName();
        String pathClassName = (path != null) ? path.getClass().getSimpleName() : "null";
        return className + ":[(" + x + "," + y + ")," + width + "x" + height + "(Shape$" + pathClassName + ")]";
    }

 	public int getX() { return this.x; }
    public int getY() { return this.y;}
    public int getWidth() { return width; }
	public void setWidth(int w) { if (w < DEFAULT_PANEL_WIDTH && w > 0) width = w; }
    public int getHeight() {return height; }
	public void setHeight(int h) { if (h < DEFAULT_PANEL_HEIGHT && h > 0) height = h; }
    public boolean isSelected() { return selected; }
    public void setSelected(boolean s) { selected = s; }
	public Color getColor() { return color; }
    public void setColor(Color fc) { color = fc; }
    public void resetPanelSize(int w, int h) {
		panelWidth = w;
		panelHeight = h;
	}
 	public void drawHandles(Graphics obj) {
        if (selected) {
			System.out.println("Draw 4 handles");
        }
    }
    public void move() {
	        path.move();
    }
    /* Inner class ===================================================================== Inner class
     *    MovingPath : The superclass of all paths. It is an inner class.
     *    A path can change the current position of the shape.
     *    =============================================================================== */
    abstract class MovingPath {
        protected int deltaX, deltaY; // moving distance
        public MovingPath() { }
        public abstract void move();
    }
    class BouncingPath extends MovingPath {
        public BouncingPath(int dx, int dy) {
            deltaX = dx;
            deltaY = dy;
         }
        public void move() {
             x = x + deltaX;
             y = y + deltaY;
             if ((x < 0) && (deltaX < 0)) {
                 deltaX = -deltaX;
                 x = 0;
             }
             else if ((x + width > panelWidth) && (deltaX > 0)) {
                 deltaX = -deltaX;
                 x = panelWidth - width;
             }
             if ((y< 0) && (deltaY < 0)) {
                 deltaY = -deltaY;
                 y = 0;
             }
             else if((y + height > panelHeight) && (deltaY > 0)) {
                 deltaY = -deltaY;
                 y = panelHeight - height;
             }
        }
    }
    // Q13: Complete the inner class for Down and Up path
    class DownAndUpPath extends MovingPath {
        public DownAndUpPath(int dx, int dy) {
            deltaX = dx;
            deltaY = dy;
        }
        @Override
        public void move() {
            x = x + deltaX;
            y = y + deltaY;
            if (y + height > panelHeight) {
                deltaY = -deltaY;
                y = panelHeight - height;
            }
            if (y < 0) {
                deltaY = -deltaY;
                y = 0;
            }
            if (x + width > panelWidth) {
                x = 0;
            }
        }
    }
    
}