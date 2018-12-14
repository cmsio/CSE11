/*
 * Name: ChakMan Sio
 * Login: cs11farm
 * Date: 11 29,2015
 * File: Point.java
 * Source of Help: tutor, website
 * This program find the location
 */


/**
 * This class is for finding the point
 */

public class Point{
	
    private int x;
    private int y;
    
    /**
      * Method name is Point
      * purpose: 
      * @param no parameter
      * @return void
      */

    public Point(){
        this.setX(0);
        this.setY(0);
    }

    /**
      * Method name is Point
      * purpose: 
      * @param x and y is int
      * @return void
      */

    public Point(int x, int y){
        this.setX(x);
        this.setY(y);
    }

    /**
      * Method name is Point
      * purpose: 
      * @param point is Point
      * @return void
      */

    public Point(Point point){
        this.setX(point.getX());
        this.setY(point.getY());
    }


    /**
      * Method name is getX
      * purpose: get the x coordinate
      * @param no parameter
      * @return void
      */

    public int getX(){
        return this.x;
    }

    /**
      * Method name is getY
      * purpose: get the Y coodinate
      * @param no parameter
      * @return void
      */

    public int getY(){
        return this.y;
    }


    /**
      * Method name is setX
      * purpose: using it when the mouse has been pressed and then drag
      * @param x is int
      * @return void
      */

    private void setX(int x){
        this.x = x;
    }

    /**
      * Method name is setY
      * purpose: 
      * @param y is int
      * @return void
      */

    private void setY(int y){
        this.y = y;
    }
    
    /**
      * Method name is move
      * purpose: 
      * @param xDelta and yDelta are int 
      * @return void
      */

    public void move(int xDelta, int yDelta){
        this.setX(this.getX() + xDelta);
        this.setY(this.getY() + yDelta);
    }

    /**
      * Method name is toString
      * purpose: it creates the string
      * @param o is Object 
      * @return true and false
      */

    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }


    /**
      * Method name is equals
      * purpose: 
      * @param o is Object 
      * @return true and false
      */

    @Override
    public boolean equals(Object o){
    	if (!(o instanceof Point)) {
    		return false;
    	}
    	if (this == o) {
    		return true;
    	}
    	Point po = (Point) o;
    	return this.getX() == po.getX() && this.getY() == po.getY();
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
}




