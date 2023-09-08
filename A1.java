/**
 *  =====================================================================================================
 *  A1.java : Extends JFrame and contains a panel where shapes move around on the screen (entire project)
 *  YOUR UPI: mmar250
 *  =====================================================================================================
 */

import java.util.ArrayList;
import java.awt.*;
public class A1 {
	private AnimationViewer panel;  // panel for bouncing area
	public static void main(String[] args) {
		A1 program = new A1();
	}
	public A1() {
		panel = new AnimationViewer();
		Graphics g = null;
		for (int i=0; i<3; i++) {
			panel.setCurrentShapeType(ShapeType.values()[i%3]);
			panel.setCurrentPathType(PathType.values()[i%2]);
			panel.setCurrentWidth(i*10);
			panel.setCurrentHeight(i*15);
			panel.createNewShape(i, i+5);
			panel.paintComponent(g);
		}
		System.out.println("=============");
		panel.paintComponent(g);
		System.out.println("DONE");
	}
}

