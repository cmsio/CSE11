/*
 * Name: ChakMan Sio
 * Login: cs11farm
 * Date: 11 29,2015
 * File: Square.java
 * Source of Help: tutor, website
 */

import java.awt.Color;

import objectdraw.DrawingCanvas;

/**
 * This class is create a square
 */

public class Square extends ARectangle{
    private int side;

    /**
      * Method name is Sqaure
      * purpose: 
      * @param no parameter
      * @return void
      */

    public Square(){
        this.side = 0;
    }

    /**
      * Method name is Square
      * purpose: 
      * @param x,y, and side are int
      * @return void
      */
    public Square(int x, int y, int side){
    	super("Square", x, y);
    	this.side = side;
    }

    /**
      * Method name is Square
      * purpose: 
      * @param upperLeft is point, side is int
      * @return void
      */
    public Square(Point upperLeft, int side) {
    	super("Square", upperLeft);
    	this.side = side;
    }

    /**
      * Method name is Square
      * purpose: 
      * @param r is Square
      * @return void
      */
    public Square(Square r){
    	super(r.getName(), r.getUpperLeft());
    	this.side = r.getSide();
    }

    /**
      * Method name is Triangle
      * purpose: 
      * @param no parameter
      * @return void
      */

    @Override
    public String toString(){
        return super.toString() + " Sides: " + this.getSide();
    }


    /**
      * Method name is equal
      * purpose: 
      * @param o is Object
      * @return void
      */

    @Override
    public boolean equals (Object o){
    	if (!(o instanceof Square)) {
    		return false;
    	}
    	if (o == this) {
    		return true;
    	}
    	Square square = (Square) o;
    	return super.equals(o) && this.getSide() == square.getSide();

    }




    /**
      * Method name is draw
      * purpose:
      * @param canvas is DrawingCanvas, c is Color, fill is boolean
      * @return void
      */

    public void draw(DrawingCanvas canvas, Color c, boolean fill) {
    	
    }

    /**
      * Method name is getSide
      * purpose: get the side
      * @param no parameter
      * @return void
      */

    public int getSide(){
        return this.side;
    }

    /**
      * Method name is setSide
      * purpose: 
      * @param no parameter
      * @return void
      */

    private void setSide(int side){
        this.side = side;
    }
}


    
