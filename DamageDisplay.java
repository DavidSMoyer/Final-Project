import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DamageDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DamageDisplay extends Actor
{
    private int lifeTime = 51; // The amount of cycles before the object is deleted
    /**
     * A constructor for the DamageDisplay class
     * 
     * @param display A message to display
     */
    public DamageDisplay(String display)
    {
        GreenfootImage img = new GreenfootImage(30,30);
        img.setColor(Color.BLUE);
        img.drawString(display,0,img.getHeight()/2);
        setImage(img);
    }

    /**
     * A constructor for the DamageDisplay class
     * 
     * @param amount The amount of damage to display
     */
    public DamageDisplay(int amount)
    {
        GreenfootImage img = new GreenfootImage(30,30);
        char operation = ' ';
        if(amount > 0)
        {
            img.setColor(Color.GREEN);
            operation = '+';
        }
        else
        {
            img.setColor(Color.RED);
            operation = '-';
            amount*=-1;
        }
        img.drawString("" + operation + amount,0,img.getHeight()/2);
        setImage(img);
    }

    /**
     * Act - do whatever the DamageDisplay wants to do. This method is called whenever
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
            getImage().setTransparency(lifeTime*5);
            if(lifeTime % 5 == 0)
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
