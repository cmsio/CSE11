/*
 * Name: ChakMan Sio
 * Login: cs11farm
 * Date: 11 29,2015
 * File: Circle.java
 * Source of Help: tutor, website
 * This program is for circle
 */

import java.awt.Color;

import objectdraw.DrawingCanvas;
import objectdraw.FilledOval;
import objectdraw.FramedOval;

/**
  * This class create circle that extends shape
  */

public class Circle extends Shape{

    private Point center;
    private int radius;


    /**
      * Method name is Circle
      * purpose: 
      * @param no parameter
      * @return void
      */

    public Circle(){
        this.center = new Point (0,0);
        this.radius = 0;

   }

    /**
      * Method name is Circle
      * purpose: 
      * @param center is Point, radius is int
      * @return void
      */

    public Circle (Point center, int radius){
        this.center = new Point(center.getX(), center.getY());
        this.radius = radius;
    }

    /**
      * Method name is Circle
      * purpose: 
      * @param c is Circle
      * @return void
      */

    public Circle(Circle c){
        this.center = new Point(c.getCenter().getX(), c.getCenter().getY());
        this.radius = c.radius;
    }

    /**
      * Method name is move
      * purpose: print the string
      * @param xDelta and yDelta are int
      * @return void
      */
    @Override
    public void move(int xDelta, int yDelta){
    	center.move(xDelta, yDelta);
    }

    /**
      * Method name is toString
      * purpose: print the string
      * @param no parameter
      * @return void
      */

    @Override
    public String toString(){
        return super.getName() + ": Center: " + this.getCenter().toString() + "; Radius: " + this.getRadius();
    }

    /**
      * Method name is equals
      * purpose:
      * @param c is Object
      * @return void
      */

    @Override
    public boolean equals (Object o){
    	if (!(o instanceof Circle)) {
    		return false;
    	}
    	if (o == this) {
    		return true;
    	}
    	Circle cir = (Circle) o;
    	return this.getCenter().equals(cir.getCenter()) 
    			&& this.getRadius() == cir.getRadius();
    }

    /**
      * Method name is hashCode
      * purpose: 
      * @param no parameter
      * @return void
      */

    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

    /**
      * Method name is draw
      * purpose: draw the mickey
      * @param Canvas is Drawing, c is Color, fill is boolean
      * @return void
      */
    @Override
    public void draw(DrawingCanvas canvas, Color c, boolean fill){

        if (fill){
            FilledOval circle = new FilledOval(this.getCenter().getX() - this.getRadius(),
                                this.getCenter().getY() - this.getRadius(), this.getRadius() * 2,
                                this.getRadius() * 2, canvas);
            circle.setColor(c);
        }
        else{
            FramedOval circle = new FramedOval(this.getCenter().getX() - this.getRadius(),
                                this.getCenter().getY() - this.getRadius(), this.getRadius() * 2,
                                this.getRadius() * 2, canvas);
            circle.setColor(c);
        }
    }

    /**
      * Method name is getCenter
      * purpose: get center 
      * @param: no parameter 
      * @return void
      */

    public Point getCenter(){
        return this.center;
    }

     /**
       * Method name is setCenter
       * purpose: set the center
       * @param: center is point
       * @return void
       */

    private void setCenter (Point center){
        this.center = center;
    }


    /**
      * Method name is getradius
      * purpose: get the raidus
      * @param: no parameter
      * @return void
      */

    public int getRadius(){
        return this.radius;
    }

     /**
       * Method name is setradius
       * purpose: set the radius
       * @param: setradius
       * @return void
       */

    private void setRadius(int radius){
        this.radius= radius;
    }
}


