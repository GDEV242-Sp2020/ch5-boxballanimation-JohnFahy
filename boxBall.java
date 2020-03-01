import java.awt.*;
import java.awt.geom.*;

/**
 * Class BouncingBall - a graphical ball that observes the effect of gravity. The ball
 * has the ability to move. Details of movement are determined by the ball itself. It
 * will fall downwards, accelerating with time due to the effect of gravity, and bounce
 * upward again when hitting the ground.
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Michael Kölling (mik)
 * @author David J. Barnes
 * @author Bruce Quig
 *
 * @version 2011.07.31
 */

public class boxBall
{
    private static final int speedFactor = 10;  // effect of gravity

    private int ballDegradation = 2;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int groundPosition;      // y position of the bottom of the box.
    private final int topPosition;         // y position of the top of the box.
    private final int rightSide;           // x position of the right side of the box.
    private final int leftSide;             // position of the left side of the box.
    private Canvas canvas;


    private int ySpeed = speedFactor;// initial downward speed
    private int xSpeed = speedFactor;

    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public boxBall(int xPos, int yPos, int ballDiameter, Color ballColor,
    int groundPos, int topPos, int right, int left, Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        groundPosition = groundPos;
        topPosition = topPos;
        rightSide = right;
        leftSide = left;
        canvas = drawingCanvas;


    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();

            
        yPosition +=ySpeed;
        xPosition +=xSpeed;

        // check if it has hit the ground.
        if(yPosition >= (groundPosition - diameter) && ySpeed > 0) {
            yPosition = (int)(groundPosition - diameter);
            ySpeed = -ySpeed ; 
        }

        //check that it has hit the top.
        if(yPosition <= (topPosition) && ySpeed < 0) {
            yPosition = (int)(topPosition);
            ySpeed = -ySpeed  ; 
        } 

        //check that it has hit the right side.
        if(xPosition >= (rightSide - diameter) && xSpeed > 0) {
            xPosition = (int)(rightSide - diameter);
            xSpeed = -xSpeed ; 
        }

        //check that the ball has hit the left side.
        if(xPosition <= (leftSide) && xSpeed < 0) {
            xPosition = (int)(leftSide);
            xSpeed = -xSpeed ; 
        }

        // draw again at new position
        draw();
    }  

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
