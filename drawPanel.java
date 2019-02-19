/*
    CS335 Graphics and Multimedia
    Author: Chelina Ortiz Montanez
    Title: Exercise 3 part 2
    Description: A java program that draws the five vertices are
        the points of the star and then plays an animation of the
        polygon rotating through 360 degrees, one degree per frame.
         -- Have a reset (set back at original position) and stop
            (stop the rotation at any point) button

    ** Starter code provided by Dr. Brent Seales
    *
    * I was not able to find a way to move the boxes when the line is being dragged,
    *   HOWEVER, if you click on the boxes again, it should be able to re-drag that
    *   specific point.
*/
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class drawPanel extends JPanel{


    // polygons for drawing
    private Polygon poly;
    private Polygon poly2[] = new Polygon[5];

    // Original coordinates of the boxes
    private int bottomRightX[] = {170, 200, 120, 220, 140};
    private int bottomRightY[] = {120, 220, 160, 160, 220};

    //constructor to draw the original polygon
    public drawPanel(Polygon poly){
        this.poly = poly;
        setBackground (Color.BLACK);
        //bottomRightX = poly.xpoints;  -- This would allow me to drag the box storing the point but
        //bottomRightY = poly.ypoints;  -- it constraints the movement of the line to 5 pixels each way
    }

    // this is the polygon that triggers the dragging / rubber-banding
    public boolean clickInPoly2(Point click){ // Checks if the box was clicked
        for (int i = 0; i < poly2.length; i++){
            if (poly2[i].contains(click)){
                return poly2[i].contains(click); // check if there is a click inside any of the polygons
            }
        } return false;
    }

    // Method that stores the point that is being dragged and allows it to modify the polygon
    public int startDrag(Point click) { /* find which polygon, if any, is trying to be dragged */
        for (int i = 0; i < poly2.length; i++) {
            if (poly2[i].contains(click)) {
                return i; // Store the index of the point in the polygon that is being dragged
            }
        }
        return -1;
    }

    // set a new polygon
    public void setPoly(Polygon poly, int x[], int y[]){
        this.poly = poly;
        //bottomRightX = poly.xpoints;  -- This would allow me to drag the box along with the point but
        //bottomRightY = poly.ypoints;  -- it constraints the movement of the line to 5 pixels each way
    }

    // where the drawing happens
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor (Color.BLUE);
        Graphics2D picture = (Graphics2D)g;
        picture.setStroke(new BasicStroke(3));

        // use the polygon draw to draw the current poly position
        picture.drawPolygon(poly);

        // draw the small poly, which is the yellow "handle" on the drag point
        draw_small_poly (picture);
    }

    // Method that creates the small polygons that activate the dragging of the lines
    public void draw_small_poly (Graphics2D g) {
        g.setColor(Color.GREEN);
        g.setStroke(new BasicStroke(1));
        // Create polygons that are 5 pixels away from the actual point
        for (int i = 0; i < bottomRightX.length; i++) {
            int x2[] = {bottomRightX[i] - 5, bottomRightX[i] + 5, bottomRightX[i] + 5, bottomRightX[i] - 5};
            int y2[] = {bottomRightY[i] - 5, bottomRightY[i] - 5, bottomRightY[i] + 5, bottomRightY[i] + 5};
            poly2[i] = new Polygon(x2, y2, 4);
            g.drawPolygon(poly2[i]); // draw a polygon for each point we have in our original polygon
        }
    }

}
