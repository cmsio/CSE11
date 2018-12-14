/*
 * Name: ChakMan Sio
 * Login: cs11farm
 * Date: 10 16, 2015
 * File: ReizablePizzaSliceController.java
 * Sources of Help: turtors, websites
 * This program allows users to create pizza and draw the line
 */


import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;




/**
 * This program allowers users create pizza in four different quadrants
 */

public class ResizablePizzaSliceController extends WindowController implements ActionListener{
    
    
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
    
    private static final int MAX_SPEED = 100;
    private static final int MIN_SPEED = 1;
    
    private boolean grabh;
    private boolean grabv;
    private static final int pixels = 5;
    private Line vline;
    private Line hline;
  
    
    // button to clear output
    private JButton clearButton;
    private JButton stopButton;
    private JButton startButton;
    private JSlider slider;
    
    private boolean pizza_created = false;
    
    
   
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
        
        drawGrid();
        
        // create a horizontal line
        //
        hline = new Line (0,canvas.getHeight()/half,canvas.getWidth(),
                          canvas.getHeight()/half, canvas );
        vline = new Line (canvas.getWidth()/half,0, canvas.getWidth()/half,
                          canvas.getHeight(),canvas );
        
        JPanel upperPanel = new JPanel();
        
        slider = new JSlider (JSlider.HORIZONTAL,MIN_SPEED,MAX_SPEED,MAX_SPEED/2);
        
        startButton = new JButton( "Start" );
        stopButton = new JButton( "Stop" );
        clearButton = new JButton( "Clear All" );
        
        startButton.addActionListener( this );
        stopButton.addActionListener( this );
        clearButton.addActionListener( this );
        
     
        upperPanel.add(startButton);
        upperPanel.add(stopButton);
        upperPanel.add(clearButton);
        
        Container contentPane = getContentPane();
        
        contentPane.add(upperPanel, BorderLayout.NORTH);
        contentPane.add(slider, BorderLayout.SOUTH);
        contentPane.validate();
        
        
      
    }
    
    
    

    public void drawGrid() {
        hline = new Line (0,canvas.getHeight()/half,canvas.getWidth(),
                          canvas.getHeight()/half, canvas );
        vline = new Line (canvas.getWidth()/half,0, canvas.getWidth()/half,
                          canvas.getHeight(),canvas );
        
        }
    
    public void	actionPerformed(ActionEvent e){
        if (e.getSource() == startButton) {
            
        } else if (e.getSource() == stopButton) {
            
          
        } else if (e.getSource() == clearButton) {
            canvas.clear();
            drawGrid();
        }
    }
    
    
    
    
    
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
    
    public void mouseDraged (MouseEvent evt){
        Location point = new Location(evt.getX(), evt.getY());
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
    
    public void mouseClicked (MouseEvent evt){
        Location point = new Location(evt.getX(), evt.getY());
        new ResizablePizzaSlice (point,DEGREE, canvas, hline, vline, startButton, stopButton, clearButton);
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
