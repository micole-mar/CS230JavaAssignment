# CS230 Java Assignment
COMPSCI230 Semester 2 2023: 

The aim of this project is to provide students with experience in object-oriented programming, principles of inheritance and polymorphism. The complete application is a simple bouncing program designed to allow different shapes to move around along various paths. Users will be able to create shapes based on the classes you will write, and they can also select individual existing shapes on the panel and modify their properties. This project consists of THREE iterations, each of which will be treated as a separate assignment. 

Assignment 01:
The first iteration (this assignment) is a text-based version of the program. The program simply creates and displays the fill color, border color and coordinate information of each shape in the console. However, in A2, you will be using methods like g.drawRect(...), g.fillRect(...), or g.drawPolygon(...) to draw shapes.

A1 class: This serves the main program. Do not make any changes in this class.
AnimationViewer class: This is the bouncing area of A1. You are required to add a data field and some methods to this class.
Shape class: This acts as the superclass of all shapes in A1. You need to modify two data fields and add some methods to this class.
ShapeType enum: Complete this enum which defines the shape types of a shape. 
PathType enum: Complete this enum which defines the path types of a shape.
RectangleShape class: Complete this subclass which defines a rectangle shape. 
TriangleArrowShape class: Complete this subclass which defines a triangle arrow shape.
SpottedRectangleShape class: Complete this subclass which defines a rectangle with spotted circles shape
