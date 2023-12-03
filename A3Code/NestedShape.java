/*
 *  ============================================================================================
 *  Q2: define a class named NestedShape which extends the RectangleShape class
 *  
 *  NestedShape.java : NestedShape class implements the composite design pattern. Represents 
 *  a hierarchical structure for shapes. Creates a tree-like structure of shapes.
 *  YOUR UPI: mmar250
 *  ============================================================================================
 */
import java.awt.*;
import java.util.ArrayList;

class NestedShape extends RectangleShape {
	
	// a private ArrayList<Shape> data field named innerShapes that defines an array list of 
	// inner shapes (rectangles, ovals or nested shapes) in a nested shape.
	private ArrayList<Shape> innerShapes = new ArrayList<>();
	
	private Color fillColor = Shape.DEFAULT_COLOR;;
	private Color borderColor = Shape.DEFAULT_BORDER_COLOR;
	
	
	// A default no-arg constructor which creates a nested shape.
	public NestedShape() {
        super();
        createInnerShape(PathType.BOUNCING, ShapeType.RECTANGLE);
    }
	
	// An overloaded constructor that takes x, y, width, height, panel width, panel height, 
	// fill color, border color and path type (i.e. 9 parameters) to create a nested shape. 
	public NestedShape(int x, int y, int width, int height, int panelWidth, int panelHeight, Color fillColor, Color borderColor, PathType pathType) {
        super(x, y, width, height, panelWidth, panelHeight, fillColor, borderColor, pathType);
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        innerShapes = new ArrayList<>();
        createInnerShape(PathType.BOUNCING, ShapeType.RECTANGLE);
    }
	
	// An overloaded constructor that takes the width and the height as parameters and creates 
	// a nested shape only. This constructor creates a nested shape object and should not create 
	// any inner shapes. This is the root of the program, and will not have any inner shapes at the beginning
	public NestedShape(int width, int height) {
        super(0, 0, width, height, Shape.DEFAULT_PANEL_WIDTH, Shape.DEFAULT_PANEL_HEIGHT, Shape.DEFAULT_COLOR, Shape.DEFAULT_BORDER_COLOR, PathType.BOUNCING);
        fillColor = Shape.DEFAULT_COLOR;
        borderColor = Shape.DEFAULT_BORDER_COLOR;
        innerShapes = new ArrayList<>();
    }
	 
	// A method named createInnerShape(PathType pt, ShapeType st) which creates an inner shape 
	// based on the given path type and shape type, and then adds the inner shape to the list of 
	// inner shapes. The method returns the inner shape object
	public Shape createInnerShape(PathType pathType, ShapeType shapeType) {
	    int innerWidth = width / 5;
	    int innerHeight = height / 5;
	    Shape innerShape = null;
	
	    switch (shapeType) {
	        case RECTANGLE:
	            innerShape = new RectangleShape(0, 0, innerWidth, innerHeight, width, height, fillColor, borderColor, pathType);
	            break;
	        case OVAL:
	            innerShape = new OvalShape(0, 0, innerWidth, innerHeight, width, height, fillColor, borderColor, pathType);
	            break;
	        case NESTED:
	            innerShape = new NestedShape(0, 0, innerWidth, innerHeight, width, height, fillColor, borderColor, pathType);
	            break;
	    }

        innerShape.setParent(this);
        innerShapes.add(innerShape);
        return innerShape;
    }
	
	// A method named getInnerShapeAt(int index) which returns the inner shape at the given index.
	public Shape getInnerShapeAt(int index) {
        if (index >= 0 && index < innerShapes.size()) {
            return innerShapes.get(index);
        }
        return null;
    }

	// A method named getSize() which returns the number of inner shapes in a nested shape.
	public int getSize() {
        return innerShapes.size();
    }
	
	// Q3: indexOf(Shape s) method : Returns the index of the parameter inner shape in the list of inner shapes.
    public int indexOf(Shape s) {
        return innerShapes.indexOf(s);
    }
    
    // Q3: addInnerShape(Shape s) method: Adds the parameter shape into the list of inner shapes and sets the parent of the parameter shape to the outer shape.
    public void addInnerShape(Shape s) {
        innerShapes.add(s);
        s.setParent(this);
    }

    // Q3: removeInnerShape(Shape s) : Removes the parameter inner shape from the list of inner shapes and sets the parent of the parameter shape to null.
    public void removeInnerShape(Shape s) {
        innerShapes.remove(s);
        s.setParent(null);
    }

    // Q3:  removeInnerShapeAt(int index) : Removes the specified inner shape from the list of inner shapes at the given index and sets the parent of the removed shape to null.
    public void removeInnerShapeAt(int index) {
        if (index >= 0 && index < innerShapes.size()) {
            Shape removedShape = innerShapes.remove(index);
            removedShape.setParent(null);
        }
    }

    // Q3:  getAllInnerShapes() : Returns an ArrayList of the inner shapes.
    public ArrayList<Shape> getAllInnerShapes() {
        return innerShapes;
    }
	
	// A method named draw(Graphics g)  which draws the nested shape. 
	// The nested shape should have a black boundary and then draws all inner shapes. 
	@Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        
        g.translate(x, y); 

        for (Shape innerShape : innerShapes) {
        	innerShape.draw(g);
        	innerShape.drawHandles(g);
        	innerShape.drawString(g);
        }
        g.translate(-x, -y); 
    }

	// A method named move()  which overrides the move() from the superclass. 
	// The method should move the nested shape itself and also move all inner shapes.
    @Override
    public void move() {
        super.move();

        for (Shape innerShape : innerShapes) {
            innerShape.move();
        }
    }

}
