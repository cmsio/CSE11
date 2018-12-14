import java.awt.Image;

import objectdraw.ActiveObject;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.RandomDoubleGenerator;
import objectdraw.VisibleImage;

public class Spin100Wheel extends ActiveObject {
    
    private static final double IMAGE_HEIGHT = 99;
    private static final int MAX = 1;
    private static final int MIN = 0;
    private Image[] pics;
    private int delay;
    private int minTicks;
    private VisibleImage p1, p2, p3, p4, p5;
    private int index0 = 2;
    private int index1 = 1;
    private int index2 = 0;
    private int index3 = 19;
    private int index4 = 18;
    
    private int totalTicks;
    private Spin100Controller controller;
    private RandomDoubleGenerator ran;

    public Spin100Wheel(Image[] pics, int delay, Location topNumLoc,
                        Spin100Controller controller, int minTicks, DrawingCanvas canvas) {
        
        this.p1 = new VisibleImage(pics[index0], topNumLoc.getX(), topNumLoc.getY() + IMAGE_HEIGHT * 0, canvas);
        this.p2 = new VisibleImage(pics[index1], topNumLoc.getX(), topNumLoc.getY() + IMAGE_HEIGHT * 1, canvas);
        this.p3 = new VisibleImage(pics[index2], topNumLoc.getX(), topNumLoc.getY() + IMAGE_HEIGHT * 2, canvas);
        this.p4 = new VisibleImage(pics[index3], topNumLoc.getX(), topNumLoc.getY() + IMAGE_HEIGHT * 3, canvas);
        this.p5 = new VisibleImage(pics[index4], topNumLoc.getX(), topNumLoc.getY() + IMAGE_HEIGHT * 4, canvas);
        
        this.controller = controller;
        this.minTicks = minTicks;
        this.pics = pics;
        this.delay = delay;
        ran = new RandomDoubleGenerator (MIN, MAX);
        start();
    }
    
    private int getTicks(double ran){
    	if (ran >= 0 && ran < 0.05) {
    		return minTicks + 1;
    	} else if (ran >= 0.05 && ran < 0.1) {
    		return minTicks + 2;
    	} else if (ran >= 0.1 && ran < 0.15) {
    		return minTicks + 3;
    	} else if (ran >= 0.15 && ran < 0.2) {
    		return minTicks + 4;
    	} else if (ran >= 0.2 && ran < 0.25) {
    		return minTicks + 5;
    	} else if (ran >= 0.25 && ran < 0.3) {
    		return minTicks + 6;
    	} else if (ran >= 0.3 && ran < 0.35) {
    		return minTicks + 7;
    	} else if (ran >= 0.35 && ran < 0.4) {
    		return minTicks + 8;
    	} else if (ran >= 0.4 && ran < 0.45) {
    		return minTicks + 9;
    	} else if (ran >= 0.45 && ran < 0.5) {
    		return minTicks + 10;
    	} else if (ran >= 0.5 && ran < 0.55) {
    		return minTicks + 11;
    	} else if (ran >= 0.55 && ran < 0.6) {
    		return minTicks + 12;
    	} else if (ran >= 0.6 && ran < 0.65) {
    		return minTicks + 13;
    	} else if (ran >= 0.65 && ran < 0.7) {
    		return minTicks + 12;
    	} else if (ran >= 0.7 && ran < 0.75) {
    		return minTicks + 11;    		
    	} else if (ran >= 0.75 && ran < 0.8) {
    		return minTicks + 10;
    	} else if (ran >= 0.8 && ran < 0.85) {
    		return minTicks + 9;
    	} else if (ran >= 0.85 && ran < 0.9) {
    		return minTicks + 8;
    	} else if (ran >= 0.9 && ran < 0.95) {
    		return minTicks + 7;
    	} else if (ran >= 0.95 && ran <= 1){
    		return minTicks + 6;
    	} else {
    		return minTicks;
    	}
    }
    
    public void reset() {
    	p1.setImage(pics[2]);
    	p2.setImage(pics[1]);
    	p3.setImage(pics[0]);
    	p4.setImage(pics[19]);
    	p5.setImage(pics[18]);
    }
    
    public void run(){
        while (true){
            if (totalTicks > 0){
                index0++;
                index1++;
                index2++;
                index3++;
                index4++;
                p1.setImage(pics[index0 % 20]);
                p2.setImage(pics[index1 % 20]);
                p3.setImage(pics[index2 % 20]);
                p4.setImage(pics[index3 % 20]);
                p5.setImage(pics[index4 % 20]);
                if (delay < 20){
                    delay += 1;
                } else if(delay >= 20 && delay < 200){
                    delay += 5;
                } else if (delay >= 200 && delay < 500){
                    delay += 20;
                }
                totalTicks--;
                if (totalTicks == 0) {
                	controller.onScoreChanged(index2 % 20);
                }
            }
            pause(delay);
        }
    }
    
    public synchronized int getTicks(){
        return totalTicks;
    }
    
    public synchronized void setTicks(int ticks){
    	if (ticks == 0){
    		totalTicks = 0;
    	} else {
    		this.minTicks = ticks;
    		double ranNumber = ran.nextValue();
    		this.totalTicks = getTicks(ranNumber);
    		System.out.println("totalTicks: " + totalTicks + " randomNumber: " + ranNumber);
    	}
    }
    
    public synchronized int getDelay() {
    	return delay;
    }
    
    public synchronized void setDelay(int delay) {
    	this.delay = delay;
    }
    
}

