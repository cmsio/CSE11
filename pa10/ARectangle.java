/*
 * Name: ChakMan SIo
 * Login: cs11farm
 * Date: 11 29, 2015
 * File ARectangle.java
 * Source of Help: tutor, website
 * This program is create ARectange
 */

/**
  * This class is create ARectangle than extends Shape
  */ 

public abstract class ARectangle extends Shape{
    // Upper left corner - Common to all rectangular shapes
    private Point upperLeft;

    /**
      * Method name is ARectangle
      * purpose:
      * @param no parameter
      * @return void
      */

    public ARectangle() {
       this.upperLeft = new Point(0,0);
    }

    /**
      * Method name is ARectangle
      * purpose:
      * @param x and y are int, name is string
      * @return void
      */

    public ARectangle (String name, int x, int y){
    	super(name);
    	this.upperLeft = new Point(x, y);
    }

    /**
      * Method name is ARectangle
      * purpose:
      * @param name is string, upperLeft is Point
      * @return void
      */
    public ARectangle (String name, Point upperLeft){
    	super(name);
    	this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
    }

    /**
      * Method name is ARectangle
      * purpose:
      * @param r is ARectangle
      * @return void
      */

    public ARectangle(ARectangle r){
    	super(r.getName());
    	this.upperLeft = new Point(r.getUpperLeft().getX(), r.getUpperLeft().getY());
    }

    /**
      * Method name is move
      * purpose: move the shape
      * @param xDelta and yDelta are int
      * @return void
      */

    public void move(int xDelta, int yDelta){
        Point p1 = new Point(getUpperLeft().getX() + xDelta, getUpperLeft().getY() + yDelta);
        this.setUpperLeft(p1);
    }

    /**
      * Method name is toString
      * purpose:print the string
      * @param no parameter
      * @return void
      */

    @Override
    public String toString(){
        return this.getName() + ": Upper Left Corner: " + upperLeft.toString();
    }


    /**
      * Method name is equa;
      * purpose: compare the two oject
      * @param o is Object
      * @return void
      */

    @Override
    public boolean equals(Object o){
    	if (!(o instanceof ARectangle)) {
    		return false;
    	}
    	if (o == this) {
    		return true;
    	}
    	ARectangle aRect = (ARectangle) o;
    	return this.getUpperLeft().equals(aRect.getUpperLeft());
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
      * Methodname is getUpperLeft
      * purpose get the upperleft point
      * @param no parameter
      * @return void
      */
  
    public Point getUpperLeft(){
        return this.upperLeft;
    }

    /**
      * Method name is setUpperLeft
      * purpose set the upperleft point
      * @param: upperLeft is point
      * @return void
      */
 
    public void setUpperLeft (Point upperLeft){
        this.upperLeft = upperLeft;    
    }
}
