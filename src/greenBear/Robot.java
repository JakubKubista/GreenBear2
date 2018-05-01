package greenBear;

import java.awt.Graphics;
import java.util.List;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Robot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robot extends Actor
{
	 private double speedL = 0;
	    private double speedR = 0;
	    private double axisLength = 1; //50px = 25cm
	    private int sensorOffset = 15;
	    private double alpha = 0;
	    private double x;
	    private double y;
	    
	    //private static final int STEP = 30;
	    private boolean init = true;
	    private boolean end = false;
	    private boolean baseDetected = false;
	    
	    
	    private double defaultX;
	    private double defaultY;
	    private boolean changeover = false;
	    private double latestX[] = new double[20];
	    private double latestY[] = new double[20];
	    
	    private Bear bear = null;
	    private boolean bearDetected;
	    private boolean bearCaught;
	    
	    private Sensor frontSensor = new Sensor();
	    private Sensor rearSensor = new Sensor();
	    private Sensor leftSensor = new Sensor();
	    private Sensor rightSensor = new Sensor();
	    
	    public void prepareSensors() {
	        getWorld().addObject(frontSensor, getX(), getY());
	        getWorld().addObject(rearSensor, getX(), getY());
	        getWorld().addObject(leftSensor, getX(), getY());
	        getWorld().addObject(rightSensor, getX(), getY());
	        setSensorLocation();
	        setSensorRotation();
	        x = getX();
	        y = getY();
	        alpha = getRotation();
	    }
	    
	    public void setSensorLocation() {
	        int x = getX();
	        int y = getY();
	        double alpha = Math.toRadians(getRotation());
	        double alphaPerp = Math.toRadians(getRotation() + 90);
	        
	        frontSensor.setLocation((int)(x + sensorOffset * Math.cos(alpha)), (int)(y + sensorOffset * Math.sin(alpha)));
	        rearSensor.setLocation((int)(x - sensorOffset * Math.cos(alpha)), (int)(y - sensorOffset * Math.sin(alpha)));
	        leftSensor.setLocation((int)(x - sensorOffset * Math.cos(alphaPerp)), (int)(y - sensorOffset * Math.sin(alphaPerp)));
	        rightSensor.setLocation((int)(x + sensorOffset * Math.cos(alphaPerp)), (int)(y + sensorOffset * Math.sin(alphaPerp)));
	    }
	    
	    public void setSensorRotation() {
	        int rot = getRotation();
	        frontSensor.setRotation(rot);
	        rearSensor.setRotation(rot);
	        leftSensor.setRotation(rot - 90);
	        rightSensor.setRotation(rot + 90);
	    }

	    public void setBear(){
    		if (getNeighbours(300, false, Bear.class).size() > 0) {    			
    			bear = getWorld().getObjects(Bear.class).get(0);
    		}
	    }
	    
	    private void initialize() {
	    	if (init) {
	    	    defaultX = getX();
	    	    defaultY = getY();   
	    	    init = false;
	    	}
	    }
	    
	    private void detectBear(){
			if(bear != null && bear.getY()==(int)y && !bearDetected) {
    			rotate(1,90);
    			bearDetected = true;
			}
	    }

	    private void tryCatchBear(){
	    	if (bearDetected  && bear.getX()==(int)x && !bearCaught) {
    			rotate(1,90);
    			bearCaught = true;
			}
	    }
	    
	    private void detectBase(){
			if((int)defaultX==(int)x && (int)y<=260 && bearCaught && !baseDetected) {
    			rotate(1,90);
    			baseDetected = true;
			}
	    }
	    
	    private void ended() {
			if (baseDetected && (int)defaultX == (int)x && (int)defaultY == (int)y) {
    			end = true;
				System.out.println("end");
			}
	    }
	    
	    public void workflow() {
	    	initialize();
			detectBear();
			tryCatchBear();
			detectBase();
			ended();	    	
	    }
	    /**
	     * Act - do whatever the Robot wants to do. This method is called whenever
	     * the 'Act' or 'Run' button gets pressed in the environment.
	     */
	    int count = 0;
	    public void act() 
	    {	 
	    	if(!frontSensor.getTouch()){
	    		workflow();
    			if(!end) {
    	    		moveBasically(0);
    			}
	    	} else {
	    		if(leftSensor.getTouch()) {
		    		rotate(1,90);	
	    		}else if(rightSensor.getTouch()) {
	    			rotate(0,90);	
	    		}else {
		    		System.out.println("posititon: "+Math.round(x)+";"+Math.round(y));	

	    		     for (int i = 0; i < latestX.length; i++) {
			    		 //System.out.println("compareX: "+Math.round(x)+";"+latestX[i]);
			    		 //System.out.println("compareY: "+Math.round(y)+";"+latestY[i]);
	    		    	 if ((latestX[i] == Math.round(x)) && 
	    		    	     (latestY[i] == Math.round(y))) {
	    		    		    changeover = true;
	    		    	 }
	    		      }
	    		     if (changeover) {
 			    		rotate(1,90);	
 			    		clearMemory();
	    		     }else {
	 	    			rotate(0,90);	
	 	    			addToMemory();
	    		     }
	    		}
	    		
	    		
	    		/*
	    		if (!changeover) {
		    		rotate(0,90);	
	    		}else {
		    		rotate(1,90);	
	    		}
	    		count++;
	    		if (count == 2) {
		    		if (changeover) {
			    		changeover = false;	
		    		}else {
			    		changeover = true;		
		    		}
		    		count = 0;
	    		}
	    		*/
	    	}
	    	
	    	
	    	// move by one step
	    	/*
    		if(count <= 10){
    			moveByDynamicDistance(5,1);
    			count++;
    		}
	    	*/
	    	
	    }
	    
	    private void clearMemory(){
    		latestX = new double[20];
    		latestY = new double[20];
    		count = 0;
    		changeover = false;
		    System.out.println("looped");
	    }
	    
	    private void addToMemory(){
    		latestX[count] = Math.round(x);
    		latestY[count] = Math.round(y);	
    		count++;
		    System.out.println("add: "+Math.round(x)+";"+Math.round(y));	
	    }
	        
	    // basic turn
	    private void rotate(int direction, int angle){
	    	//0 = left, others right
	    	if(direction == 0){
	    		for(int i = 0; i < angle; i++){
		    		speedR = 0.5;
		    		speedL = -0.5;
		    		move();
	    		}
	    	}else{
	    		for(int i = 0; i < angle; i++){
		    		speedR = -0.5;
		    		speedL = 0.5;
		    		move();
	    		}
	    	}
	    }      	    
	    
	    // robot will go front or back
	    private void moveBasically(int direction){
	    	if(direction == 0){
	    		speedL = 1.5;
	    		speedR = 1.5;
	    	}else{
	    		speedL = -1.5;
	    		speedR = -1.5;
	    	}
    		move();
	    }
	    
	    // movement of distance - TRUE-front, FALSE-back
	    private void moveByDistance(int distance, boolean direction){
	    	if (direction){
	    		for (int i = 0; i < distance; i++){
	    			speedR = 1.5;
	    			speedL = 1.5;
	    			move();	
		    		repaint();
	    		}
    		}else{
	    		for (int i = 0; i < distance; i++){
	    			speedR = 1.5;
	    			speedL = 1.5;
	    			move();
		    		repaint();
	    		}
	    	}
	    } 
	    
	    private void move() {
	        double ralpha = Math.toRadians(alpha);
	        double speed = (speedL + speedR) / 2;
	        double da = Math.atan((speedL - speedR) / axisLength);
	        alpha += Math.toDegrees(da);
	        
	        x = x + speed * Math.cos(ralpha);
	        y = y + speed * Math.sin(ralpha);
	        
	        setLocation((int)x, (int)y);
	        setRotation((int)alpha);
	        setSensorLocation();
	        setSensorRotation();   	
    	    setBear();
	        
	        if(bearCaught) {
	        	bear.setLocation((int)x, (int)y);
	        }
	    }
	    
	  	    
	    private void repaint(){
	        getWorld().repaint();
	        try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
