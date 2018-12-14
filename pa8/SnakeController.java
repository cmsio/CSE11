/*
 * Name: ChakMan Sio
 * Login: cs11farm
 * Date: 11 18, 2015
 * File: SnakeController.java
 * Sources of Help: tutor, website
 * This Controller allow users to control the snake class
   and create the apple.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objectdraw.DrawingCanvas;
import objectdraw.FilledRect;
import objectdraw.Text;
import objectdraw.WindowController;

/**
 * This class allow users to control the snake class
   and create an apple
 */

public class SnakeController extends WindowController implements ActionListener, KeyListener {

    private static final int Y_PADDING = 50;
    private static final int LINUX_MIGHT_HAVE_THIS_EXTRA_WEIRD_PADDING = 6;
    private static final int MIN_DIM = 500;
    private static final int MAX_DIM = 800;
    private static final int MAX_SPEED = 100;
    private static final int MIN_SPEED = 1000;
    private static final int MIN_SIZE = 20;
    private static final int MAX_SIZE = 400;
    private static final int NUM_TOP_COLUMNS = 2;
    private static final int NUM_TOP_ROWS = 1;
    
    private static int dimensions;
    private static int segmentSize;
    private static int game_delay;
    
    private int size;
    private int delay;
    private int score;
    private int highScore;
    
    private JLabel scoreLabel;
    private JLabel highScoreLabel;
    
    private JButton newgame;
    private Text gameOverText;
    private Text winText;
    private Text pauseText;
    
    private boolean gameOver;
    private boolean won;
    private boolean paused;
    
    private Snake snake;
    
    private FilledRect apple;
    
    private Random randomIndexGenerator = new Random();
    
    private int appleCoodIndex;
    private JLabel score_text1,score_text2;
    
    private int numRow;
    private int numCol;
    
    private ArrayList<Coordinate> openSpaces;
    
    public SnakeController(String [] args) {
    	dimensions = Integer.parseInt(args[0]);
    	size = Integer.parseInt(args[1]);
    	delay= Integer.parseInt(args[2]);
    	openSpaces = new ArrayList<Coordinate>();
    }
    
    /**
     * Method name is main
     * @param args is String[]
     * @return void
     */
    
    public static void main (String[] args){
    	validateArgs(args);
        new Acme.MainFrame(new SnakeController(args), args, dimensions, dimensions + Y_PADDING);
    }
    
    private static void validateArgs(String[] args){
    	if (args.length < 3) {
    		System.out.println(PA8Strings.USAGE);
    		System.exit(1);
    	}
    	dimensions = Integer.parseInt(args[0]);
    	if (dimensions < MIN_DIM || dimensions > MAX_DIM) {
    		System.out.printf(PA8Strings.OUT_OF_RANGE, dimensions, MIN_DIM, MAX_DIM);
    		System.exit(1);
    	}
    	segmentSize = Integer.parseInt(args[1]);
    	if (segmentSize < MIN_SIZE || segmentSize > MAX_SIZE) {
    		System.out.printf(PA8Strings.OUT_OF_RANGE, segmentSize, MIN_SIZE, MAX_SIZE);
    		System.exit(1);
    	}
    	if (dimensions % segmentSize != 0){
    		System.out.printf(PA8Strings.SEG_DOES_NOT_FIT, segmentSize, dimensions, dimensions);
    		System.exit(1);
    	}
    	if ((dimensions / segmentSize) > (dimensions / 2)) {
    		System.out.printf(PA8Strings.SEG_TOO_LARGE, segmentSize, dimensions, dimensions);
    	}
    	game_delay = Integer.parseInt(args[2]);
    	if (game_delay < MAX_SPEED && game_delay > MIN_SPEED) {
    		System.out.printf(PA8Strings.USAGE, dimensions, MIN_DIM, MAX_DIM);
    		System.exit(1);
    	}
    }
    
