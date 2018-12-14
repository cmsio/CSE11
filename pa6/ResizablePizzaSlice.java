/*
 * Name: ChakMan Sio
 * Login: cs11farm
 * Date: 10 16, 2015
 * File: ReizablePizzaSlice.java
 * Sources of Help: turtors, websites
 * This program allows users to create four quadrants
 */

import java.awt.Color;
import objectdraw.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class allowers create two line objects to divide the canvas into
 * four quadrants and create a pizza slice
 */

public class ResizablePizzaSlice extends ActiveObject {
    
    // it creates the pause time
    private static final double PAUSE_TIME = 33;
    private static final double GROWTH = 5;
    private static final double ARC_ANGLE = 45;
    private static final int DEGREE = 45;
    private static final int DEGREE1 = 180;
    private static final int half = 2;
    private static final double START_ANGLE = 180;
    private static final double OFF_SEAT = 22.5;
    private int newStartAngles = 0;
    // set each quadrant
    private static final int FIRST_QUAD_ANGLE = 45;
    private static final int SEC_QUAD_ANGLE = FIRST_QUAD_ANGLE+90;
    private static final int THIRD_QUAD_ANGLE = SEC_QUAD_ANGLE +90;
    private static final int FOURTH_QUAD_ANGLE = THIRD_QUAD_ANGLE+90;
    private FilledArc pizzaSlice;
    private double startSize;
    private static final int height = 90;
    private static final int width = 90;
    //tip of pizza slice (center of arc's bounding box)
    private Location tip;
    private static final int MAXV = 19;
    private Line vline;
    private Line hline;
    // Set the color to specific color
    private static Color RGB = new Color(225,216,0);
    private static Color RGB1 = new Color(219,162,74);
    private static Color RGB2 = new Color(225,35,1);
    private static Color RGB3 = new Color(255,250,138);
    
    private boolean shouldClear = false;
    
    private JButton start;
    private JButton stop;
    private JButton clear;
    
    /**
     * method name is setColor
     * purpose:it make the pizza has the gradual change
     * @param: no param
     * @return void
     */
    
    public void setColor(){
        
        // set color
        // if the pizza's xpoint plus pizzaSlice's width
        // divided by 2 greather than the
        // end point of vline and ypoint plus the
        // height of pizza divided by 2 less than the point
        if(pizzaSlice.getX()+pizzaSlice.getWidth()/half >
           vline.getEnd().getX()
           && pizzaSlice.getY()+pizzaSlice.getHeight()/
           half< hline.getEnd().getY()){
            
            pizzaSlice.setColor(RGB);
        }
        
        // set color
        // if the pizza's xpoint plus pizzaSlice's width
        // divided by 2 less than the
        // end point of vline and ypoint plus the
        // height of pizza divided by 2 less than the point
        else if (pizzaSlice.getX()+pizzaSlice.getWidth()/
                 half< vline.getEnd().getX() &&
                 pizzaSlice.getY()+pizzaSlice.getHeight()/
                 half<hline.getEnd().getY()){
            pizzaSlice.setColor(RGB1);
        }
        
        // set color
        // if the pizza's xpoint plus pizzaSlice's width
        // divided by 2 greather than the
        // end point of vline and ypoint plus the
        // height of pizza divided by 2 less than the point
        else if (pizzaSlice.getX()+pizzaSlice.getWidth()/
                 half< vline.getEnd().getX() &&
                 pizzaSlice.getY()+pizzaSlice.getHeight()/
                 half>hline.getEnd().getY()){
            pizzaSlice.setColor(RGB2);
        }
        
        // set color
        // if the pizza's xpoint plus pizzaSlice's width
        // divided by 2 greather than the
        // end point of vline and ypoint plus the
        // height of pizza divided by 2 less than the point
        else if (pizzaSlice.getX()+pizzaSlice.getWidth()/half>
                 vline.getEnd().getX() &&
                 pizzaSlice.getY()+pizzaSlice.getHeight()/
                 half>hline.getEnd().getY()){
            pizzaSlice.setColor(RGB3);
        }
    }
    
    
    /**
     * constructor name is ResizablePizzaSlic
     * purpose: it is for create pizza and test whats quatrant the mouse click
     * @param point is location
     * @param1 size is double
     * @param2 canvas is DrawingCanvas
     * @param3 hline is Line
     * @param4 vLine is Line
     * @return void
     */
    
