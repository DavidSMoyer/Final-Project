
 import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AttackAnimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackAnimation extends Actor
{
    private static GreenfootImage img[] = {new GreenfootImage("1-attack.png"), new GreenfootImage("2-attack.png"), new GreenfootImage("3-attack.png"), new GreenfootImage("4-attack.png"), new GreenfootImage("5-attack.png"), new GreenfootImage("6-attack.png"), new GreenfootImage("7-attack.png"), new GreenfootImage("8-attack.png")};
    // An image array of the animation
    private int currentImage = 0; // The current image of the img array being displayed
    private int animationDelay = 0; // The delay between switching frames
    private boolean initialized = false; // Has the object been initialized or not
    /**
     * The consturctor for the AttackAnimation object
     */
    public AttackAnimation()
    {
        for(int i = 0;i < img.length;i++)
        {
            img[i].scale(70,70);
        }
        setImage(img[0]);
        Greenfoot.playSound("attack.wav");
    }
    /**
     * Act - do whatever the AttackAnimation wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(initialized == false)
        {
            setLocation(getX()+Greenfoot.getRandomNumber(21)-10,getY()+Greenfoot.getRandomNumber(21)-10);
            initialized = true;
        }
        animate();
    }    
    /**
     * Animate
     * 
     * Controls the animation of the object
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void animate()
    {
        if(animationDelay < 1)
        {
            animationDelay++;
        }
        else
        {
            if(currentImage < img.length-1)
            {
                currentImage++;
                setImage(img[currentImage]);
                animationDelay = 0;
            }
            else
            {
                getWorld().removeObject(this);
            }
        }
    }
}
