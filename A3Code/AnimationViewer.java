/*
 * ==========================================================================================
 * AnimationViewer.java : Moves shapes around on the screen according to different paths.
 * It is the main drawing area where shapes are added and manipulated.
 * YOUR UPI: mmar250
 * ==========================================================================================
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.tree.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.ListDataListener;
import java.lang.reflect.Field;

class AnimationViewer extends JComponent implements Runnable, TreeModel{
	private Thread animationThread = null; // the thread for animation
	private static int DELAY = 120; // the current animation speed
	private ShapeType currentShapeType = Shape.DEFAULT_SHAPETYPE; // the current shape type,
	private PathType currentPathType = Shape.DEFAULT_PATHTYPE; // the current path type
	private Color currentColor = Shape.DEFAULT_COLOR; // the current fill colour of a shape
	private Color currentBorderColor = Shape.DEFAULT_BORDER_COLOR;
	private int currentPanelWidth = Shape.DEFAULT_PANEL_WIDTH, currentPanelHeight = Shape.DEFAULT_PANEL_HEIGHT,currentWidth = Shape.DEFAULT_WIDTH, currentHeight = Shape.DEFAULT_HEIGHT;
	private String currentLabel = Shape.DEFAULT_LABEL;
	
	// Q5: remove the shapes array list 
	// ArrayList<Shape> shapes = new ArrayList<Shape>(); //create the ArrayList to store shapes
	
	// Q6: A private ArrayList<TreeModelListener> data field named treeModelListeners that defines a list of tree model listeners
	private ArrayList<TreeModelListener> treeModelListeners = new ArrayList<>();

	// Q5: Add a protected NestedShape data field named root which defines the root in the AnimationViewer class and the root is a nested shape
	protected NestedShape root;
	
	// Q11: A protected DefaultListModel<Shape> data named listModel which defines the list model in the bouncing program
	protected DefaultListModel<Shape> listModel = new DefaultListModel<>();
	
	// Q5: create the root by calling the constructor of NestedShape with the Shape.DEFAULT_PANEL_WIDTH and Shape.DEFAULT_PANEL_HEIGHT as parameters
	/* public AnimationViewer() {
        start();
        root = new NestedShape(Shape.DEFAULT_PANEL_WIDTH, Shape.DEFAULT_PANEL_HEIGHT);
    } */
	
	// Q10: Modify the no-arg constructor. The constructor should initialize the listModel attribute. 
	public AnimationViewer() {
	    start();
	    root = new NestedShape(Shape.DEFAULT_PANEL_WIDTH, Shape.DEFAULT_PANEL_HEIGHT);
	}

	
	// Q5: remove createNewShape() method
