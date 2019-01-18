import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Terrain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Terrain extends Actor
{
    private String type; // The type of terrain
    
    /**
     * The constructor for the Terrain object
     * 
     * @param t The type of terrain
     */
    public Terrain(String t)
    {
        GreenfootImage img;
        type = t;
        if(type == "forest")
        {
            img = new GreenfootImage("forest.png");
            img.scale(50,50);
            setImage(img);
        }
        else if (type == "rocks")
        {
            img = new GreenfootImage("rocks.png");
            img.scale(50,50);
            setImage(img);
        }
        else if (type == "mountain")
        {
            img = new GreenfootImage("mountain.png");
            img.scale(50,50);
            setImage(img);
        }
    }
    /**
     * Act - do whatever the Terrain wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        hideDetection();
    }    
    /**
     * HideDetection
     * 
     * Detects if it should hide the image or not
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void hideDetection()
    {
        if(isTouching(Buildings.class))
        {
            getImage().setTransparency(0);
        }
        else
        {
            getImage().setTransparency(255);
        }
    }
    /**
     * GetType
     * 
     * Gets the terrain type
     * 
     * @param None There are no parameters
     * @return Returns the value of the "type" variable
     */
    public String getType()
    {
        return type;
    }
}
