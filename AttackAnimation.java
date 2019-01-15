import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AttackAnimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackAnimation extends Actor
{
    private GreenfootImage img[] = {new GreenfootImage("1-attack.png"), new GreenfootImage("2-attack.png"), new GreenfootImage("3-attack.png"), new GreenfootImage("4-attack.png"), new GreenfootImage("5-attack.png"), new GreenfootImage("6-attack.png"), new GreenfootImage("7-attack.png"), new GreenfootImage("8-attack.png")};
    private int currentImage = 0;
    private int animationDelay = 0;
    private boolean initialized = false;
    public AttackAnimation()
    {
        for(int i = 0;i < img.length;i++)
        {
            img[i].scale(70,70);
        }
        setImage(img[0]);
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
