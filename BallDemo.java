import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.List;
import java.util.*;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Graphics2D graphic;
    private Random randomGenerator;
    private ArrayList<BoxBall> totalBoxBalls;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        randomGenerator = new Random();
        totalBoxBalls = new ArrayList<BoxBall>();
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line
        int top = 10;
        int right = 550;
        int left = 50;
        myCanvas.setVisible(true);

        myCanvas.drawLine(50, ground, 550, ground); // draw the ground
        myCanvas.drawLine(50, top, 550, top); // draw the top
        myCanvas.drawLine(left, ground, left, top);  // draw the left
        myCanvas.drawLine(right, ground, right, top);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }

    public void boxBounce()
    {
        int ground = 400;   // position of the ground line
        int top = 10;       // position of the top line
        int right = 550;    // position of the right side.
        int left = 50;      // position of the left side.
        myCanvas.setVisible(true);

        myCanvas.drawLine(50, ground, 550, ground); // draw the ground
        myCanvas.drawLine(50, top, 550, top); // draw the top
        myCanvas.drawLine(left, ground, left, top);  // draw the left
        myCanvas.drawLine(right, ground, right, top); //draw the right

        // for loop to create a random number of up to 30 new balls.
        for(int i = 5; i <= randomGenerator.nextInt(26) + 5; i++){
            BoxBall ball = new BoxBall(randomGenerator.nextInt((right - left) + 1) + left + 25, 
                    randomGenerator.nextInt((ground - top) + 1) + top +25, 25, Color.YELLOW,
                    ground, top, right, left, myCanvas);
            totalBoxBalls.add(ball);

        }

        boolean finished =  false;
        while(!finished) {
            for(BoxBall ball: totalBoxBalls){
                myCanvas.wait(10);           // small delay
                ball.draw();
                ball.move();
                // stop once ball has travelled a certain distance on x axis
                if(ball.getXPosition() >= 600) {
                    finished = true;
                }

            }
        }

    }
}

