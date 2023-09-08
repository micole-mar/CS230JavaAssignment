/**
 * ==========================================================================================
 *  AnimationPanel.java : Moves shapes around on the screen according to different paths.
 *  It is the main drawing area where shapes are added and manipulated.
 *  YOUR UPI: mmar250
 *  =========================================================================================
 */

import java.awt.*;
import java.util.ArrayList;

class AnimationViewer  {
    private ArrayList<Shape> shapes = new ArrayList<Shape>(); //create the ArrayList to store shapes
    private Color currentColor=Shape.DEFAULT_COLOR;  // the current fill color of a shape
    private int currentPanelWidth=Shape.DEFAULT_PANEL_WIDTH, currentPanelHeight = Shape.DEFAULT_PANEL_HEIGHT, currentWidth=Shape.DEFAULT_WIDTH, currentHeight=Shape.DEFAULT_HEIGHT;
    private Color currentBorderColor = Shape.DEFAULT_BORDER_COLOR; // Q11: private Color data field which defines current border color in AnimationViewer class

    
	// Q4: data field currentShapeType and currentPathType
    private ShapeType currentShapeType = Shape.DEFAULT_SHAPETYPE; 
    private PathType currentPathType = Shape.DEFAULT_PATHTYPE; 

	// Q12: Complete the createNewshape method
    protected void createNewShape(int x, int y) {
        Shape newShape = null;

        switch (currentShapeType) {
            case RECTANGLE:
                newShape = new RectangleShape(x, y, currentWidth, currentHeight, currentPanelWidth, currentPanelHeight, currentColor, currentBorderColor, currentPathType);
                break;
            case TRIANGLE_ARROW:
                newShape = new TriangleArrowShape(x, y, currentWidth, currentHeight, currentPanelWidth, currentPanelHeight, currentColor, currentBorderColor, currentPathType);
                break;
            case SPOTTED:
                newShape = new SpottedRectangleShape(x, y, currentWidth, currentHeight, currentPanelWidth, currentPanelHeight, currentColor, currentBorderColor, currentPathType);
                break;
            default:
                break;
        }

        if (newShape != null) {
            shapes.add(newShape);
        }
    }
	public AnimationViewer() {}

	// Q4: Complete the get/set currentShapeType methods
	public ShapeType getCurrentShapeType() {
	    return currentShapeType;
	}
	public void setCurrentShapeType(ShapeType s) {
	    currentShapeType = s;
	}
	
	// Q4: Complete the get/set currentPathType methods
	public PathType getCurrentPathType() {
	    return currentPathType;
	}
	public void setCurrentPathType(PathType p) {
	    currentPathType = p;
	}
	
	// Q11: get/set for Current Border Color
	public Color getCurrentBorderColor() {
	    return currentBorderColor;
	}
	public void setCurrentBorderColor(Color bc) {
	    currentBorderColor = bc;
	}
	

	public int getCurrentWidth() { return  currentWidth; }
    public void setCurrentWidth(int w) {currentWidth=w;}
	public int getCurrentHeight() { return currentHeight; }
    public void setCurrentHeight(int h) {currentHeight=h;}
    public Color getCurrentColor() { return currentColor; }
    public void setCurrentColor(Color c){currentColor = c;}
    public void setPanelWidth(int w) {currentPanelHeight=w;}
    public void setPanelHeight(int h) {currentPanelHeight=h;}
    public void paintComponent(Graphics g) {
		for (Shape currentShape: shapes) {
			currentShape.move();
			currentShape.draw(g);
			currentShape.drawHandles(g);
		}
    }
 
}