import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealAnimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EffectAnimation extends Actor
{
    private static GreenfootImage img[] = {new GreenfootImage("1-effect.png"), new GreenfootImage("2-effect.png"), new GreenfootImage("3-effect.png"), new GreenfootImage("4-effect.png")};
    // An array that holds all the images of the animation
    private int currentImage = 0; // The current image being displayed
    private int animationDelay = 0; // The delay between frames
    
    /**
     * The constructor for the EffectAnimation object
     */
    public EffectAnimation()
    {
        for(int i = 0;i < img.length;i++)
        {
            img[i].scale(70,70);
        }
        setImage(img[0]);
        Greenfoot.playSound("attack.wav");
    }
    /**
     * Act - do whatever the HealAnimation wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(animationDelay < 2)
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
