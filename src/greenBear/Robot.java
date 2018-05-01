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
	    
	    private static final int STEP = 30;
	    
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
	    
	    /**
	     * Act - do whatever the Robot wants to do. This method is called whenever
	     * the 'Act' or 'Run' button gets pressed in the environment.
	     */
	    int count = 0;
	    public void act() 
	    {
	    	// move at wall by sensor
	    	/*
	    	if(!frontSensor.getTouch()){
	    		drive(0);
	    	} else {
	    		rotate(0,90);
	    	}
	    	*/
	    	
	    	// move by one step
	    	
    		if(count <= 10){
    			moveByDynamicDistance(1,1);
    			count++;
    		}
	    	
	    	
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
	    		speedL = 1;
	    		speedR = 1;
	    	}else{
	    		speedL = -1;
	    		speedR = -1;
	    	}
    		move();
	    }
	    
	    // movement of static distance
	    private void moveByStaticDistance(){
	    	for(int i = 0;i <= STEP; i++ ){
	    		move();
	    	}
	    }
	    
	    // movement of dynamic distance
	    private void moveByDynamicDistance(int distance,int direction){
	    	if (direction == 0){
	    		for (int i = 0; i < distance; i++){
	    			speedR = 1;
	    			speedL = 1;
	    			move();	
	    		}
    		}else{
	    		for (int i = 0; i < distance; i++){
	    			speedR = 1;
	    			speedL = 1;
	    			move();
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
	        
	        // TODO movement with bear
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
