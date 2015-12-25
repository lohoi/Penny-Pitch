/*
 * Ballistic is a penny-pitching game that uses
 * simple physics to model the trajectory 
 * of a penny, given angle and intial vel.
*/

package ballistic;
import javax.swing.*;
import java.awt.*;

/**
 * Last modified Tuesday, April 29th, 2014 at 10:34 AM
 * @author Albert Lo for Questar III NV STEM
 */
public class Ballistic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
        String instring;    // temporarily holds input String
        double vel;         // velocity in m/s
        double deg;         // intial angle 
 
        // message dialog box with default title and icon
        JOptionPane.showMessageDialog(null, "Hello World!","Albert's Super Cool Game", JOptionPane.PLAIN_MESSAGE);
 
         
        // input dialog window getting the angle
        instring = JOptionPane.showInputDialog("Please enter the angle (in degrees):");
        deg = Double.parseDouble(instring);
 
        // input dialog window getting the velocity
        instring = JOptionPane.showInputDialog("Please enter the velocity (in m/s):");
        vel = Double.parseDouble(instring);
 
        MyCanvas canvas1 = new MyCanvas(deg, vel);
 
        //set up the JFrame to to hold the canvas
        JFrame frame = new JFrame();
        frame.setSize(500, 400);
        frame.setLocation(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //add canvas to the frame as a contentpanel
        frame.getContentPane().add(canvas1);
        frame.setVisible(true);
    }
}

class MyCanvas extends Canvas {
 
    double degrees;
    double velocity;
 
    // null constructor
    public MyCanvas() {
    }
 
    // initalizing constructor
    public MyCanvas(double deg, double vel) {
        this.degrees = deg;
        this.velocity = vel;
    }
 
    public void paint(Graphics graphics) {
        //naming variables
        double x = 0.0;         //x in m
        double y = 0.0;         //y in m
        double time = 0.0;      //time in s
        double xVelocity;       //x vector of velocity
        double yVelocity;       //y vector if velocity
        double radians;
        final double g = -9.81; //defining gravity in m/s
        String dMessage;        // degrees message String
        String vMessage;        // velocity message String
        String finalMessage;    // final message String
        boolean bounce = false; // bounce is true if the coin hits the wall
 
        //paints the background black
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, 500, 400);
 
        //draws a base line
        graphics.setColor(Color.WHITE);
        graphics.drawLine(0, 300, 300, 300);
 
        //draws the wall
        graphics.setColor(Color.WHITE);
        graphics.drawLine(300, 100, 300, 300);
 
        //adds labels
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("ARIAL", Font.BOLD, 14));  //Types "Wall" in red
        graphics.drawString("WALL (10 m away)", 280, 330);
        graphics.setColor(Color.GREEN);
        graphics.drawString("Start", 0, 330);        //Types a green "Start" 
        graphics.drawString("5 meters", 130, 330);
        graphics.drawLine(150, 300, 150, 280);              //Labels the axis with a 5m mark
 
        radians = Math.toRadians(degrees);           //changing degrees to radians
 
        //Find vector components of velocity
        yVelocity = velocity * (Math.sin(radians));    //finds y velocity in m
        xVelocity = velocity * (Math.cos(radians));    // finds x velocity in m
 
        dMessage = String.format("initial angle (degrees): %5.1f", degrees);
        graphics.drawString(dMessage, 40, 40);
        vMessage = String.format("initial velocity (m/s): %5.1f", velocity);
        graphics.drawString(vMessage, 300, 40);
 
        //loop
        while (y >= 0.0) {
            double y2 = y;
            double x2 = x;
            time = (time + .001);                              //increments of time that are displayed (in s)
            y = ((0.5 * g * time * time) + (yVelocity * time));       //finds y position in m
            x = (xVelocity * time);                             ////finds x position in m;       
            // if it hits the wall it bounces back
            if (x > 10) {
                x = 10 - (x - 10);   // x-10 is the amount that the object bounced back from the wall
                bounce = true;  // true if the coin hits the wall.
            }
 
            //draws the graph every .001 seconds in blue
            graphics.setColor(Color.blue);
            graphics.drawLine((int) ((x2 * 30)), (int) (300 - (y2 * 30)), (int) ((x * 30)), (int) (300 - (y * 30)));
 
            System.out.printf("%15s%15s%15s%n%15.3f%15.3f%15.3f%n", "x position(in m): ", "y position(in m): ", "time(in s)", x, y, time);
 
            System.out.println("*********************************");
 
        } //end loop
        //prints out the final positions of the projectile and tells the user how far away it is from the wall.
 
        // set the final message
        if (bounce) {
            finalMessage = String.format("Hit the wall and bounced back %5.1f meters", 10 - x);
        } else {
            finalMessage = String.format("short of the wall by %5.1f meters", (10 - x));
        }
 
        // display the final message
        graphics.drawString(finalMessage, 40, 80);
 
    } //end MyCanvas() constructor        
} //end class MyCanvas
