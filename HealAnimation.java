import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealAnimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealAnimation extends Actor
{
    private static GreenfootImage img[] = {new GreenfootImage("1-heal.png"), new GreenfootImage("2-heal.png"), new GreenfootImage("3-heal.png"), new GreenfootImage("4-heal.png"), new GreenfootImage("5-heal.png"), new GreenfootImage("6-heal.png"), new GreenfootImage("7-heal.png"), new GreenfootImage("8-heal.png"), new GreenfootImage("9-heal.png"), new GreenfootImage("10-heal.png"), new GreenfootImage("11-heal.png"), new GreenfootImage("12-heal.png"), new GreenfootImage("13-heal.png")};
    // An array that holds all the images of the animation
    private int currentImage = 0; // The current image being displayed
    private int animationDelay = 0; // The delay between frames
    
    /**
     * The constructor for the HealAnimation object
     */
    public HealAnimation()
    {
        for(int i = 0;i < img.length;i++)
        {
            img[i].scale(70,70);
        }
        setImage(img[0]);
        Greenfoot.playSound("heal.wav");
    }
    /**
     * Act - do whatever the HealAnimation wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void act() 
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
