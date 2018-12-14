/*
 * Name: ChakMan Sio
 * Login: cs11farm
 * Date: 11 29,2015
 * File: CSE11_Line.java
 * Source of Help: tutor, website
 * This program extends Shaps
 */


import java.awt.Color;

import objectdraw.DrawingCanvas;


public class CSE11_Line extends Shape{
    private Point start;
    private Point end;

    public CSE11_Line(){
        this.start = new Point (0,0);
        this.end = new Point(0,0);
    }

    public CSE11_Line(Point start, Point end){
        this.start = new Point (start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    public CSE11_Line(CSE11_Line line){
        setStart(new Point(line.getStart()));
        setEnd(new Point(line.getEnd()));
    }

    @Override
    public String toString(){
        return super.getName() + ":" + getStart().toString() + " to " + getEnd().toString();
    }

    @Override
    public boolean equals( Object o ){
    	if (!(o instanceof CSE11_Line)) {
    		return false;
    	}
    	if (o == this) {
    		return true;
    	}
    	CSE11_Line line = (CSE11_Line) o;
    	return this.getStart().equals(line.getStart()) 
    			&& this.getEnd().equals(line.getEnd()); 
    }


    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

    public void draw(DrawingCanvas canvas, Color c, boolean fill){
    
    }

    public Point getStart() {
        return this.start;
    }
    
    private void setStart (Point p) {
        this.start = p;
    }

    public Point getEnd() {
    	return this.end;
    }

    private void setEnd (Point p){
        this.end = p;
    }

	@Override
	public void move(int xDelta, int yDelta) {
		this.getStart().move(xDelta, yDelta);
		this.getEnd().move(xDelta, yDelta);
	}



}
