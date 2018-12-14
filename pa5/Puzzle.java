

import java.awt.Color;
import java.awt.Image;

import objectdraw.*;


/**
 *  Class name is Puzzle, and it allows user to drag puzzle
 */

public class Puzzle extends WindowController {
	
	private static final int PIECES_PER_COL = 3; // can be changed to expand the puzzle
	private static final int PIECES_PER_ROW = 3; // can be changed to expand the puzzle
	private static final int PUZZLE_SPACING = 20; // num of px between PuzzlePieces
	private static final int PUZZLE_OFFSET = 355; // offset from left side of canvas
	private static final int BOARD_MARGIN_X = 25; // left margin of the board
	private static final int BOARD_MARGIN_Y = 40; // top margin of the board
	private static final int SIDE_LENGTH = 100; // side length of each Piece
	private static final int PUZZLE_SIZE = 9;
    private static final int CANVAS_WIDTH = 735;
    private static final int CANVAS_HEIGHT = 380;
    private static final int XCOORDINATE = 375;
    private static final int YCOORDINATE = 140;
    private static final int FRONTSIZE = 50;
    private BoardPiece[] boardPieces;
    private PuzzlePiece[] puzzlePieces;
    private static final int MIN = 0;
    private static final int MAX = 8;
    private Piece puzzlePiece;
    private boolean[] repeat = {false,false,false,false,false,false,false,false,false};
    private Image[] images = new Image [9];
    private int[] check;
    private boolean[] win_lost = new boolean [9];
    private boolean win_lost_again;
    private Text text;

    
    
    public static void main(String[] args) {
        new Acme.MainFrame(new Puzzle(), args, CANVAS_WIDTH, CANVAS_HEIGHT);    	
    }
    
    
    public PuzzlePiece[] getPuzzlePieces() {
        RandomIntGenerator ran = new RandomIntGenerator (MIN, MAX);
        
    	PuzzlePiece[] puzzlePiece = new PuzzlePiece[PUZZLE_SIZE];
        for (int i = 0; i < PUZZLE_SIZE; i++){
            images[i] = getImage("p" + i + ".jpg");
        }
        
    	for (int i = 0; i < PUZZLE_SIZE; i++){
    		Location location = getPuzzlePieceLocation(i);
            
            int random;
            while(true)
            {
                random = ran.nextValue();
                if(repeat[random] == false) {
                repeat [random]= true;
                    break;
                }
                

            }
            puzzlePiece[i] = new PuzzlePiece(images[random], random, location, canvas);
            
           }
         return puzzlePiece;
    }
   
        
    public BoardPiece[] getBoardPieces() {
    	BoardPiece[] boardPieces = new BoardPiece[PUZZLE_SIZE];
    	for (int i = 0; i < PUZZLE_SIZE; i++){
    		Image image = getImage("p" + i + ".jpg");
    		Location location = getBoardPieceLocation(i);
    		boardPieces[i] = new BoardPiece(image, i, location, canvas);
    	}
		return boardPieces;
	}
    
    private static Location getBoardPieceLocation(int i){
    	int x = BOARD_MARGIN_X + SIDE_LENGTH * (i % PIECES_PER_COL);
    	int y = BOARD_MARGIN_Y + SIDE_LENGTH * (i / PIECES_PER_ROW);
    	return new Location(x, y);
    }

    private static Location getPuzzlePieceLocation(int i){
    	int x = PUZZLE_OFFSET + PUZZLE_SPACING * (i % PIECES_PER_COL + 1) + SIDE_LENGTH * (i % PIECES_PER_COL);
    	int y = PUZZLE_SPACING * (i / PIECES_PER_ROW + 1) + SIDE_LENGTH * (i / PIECES_PER_ROW);
    	return new Location(x, y);
    }
    
    
    
    /**
     * Method name is being
     * purpose: using it to click in the Windowcontroller
     * @param point, the type is location
     * @return void
     */
    

	public void begin(){
        text = new Text("YOU WIN!!!",XCOORDINATE , YCOORDINATE , canvas);
        text.setColor(Color.GREEN);
        text.setFontSize(FRONTSIZE);
        text.setBold(true);
        
    	boardPieces = getBoardPieces();
    	puzzlePieces = getPuzzlePieces();
    	
    	for (int i = 0; i < boardPieces.length; i++){
    		boardPieces[i].hide();
            text.hide();
    	}
    }
    
    /**
     * Method name is onMousePress
     * purpose: using it to test the lastpoint in the puzzle
     * @param point, the type is location
     * @return void
     */
    
    public void onMousePress(Location point){
    	
    	lastPoint = point;
    	for (int i = 0; i < puzzlePieces.length; i++){
    		if (puzzlePieces[i].contains(point)){
    			puzzlePiece = puzzlePieces[i];
    		}
            
    	}
    }
    Location lastPoint = new Location(0, 0);
    
    
    /**
     * Method name is onMouseDrag
     * purpose: using it to drag puzzles
     * @param point, the type is location
     * @return void
     */
    
    public void onMouseDrag(Location point){
        //if (!win_lost_again){
        
    	if (puzzlePiece != null) {
            double dx = point.getX() - lastPoint.getX();
            double dy = point.getY() - lastPoint.getY();
            
            if (puzzlePiece.getCenter().getX()+dx<canvas.getWidth() && puzzlePiece.getCenter().getY()+dy<canvas.getHeight() &&puzzlePiece.getCenter().getX()+dx>0 && puzzlePiece.getCenter().getY()+dy>0){
           
    		
    		puzzlePiece.move(dx, dy);
    		lastPoint = point;
            
            }
         
        }
    
    }
    
    /**
     * Method name is onMouseDrag
     * purpose: using it to drag puzzles
     * @param point, the type is location
     * @return void
     */


    public boolean win(){
        
        for (int h =0; h<PUZZLE_SIZE;h++){
            if (win_lost[h]== false){
            return false;
            }
        }
        return true;
    }

    /**
     * Method name is onMouseDrag
     * purpose: using it to drag puzzles
     * @param point, the type is location
     * @return void
     */
    
    public void onMouseRelease (Location point){
    	if (puzzlePiece == null){
    		return;
    	}
    	
        
        
for (int i = 0; i < boardPieces.length; i++){
    		boolean contains = boardPieces[i].contains(puzzlePiece.getCenter());
    		boolean equals = boardPieces[i].equals(puzzlePiece);
    		if (contains && equals){
                win_lost[i] = true;
    			boardPieces[i].show();
    			boardPieces[i].hideHighlight();
    			boardPieces[i].showHighlight(Color.GREEN);
                
                for(int j = 0; j < puzzlePieces.length; j++){
                    if(puzzlePieces[j].getId() == i){
                        
                        puzzlePieces[j].hide();
                        puzzlePieces[j].hideHighlight();
                
                    }
                }
    			
            }
        }
        
    	puzzlePiece = null;
    
    if (win()){
        win_lost_again = true;
        
        text.show();
        for (int k=0; k<PUZZLE_SIZE;k++){
            boardPieces[k].hideHighlight();
            
        }
        
    }
    }
}