import objectdraw.*;

/**
 * Class Name is Flipping mickey, this class allows user to use
 * WindowController, so that user can flip mickey
 */

public class FlippingDeadmau5 extends WindowController {
    // the coordinate of the fram
    private Deadmau5 drawmickey;
    private Text instr1, instr2, instr3;
    private static final int INSTR1_X = 50;
    private static final int INSTR1_Y = 50;
    private static final int INSTR2_X = INSTR1_X;
    private static final int INSTR2_Y = INSTR1_Y + 20;
    private static final int INSTR3_X = INSTR1_X;
    private static final int INSTR3_Y = INSTR2_Y + 20;
    // this is canvas size
    private static final int CANVAS_WIDTH = 750;
    private static final int CANVAS_HEIGHT = 750;
    private static final int FLIP_PRESS_THRESHOLD = 500;
    // This is using time
    private Timer timer;
    // This is for how much time we used
    private static double time_count;
    // This is test whether the mickey created or not
    private boolean mickeyCreated = false;
    // This is test whether the circle grabbed or not
    private boolean circleGrabbed;
    private Location lastpoint;
    
    /**
     * Method name is onMouseRelease
     * purpose: using it to create a canvas for FlippingDeadmau
     * @param args, the type is string
     * @return void
     */
    
    public static void main(String[] args) {
        new Acme.MainFrame(new FlippingDeadmau5(),
                           args, CANVAS_WIDTH, CANVAS_HEIGHT);
    }
    
    public void begin(){
        timer = new Timer();
        // show the three texts
        instr1 = new Text(
                          "Click to display a Mickey silhouette " +
                          "centered at the mouse click.",
                          INSTR1_X, INSTR1_Y, canvas);
        instr2 = new Text (
                           "Mouse press in any part of the image " +
                           "and drag to move image around.",
                           INSTR2_X, INSTR2_Y, canvas);
        instr3 = new Text (
                           "Mouse click in ear parts of the image with "+
                           "a mouse press for more than 0.5 seconds to rotate the image ",
                           INSTR3_X, INSTR3_Y,canvas);
        mickeyCreated = false;
    }
    
    /**
     * Method name is onMouseRelease
     * purpose: using it to click in the Windowcontroller
     * @param point, the type is location
     * @return void
     */
    
    public void onMouseClick(Location point){
        // hide the instruction
        instr1.hide();
        instr2.hide();
        instr3.hide();
        //if mickey has not been created yet, do the following
        if (mickeyCreated == false){
            drawmickey = new Deadmau5(point, canvas);
            mickeyCreated = true;
        }
        // if the time greather than 1/2
        if (time_count >=500){
            // if the point in the left ear, and flip to left
            // but for the extra credit , it rotate to left
            if (drawmickey.inLeftEar(point)){
                drawmickey. flipLeft();
            }
            // if the point in face, and then send the face to back
            // but for the extra credit, it send to center
            else if (drawmickey.inFace(point)){
                drawmickey. flip();
            }
            // if the point in the left ear, and flip to right
            // but for the extra credit , it rotate to right
            else if(drawmickey.inRightEar(point)){
                drawmickey. flipRight();
            }
        }
    }
    
    /**
     * Method name is onMousePress
     * purpose: when the mouse has been clicked in WindowController
     * @param point is location
     * @return void
     */
    
    public void onMousePress(Location point){
        timer.reset();
        if (mickeyCreated== true && drawmickey.contains(point)== true){
        }
        lastpoint=point;
    }
    
    /**
     * Method name is onMouseDrag
     * purpose: using it when the mouse has been pressed and then drag
     * @param point is location
     * @return void
     */
    
    public void onMouseDrag(Location point){
        if ( mickeyCreated && drawmickey.contains(point)){
            drawmickey.move(point.getX()-lastpoint.getX(),
                            point.getY()-lastpoint.getY());
        }
        lastpoint=point;
    }
    
    /**
     * Method name is onMouseExit
     * purpose: using it when the mouse has been pressed and then drag
     * @param point is location
     * @return void
     */
    
    public void onMouseExit(Location point){
        if (drawmickey != null)
        {
            // if mickeyCreaed is false
            mickeyCreated = false;
            // mickey remove from canvas
            drawmickey.removeFromCanvas();
        }
    }
    
    /**
     * Method name is onMouseEnter
     * purpose: using it when mouse entered in WindowController's canvas
     * @param point is location
     * @return void
     */
    
    public void onMouseEnter (Location point){
        // show the intructions
        instr1.show();
        instr2.show();
        instr3.show();
    }
    
    /**
     * Method name is onMouseRelease
     * purpose: using it when mouse entered in WindowController's canvas
     * @param point is location
     * @return void
     */
    
    public void onMouseRelease (Location point){
        // the time counter set to 1/2
        time_count = timer.elapsedMilliseconds();
    }
}