/**
 *	===============================================================================
 *	StarShape.java : A shape that is a rectangle with spotted circles inside.
 *  YOUR UPI: mmar250
 *	=============================================================================== */
import java.awt.*;
import java.util.*;
//Complete the SpottedRectangleShape

class SpottedRectangleShape extends RectangleShape {
    private ArrayList<Point> circleReferencePoints = new ArrayList<>();

	// Q8: default and overloaded constructors of SpottedRectangleShape

    // Default constructor
    public SpottedRectangleShape() {
        super(); // Call default constructor of RectangleShape
        Random rand = new Random(30);

        int circleX = rand.nextInt(getWidth() - 10);
        int circleY = rand.nextInt(getHeight() - 10);
        circleReferencePoints.add(new Point(circleX, circleY));
    }

    // Overloaded constructor with Color, Color, PathType parameters
    public SpottedRectangleShape(Color c, Color bc, PathType pt) {
        super(c, bc, pt); // Call constructor of RectangleShape with parameters
        Random rand = new Random(30);

        for (int i = 0; i < 3; i++) {
            int circleX = rand.nextInt(getWidth() - 10);
            int circleY = rand.nextInt(getHeight() - 10);
            circleReferencePoints.add(new Point(circleX, circleY));
        }
    }

    // Overloaded constructor with x, y, width, height, panelWidth, panelHeight, Color, Color, PathType parameters
    public SpottedRectangleShape(int x, int y, int width, int height, int panelWidth, int panelHeight,
                                 Color c, Color bc, PathType pt) {
        super(x, y, width, height, panelWidth, panelHeight, c, bc, pt); // Call constructor of RectangleShape with parameters
        Random rand = new Random(30);

        for (int i = 0; i < 3; i++) {
            int circleX = rand.nextInt(getWidth() - 10);
            int circleY = rand.nextInt(getHeight() - 10);
            circleReferencePoints.add(new Point(circleX, circleY));
        }
    }

    // Overloaded constructor with variable length of Point objects as parameters
    public SpottedRectangleShape(Point... points) {
        super(); // Call default constructor of RectangleShape
        circleReferencePoints.addAll(Arrays.asList(points));
    }

    // Q10: overridden method which 'draws' the shape
    @Override
    public void draw(Graphics g) {
        super.draw(g); // Draw the base rectangle

        if (g != null) {
            g.setColor(Color.BLACK);

            for (Point point : circleReferencePoints) {
                g.fillOval(x + point.x, y + point.y, 10, 10); // Draw black circles
            }
        }

        // Print the reference points of the circles
        System.out.print("[");
        for (int i = 0; i < circleReferencePoints.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(circleReferencePoints.get(i).toString());
        }
        System.out.println("]");
    }
}