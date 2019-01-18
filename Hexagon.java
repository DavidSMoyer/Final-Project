import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List; // List functionality
/**
 * Write a description of class Hexagon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hexagon extends Actor
{
    private GreenfootImage img = new GreenfootImage(61,61); // Sets the image of the object
    private boolean initialized = false; // Checks if the object is initialized or not
    /**
     * The constructor of the Hexagon class
     */
    public Hexagon()
    {
        updateImage();
        initialized = true;
    }
    /**
     * Act - do whatever the Hexagon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void act() 
    {
        updateImage();
    }    
    /**
     * UpdateImage
     * 
     * Updates the image
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void updateImage()
    {
        img.clear();
        img.setColor(Color.BLACK);
        if(initialized == true)
        {
            List<Buildings> struct = getObjectsInRange(30,Buildings.class);
            if(!struct.isEmpty())
            {
                if(struct.get(0).getPlayer() == 1)
                {
                    img.setColor(Color.RED);
                }
                else if(struct.get(0).getPlayer() == 2)
                {
                    img.setColor(Color.GREEN);
                }
                else if(struct.get(0).getPlayer() == 3)
                {
                    img.setColor(Color.BLUE);
                }
                else
                {
                    img.setColor(Color.WHITE);
                }
            }
        }
        img.drawLine(29,0,0,14);
        img.drawLine(0,14,0,44);
        img.drawLine(0,44,29,59);
        img.drawLine(29,59,59,44);
        img.drawLine(59,44,59,14);
        img.drawLine(59,14,29,0);
        img.scale(60,60);
        setImage(img);
    }
    /**
     * NoTerrain
     * 
     * Checks to see if there is any terrain in the current Hexagon
     * 
     * @param None There are no parameters
     * @return Returns a true value if not touching terrain, otherwise will give a false value
     */
    public boolean noTerrain()
    {
        if(isTouching(Terrain.class))
        {
            return false;
        }
        return true;
    }
}
