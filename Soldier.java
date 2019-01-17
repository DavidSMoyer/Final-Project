import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List; // List functionality
/**
 * Write a description of class Soldier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Soldier extends Actor
{
    private GreenfootImage img; // The image of the object
    private String type; // The type of soldier
    private int player; // The player that owns the soldier
    private int health; // The current health of the soldier
    private int maxHealth; // The maximum health of the soldier
    private boolean moved = false; // If the soldier has moved or not
    private boolean attacked = false; // If the soldier has attacked or not
    private boolean selected = false; // If the soldier is selected or not
    private int originalDamage; // The default damage of the soldier
    private int damage; // The updated damage value after strengths & weaknesses
    private boolean initialized = false; // Has the soldier been initialized

    private int fireTurns = 0; // The amount of turns with the fire status effect
    private int slowTurns = 0; // The amount of turns with the slow status effect
    private int poisonTurns = 0; // The amount of turns with the poison status effect
    private int stunTurns = 0; // The amount of turns with the stun status effect
    /**
     * The constructor for the soldier object
     * 
     * @Param t The type of soldier
     * @param p The player that owns it
     */
    public Soldier(String t, int p)
    {
        type = t;
        player = p;
        if(type == "knight")
        {
            img = new GreenfootImage("soldierknight.png");
            health = 7;
            maxHealth = 7;
            originalDamage = 3;
        }
        else if (type == "archer")
        {
            img = new GreenfootImage("soldierarcher.png");
            health = 5;
            maxHealth = 5;
            originalDamage = 2;
        }
        else if (type == "mage")
        {
            img = new GreenfootImage("soldiermage.png");
            health = 5;
            maxHealth = 5;
            originalDamage = 2;
        }
        else if (type == "skeleton")
        {
            img = new GreenfootImage("skeleton.png");
            health = 3;
            maxHealth = 3;
            originalDamage = 2;
        }
        else if (type == "phoenix")
        {
            img = new GreenfootImage("phoenix.png");
            health = 5;
            maxHealth = 5;
            originalDamage = 2;
        }
        else if (type == "golem")
        {
            img = new GreenfootImage("golem.png");
            health = 10;
            maxHealth = 10;
            originalDamage = 2;
        }
        else if (type == "serpent")
        {
            img = new GreenfootImage("serpent.png");
            health = 5;
            maxHealth = 5;
            originalDamage = 2;
        }
        img.scale(50,50);
    }

    /**
     * GetSelected
     * 
     * Gets if the soldier is selected or not
     * 
     * @param None There are no parameters
     * @return Returns a boolean
     */
    public boolean getSelected()
    {
        return selected;
    }
    
    /**
     * Act - do whatever the Soldier wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(initialized == false)
        {
            getWorld().addObject(new HealAnimation(),getX(),getY());
            initialized = true;
            if(type == "phoenix" || type == "golem" || type == "serpent")
            {
                List<Hero> heroes = getWorld().getObjects(Hero.class);
                for(int i = 0;i < heroes.size();i++)
                {
                    if(heroes.get(i).getPlayer() == player && heroes.get(i).getAbility2() == "War Constructs")
                    {
                        originalDamage++;
                    }
                    if(heroes.get(i).getPlayer() == player && heroes.get(i).getAbility2() == "Tough Runes")
                    {
                        health+=2;
                        maxHealth+=2;
                    }
                }
            }
        }
        clickDetection();
        updateImage();
        deathDetection();
    }   
    
    /**
     * ClickDetection
     * 
     * Detects if the object is clicked
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void clickDetection()
    {
        MyWorld world = ((MyWorld)getWorld());
        if(Greenfoot.mouseClicked(this) && player == world.getTurn() && stunTurns <= 0)
        {
            if(selected == false)
            {
                if(world.noneSelected() == true)
                {
                    selected = true;
                    if(moved == false)
                    {
                        addMove();
                    }
                    if(attacked == false)
                    {
                        addAttack();
                    }
                }
            }
            else
            {
                selected = false;
                removeMove();
                removeAttack();
            }
        }
    }

    /**
     * AddAttack
     * 
     * Adds AttackPosition to all available targets
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void addAttack()
    {
        damage = originalDamage;
        weak();
        strong();
        int changedDamage = damage;
        List<Hero> heroes1 = getObjectsInRange(75,Hero.class);
        List<Hero> heroes2 = getObjectsInRange(150,Hero.class);
        List<Hero> heroes3 = getObjectsInRange(197,Hero.class);
        List<Buildings> builds1 = getObjectsInRange(75,Buildings.class);
        List<Buildings> builds2 = getObjectsInRange(150,Buildings.class);
        List<Buildings> builds3 = getObjectsInRange(197,Buildings.class);
        List<Soldier> soldiers1 = getObjectsInRange(75,Soldier.class);
        List<Soldier> soldiers2 = getObjectsInRange(150,Soldier.class);
        List<Soldier> soldiers3 = getObjectsInRange(197,Soldier.class);
        if(type == "knight" || type == "skeleton" || type == "serpent" || type == "golem")
        {
            if(!heroes1.isEmpty())
            {
                for(int i = 0;i < heroes1.size();i++)
                {
                    if(heroes1.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(heroes1.get(i),this,changedDamage),heroes1.get(i).getX(),heroes1.get(i).getY());
                    }
                }
            }
            if(!builds1.isEmpty())
            {
                for(int i = 0;i < builds1.size();i++)
                {
                    if(builds1.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(builds1.get(i),this,changedDamage),builds1.get(i).getX(),builds1.get(i).getY());
                    } 
                }
            }
            if(!soldiers1.isEmpty())
            {
                for(int i = 0;i < soldiers1.size();i++)
                {
                    if(soldiers1.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(soldiers1.get(i),this,changedDamage),soldiers1.get(i).getX(),soldiers1.get(i).getY());
                    } 
                }
            }
        }
        if(type == "mage" || type == "phoenix")
        {
            if(!heroes2.isEmpty())
            {
                for(int i = 0;i < heroes2.size();i++)
                {
                    if(heroes2.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(heroes2.get(i),this,changedDamage),heroes2.get(i).getX(),heroes2.get(i).getY());
                    }
                }
            }
            if(!builds2.isEmpty())
            {
                for(int i = 0;i < builds2.size();i++)
                {
                    if(builds2.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(builds2.get(i),this,changedDamage),builds2.get(i).getX(),builds2.get(i).getY());
                    } 
                }
            }
            if(!soldiers2.isEmpty())
            {
                for(int i = 0;i < soldiers2.size();i++)
                {
                    if(soldiers2.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(soldiers2.get(i),this,changedDamage),soldiers2.get(i).getX(),soldiers2.get(i).getY());
                    } 
                }
            }
        }
        if(type == "archer")
        {
            if(!heroes3.isEmpty())
            {
                for(int i = 0;i < heroes3.size();i++)
                {
                    if(heroes3.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(heroes3.get(i),this,changedDamage),heroes3.get(i).getX(),heroes3.get(i).getY());
                    }
                }
            }
            if(!builds3.isEmpty())
            {
                for(int i = 0;i < builds3.size();i++)
                {
                    if(builds3.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(builds3.get(i),this,changedDamage),builds3.get(i).getX(),builds3.get(i).getY());
                    } 
                }
            }
            if(!soldiers3.isEmpty())
            {
                for(int i = 0;i < soldiers3.size();i++)
                {
                    if(soldiers3.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(soldiers3.get(i),this,changedDamage),soldiers3.get(i).getX(),soldiers3.get(i).getY());
                    } 
                }
            }
        }
    }

    /**
     * AddMove
     * 
     * Adds MovePosition objects wherever the soldier can move
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void addMove()
    {
        if(slowTurns <= 0)
        {
            List<Hexagon> hex = getObjectsInRange(150,Hexagon.class);
            for(int i = 0;i < hex.size();i++)
            {
                if(getObjectsInRange(10,Hexagon.class).get(0) != hex.get(i))
                {
                    getWorld().addObject(new MovePosition(this),hex.get(i).getX(),hex.get(i).getY());
                }
            }
        }
        else
        {
            List<Hexagon> hex = getObjectsInRange(75,Hexagon.class);
            for(int i = 0;i < hex.size();i++)
            {
                if(getObjectsInRange(10,Hexagon.class).get(0) != hex.get(i))
                {
                    getWorld().addObject(new MovePosition(this),hex.get(i).getX(),hex.get(i).getY());
                }
            }
        }
    }

    /**
     * RemoveAttack
     * 
     * Removes all AttackPosition objects
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void removeAttack()
    {
        getWorld().removeObjects(getWorld().getObjects(AttackPosition.class));
    }

    /**
     * RemoveMove
     * 
     * Removes all MovePosition objects
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void removeMove()
    {
        getWorld().removeObjects(getWorld().getObjects(MovePosition.class));
    }

    /**
     * Unselect
     * 
     * Unselects the soldier, and removes all MovePosition and AttackPosition objects
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void unselect()
    {
        selected = false;
        removeMove();
        removeAttack();
    }

    /**
     * SetMoved
     * 
     * Sets moved to a specified value
     * 
     * @param v The value to set moved to
     * @return Returns nothing
     */
    public void setMoved(boolean v)
    {
        moved = v;
    }

    /**
     * SetAttacked
     * 
     * Sets attacked to a specified value
     * 
     * @param v The value to set attacked to
     * @return Returns nothing
     */
    public void setAttacked(boolean v)
    {
        attacked = v;
    }

    /**
     * GetType
     * 
     * Gets the type of soldier the object is
     * 
     * @param None There are no parameters
     * @return Returns string
     */
    public String getType()
    {
        return type;
    }

    /**
     * UpdateImage
     * 
     * Updates the image of the object
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void updateImage()
    {
        GreenfootImage img2;
        img2 = new GreenfootImage(50,50);
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
        else if (player == 4)
        {
            img2.setColor(Color.WHITE);
        }
        img2.setFont(new Font("Helvetica",25));
        img2.drawString("" + health,img2.getWidth()/2-10,img2.getHeight()/2+20);
        if(stunTurns > 0)
        {
            GreenfootImage img3 = new GreenfootImage("stun.png");
            img3.scale(30,30);
            img2.drawImage(img3,10,0);
        }
        if(fireTurns > 0)
        {
            GreenfootImage img3 = new GreenfootImage("fire.png");
            img3.scale(20,20);
            img2.drawImage(img3,0,30);
        }
        if(poisonTurns > 0)
        {
            GreenfootImage img3 = new GreenfootImage("poison.png");
            img3.scale(20,20);
            img2.drawImage(img3,0,0);
        }
        if(slowTurns > 0)
        {
            GreenfootImage img3 = new GreenfootImage("slow.png");
            img3.scale(20,20);
            img2.drawImage(img3,img2.getWidth()-20,0);
        }
        if(selected == true)
        {
            img2.scale(70,70);
        }
        setImage(img2);
    }

    /**
     * DeathDetection
     * 
     * Removes the object if the health is 0 or less
     * 
     * @param None There are no parmameters
     * @return Returns nothing
     */
    private void deathDetection()
    {
        if(health <= 0)
        {
            getWorld().addObject(new DeathDisplay(),getX(),getY());
            getWorld().removeObject(this);
        }
    }

    /**
     * SetFire
     * 
     * Applies the fire status effect for three turns
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void setFire()
    {
        fireTurns = 3;
    }

    /**
     * Fire
     * 
     * Reduces the duration of the fire status effect by one and damages the soldier
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void fire()
    {
        if(fireTurns > 0)
        {
            fireTurns--;
            health--;
            getWorld().addObject(new EffectAnimation(),getX(),getY());
        }
    }

    /**
     * PoisonAttack
     * 
     * Applies the poison status effect for three turns
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void poisonAttack()
    {
        poisonTurns = 3;
    }

    /**
     * Fire
     * 
     * Reduces the duration of the fire status effect by one and damages the soldier
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void poison()
    {
        if(poisonTurns > 0)
        {
            poisonTurns--;
            health-=2;
            if(fireTurns <= 0)
            {
                getWorld().addObject(new EffectAnimation(),getX(),getY());
            }
        }
    }

    /**
     * StunAttack
     * 
     * Applies the stun status effect for two turns
     * 
     * @param None There are no parameters
     * @Return Returns nothing
     */
    public void stunAttack()
    {
        stunTurns = 2;
    }

    /**
     * LowerStun
     * 
     * Reduces the duration of the stun status effect by one
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void lowerStun()
    {
        if(stunTurns > 0)
        {
            stunTurns--;
        }
    }

    /**
     * SlowAttack
     * 
     * Applies the slow status effect for two turns
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void slowAttack()
    {
        slowTurns = 2;
    }

    /**
     * Slow
     * 
     * Reduces the duration of the slow status effect
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void slow()
    {
        if(slowTurns > 0)
        {
            slowTurns--;
        }
    }

    /**
     * ChangeHP
     * 
     * Changes the current health of the soldier by a set amount
     * 
     * @param amount The amount to change
     * @return Returns nothing
     */
    public void changeHP(int amount)
    {
        boolean protection = false;
        List<AttackPosition> attack = getObjectsInRange(10,AttackPosition.class);
        if(!attack.isEmpty())
        {
            if(amount < 0)
            {
                List<Hero> nearbyHeroes = getObjectsInRange(75,Hero.class);
                if(!nearbyHeroes.isEmpty())
                {
                    for(int i = 0;i < nearbyHeroes.size();i++)
                    {
                        if(nearbyHeroes.get(i).getAbility2() == "Protective Aura" && nearbyHeroes.get(i).getPlayer() == player)
                        {
                            protection = true;
                        }
                    }
                }
                if(protection == true)
                {
                    amount++;
                    if(amount >= 0)
                    {
                        amount--;
                    }
                }
            }
        }
        health+=amount;
        if(!attack.isEmpty())
        {
            if(amount < 0)
            {
                getWorld().addObject(new AttackAnimation(),getX(),getY());
            }
            if(attack.get(0).getAttacker() != null)
            {
                if(attack.get(0).getAttacker().getAbility2() == "Vampiric Fang" && attack.get(0).getAbility() != "drain")
                {
                    attack.get(0).getAttacker().changeHP(1);
                }
            }
        }
        if(health > maxHealth)
        {
            health = maxHealth;
        }
        else
        {
            getWorld().addObject(new DamageDisplay(amount),getX(),getY()-30);
            if(amount > 0)
            {
                getWorld().addObject(new HealAnimation(),getX(),getY());
            }
        }
    }

    /**
     * GetPlayer
     * 
     * Gets the player that owns the soldier
     * 
     * @param None There are no parameters
     * @return Returns an int
     */
    public int getPlayer()
    {
        return player;
    }

    /**
     * GetHealth
     * 
     * Gets the current health of the hero
     * 
     * @Param None There are no parameters
     * @return Returns an int
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * GetHealth
     * 
     * Gets the max health of the hero
     * 
     * @Param None There are no parameters
     * @return Returns an int
     */
    public int getMaxHealth()
    {
        return maxHealth;
    }
    
    /**
     * MindControl
     * 
     * Changes the ownership of the soldier to another player
     * 
     * @param p The player to switch ownership to
     * @return Returns nothing
     */
    public void mindControl(int p)
    {
        player = p;
        attacked = false;
        moved = false;
    }

    /**
     * YourTurn
     * 
     * Runs when it's the player that owns this soldier's turn
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void yourTurn()
    {
        heal();
        lowerStun();
        if(stunTurns <= 0)
        {
            moved = false;
            attacked = false;
        }
        fire();
        slow();
        poison();
    }

    /**
     * Cure
     * 
     * Removes all negative status effects on the soldier
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void cure()
    {
        slowTurns = 0;
        fireTurns = 0;
        poisonTurns = 0;
        stunTurns = 0;
    }

    /**
     * NearbyBuildings
     * 
     * Gets all the buildings next to the soldier
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public List nearbyBuildings()
    {
        return getObjectsInRange(75,Buildings.class);
    }

    /**
     * Heal
     * 
     * Passive healing when nearby a building
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void heal()
    {
        boolean heal = false;
        List<Buildings> nearbyBuildings = nearbyBuildings();
        List<Hero> nearbyHeroes = getObjectsInRange(75,Hero.class);
        if(!nearbyBuildings.isEmpty())
        {
            for(int i = 0;i < nearbyBuildings.size();i++)
            {
                if(nearbyBuildings.get(i).getPlayer() == player)
                {
                    heal = true;
                }
            }
        }
        if(!nearbyHeroes.isEmpty())
        {
            for(int i = 0;i < nearbyHeroes.size();i++)
            {
                if(nearbyHeroes.get(i).getPlayer() == player && nearbyHeroes.get(i).getAbility2() == "Inspire")
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

    /**
     * Weak
     * 
     * Reduces the damage of the soldier's attack
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void weak()
    {
        boolean weak = false;
        List<Hero> nearbyHeroes = getObjectsInRange(75,Hero.class);
        if(!nearbyHeroes.isEmpty())
        {
            for(int i = 0;i < nearbyHeroes.size();i++)
            {
                if(nearbyHeroes.get(i).getAbility2() == "Disarming Field" && nearbyHeroes.get(i).getPlayer() != player)
                {
                    weak = true;
                }
            }
        }
        if(weak == true)
        {
            damage--;
            if(damage < 1)
            {
                damage = 1;
            }
        }
    }

    /**
     * Strong
     * 
     * Makes the soldier's attack deal more damage
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void strong()
    {
        boolean strong = false;
        List<Hero> nearbyHeroes = getObjectsInRange(75,Hero.class);
        if(!nearbyHeroes.isEmpty())
        {
            for(int i = 0;i < nearbyHeroes.size();i++)
            {
                if(nearbyHeroes.get(i).getAbility2() == "War Banner" || nearbyHeroes.get(i).getAbility2() == "Inspire")
                {
                    strong = true;
                }
            }
        }
        if(strong == true)
        {
            damage++;
        }
    }
}
