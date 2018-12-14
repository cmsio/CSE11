/*
 * Name: Chakman Sio
 * Login: cs11farm
 * Date: 10 29 2015
 * File: BoardPiece.java
 * Sources of Help:  tutor, book
 * This program allow users to create boardpiece
 * your program does
 */

import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import objectdraw.DrawingCanvas;
import objectdraw.FramedRect;
import objectdraw.Location;
import objectdraw.VisibleImage;




public class BoardPiece implements Piece {

	private static final int PIECES_PER_ROW = 3; // can be changed to expand the puzzle
	private static final int PUZZLE_SPACING = 20; // num of px between PuzzlePieces
	private static final int PUZZLE_OFFSET = 355; // offset from left side of canvas
	private static final int BOARD_MARGIN_X = 25; // left margin of the board
	private static final int BOARD_MARGIN_Y = 40; // top margin of the board
	private static final int SIDE_LENGTH = 100; // side length of each Piece
    private static final int HALF = 2;
    private static final int COONTINATE =1;
	private VisibleImage img;
	private int id;
	private Location center;
	private FramedRect rect;
	private FramedRect inset;
    private static final int SIZE = 50;
	
	public BoardPiece(Image img, int id, Location loc, DrawingCanvas canvas) {
	    this.img = new VisibleImage(img, loc, canvas);
	    this.img.hide();
		this.id = id;
		center = loc;
		Location upperLeft = new Location(loc.getX(), loc.getY());
		Location bottomRight = new Location(loc.getX() + SIDE_LENGTH, loc.getY() + SIDE_LENGTH);
		rect = new FramedRect(upperLeft, bottomRight, canvas);
		
		upperLeft = new Location(loc.getX() + COONTINATE, loc.getY() + COONTINATE);
		bottomRight = new Location(loc.getX() + SIDE_LENGTH - COONTINATE, loc.getY() + SIDE_LENGTH - COONTINATE);
		inset = new FramedRect(upperLeft, bottomRight, canvas);
		inset.setColor(Color.GREEN);
		inset.hide();
		
		center = new Location(loc.getX() + (SIDE_LENGTH / HALF), loc.getY() + (SIDE_LENGTH / HALF));
	}
	
	@Override
	public void showHighlight(Color color) {
		inset.show();
        rect.show();
        rect.setColor(color);
        
	}

	@Override
	public void hideHighlight() {
		rect.hide();
		inset.hide();
	}

	@Override
	public void show() {
		img.show();
	}

	@Override
	public void hide() {
		//do nothing unless doing extra credit
	}

	@Override
	public boolean contains(Location point) {
		if (point.getX() >= center.getX() - SIZE && point.getX() <= center.getX() + SIZE &&
			point.getY() >= center.getY() - SIZE && point.getY() <= center.getY() + SIZE){
			System.out.println("it is contained");
			return true;
		}
		System.out.println("it is not contained");
		return false;
	}
    
    

	@Override
	public Location getCenter() {
		return center;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void move(double dx, double dy) {
		//do nothing unless doing extra credit
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.id == ((Piece) obj).getId()){
			return true;
		}
		return false;
	}
    
    

}