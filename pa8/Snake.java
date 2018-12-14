
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import objectdraw.ActiveObject;
import objectdraw.DrawingCanvas;
import objectdraw.FilledArc;


public class Snake extends ActiveObject implements KeyListener {
    
    // How much the Snake grows by when it eats an apple.
    private static final int GROW_BY = 1;
    
    // The number of cells the snake has left to grow.
    private int leftToGrow;
    // The diameter of each SnakeSegment.
    private int size;
    // The delay between each pause in run.
    private int delay;
    private DrawingCanvas canvas;
    // Which way the snake is going.
    private Direction currentDir;
    // Whether the game is activated or not.
    private boolean isRunning = false, paused = false;
    // The coordinate the snake needs to go to in order to grow
    private Coordinate nextApple;
    // The snake is a collection of segments.
    ArrayList<SnakeSegment> snake;
    // We need to know where the head is for apple eating and crashing
    SnakeSegment head;
    SnakeController controller;
    
    
    public Snake(Coordinate coord, int size, int delay, DrawingCanvas canvas, SnakeController controller) {
    	currentDir = Direction.RIGHT;
    	
        head = new SnakeSegment(coord, size, true, canvas);
        // create a new Array list for snake segment
        snake = new ArrayList<SnakeSegment>();
        // add snake's head
        snake.add(head);
        
        System.out.println("snake head coord x: " + coord.getX() + " y: " + coord.getY());
                
        this.delay = delay;
        this.size = size;
        this.canvas = canvas;
        this.controller = controller;
        
        start();
    }
    
    public ArrayList<Coordinate> getSnakeSegmentsCoord() {
    	ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
    	for (int i = 0; i < snake.size(); i++) {
    		coords.add(snake.get(i).getCoord());
    	}
    	return coords;
    }
    
    public int snakesize(){
        return snake.size;
    }
    
    public synchronized boolean move() {
    	int prevX = head.getCoord().getX();
    	int prevY = head.getCoord().getY();
    	boolean wasAValidMove = false;
    	for (int i = 0; i < snake.size(); i++) {
    		SnakeSegment seg = snake.get(i);
    		if (seg.isHead()) {
    	    	int x = seg.getCoord().getX();
    	    	int y = seg.getCoord().getY();
    	    	if (currentDir == Direction.UP) {
    	    		x += Direction.UP.getX() * size;
    	    		y += Direction.UP.getY() * size;
    	    	} else if (currentDir == Direction.DOWN) {
    	    		x += Direction.DOWN.getX() * size;
    	    		y += Direction.DOWN.getY() * size;
    	    	} else if (currentDir == Direction.LEFT) {
    	    		x += Direction.LEFT.getX() * size;
    	    		y += Direction.LEFT.getY() * size;
    	    	} else if (currentDir == Direction.RIGHT) {
    	    		x += Direction.RIGHT.getX() * size;
    	    		y += Direction.RIGHT.getY() * size;
    	    	}
    	    	System.out.println("canvas width: " + canvas.getWidth() + " canvas height: " + canvas.getHeight() + " x: " + x + " y: " + y + " size: " + size);
    	    	if (x >= 0 && x <= 400 && y >= 0 && y <= 400) {
    	    		head.setCoord(x, y);
    	    		head.moveTo();;
    	    		wasAValidMove = true;
    	    	} else {
    	    		return false;
    	    	}
    		} else {
    			int currX = seg.getCoord().getX();
    			int currY = seg.getCoord().getY();
    			seg.setCoord(prevX, prevY);
    			seg.moveTo();
    			prevX = currX;
    			prevY = currY;
    		}
    	}
    	return wasAValidMove;
    }
    //
    public void run(){
    	while (true){
    		if (paused) {
    			boolean wasValidMove = move();
    			System.out.println("was valid: " + wasValidMove);
                
    		}
    		pause(delay);
    	}
    }
    
    
    
  

    
    public boolean getPause(){
        return paused;
    }
    
    public synchronized void setNextApple(Coordinate appleCoord) {
    	nextApple = appleCoord;
    }
    
    public synchronized void toggleIsRunning() {
    	paused = !paused;
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            currentDir = Direction.LEFT;
            System.out.println("LEFT");
        } else if (key == KeyEvent.VK_RIGHT) {
            currentDir = Direction.RIGHT;
            System.out.println("RIGHT");
        } else if (key == KeyEvent.VK_UP) {
            currentDir = Direction.UP;
            System.out.println("UP");
        } else if (key == KeyEvent.VK_DOWN) {
            currentDir = Direction.DOWN;
            System.out.println("DOWN");
        }
    }

    public void keyReleased(KeyEvent e){
    	// do nothing
    }
    
    public void keyTyped(KeyEvent e){
    	// do nothing
    }
    
    private class SnakeSegment {
        // visible appearance of the snake.
        private final Color SNAKE_COLOR = Color.GREEN;
        private final Color SNAKE_OUTLINE = Color.BLACK;
        private FilledArc segment;
        // the location of each snake segment.
        private Coordinate coord;
        // head constants
        private static final double UP_ANGLE = 90 + 22.5;
        private static final double LEFT_ANGLE = 90 + UP_ANGLE;
        private static final double DOWN_ANGLE = 90 + LEFT_ANGLE;
        private static final double RIGHT_ANGLE = 90 + DOWN_ANGLE;
        private static final double HEAD_ARC_ANGLE = 360 - 45;
        private double BODY_ARC_ANGLE = 360;
        
        private boolean isHead;
        private int size;
        private DrawingCanvas canvas;
        
        public SnakeSegment(Coordinate coord, int size, boolean isHead, DrawingCanvas canvas) {
            this.coord = coord;
            this.isHead = isHead;
            this.size = size;
            this.canvas = canvas;
            init();
        }
        
        public Coordinate getCoord() {
            return coord;
        }
        
        public void setCoord(int x, int y) {
        	this.coord.set(x, y);
        }
        
        public boolean isHead() {
        	return isHead;
        }
        
        public void moveTo() {
        	if (isHead){
            	segment.setStartAngle(getStartAngle());
        	}
        	segment.moveTo(coord.getX(), coord.getY());
        }
        
        public void init() {
            if (isHead){
            	double startAngle = getStartAngle();
            	segment = new FilledArc(coord.getX(), coord.getY(), size, size, startAngle, HEAD_ARC_ANGLE, canvas);
            	segment.setColor(SNAKE_COLOR);
            } else{
                segment = new FilledArc(coord.getX(), coord.getY(), size, size, 0, BODY_ARC_ANGLE, canvas);
                segment.setColor(SNAKE_COLOR);
            }
        }
        
       
        
        private double getStartAngle() {
        	if (currentDir == Direction.UP) {
        		return UP_ANGLE;
        	} else if (currentDir == Direction.LEFT) {
        		return LEFT_ANGLE;
        	} else if (currentDir == Direction.RIGHT) {
        		return RIGHT_ANGLE;
        	} else {
        		return DOWN_ANGLE;
        	}
        }
    }
}

    