/*	protected void createNewShape(int x, int y) {
		switch (currentShapeType) {
			case RECTANGLE: {
			shapes.add( new RectangleShape(x, y,currentWidth,currentHeight,currentPanelWidth,currentPanelHeight,currentColor,currentBorderColor,currentPathType));
			break;
			}  case OVAL: {
			shapes.add( new OvalShape(x, y,currentWidth,currentHeight,currentPanelWidth,currentPanelHeight,currentColor,currentBorderColor,currentPathType));
			break;
			}
		}
	} */
	
	// Q5: Modify paintComponent and resetMarginSize methods such that the program should iterate the list of inner shapes of the root instead of the shapes array list.
	public final void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape currentShape : root.getAllInnerShapes()) {
            currentShape.move();
            currentShape.draw(g);
            currentShape.drawHandles(g);
            currentShape.drawString(g);
        }
    }
    
    public void resetMarginSize() {
        currentPanelWidth = getWidth();
        currentPanelHeight = getHeight();
        for (Shape currentShape : root.getAllInnerShapes()) {
            currentShape.resetPanelSize(currentPanelWidth, currentPanelHeight);
        }
    }
    
    // Q6: A method named getRoot() which returns the root in the AnimationViewer class.
    public NestedShape getRoot() {
        return root;
    }
    
    // Q6: A method named isLeaf(Object node) which returns true if the parameter node is a leaf, and returns false otherwise.
    public boolean isLeaf(Object node) {
        return !(node instanceof NestedShape);
    }

    // Q6: A method named isRoot(Shape selectedNode) which returns true if the parameter selectedNode is the root of the tree model
    public boolean isRoot(Shape selectedNode) {
        return selectedNode == root;
    }

    // Q6: A method named getChild(Object parent, int index) which returns the child of a parent at the parameter index in the parent object
    public Object getChild(Object parent, int index) {
        if (parent instanceof NestedShape) {
            NestedShape nestedShape = (NestedShape) parent;
            ArrayList<Shape> innerShapes = nestedShape.getAllInnerShapes();
            if (index >= 0 && index < innerShapes.size()) {
                return innerShapes.get(index);
            }
        }
        return null;
    }
    
    // Q6: A method named getChildCount(Object parent) which returns the number of children of a parent
    public int getChildCount(Object parent) {
        if (parent instanceof NestedShape) {
            NestedShape nestedShape = (NestedShape) parent;
            return nestedShape.getAllInnerShapes().size();
        }
        return 0;
    }
    
    // Q6: A method named getIndexOfChild(Object parent, Object child) which returns the index of child from a parent
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof NestedShape && child instanceof Shape) {
            NestedShape nestedShape = (NestedShape) parent;
            ArrayList<Shape> innerShapes = nestedShape.getAllInnerShapes();
            return innerShapes.indexOf(child);
        }
        return -1;
    }
    
    // Q6: A method named addTreeModelListener(final TreeModelListener tml) which adds the parameter tml object to the array list of tree model listeners
    public void addTreeModelListener(final TreeModelListener tml) {
        treeModelListeners.add(tml);
    }

    // Q6: A method named removeTreeModelListener(final TreeModelListener tml) which removes the parameter tml object from the array list of tree model listeners
    public void removeTreeModelListener(final TreeModelListener tml) {
        treeModelListeners.remove(tml);
    }
    
    // Q6: A empty method named valueForPathChanged(TreePath path, Object newValue) which sets the user object of the TreeNode identified by path and posts a node changed
    public void valueForPathChanged(TreePath path, Object newValue) {
        // Empty method, no implementation needed here
    }
    
    // Q7: fireTreeNodesInserted(Object source, Object[] path,int[] childIndices,Object[] children) method :  creates a TreeModelEvent 
    // object using the given parameters. The method also invokes the treeNodesInserted() method of all instances in the treeModelListeners 
    // array list using the TreeModelEvent object
    public void fireTreeNodesInserted(Object source, Object[] path, int[] childIndices, Object[] children) {
        TreeModelEvent event = new TreeModelEvent(source, path, childIndices, children);
        for (TreeModelListener listener : treeModelListeners) {
            listener.treeNodesInserted(event);
        }
        System.out.printf("Called fireTreeNodesInserted: path=%s, childIndices=%s, children=%s\n", Arrays.toString(path), Arrays.toString(childIndices), Arrays.toString(children));
    }
    
    // Q7: addShapeNode(NestedShape selectedNode) method :  adds an inner shape to the parameter selected node
    // Q11: Modify the addShapeNode method. The method should add the new shape into the listModel object.
    public void addShapeNode(NestedShape selectedNode) {
        int x = 0;
        int y = 0;
        int w = selectedNode.getWidth() / 5;
        int h = selectedNode.getHeight() / 5;
        int mw = selectedNode.getWidth();
        int mh = selectedNode.getHeight();
        Color c = selectedNode.getColor();
        Color bc = selectedNode.getBorderColor();
        ShapeType s = currentShapeType;
        PathType p = currentPathType;

        Shape newShape = null;

        switch (s) {
            case RECTANGLE:
                newShape = new RectangleShape(x, y, w, h, mw, mh, c, bc, p);
                break;
            case NESTED:
                newShape = new NestedShape(x, y, w, h, mw, mh, c, bc, p);
                break;
            case OVAL:
                newShape = new OvalShape(x, y, w, h, mw, mh, c, bc, p);
                break;  
            default:
                break;
        }

        if (newShape != null) {
            selectedNode.addInnerShape(newShape);

            int childIndex = selectedNode.getSize() - 1;

            Object[] path = selectedNode.getPath();
            int[] childIndices = { childIndex };
            Object[] children = { newShape };
            fireTreeNodesInserted(this, path, childIndices, children);

            reload(selectedNode);
        }
    }
    
    // Q11: reload(Shape selectedNode) method :  takes a selected shape 
    // as a parameter and reloads the list model using all inner shapes of the selected shape if the parameter is a nested shape
    public void reload(Shape selectedNode) {
        if (selectedNode instanceof NestedShape) {
            listModel.clear();
            NestedShape nestedShape = (NestedShape) selectedNode;
            for (Shape innerShape : nestedShape.getAllInnerShapes()) {
                listModel.addElement(innerShape);
            }
        }
    }
    
    // Q8: A method named fireTreeNodesRemoved(Object source, Object[] path, int[] childIndices,Object[] children) 
    // which creates a TreeModelEvent object and invokes the treeNodesRemoved() method of all instances in the 
    // treeModelListeners array list using the TreeModelEvent object as a parameter
    public void fireTreeNodesRemoved(Object source, Object[] path, int[] childIndices, Object[] children) {
        TreeModelEvent event = new TreeModelEvent(source, path, childIndices, children);
        for (TreeModelListener listener : treeModelListeners) {
            listener.treeNodesRemoved(event);
        }
        System.out.printf("Called fireTreeNodesRemoved: path=%s, childIndices=%s, children=%s\n", Arrays.toString(path), Arrays.toString(childIndices), Arrays.toString(children));
    }
    
    // Q8: removeNodeFromParent(Shape selectedNode) method : removes the shape from the model and notifies all listeners that a node has been removed
    public void removeNodeFromParent(Shape selectedNode) {
        NestedShape parent = selectedNode.getParent(); 

        if (parent != null) {
            int index = parent.indexOf(selectedNode);

            parent.removeInnerShape(selectedNode);
            
            
            Object[] path = parent.getPath();
            Object[] children = { selectedNode };
            int[] childIndices = { index };
            fireTreeNodesRemoved(this, path, childIndices, children);
        }
    }

	// you don't need to make any changes after this line ______________
	public String getCurrentLabel() {return currentLabel;}
	public int getCurrentHeight() { return currentHeight; }
	public int getCurrentWidth() { return currentWidth; }
	public Color getCurrentColor() { return currentColor; }
	public Color getCurrentBorderColor() { return currentBorderColor; }
	public void setCurrentShapeType(ShapeType value) {currentShapeType = value;}
	public void setCurrentPathType(PathType value) {currentPathType = value;}
	public ShapeType getCurrentShapeType() {return currentShapeType;}
	public PathType getCurrentPathType() {return currentPathType;}
	public void update(Graphics g) {
		paint(g);
	}
	public void start() {
		animationThread = new Thread(this);
		animationThread.start();
	}
	public void stop() {
		if (animationThread != null) {
			animationThread = null;
		}
	}
	public void run() {
		Thread myThread = Thread.currentThread();
		while (animationThread == myThread) {
			repaint();
			pause(DELAY);
		}
	}
	private void pause(int milliseconds) {
		try {
			Thread.sleep((long) milliseconds);
		} catch (InterruptedException ie) {}
	}
}
