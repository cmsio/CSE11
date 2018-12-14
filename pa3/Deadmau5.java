import objectdraw.*;
import java.awt.*;

/**
 * This class create mickey figure, and flip, rotate the mickey
 */

public class Deadmau5 {
    // this one is for draw the mickey
    private FilledOval leftEar, rightEar, face, leftEye, rightEye;
    private FilledArc mouth;
    private static final int FACE_RADIUS = 50;
    private static final int EAR_RADIUS = 40;
    private static final int EYE_RADIUS = 15;
    private static final int MOUTH_RADIUS = 40;
    private static final int EAR_OFFSET = 50;
    private static final int EYE_OFFSET = 20;
    private static final int ANGLE = 180;
    private static final int STARTANGLE = 180;
    private static final int widthface = 2*FACE_RADIUS;
    private static final int heightface = 2* FACE_RADIUS;
    private static final int widthear = 2*EAR_RADIUS;
    private static final int heightear = 2*EAR_RADIUS;
    private static final int halfeye = 2*EYE_RADIUS;
    private static final int halfmouse = 2*MOUTH_RADIUS;
    private static final int positivedistance = 100;
    private static final int negativedistance = -100;
    private static final int negative_y_distance = 100;
    private static final int ydistance = 0;
    private static final int half = 2;
    private static final int degree1 = 0;
    private static final int degree2 = 90;
    private static final int degree3 = 180;
    private static final int degree4 = 270;
    private static final int degree5 = 360;
    private static final int coordinate1 = 40;
    private static final int coordinate2 = 60;
    private static final int coordinate3 = 100;
    private Location lastpoint;
    private boolean circleGrabbed;
    private boolean checkdirection = false;
    private boolean faceup = false;
    private boolean facedown = false;
    private boolean faceright = false;
    private boolean faceleft = false;
    private DrawingCanvas canvas;
    
    /**This contructor is for drawing the mickey figure
     * @parm point, type is location
     * @parm canvas, type is drawingCanvas
     * @return void
     */
    
    public Deadmau5( Location point, DrawingCanvas canvas){
        // draw mickey
        face = new FilledOval
        (point.getX()- FACE_RADIUS, point.getY()-
         FACE_RADIUS, widthface, heightface, canvas);
        leftEar = new FilledOval
        (point.getX()-EAR_OFFSET-EAR_RADIUS, point.getY()-
         FACE_RADIUS-EAR_RADIUS, widthear, heightear, canvas);
        rightEar = new FilledOval
        (point.getX()+EAR_OFFSET-EAR_RADIUS, point.getY()-
         FACE_RADIUS-EAR_RADIUS, widthear, heightear, canvas);
        leftEye = new FilledOval
        (point.getX()-EYE_OFFSET-EYE_RADIUS, point.getY()
         -EYE_OFFSET-EYE_RADIUS, halfeye, halfeye, canvas);
        leftEye.setColor (Color.WHITE);
        rightEye = new FilledOval
        (point.getX()+EYE_OFFSET-EYE_RADIUS, point.getY()-
         EYE_OFFSET-EYE_RADIUS, halfeye, halfeye, canvas);
        rightEye.setColor (Color.WHITE);
        mouth = new FilledArc
        (point.getX()-MOUTH_RADIUS, point.getY()-MOUTH_RADIUS,
         halfmouse, halfmouse,STARTANGLE, ANGLE, canvas);
        mouth.setColor (Color.WHITE);
        faceup = true;
    }
    
    /** This method determine whether contains the point
     * @parm point, type is location
     * @return true or false
     */
    
    public boolean contains( Location point ) {
        // checking whether face, leftEar and rightEar contain point
        if (face.contains(point) || leftEar.contains(point)
            || rightEar.contains(point)){
            // if yes retrun true
            return true;
        }
        else{
            // if no return false
            return false;
        }
    }
    
    /** This method determine whether contains the point
     * @parm point, type is location
     * @return true or false
     */
    
