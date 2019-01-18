import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DeathDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeathDisplay extends Actor
{
    private int lifeTime = 51;
    private int transparency = 255;
    /**
     * The constructor of the DeathDisplay class
     */
    public DeathDisplay()
    {
        setImage("die.png");
        getImage().scale(30,30);
    }
    /**
     * Act - do whatever the DeathDisplay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void act() 
    {
        if(lifeTime > 0)
        {
            lifeTime--;
            transparency = lifeTime*5;
            getImage().setTransparency(transparency);
            if(transparency % 10 == 0)
            {
                setLocation(getX(),getY()-1);
            }
        }
        else
        {
            getWorld().removeObject(this);
        }
    }    
}
