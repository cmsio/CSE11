/*
 * Name: ChakMan Sio
 * Login: cs11farm
 * Date: 11 29,2015
 * File: Triangle.java
 * Source of Help: tutor, website
 */

import java.awt.Color;

import objectdraw.DrawingCanvas;

/**
 * This class is for drawing the Triangle
 */

public class Triangle extends Shape{

    private Point p1;
    private Point p2;
    private Point p3;

    /**
      * Method name is Triangle
      * purpose: 
      * @param no parameter
      * @return void
      */

    public Triangle(){
        this.p1 = new Point(0,0);
        this.p2 = new Point(0,0);
        this.p3 = new Point(0,0);
    }


    /**
      * Method name is Triangle
      * purpose: 
      * @param p1, p2, and p3 are Point
      * @return void
      */

    public Triangle(Point p1, Point p2, Point p3) {
    	super("Triangle");
    	this.p1 = new Point(p1.getX(), p1.getY());
    	this.p2 = new Point(p2.getX(), p2.getY());
    	this.p3 = new Point(p3.getX(), p3.getY());
    }

    /**
      * Method name is Triangle
      * purpose: 
      * @param tri is Triangle
      * @return void
      */

    public Triangle(Triangle tri){
    	super("Triangle");
    	this.p1 = new Point(tri.getP1().getX(), tri.getP1().getY());
    	this.p2 = new Point(tri.getP2().getX(), tri.getP2().getY());
    	this.p3 = new Point(tri.getP3().getX(), tri.getP3().getY());
    }
    

    /**
      * Method name is move
      * purpose: 
      * @param xDelta and yDelta are int
      * @return void
      */

    public void move (int xDelta, int yDelta) {
    	this.p1.move(xDelta, yDelta);
    	this.p2.move(xDelta, yDelta);
    	this.p3.move(xDelta, yDelta);
    }

    /**
      * Method name is toString
      * purpose: 
      * @param no parameter
      * @return void
      */

    @Override
    public String toString(){
        return "" + super.getName() + ": " + getP1().toString() + ", "+ getP2().toString() + ", "     + getP3().toString();
    }

    /**
      * Method name is equals
      * purpose: 
      * @param o Object
      * @return true and false
      */
   
    @Override
    public boolean equals(Object o){
    	if (!(o instanceof Triangle)) {
    		return false;
    	}
    	if (o == this) {
    		return true;
    	}
    	Triangle tri = (Triangle) o;
    	return this.getP1().equals(tri.getP1())
    			&& this.getP2().equals(tri.getP2())
    			&& this.getP3().equals(tri.getP3());
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
      * purpose: 
      * @param canvas is DrawingCanvas, c is Color, fill is boolean
      * @return void
      */
    public void draw (DrawingCanvas canvas, Color c, boolean fill) {
    	
    }


    public Point getP1() {
        return p1;
    }

    private void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2(){
        return p2;
    }

    private void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP3() {
        return p3;
    }

    private void setP3(Point p3) {
        this.p3 = p3;
    }

}
