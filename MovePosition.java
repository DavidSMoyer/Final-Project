import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MovePosition here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovePosition extends Actor
{
    private boolean flight = false; // Holds if the flight ability is being used
    private Hero hero; // Holds the hero moving
    private Soldier soldier; // Holds the soldier moving
    /**
     * A constructor for the MovePosition object
     * 
     * @param h The hero moving
     */
    public MovePosition(Hero h)
    {
        GreenfootImage img = new GreenfootImage(60,60);
        hero = h;
        img.setColor(new Color(0,255,0,100));
        img.fillOval(0,0,img.getWidth()-1,img.getHeight()-1);
        setImage(img);
    }
    /**
     * A constructor for the MovePosition object
     * 
     * @param h The hero moving
     * @param fl If the hero is flying
     */
    public MovePosition(Hero h,boolean fl)
    {
        GreenfootImage img = new GreenfootImage(60,60);
        flight = fl;
        hero = h;
        img.setColor(new Color(0,255,0,100));
        img.fillOval(0,0,img.getWidth()-1,img.getHeight()-1);
        setImage(img);
    }
    /**
     * A constructor for the MovePosition object
     * 
     * @param s The soldier moving
     */
    public MovePosition(Soldier s)
    {
        GreenfootImage img = new GreenfootImage(60,60);
        soldier = s;
        img.setColor(new Color(0,255,0,100));
        img.fillOval(0,0,img.getWidth()-1,img.getHeight()-1);
        setImage(img);
    }
    /**
     * Act - do whatever the MovePosition wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        removeDetection();
        clickDetection();
    }    
    /**
     * ClickDetection
     * 
     * Detects if the object was clicked
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void clickDetection()
    {
        if(Greenfoot.mouseClicked(this))
        {
            if(hero != null)
            {
                hero.setLocation(getX(),getY());
                if(flight == true)
                {
                    hero.setCooldown(10);
                }
                hero.setMoved(true);
                hero.unselect();
            }
            if(soldier != null)
            {
                soldier.setLocation(getX(),getY());
                soldier.setMoved(true);
                soldier.unselect();
            }
        }
    }
    /**
     * RemoveDetection
     * 
     * Detects if the object should be removed
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void removeDetection()
    {
        if(!getObjectsInRange(10,Hero.class).isEmpty() || !getObjectsInRange(10,Buildings.class).isEmpty() || !getObjectsInRange(10,Soldier.class).isEmpty())
        {
            getWorld().removeObject(this);
        }
    }
}
