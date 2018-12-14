/*
 * Name: Chakman Sio
 * Login: cs11farm
 * Date: 10 29 2015
 * File: PuzzlePiece.java
 * Sources of Help:  tutor, book
 * This program allow users to create pieces
 */

import java.awt.Color;
import java.awt.Image;

import objectdraw.DrawingCanvas;
import objectdraw.FramedRect;
import objectdraw.Location;
import objectdraw.VisibleImage;

public class PuzzlePiece implements Piece{
	private static final int SIDE_LENGTH = 100; // side length of each Piece
    private static final int HALF = 2;
	private VisibleImage img;
	private int id;
	private FramedRect rect;
	
	public PuzzlePiece(Image img, int id, Location loc, DrawingCanvas canvas) {		
		this.img = new VisibleImage(img, loc, canvas);
		this.id = id;
		Location upperLeft = new Location(loc.getX(), loc.getY());
		Location bottomRight = new Location(loc.getX() + SIDE_LENGTH, loc.getY() + SIDE_LENGTH);
		rect = new FramedRect(upperLeft, bottomRight, canvas);
	}

    public void showHighlight(Color color) {
		rect.show();
	}

	
	public void hideHighlight() {
		rect.hide();
	}

	
	public void show() {
		img.show();
		rect.show();
	}

	
	public void hide() {
		this.img.hide();
		this.hideHighlight();
	}

	
	public boolean contains(Location point) {
		if (this.img.contains(point)) {
			return true;
		}
		return false;
	}

	
	public Location getCenter() {
		Location center = new Location(img.getLocation().getX() + (SIDE_LENGTH / HALF), img.getLocation().getY() + (SIDE_LENGTH / HALF));
		System.out.println("puzzle piece id: " + id + " center: x:" + center.getX() + " y: " + center.getY());
		return center;
	}

	
	public int getId() {
		return id;
	}

	
	public void move(double dx, double dy) {
        
		this.rect.move(dx, dy);
		this.img.move(dx, dy);
        
	}
}