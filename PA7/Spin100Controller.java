/*
 * Name: ChakMan Sio
 * Login: cs11farm
 * Date: 10 16, 2015
 * File: ReizablePizzaSliceController.java
 * Sources of Help: turtors, websites
 * This program allows users to create pizza and draw the line
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import objectdraw.FilledArc;
import objectdraw.Location;
import objectdraw.WindowController;

/**
 * This program allowers users create pizza in four different quadrants
 */

public class Spin100Controller extends WindowController implements ActionListener,MouseMotionListener, ChangeListener, MouseListener{
    
    private static final int FRAME_WIDTH = 840;
    private static final int FRAME_HEIGHT = 660;
    private static final int NUM_OF_IMAGES = 20;
    private static final double IMAGE_WIDTH = 185;
    private static final double IMAGE_HEIGHT = 99;
    private static final int MIN_TICKS = 50;
    private static final int WHEEL_DELAY = 1;
    private static final int WHEEL_Y_COORD = 10;
    private static final int GAME_OVER_Y_COORD = 5;
    private static final int FRONTSIZE = 24;
    private static final int HEIGHT = 150;
    private static final int WIDTH = 150;
    private static final int STARTANGLE = -15;
    private static final int ARCANGLE = 30;
  
    private JButton click_to_spin1;
    private JButton finish_player1;
    private JButton click_to_spin2;
    private JButton finish_player2;
    private JButton restart;
    
    private Spin100Wheel spin,spin1;
    private FilledArc triangle;
    private FilledArc triangle1;
    private int[] order = {50,85,30,65,10,45,70,25,90,5,100,15,80,35,60,20,40,75,55,95};
    private Image[] pics;

    private JLabel spin_text,control_text1,control_text2,instruction;
    
    private boolean hasSpinWheel = false;
    private boolean hasSpinWheel1 = false;
    private int player1Score = 0;
    private int player2Score = 0;
    
    public static void main (String[] args){
        new Acme.MainFrame( new Spin100Controller(), args, FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    public void begin(){
        JPanel upperpanel = new JPanel();
        JPanel lowerpanel = new JPanel();
        
        spin_text = new JLabel("Spin100");
        spin_text.setFont(new Font("Comic Sans MS", Font.BOLD,24));
        
        control_text1 = new JLabel("Player 1's score is " + player1Score);
        control_text2 = new JLabel("Player 2's score is " + player2Score);
        
        JPanel uppanel = new JPanel();
        JPanel uppanel1 = new JPanel();
        JPanel uppanel2= new JPanel();
       
        uppanel.add(spin_text);
        uppanel1.add (control_text1);
        uppanel2.add (control_text2);
       
        upperpanel.add (uppanel);
        upperpanel.add(uppanel1);
        upperpanel.add(uppanel2);
        
        upperpanel.setLayout(new GridLayout(3,1));
        
        click_to_spin1 = new JButton("Click to Spin P1");
        finish_player1 = new JButton("Finish Player1");
        restart = new JButton("Restart");
        click_to_spin2 = new JButton("Click to Spin P2");
        finish_player2 = new JButton("Finish Player2");
        JPanel whateverpanel = new JPanel();
        whateverpanel.add(click_to_spin1);
        whateverpanel.add(finish_player1);
        whateverpanel.add(restart);
        whateverpanel.add(click_to_spin2);
        whateverpanel.add(finish_player2);
        click_to_spin1.addActionListener(this);
        finish_player1.addActionListener(this);
        restart.addActionListener(this);
        click_to_spin2.addActionListener(this);
        finish_player2.addActionListener(this);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        
        lowerpanel.add (whateverpanel);
        Container contentPane = getContentPane();
        
        contentPane.add(lowerpanel, BorderLayout.SOUTH);
        contentPane.add(upperpanel, BorderLayout.NORTH);
        contentPane.validate();
        
        pics= new Image[NUM_OF_IMAGES];
        for(int i = 0; i < NUM_OF_IMAGES; i++){
             pics[i] = getImage("Big_Wheel-" + order[i] + ".png");
        }
        spin = new Spin100Wheel(pics, WHEEL_DELAY, new Location(117.5, WHEEL_Y_COORD), this, MIN_TICKS, canvas);
        spin1 = new Spin100Wheel(pics, WHEEL_DELAY, new Location(537.5, WHEEL_Y_COORD), this, MIN_TICKS,canvas);
        spin.setTicks(0);
        spin1.setTicks(0);
        
        triangle = new FilledArc(220, 200,150, 150, -15, 30, canvas);
        triangle.setColor (Color.RED);
        triangle1 = new FilledArc(canvas.getWidth() - 200, 200,150, 150,-15, 30, canvas);
        triangle1.setColor (Color.RED);
    }
    
    public void	actionPerformed(ActionEvent e){
        if (e.getSource() == click_to_spin1) {
        	hasSpinWheel = true;
        	hasSpinWheel1 = false;
        	spin.setTicks(MIN_TICKS);
        	spin.setDelay(WHEEL_DELAY);
        }
        else if (e.getSource() == finish_player1 ) {
        	if (hasSpinWheel) {
        		finish_player1.setEnabled(false);
        		click_to_spin1.setEnabled(false);        		
        	}
        }
        else if (e.getSource() == click_to_spin2) {
        	if (!finish_player1.isEnabled()) {       
        		hasSpinWheel = false;
        		hasSpinWheel1 = true;
        		spin1.setTicks(MIN_TICKS);
        		spin1.setDelay(WHEEL_DELAY);
        	}
        }
        else if (e.getSource() == finish_player2) {
        	if (hasSpinWheel1){
        		finish_player2.setEnabled(false);
        		click_to_spin2.setEnabled(false);
        	}
        }
        else if (e.getSource() == restart) {
        	spin.reset();
        	spin1.reset();
        	
        	hasSpinWheel = false;
        	hasSpinWheel1 = false;
        	
        	player1Score = 0;
        	player2Score = 0;
            updatePlayer1Score(0);
            updatePlayer2Score(0);
            
            finish_player1.setEnabled(true);
            click_to_spin1.setEnabled(true);
            finish_player2.setEnabled(true);
            click_to_spin2.setEnabled(true);
        }
    }
    
    public void onScoreChanged(int scoreIndex) {
    	int score = order[scoreIndex];
    	if (hasSpinWheel) {
    		updatePlayer1Score(score);
    		if (player1Score >= 100) {
    			click_to_spin1.setEnabled(false);
    			finish_player1.setEnabled(false);
    		}
    	} else if (hasSpinWheel1) {
    		updatePlayer2Score(score);
    		if (player2Score >= 100) {
    			click_to_spin2.setEnabled(false);
    			finish_player2.setEnabled(false);
    		}
    	}
    }
    
    private void updatePlayer1Score(int newScore) {
    	player1Score += newScore;
    	control_text1.setText("Player 1's score is " + player1Score);
    }
    
    private void updatePlayer2Score(int score) {
    	player2Score += score;
    	control_text2.setText("Player 2's score is " + player2Score);
    }

    public void mouseClicked (MouseEvent evt){}
    public void mousePressed (MouseEvent evt){}
    public void mouseReleased (MouseEvent evt){}
    public void mouseDragged (MouseEvent evt){}
    public void stateChanged(ChangeEvent e){}
    public void mouseEntered (MouseEvent evt){}
    public void mouseExited (MouseEvent evt){}
    public void mouseMoved (MouseEvent evt){}
    
}