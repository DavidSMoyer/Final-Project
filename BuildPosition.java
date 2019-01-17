import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BuildPosition here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BuildPosition extends Actor
{
    private String type; // The type of object being built
    private int player; // The player that owns the building
    private GreenfootImage img = new GreenfootImage(60,60); // The image of the object
    /**
     * The constructor of the BuildPosition object
     * 
     * @param t The type being built
     * @param p The player that owns it
     */
    public BuildPosition(String t, int p)
    {
        type = t;
        player = p;
        img.setColor(new Color(255,255,0,100));
        img.fillOval(0,0,img.getWidth()-1,img.getHeight()-1);
        setImage(img);
    }
    /**
     * Act - do whatever the BuildPosition wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        removeDetection();
        clickDetection();
    }    
    /**
     * GetType
     * 
     * Returns the type the object is
     * 
     * @param None There are no parameters
     * @return Returns string
     */
    public String getType()
    {
        return type;
    }
    /**
     * RemoveDetection
     * 
     * Detects if it should be removed
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void removeDetection()
    {
        if(!getObjectsInRange(10,Buildings.class).isEmpty() || !getObjectsInRange(10,Hero.class).isEmpty() || !getObjectsInRange(10,Soldier.class).isEmpty())
        {
            getWorld().removeObject(this);
        }
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
            MyWorld world = ((MyWorld)getWorld());
            if(type == "SM")
            {
                if(world.get("stone",player) >= 5)
                {
                    world.change("stone",player,-5);
                    world.addObject(new Buildings("lumberMill",player),getX(),getY());
                    getWorld().getObjects(UI.class).get(0).closeBuildMenu();
                    Greenfoot.playSound("build.mp3");
                }
            }
            if(type == "M")
            {
                if(world.get("wood",player) >= 5)
                {
                    world.change("wood",player,-5);
                    world.addObject(new Buildings("rockMine",player),getX(),getY());
                    getWorld().getObjects(UI.class).get(0).closeBuildMenu();
                    Greenfoot.playSound("build.mp3");
                }
            }
            if(type == "GM")
            {
                if(world.get("wood",player) >= 5 && world.get("stone",player) >= 5 && world.get("iron",player) >= 2)
                {
                    world.change("iron",player,-2);
                    world.change("wood",player,-5);
                    world.change("stone",player,-5);
                    world.addObject(new Buildings("goldMine",player),getX(),getY());
                    getWorld().getObjects(UI.class).get(0).closeBuildMenu();
                    Greenfoot.playSound("build.mp3");
                }
            }
            if(type == "A")
            {
                if(world.get("iron",player) >= 3 && world.get("stone",player) >= 8)
                {
                    world.change("iron",player,-3);
                    world.change("stone",player,-8);
                    world.addObject(new Buildings("armoury",player),getX(),getY());
                    getWorld().getObjects(UI.class).get(0).closeBuildMenu();
                    Greenfoot.playSound("build.mp3");
                }
            }
            if(type == "B")
            {
                if(world.get("iron",player) >= 3 && world.get("wood",player) >= 8)
                {
                    world.change("iron",player,-3);
                    world.change("wood",player,-8);
                    world.addObject(new Buildings("barracks",player),getX(),getY());
                    getWorld().getObjects(UI.class).get(0).closeBuildMenu();
                    Greenfoot.playSound("build.mp3");
                }
            }
            if(type == "T")
            {
                if(world.get("gold",player) >= 8 && world.get("iron",player) >= 3)
                {
                    world.change("gold",player,-8);
                    world.change("iron",player,-3);
                    world.addObject(new Buildings("tower",player),getX(),getY());
                    getWorld().getObjects(UI.class).get(0).closeBuildMenu();
                    Greenfoot.playSound("build.mp3");
                }
            }
            if(type == "archer")
            {
                if(world.get("gold",player) >= 3)
                {
                    world.change("gold",player,-3);
                    world.addObject(new Soldier("archer",player),getX(),getY());
                    getWorld().getObjects(UI.class).get(0).closeTrainMenu();
                }
            }
            if(type == "knight")
            {
                if(world.get("gold",player) >= 4)
                {
                    world.change("gold",player,-4);
                    world.addObject(new Soldier("knight",player),getX(),getY());
                    getWorld().getObjects(UI.class).get(0).closeTrainMenu();
                }
            }
            if(type == "mage")
            {
                if(world.get("gold",player) >= 3)
                {
                    world.change("gold",player,-3);
                    world.addObject(new Soldier("mage",player),getX(),getY());
                    getWorld().getObjects(UI.class).get(0).closeTrainMenu();
                }
            }
            if(type == "skeleton")
            {
                world.addObject(new Soldier("skeleton",player),getX(),getY());
                for(int i = 0;i < world.getObjects(Hero.class).size();i++)
                {
                    if(world.getObjects(Hero.class).get(i).getPlayer() == player)
                    {
                        world.getObjects(Hero.class).get(i).setCooldown(3);
                        world.getObjects(Hero.class).get(i).setAttacked(true);
                        world.getObjects(Hero.class).get(i).unselect();
                    }
                }
            }
            if(type == "golem")
            {
                world.addObject(new Soldier("golem",player),getX(),getY());
                for(int i = 0;i < world.getObjects(Hero.class).size();i++)
                {
                    if(world.getObjects(Hero.class).get(i).getPlayer() == player)
                    {
                        world.getObjects(Hero.class).get(i).setCooldown(10);
                        world.getObjects(Hero.class).get(i).setAttacked(true);
                        world.getObjects(Hero.class).get(i).unselect();
                    }
                }
            }
            if(type == "serpent")
            {
                world.addObject(new Soldier("serpent",player),getX(),getY());
                for(int i = 0;i < world.getObjects(Hero.class).size();i++)
                {
                    if(world.getObjects(Hero.class).get(i).getPlayer() == player)
                    {
                        world.getObjects(Hero.class).get(i).setCooldown(4);
                        world.getObjects(Hero.class).get(i).setAttacked(true);
                        world.getObjects(Hero.class).get(i).unselect();
                    }
                }
            }
            if(type == "phoenix")
            {
                world.addObject(new Soldier("phoenix",player),getX(),getY());
                for(int i = 0;i < world.getObjects(Hero.class).size();i++)
                {
                    if(world.getObjects(Hero.class).get(i).getPlayer() == player)
                    {
                        world.getObjects(Hero.class).get(i).setCooldown(6);
                        world.getObjects(Hero.class).get(i).setAttacked(true);
                        world.getObjects(Hero.class).get(i).unselect();
                    }
                }
            }
        }
    }
}
