package greenBear;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wall extends Actor
{
    public static int VERTICAL = 0;
    public static int HORIZONTAL = 1;
    
    public Wall(int align) {
        if(align == VERTICAL) {
            setRotation(90);
        }
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
