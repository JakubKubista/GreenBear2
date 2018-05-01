package greenBear;

 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RescuePlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RescuePlace extends World
{
	public static int CA = 60;
    public static int CB = 120;
    public static int CC = 180;
    public static int CD = 240;
    public static int CE = 300;

    public static int R1 = 60;
    public static int R2 = 120;
    public static int R3 = 180;
    public static int R4 = 240;
    public static int R5 = 300;
    
    public static int STEP = 60;
    
    /**
     * Constructor for objects of class KetchupHouse.
     * 
     */
    public RescuePlace()
    {    
        //1px = 5mm
        super(300*2, 300*2, 1);
        
        setupGrid();
        setupRobots();
        setupKetchups();
    }
    
    public void setupGrid() {
    	
        for(int x = 60; x < 540; x += 60) {
            for(int y = 100; y < 500; y += 100) {
            	if(y == 100 || y == 400){
                    Wall lineV = new Wall(Wall.VERTICAL);
                    addObject(lineV, y, x);
            	}
            	
            	if(y == 200 && x <= 120 ){
                    Wall lineV = new Wall(Wall.VERTICAL);
                    addObject(lineV, y, x);
            	}
            	
            	if(y == 300 && x > 120 && x <= 240 ){
                    Wall lineV = new Wall(Wall.VERTICAL);
                    addObject(lineV, y, x);
            	}
            }   
        }
        
        for(int x = 30; x < 540; x += 240) {
            for(int y = 100; y < 400; y += 60) {
            	if(x == 30){
                    Wall lineV = new Wall(Wall.HORIZONTAL);
                    addObject(lineV, y+30, x);
            	}
            	
            	if(x == 270 && y < 270){
                    Wall lineV = new Wall(Wall.HORIZONTAL);
                    addObject(lineV, y+30, x);
            	}
            	
            	if( x == 510 ){
                    Wall lineV = new Wall(Wall.HORIZONTAL);
                    addObject(lineV, y+30, x);
            	}
            }   
            
            Wall lineV = new Wall(Wall.HORIZONTAL);
            addObject(lineV, 270, 270);
        }

    }
    
    public void setupRobots() {
        Robot rob1 = new Robot();
        rob1.setRotation(90);
        
        addObject(rob1, 150, 70);
        
        rob1.prepareSensors();
    }
    
    public void setupKetchups() {
        Bear k1 = new Bear();
        addObject(k1, 190, 440);
        
    }
}
