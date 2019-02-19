/*
    CS335 Graphics and Multimedia
    Author: Chelina Ortiz Montanez
    Title: Exercise 3 part 2
    Description:  java program to allow the user to “grab” a vertex
        of the polygon above and drag it.  Each vertex should be
        “draggable”.

    ** Starter code provided by Dr. Brent Seales
    *
    * I was not able to find a way to move the boxes when the line is being dragged,
    *   HOWEVER, if you click on the boxes again, it should be able to re-drag that
    *   specific point.
*/
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Polygon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Part2 extends JFrame {

    // simple hard-coded rectangle as starting point to draw
    private int x[] = {170, 200, 120, 220, 140};
    private int y[] = {120, 220, 160, 160, 220};
    //private Polygon poly  = new Polygon(x,y,4);
    private Polygon poly  = new Polygon(x,y,5);
    private Container c;
    private drawPanel panel;
    private Point currClick;

    //boolean to tell if the polygon is being dragged
    private boolean isDragging = false;

    public Part2() {
        super("Draggable Star");
        c = getContentPane();
        //create a new drawPanel with the banding boolean set
        panel = new drawPanel(poly);

        //create a mouse listener to tell when the mouse is clicked and released
        panel.addMouseListener(new MouseListener(){
            public void mouseExited(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseReleased(MouseEvent e){
                isDragging = false;
            }
            public void mousePressed(MouseEvent e){
                if(panel.clickInPoly2(e.getPoint())){ // Check if one of the boxes has been clicked and dragged
                    isDragging = true;
                    currClick = e.getPoint(); // Store the coordinate of the click
                }
            }
            public void mouseClicked(MouseEvent e){}
        });

        //create a motion listener to track the mouse dragging the polygon
        panel.addMouseMotionListener(new MouseMotionListener(){
            public void mouseDragged(MouseEvent e) {
                if(isDragging){ // When one of the boxes is clicked
                    // store the index of the point being dragged
                    int dragElement = panel.startDrag(currClick);
                    if (dragElement >= 0) {
                        // modify the specific index of the array of points to update the polygon
                        x[dragElement] = e.getX();
                        y[dragElement] = e.getY();
                    }
                    // set the new polygon from the original polygon, using the updated points
                    panel.setPoly(new Polygon(x,y,5), x, y);
                    panel.repaint();
                }
            }
            public void mouseMoved(MouseEvent e) {}
        });

        c.add(panel, BorderLayout.CENTER);
        setSize(380, 380);
        setVisible(true);
    }

    public static void main(String[] args) {
        Part2 poly = new Part2();
        poly.addWindowListener(
                new WindowAdapter(){
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
    }

} // end PolyBand JFrame object