    public void begin() {
    	
    	// ------------------------------- UI Stuff ------------------------------- //
        JPanel upperpanel = new JPanel();
        JPanel lowerpanel = new JPanel();
        score_text1 = new JLabel("Score： " + score);
        score_text2 = new JLabel("High Score： " + highScore);
        
        JPanel up_text = new JPanel();
        upperpanel.setLayout(new GridLayout(1,1));
        upperpanel.add(score_text1);
        upperpanel.add(score_text2);
        //lowerpanel.setLayout(new BorderLayout());
        
        newgame= new JButton("New Game");
        lowerpanel.add(newgame);
        upperpanel.add(up_text);
        Container contentPane = getContentPane();
        contentPane.add(upperpanel, BorderLayout.NORTH);
        contentPane.add(lowerpanel, BorderLayout.SOUTH);
        contentPane.validate();
        contentPane.setBounds(0, 0, dimensions, dimensions);
        System.out.println(canvas.getWidth() + " " + canvas.getHeight());
        
        gameOverText = new Text("GAME OVER", 0,0, canvas);
        gameOverText.setFontSize(40);
        gameOverText.moveTo(dimensions/2-gameOverText.getWidth()/2,dimensions/2-gameOverText.getHeight()/2);
        gameOverText.hide();
        
        winText = new Text("YOU WON!", 0,0, canvas);
        winText.setFontSize(40);
        winText.moveTo(dimensions/2-winText.getWidth()/2,dimensions/2-winText.getHeight()/2);
        winText.hide();
        
        pauseText = new Text("PAUSE", 0,0, canvas);
        pauseText.setFontSize(40);
        pauseText.moveTo(dimensions/2-pauseText.getWidth()/2,dimensions/2-pauseText.getHeight()/2);
        pauseText.hide();
        
        // ------------------------------- App State ------------------------------- //
        numCol = numRow = dimensions / size;
       
        
//        System.out.println(canvas.getWidth());
//        apple = new FilledRect(1 * size, 1 * size, size, size, canvas);
//        apple.setColor(Color.RED);
        
        // call the snake's constructor
        snake = new Snake(new Coordinate(size, size), size, delay, canvas, this);
        buildAllPossibleCoordinates();
        snake.setNextApple(placeApple(canvas));
        canvas.addKeyListener(snake);
        canvas.addKeyListener(this);
        //snakesegment = new SnakeSegment(coord, size, isHead,canvas);
        canvas.requestFocusInWindow();
    }

    
    public void	actionPerformed(ActionEvent e){
        if (e.getSource() == newgame) {
            System.out.println("Hello World");
            score = 0;
            highScore = 0;
            updatescore(0);
            highscore(0);
        }
        canvas.requestFocusInWindow();
    }
    
    public Coordinate placeApple(DrawingCanvas canvas){
    	int randCoordinateIndex = randomIndexGenerator.nextInt(openSpaces.size());
    	System.out.println("open space size: " + openSpaces.size() + " rand index: " + randCoordinateIndex);
    	Coordinate appleCoord = openSpaces.get(randCoordinateIndex);
    	
    	apple = new FilledRect(appleCoord.getX(), appleCoord.getY(), size, size, canvas);
    	apple.setColor(Color.RED);
    	return appleCoord;
    }
    
    private void buildAllPossibleCoordinates() {
    	System.out.println("built all possible open spaces");
    	for (int i = 0; i < numRow; i++) {
    		for (int j = 0; j < numCol; j++) {
    			openSpaces.add(new Coordinate(i * size, j * size));
    		}
    	}
    	ArrayList<Coordinate> snakeSegCoords = snake.getSnakeSegmentsCoord();
    	for (int i = 0; i < snakeSegCoords.size(); i++) {
    		openSpaces.remove(snakeSegCoords.get(i));
    	}
    }
    
    private void updatescore(int Update_Score) {
        score += Update_Score;
        score_text1 = new JLabel("Score： " + score);
    }
    
    private void highscore(int High_Score) {
        highScore += High_Score;
        score_text2 = new JLabel("High Score： " +highScore);
    }
    
    
    
    public void keyPressed(KeyEvent e){
    	if (e.getKeyCode() == KeyEvent.VK_SPACE) {
    		System.out.println("space is pressed");
    		snake.toggleIsRunning();
            if (snake.getPause() == false){
            pauseText.show();
            
    	}
            else if (snake.getPause() == true){
                pauseText.hide();
            }
        
        }
    }
    
    public void setscore (){
        
        
    }
    
    
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
}