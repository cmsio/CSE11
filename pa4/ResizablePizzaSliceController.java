/*
 * Name: ChakMan Sio
 * Login: cs11farm
 * Date: 10 16, 2015
 * File: ReizablePizzaSliceController.java
 * Sources of Help: turtors, websites
 * This program allows users to create pizza and draw the line
 */

import java.awt.Graphics;
import objectdraw.*;

/**
 * This program allowers users create pizza in four different quadrants
 */

public class ResizablePizzaSliceController extends WindowController{
    
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;
    private static final int horizLine = 50;
    private static final int vertLine = 50;
    private double widthProportion = 0.5;
    private double heightProportion = 0.5;
    private static final double gap = 0.5;
    private static final int half = 2;
    private static final int MINV = 2;
    private static final int MAXV = 19;
    private static final int DEGREE = 90;
    private boolean grabh;
    private boolean grabv;
    private static final int pixels = 5;
    private Line vline;
    private Line hline;
    
    /**
     * method name is main
     * purpose: run the program
     * @param args is array of string
     * @return void
     */
    
    public static void main (String[] args){
        new Acme.MainFrame( new ResizablePizzaSliceController(),
                           args, FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    /**
     * Method name is begin
     * purpose:create a horizontal line, vertical line
     * @param: point is location
     * @return void
     */
    
    public void begin(){
        // create a horizontal line
        //
        hline = new Line (0,canvas.getHeight()/half,canvas.getWidth(),
                          canvas.getHeight()/half, canvas );
        vline = new Line (canvas.getWidth()/half,0, canvas.getWidth()/half,
                          canvas.getHeight(),canvas );
    }
    
    /**
     * Method name is onMousePress
     * purpose:checking whether the point one the vline,hline,or intersection
     * @param: point is location
     * @return void
     */
    
    public void onMousePress (Location point){
        // if the horizontal line contain point
        if (hline.contains(point)== true){
            grabh = true;
        }
        else
            grabh = false;
        // if the vertical line contain point
        if (vline.contains(point) == true){
            grabv = true;
        }
        else
            grabv = false;
    }
    
    /**
     * Method name is onMouseDrag
     * purpose: drag the horizontal line, vertical line, or intersection
     * @param: point is location
     * @return void
     */
    
    public void onMouseDrag (Location point){
        // it is for drag the horizontal line
        // if the point.Y greather than the pixeles(5). the point.Y
        // less than and equal canvas height,
        // and is grabed . it can drag the hline
        if (point.getY()>=pixels && point.getY()<=canvas.
            getHeight()-pixels && grabh == true){
            hline.setEndPoints(0,point.getY(),
                               canvas.getWidth(), point.getY());
        }
        // it is for drag the vertical line
        // if the point.X greather than the pixeles(5) and the point.X
        // less then and equal canvas width and is grabed, it can
        // drag the x line
        if (point.getX()>= pixels && point.getX()<= canvas.getWidth()-
            pixels && grabv == true ){
            vline.setEndPoints(point.getX(),0,
                               point.getX(), canvas.getHeight());
        }
    }
    
    
    /**
     * Method name is onMouseClick
     * purpose: Click it for create pizzaslice
     * @param: point is location
     * @return void
     */
    
    public void onMouseClick (Location point){
        new ResizablePizzaSlice (point,DEGREE, canvas, hline,vline);
    }
    
    /**
     * Method name is onMouseRealse
     * purpose: using it to create a canvas for FlippingDeadmau
     * @param: point is location
     * @return void
     */
    
    public void onMouseRelease (Location point){
        widthProportion = vline.getStart().getX()/canvas.getWidth();
        heightProportion = hline.getStart().getY()/canvas.getHeight();
        
    }
    
    /**
     * Method name is paint
     * purpose: to resize the quadrants when windows change size
     * @param: g is Graphics
     * @return void
     */
    
    public void paint (java.awt.Graphics g){
        super.paint(g);
        hline.setEndPoints(0,canvas.getHeight()*heightProportion,
                           canvas.getWidth(),heightProportion*canvas.getHeight());
        vline.setEndPoints(canvas.getWidth()*widthProportion,0,
                           canvas.getWidth()*widthProportion,canvas.getHeight());   
    }
}
