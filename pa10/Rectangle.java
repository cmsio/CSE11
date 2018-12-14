import java.awt.Color;

import objectdraw.DrawingCanvas;
import objectdraw.FilledRect;
import objectdraw.FramedRect;

/*
 * Name: ChakMan Sio
 * Login: cs11farm
 * Date: 11 29,2015
 * File: Rectangle.java
 * Source of Help: tutor, website
 * This program find the location
 */

/**
 * This class is create rectangle
 */
public class Rectangle extends ARectangle{
    private int width;
    private int height;

    /**
      * Method name is Rectangle
      * purpose: 
      * @param x is int
      * @return void
      */
    public Rectangle() {
        this.width = 0;
        this.height = 0;
    }

    /**
      * Method name is Rectangle
      * purpose: 
      * @param x,y,width, and height are int 
      * @return void
      */
      
    public Rectangle(int x, int y, int width, int height){
    	super("Rectangle", x, y);
        this.setHeight(height);
        this.setWidth(width);
    }

    /**
      * Method name is Rectangle
      * purpose: 
      * @param upperLeft is Point, width and height is int
      * @return void
      */
    public Rectangle(Point upperLeft, int width, int height) {
    	super("Rectangle", upperLeft);
        this.width = width;
        this.height = height;
    }


    /**
      * Method name is Rectangle 
      * purpose: 
      * @param r is Rectangle
      * @return void
      */
    public Rectangle(Rectangle r){
        super(r.getName(), r.getUpperLeft());
        this.setWidth(r.getWidth());
        this.setHeight(r.getHeight());
    }

    /**
      * Method name is toString
      * purpose: for make the string
      * @param no parameter
      * @return void
      */
    @Override
    // super.toString() + width + height
    public String toString(){
        return super.toString() + " Width: " + getWidth() + " Height: " + getHeight();
    }

    /**
      * Method name is equals
      * purpose: it is for comparing the object
      * @param o is Object
      * @return void
      */

    @Override
    // super.equals() + width + height
    public boolean equals(Object o){
    	if (!(o instanceof Rectangle)) {
    		return false;
    	}
    	if (o == this) {
    		return true;
    	}
    	Rectangle rect = (Rectangle) o;
    	return super.equals(o) 
    			&& this.getWidth() == rect.getWidth() 
    			&& this.getHeight() == rect.getHeight();  
    }



    /**
      * Method name is draw
      * purpose: 
      * @param canvas is DrawingCanvas, c is Color, fill is boolean
      * @return void
      */

    @Override
    public void draw (DrawingCanvas canvas, Color c, boolean fill){
        if(fill){
            FilledRect newFilledRect = new FilledRect(super.getUpperLeft().getX(), 
            		super.getUpperLeft().getY(), this.getWidth(), this.getHeight(), canvas);
            newFilledRect.setColor(c);
        } else{
            FramedRect newFramedRect = new FramedRect(super.getUpperLeft().getX(), 
            		super.getUpperLeft().getY(), this.getWidth(),this.getHeight(), canvas);
            newFramedRect.setColor(c);
        }
     }

     /**
      * Method name is getWidth
      * purpose: get the width
      * @param no parameter
      * @return void
      */

    public int getWidth(){
        return this.width;
    }

    /**
      * Method name is setWidth
      * purpose: set the Width
      * @param w is int
      * @return void
      */
    private void setWidth(int w){
        this.width = w;
    }

    /**
      * Method name is getHeight
      * purpose: get the height
      * @param no parameter
      * @return void
      */

    public int getHeight(){
        return this.height;
    }


    /**
      * Method name is setHeight
      * purpose: set the height
      * @param h is int
      * @return void
      */

    private void setHeight(int h){
        this.height = h;
    }

}