    public boolean inFace (Location point){
        // test whether the point in the face
        if (face.contains(point)){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    /** This method determine whether contains the point
     * @parm point, type is location
     * @return true or false
     */
    
    public boolean inLeftEar( Location point ) {
        // test whether the point in the left ear
        if (leftEar.contains(point)){
            return true;
        }
        else{
            return false;
        }
    }
    
    /** This method determine whether contains the point
     * @parm point, type is location
     * @return true or false
     */
    
    public boolean inRightEar( Location point ) {
        if (rightEar.contains(point)){
            return true;
        }
        else{
            return false;
        }
    }
    
    /** This method control the movenment
     * @parm dy, type is double
     * @parm dx, type is double
     * @return true or false
     */
    
    public void move( double dx, double dy) {
        leftEar.move(dx,dy);
        face.move( dx,dy);
        rightEar.move(dx,dy);
        leftEye.move(dx,dy);
        rightEye.move( dx,dy);
        mouth.move(dx,dy);
    }
    
    /** This method is for remove from canvas
     * @return true or false
     */
    
    public void removeFromCanvas() {
        // remove all the part in the canvas
        face.hide();
        leftEar.hide();
        rightEar.hide();
        mouth.hide();
        leftEye.hide();
        rightEye.hide();
    }
    
    /** This method is for flip the face to left
     * @return void
     */
    
    public void flipLeft() {
        move (negativedistance, ydistance);
        flip ();
    }
    
    /** This method is for flip the face to right
     * @return void
     */
    
    public void flipRight() {
        move (positivedistance ,ydistance);
        flip();
    }
    
    /** This method is for flip the face
     * @return void
     */
    
    public void flip() {
        // check the direction is back or front
        // if it is false, the direction change to front
        if (checkdirection){
            leftEar.sendToBack();
            face.sendToBack();
            rightEar.sendToBack();
            // if the direction
            checkdirection = false;
        }
        
        else {
            // if the direction is true, the direction change to back
            checkdirection = true;
            leftEar.sendToFront();
            face.sendToFront();
            rightEar.sendToFront();
        }
    }
    
    
    /** This method is for rotate the face to left
     * @parm point, type is location
     * @return true or false
     */
    
    public void flipLeftEC(){
        // if the face is up, we move the distance, and change the color
        if (faceup){
            leftEar.move (ydistance ,ydistance );
            rightEar.move (negativedistance, negativedistance);
            face.move(ydistance, negativedistance);
            rightEye.move (negativedistance+coordinate2,negativedistance);
            leftEye.move (negativedistance+coordinate3,
                          negativedistance+coordinate1);
            mouth.move (ydistance, negativedistance);
            // rotate the angle
            mouth.setStartAngle(degree4);
            // change the color to black
            face.setColor (Color.BLUE);
            leftEar.setColor (Color.BLUE);
            rightEar.setColor (Color.BLUE);
            faceleft = true;
            faceup = false;
        }
        
        // if the face is left, we move the distance, and change the color
        else if (faceleft){
            leftEar.move (ydistance ,ydistance );
            rightEar.move (negativedistance, positivedistance);
            face.move (negativedistance, ydistance );
            rightEye.move (negativedistance+coordinate1,
                           positivedistance-coordinate2);
            leftEye.move (negativedistance,ydistance);
            mouth.move (negativedistance,ydistance);
            mouth.setStartAngle(degree5);
            // change the color to green
            face.setColor (Color.GREEN);
            leftEar.setColor (Color.GREEN);
            rightEar.setColor (Color.GREEN);
            facedown = true;
            faceleft = false;
        }
        
        //if the face is down, we move the distance, and change the color
        else if (facedown){
            // move the distance
            leftEar.move (ydistance ,ydistance );
            rightEar.move (positivedistance, ydistance);
            rightEar.move (ydistance, positivedistance);
            face.move (ydistance, positivedistance);
            rightEye.move (ydistance,positivedistance);
            leftEye.move(positivedistance-coordinate2,
                         positivedistance-coordinate1);
            mouth.move (ydistance,positivedistance);
            mouth.setStartAngle(degree2);
            // change the color to red
            face.setColor (Color.RED);
            leftEar.setColor (Color.RED);
            rightEar.setColor (Color.RED);
            facedown = false;
            faceright = true;
        }
        
        // if the face is right, we move the distance, and change the color
        else if (faceright){
            // move the distance
            leftEar.move (ydistance ,ydistance);
            rightEar.move (positivedistance, negativedistance);
            face.move (positivedistance, ydistance);
            rightEye.move (positivedistance,ydistance-coordinate1);
            leftEye.move (positivedistance-coordinate1, ydistance);
            mouth.move (positivedistance, ydistance);
            mouth.setStartAngle(degree3);
            // change to color to black
            face.setColor (Color.BLACK);
            leftEar.setColor (Color.BLACK);
            rightEar.setColor (Color.BLACK);
            faceright = false;
            faceup = true;
        }
    }
    
    public void flipRightEC(){
        // if the face is up, we move the distance, and change the color
        if (faceup){
            rightEar.move (ydistance ,ydistance);
            leftEar.move (positivedistance, negativedistance);
            face.move(ydistance, negativedistance);
            leftEye.move (positivedistance-coordinate2,negativedistance);
            rightEye.move (positivedistance-coordinate3,
                           negativedistance+coordinate1);
            mouth.move (ydistance, negativedistance);
            mouth.setStartAngle(degree2);
            // change the color to blue
            face.setColor (Color.BLUE);
            leftEar.setColor (Color.BLUE);
            rightEar.setColor (Color.BLUE);
            faceup = false;
            faceright = true;
        }
        
        // if the face is right, we move the distance, and change the color
        else if (faceright){
            rightEar.move (ydistance ,ydistance );
            leftEar.move (positivedistance, positivedistance);
            face.move (positivedistance, ydistance );
            leftEye.move (positivedistance-coordinate1,
                          positivedistance-coordinate2);
            rightEye.move (positivedistance,ydistance);
            mouth.move (positivedistance,ydistance);
            mouth.setStartAngle(degree1);
            // change the color to green
            face.setColor (Color.GREEN);
            leftEar.setColor (Color.GREEN);
            rightEar.setColor (Color.GREEN);
            faceright = false;
            facedown = true;
        }
        
        // if the face is down, we move the distance, and change the color
        else if (facedown){
            rightEar.move (ydistance ,ydistance );
            leftEar.move (negativedistance, positivedistance);
            rightEar.move (ydistance, ydistance);
            face.move (ydistance, positivedistance);
            leftEye.move (ydistance,positivedistance);
            rightEye.move (negativedistance+coordinate2,
                           positivedistance-coordinate1);
            mouth.move (ydistance,positivedistance);
            mouth.setStartAngle(degree4);
            // change the color to red
            face.setColor (Color.RED);
            leftEar.setColor (Color.RED);
            rightEar.setColor (Color.RED);
            // checking the face direction
            facedown = false;
            faceleft = true;
        }
        
        // if the face is left, we move the distance, and change the color
        else if (faceleft){
            // moving the Ear, Eye, Face, and Mouth direction
            rightEar.move (ydistance ,ydistance);
            leftEar.move (negativedistance, negativedistance);
            face.move (negativedistance, ydistance);
            leftEye.move (negativedistance,ydistance-coordinate1);
            rightEye.move (negativedistance+coordinate1, ydistance);
            mouth.move (negativedistance, ydistance);
            mouth.setStartAngle(degree3);
            // Change the color to black
            face.setColor (Color.BLACK);
            leftEar.setColor (Color.BLACK);
            rightEar.setColor (Color.BLACK);
            // checking the face direction
            faceleft = false;
            faceup = true;
        }
    }
    
    /**This method is for center the mickeymouse
     * @parm dy, type is integer
     * @parm dx, type is integer
     * @return void
     */
    
    public void flipEC(int dx, int dy){
        // this one is for center the mickey
        this.move(dx/half-face.getX()-FACE_RADIUS,dy/half
                  -face.getY()-FACE_RADIUS);              
    }
}