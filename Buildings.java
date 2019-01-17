import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List; // List functionality
/**
 * Write a description of class Buildings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Buildings extends Actor
{
    private GreenfootImage img; // The image of the building
    private String type; // The type of building
    private int player; // The player that owns it
    private int health; // The current amount of health
    private int maxHealth; // The maximum amount of health
    private int healDelay = 0; // The amount of turns before a building can be repaired by a soldier or hero
    /**
     * The constructor of the Buildings object
     * 
     * @param t The type of building
     * @param p The player that owns it
     */
    public Buildings(String t, int p)
    {
        type = t;
        player = p;
        if(type == "fortress")
        {
            img = new GreenfootImage("fortress.png");
            health = 20;
            maxHealth = 20;
        }
        else if (type == "lumberMill")
        {
            img = new GreenfootImage("lumber_mill.png");
            health = 10;
            maxHealth = 10;
        }
        else if (type == "rockMine")
        {
            img = new GreenfootImage("stone_mine.png");
            health = 10;
            maxHealth = 10;
        }
        else if (type == "goldMine")
        {
            img = new GreenfootImage("gold_mine.png");
            health = 10;
            maxHealth = 10;
        }
        else if (type == "armoury")
        {
            img = new GreenfootImage("armoury.png");
            health = 15;
            maxHealth = 15;
        }
        else if (type == "barracks")
        {
            img = new GreenfootImage("barracks.png");
            health = 15;
            maxHealth = 15;
        }
        else if (type == "tower")
        {
            img = new GreenfootImage("tower.png");
            health = 15;
            maxHealth = 15;
        }
        img.scale(50,50);
        updateImage();
    }
    /**
     * Act - do whatever the Buildings wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateImage();
        deathDetection();
    }   
    /**
     * DeathDetection
     * 
     * Detects if the buildings HP has reached 0 or less
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void deathDetection()
    {
        if (health <= 0)
        {
            if(type == "fortress")
            {
                List<Hero> heroes = getWorld().getObjects(Hero.class);
                for(int i = 0;i < heroes.size();i++)
                {
                    if(heroes.get(i).getPlayer() == player)
                    {
                        heroes.get(i).changeHP(-9999);
                    }
                }
                ((MyWorld)getWorld()).lose(player);
                List<Buildings> builds = getWorld().getObjects(Buildings.class);
                for(int i = 0;i < builds.size();i++)
                {
                    if(builds.get(i).getPlayer() == player && builds.get(i) != this)
                    {
                        getWorld().removeObject(builds.get(i));
                    }
                }
                List<Soldier> soldiers = getWorld().getObjects(Soldier.class);
                for(int i = 0;i < soldiers.size();i++)
                {
                    if(soldiers.get(i).getPlayer() == player)
                    {
                        getWorld().removeObject(soldiers.get(i));
                    }
                }
            }
            getWorld().addObject(new DeathDisplay(),getX(),getY());
            getWorld().removeObject(this);
        }
    }
    /**
     * UpdateImage
     * 
     * Updates the image of the building
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void updateImage()
    {
        GreenfootImage img2 = new GreenfootImage(50,50);
        img2.drawImage(img,0,0);
        if(player == 1)
        {
            img2.setColor(Color.RED);
        }
        else if (player == 2)
        {
            img2.setColor(Color.GREEN);
        }
        else if (player == 3)
        {
            img2.setColor(Color.BLUE);
        }
        else
        {
            img2.setColor(Color.WHITE);
        }
        img2.setFont(new Font("Helvetica",25));
        img2.drawString("" + health,getImage().getWidth()/2-10,getImage().getHeight()/2+20);
        setImage(img2);
    }
    /**
     * GetPlayer
     * 
     * Gets the player that owns the building
     * 
     * @param None There are no parameters
     * @return Returns an integar
     */
    public int getPlayer()
    {
        return player;
    }
    /**
     * GetType
     * 
     * Gets the type of building
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public String getType()
    {
        return type;
    }
    /**
     * YourTurn
     * 
     * The actions the building runs when it's the building's turn
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void yourTurn()
    {
        cureNearby();
        heal();
    }
    /**
     * Heal
     * 
     * Heals the building if conditions are met
     * 
     * @Param None There are no parameters
     * @return Returns nothing
     */
    private void heal()
    {
        if(healDelay > 0)
        {
            healDelay--;
        }
        else
        {
            boolean heal = false;
            List<Hero> nearbyHeroes = nearbyHeroes();
            List<Soldier> nearbySoldiers = getObjectsInRange(75,Soldier.class);
            if(!nearbyHeroes.isEmpty() || !nearbySoldiers.isEmpty())
            {
                if(!nearbyHeroes.isEmpty())
                {
                    for(int i = 0;i < nearbyHeroes.size();i++)
                    {
                        if(nearbyHeroes.get(i).getPlayer() == player)
                        {
                            heal = true;
                        }
                    }
                }
                if(!nearbySoldiers.isEmpty())
                {
                    for(int i = 0;i < nearbySoldiers.size();i++)
                    {
                        if(nearbySoldiers.get(i).getPlayer() == player)
                        {
                            heal = true;
                        }
                    }
                }
                if(heal == true)
                {
                    changeHP(1);
                }
            }
        }
    }
    /**
     * ChangeHP
     * 
     * Changes the building's current HP
     * 
     * @param amount The amountto update
     * @return Returns nothing
     */
    public void changeHP(int amount)
    {
        health+=amount;
        if(health > maxHealth)
        {
            health = maxHealth;
        }
        else
        {
            getWorld().addObject(new DamageDisplay(amount),getX(),getY()-30);
            if(amount < 0)
            {
                healDelay = 2;
                getWorld().addObject(new AttackAnimation(),getX(),getY());
            }
            else
            {
                getWorld().addObject(new HealAnimation(),getX(),getY());
            }
        }
    }
    /**
     * CureNearby
     * 
     * Removes the status effects of nearby Heroes and Soldiers
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void cureNearby()
    {
        List<Hero> heroes = getObjectsInRange(75,Hero.class);
        if(!heroes.isEmpty())
        {
            for(int i = 0;i < heroes.size();i++)
            {
                if(heroes.get(i).getPlayer() == player)
                {
                    heroes.get(i).cure();
                }
            }
        }
        List<Soldier> soldiers = getObjectsInRange(75,Soldier.class);
        if(!soldiers.isEmpty())
        {
            for(int i = 0;i < soldiers.size();i++)
            {
                if(soldiers.get(i).getPlayer() == player)
                {
                    soldiers.get(i).cure();
                }
            }
        }
    }
    /**
     * NearbyHex
     * 
     * Gets the hexagons next to the building
     * 
     * @param None There are no parameters
     * @return Returns a list of nearby hexagons
     */
    public List nearbyHex()
    {
        return getObjectsInRange(75,Hexagon.class);
    }
    /**
     * NearbyHeroes
     * 
     * Gets the heroes next to the building
     * 
     * @param None There are no parameters
     * @return Returns a List of nearby heroes
     */
    public List nearbyHeroes()
    {
        return getObjectsInRange(75,Hero.class);
    }
}