    public ResizablePizzaSlice( Location point, double size,
                               DrawingCanvas canvas,
                               Line hLine, Line vLine){
        
        //FilledArc( Location upperLeft, double width,double height,
        //double startAngle, double arcAngle, DrawingCanvas canvas )
        this.vline = vLine;
        this.hline = hLine;
        
        if(point.getX()> vLine.getEnd().getX() &&
           point.getY()<hLine.getEnd().getY()){
            
            // is for create a pizza, and change the color to specific color
            // in the first quatrant
            pizzaSlice = new FilledArc(point.getX()-DEGREE,
                                       point.getY()-DEGREE,
                                       height ,width,
                                       FIRST_QUAD_ANGLE-OFF_SEAT, ARC_ANGLE, canvas);
            pizzaSlice.setColor(RGB);
        }
        
        // It is for create a pizza, and change the color to specific color
        // in the second quatrant
        else if (point.getX()< vLine.getEnd().getX() &&
                 point.getY()<hLine.getEnd().getY()){
            pizzaSlice = new FilledArc(point.getX()-
                                       DEGREE,point.getY()-DEGREE,
                                       height,width,
                                       SEC_QUAD_ANGLE-OFF_SEAT, ARC_ANGLE, canvas);
            pizzaSlice.setColor(RGB1);
            
        }
        
        // It is for create a pizza, and change the color to specific color
        // in the third quatrant
        else if (point.getX()< vLine.getEnd().getX() &&
                 point.getY()>hLine.getEnd().getY()){
            pizzaSlice = new FilledArc(point.getX()-DEGREE,
                                       point.getY()-DEGREE, height,width,
                                       THIRD_QUAD_ANGLE-OFF_SEAT, ARC_ANGLE, canvas);
            pizzaSlice.setColor(RGB2);
        }
        
        // It is for create a pizza, and change the color to specific color
        // in the fourth quatrant
        else if (point.getX()> vLine.getEnd().getX() &&
                 point.getY()>hLine.getEnd().getY()){
            pizzaSlice = new FilledArc(point.getX()-DEGREE,point.getY()-DEGREE
                                       ,height,width,
                                       FOURTH_QUAD_ANGLE-OFF_SEAT, ARC_ANGLE, canvas);
            pizzaSlice.setColor(RGB3);
        }
        
        // it run all the object
        start();
    }
    
    /**
     * method name is run
     * purpose: this method is for resizing of the pizzaslice
     * @param no parammeter
     * @return void
     */
    
    public void run() {
        double local = GROWTH;
        while (true)
        {
            pizzaSlice.setStartAngle (newStartAngles-10);
            newStartAngles = newStartAngles-10;

            // call the setcolor method
            setColor();
            
            // using the while loop to make the pizza bigger
            // and smaller automatically
            pizzaSlice.setSize (pizzaSlice.getWidth() + local,
                                pizzaSlice.getHeight() + local);
            pizzaSlice.move (-local/half,-local/half);
            
            // if the pizzaSlice's width is grather than 180 or
            // the width is less than 45
            if ( pizzaSlice.getWidth() > DEGREE1 ||
                pizzaSlice.getWidth() <DEGREE){
                local = -local;
            }
            
            // time pause
            pause(PAUSE_TIME);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            shouldClear = false;
        } else if (e.getSource() == stop) {
            shouldClear = true;
        } else if (e.getSource() == clear) {
            shouldClear = true;
        }
    }
}