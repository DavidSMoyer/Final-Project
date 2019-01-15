import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List; // List functionality
/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Actor
{
    private GreenfootImage img; // The variable that holds the image of the object
    private GreenfootImage img2 = new GreenfootImage(50,50); // The image being displayed
    private int player; // The player that owns the hero
    private int health = 20; // The current health of the hero
    private String classType; // The class type of the hero
    private String ability1; // The active ability of the hero
    private String ability2; // The passive ability of the hero
    private boolean moved = false; // Has the hero has moved this turn
    private boolean attacked = false; // Has the hero attacked this turn
    private boolean selected = false; // Has the hero been selected
    private int abilityCooldown; // The cooldown of the hero's active ability
    private int passiveCooldown; // The cooldown of the hero's passive ability
    private int originalDamage; // The original damage depending on class
    private int damage; // The amount of damage the hero will deal

    private int invulTurns = 0; // The amount of turns the hero is invulnerable
    private int fireTurns = 0; // The amount of turns the hero is on fire
    private int stunTurns = 0; // The amount of turns the hero is stunned
    private int invisTurns = 0; // The amount of turns the hero is invisible
    private int poisonTurns = 0; // The amount of turns the hero is poisoned
    private int slowTurns = 0; // The amount of turns the hero is slowed
    /**
     * The constructor of the Hero class
     * 
     * @param p The player that owns the hero
     * @param ct The class type of the hero
     * @param ab1 The active ability of the hero
     * @param ab2 The passive ability of the hero
     */
    public Hero(int p, String ct, String ab1, String ab2)
    {
        player = p;
        classType = ct;
        ability1 = ab1;
        ability2 = ab2;
        if(classType == "Knight")
        {
            originalDamage = 3;
            img = new GreenfootImage("knight.png");
        }
        else if (classType == "Thief")
        {
            originalDamage = 3;
            img = new GreenfootImage("thief.png");
        }
        else if (classType == "Mage")
        {
            originalDamage = 2;
            img = new GreenfootImage("mage.png");
        }
        else if (classType == "Necromancer")
        {
            originalDamage = 2;
            img = new GreenfootImage("necromancer.png");
        }
        else if (classType == "Dragon")
        {
            originalDamage = 2;
            img = new GreenfootImage("dragon.png");
        }
        else if (classType == "Archer")
        {
            originalDamage = 2;
            img = new GreenfootImage("archer.png");
        }
        else if (classType == "Gorgon")
        {
            originalDamage = 2;
            img = new GreenfootImage("gorgon.png");
        }
        img.scale(50,50);
        updateImage();
    }

    /**
     * Act - do whatever the Hero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateImage();
        clickDetection();
        deathDetection();
    } 

    /**
     * GetSelected
     * 
     * Gets whether the hero is selected or not
     * 
     * @param None There are no parameters
     * @return Returns a boolean
     */
    public boolean getSelected()
    {
        return selected;
    }

    /**
     * ClickDetection
     * 
     * Detects when the hero is clicked
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
                unselect();
            }
        }
    }

    /**
     * UpdateImage
     * 
     * Updates the image of the hero
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void updateImage()
    {
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
        else
        {
            img2.setColor(Color.WHITE);
        }
        img2.setFont(new Font("Helvetica",25));
        img2.drawString("" + health,img2.getWidth()/2-10,img2.getHeight()/2+20);
        if(invulTurns > 0)
        {
            img2.setColor(new Color(100,100,255,100));
            img2.fillOval(0,0,img2.getWidth()-1,img2.getHeight()-1);
        }
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
        if(invisTurns > 0)
        {
            img2.setTransparency(155);
        }
        else
        {
            img2.setTransparency(255);
        }
        if(selected == true)
        {
            img2.scale(70,70);
        }
        setImage(img2);
    }

    /**
     * AddMove
     * 
     * Adds MovePosition objects to all hexes in movement range
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void addMove()
    {
        if(slowTurns <= 0 && ability2 != "Haste" && ability2 != "Quick Tail")
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
        else if (slowTurns > 0)
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
        else if (ability2 == "Haste" || ability2 == "Quick Tail")
        {
            List<Hexagon> hex = getObjectsInRange(197,Hexagon.class);
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
     * AddAttack
     * 
     * Adds AttackPosition objects to all enemies in range
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
        if(classType == "Knight" || classType == "Thief")
        {
            if(!heroes1.isEmpty())
            {
                for(int i = 0;i < heroes1.size();i++)
                {
                    damage = changedDamage;
                    if(heroes1.get(i).getAbility2() == "Protective Aura")
                    {
                        damage--;
                    }
                    getWorld().addObject(new AttackPosition(heroes1.get(i),this,damage),heroes1.get(i).getX(),heroes1.get(i).getY());
                }
            }
            if(!builds1.isEmpty())
            {
                for(int i = 0;i < builds1.size();i++)
                {
                    if(builds1.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(builds1.get(i),this,damage),builds1.get(i).getX(),builds1.get(i).getY());
                    } 
                }
            }
            if(!soldiers1.isEmpty())
            {
                for(int i = 0;i < soldiers1.size();i++)
                {
                    if(soldiers1.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(soldiers1.get(i),this,damage),soldiers1.get(i).getX(),soldiers1.get(i).getY());
                    } 
                }
            }
        }
        else if (classType == "Mage" || classType == "Necromancer")
        {
            if(!heroes2.isEmpty())
            {
                for(int i = 0;i < heroes2.size();i++)
                {
                    damage = changedDamage;
                    if(heroes2.get(i).getAbility2() == "Protective Aura")
                    {
                        damage--;
                    }
                    getWorld().addObject(new AttackPosition(heroes2.get(i),this,damage),heroes2.get(i).getX(),heroes2.get(i).getY());
                }
            }
            if(!builds2.isEmpty())
            {
                for(int i = 0;i < builds2.size();i++)
                {
                    if(builds2.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(builds2.get(i),this,damage),builds2.get(i).getX(),builds2.get(i).getY());
                    } 
                }
            }
            if(!soldiers2.isEmpty())
            {
                for(int i = 0;i < soldiers2.size();i++)
                {
                    if(soldiers2.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(soldiers2.get(i),this,damage),soldiers2.get(i).getX(),soldiers2.get(i).getY());
                    } 
                }
            }
        }
        else if (classType == "Archer" || classType == "Dragon" || classType == "Gorgon")
        {
            if(!heroes3.isEmpty())
            {
                for(int i = 0;i < heroes3.size();i++)
                {
                    damage = changedDamage;
                    if(heroes3.get(i).getAbility2() == "Protective Aura")
                    {
                        damage--;
                    }
                    getWorld().addObject(new AttackPosition(heroes3.get(i),this,damage),heroes3.get(i).getX(),heroes3.get(i).getY());
                }
            }
            if(!builds3.isEmpty())
            {
                for(int i = 0;i < builds3.size();i++)
                {
                    if(builds3.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(builds3.get(i),this,damage),builds3.get(i).getX(),builds3.get(i).getY());
                    } 
                }
            }
            if(!soldiers3.isEmpty())
            {
                for(int i = 0;i < soldiers3.size();i++)
                {
                    if(soldiers3.get(i).getPlayer() != player)
                    {
                        getWorld().addObject(new AttackPosition(soldiers3.get(i),this,damage),soldiers3.get(i).getX(),soldiers3.get(i).getY());
                    } 
                }
            }
        }
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
     * SetMoved
     * 
     * Sets the moved variable to a new value
     * 
     * @param b The value to set moved
     * @return Returns nothing
     */
    public void setMoved(boolean b)
    {
        moved = b;
    }

    /**
     * SetAttacked
     * 
     * Sets the attacked variable to a new value
     * 
     * @param b The value to set attacked
     * @return Returns nothing
     */
    public void setAttacked(boolean b)
    {
        attacked = b;
    }

    /**
     * Unselect
     * 
     * Unselects the hero
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void unselect()
    {
        selected = false;
        removeMove();
        removeAttack();
        getWorld().removeObjects(getWorld().getObjects(BuildPosition.class));
    }

    /**
     * GetPlayer
     * 
     * Gets the owner of the hero
     * 
     * @param None There are no parameters
     * @return Returns an integer
     */
    public int getPlayer()
    {
        return player;
    }

    /**
     * ChangeHP
     * 
     * Changes the hero's health by a set amount
     * 
     * @param amount The amount to change
     * @return Returns nothing
     */
    public void changeHP(int amount)
    {
        List<AttackPosition> attack = getObjectsInRange(10,AttackPosition.class);
        if(attack.isEmpty())
        {
            health+=amount;
            if(health > 20)
            {
                health = 20;
            }
            else
            {
                getWorld().addObject(new DamageDisplay(amount),getX(),getY()-30);
            }
        }
        else
        {
            getWorld().addObject(new AttackAnimation(),getX(),getY());
            if(invulTurns <= 0 & invisTurns <= 0 & amount < 0)
            {
                if(ability2 == "Evade")
                {
                    if(Greenfoot.getRandomNumber(5) != 0)
                    {
                        health+=amount;
                        if(attack.get(0).getAttacker() != null)
                        {
                            if(attack.get(0).getAttacker().getAbility2() == "Vampiric Fang" && attack.get(0).getAbility() != "drain")
                            {
                                attack.get(0).getAttacker().changeHP(1);
                            }
                        }
                        getWorld().addObject(new DamageDisplay(amount),getX(),getY()-30);
                    }
                    else
                    {
                        getWorld().addObject(new DamageDisplay("Miss"),getX(),getY()-30);
                    }
                }
                else if (ability2 == "Vanishing Act")
                {
                    if(passiveCooldown > 0)
                    {
                        health+=amount;
                        if(attack.get(0).getAttacker() != null)
                        {
                            if(attack.get(0).getAttacker().getAbility2() == "Vampiric Fang" && attack.get(0).getAbility() != "drain")
                            {
                                attack.get(0).getAttacker().changeHP(1);
                            }
                        }
                        getWorld().addObject(new DamageDisplay(amount),getX(),getY()-30);
                    }
                    else
                    {
                        invisTurns = 1;
                        passiveCooldown = 5;
                        getWorld().addObject(new DamageDisplay("Miss"),getX(),getY()-30);
                    }
                }
                else
                {
                    health+=amount;
                    if(attack.get(0).getAttacker() != null)
                    {
                        if(attack.get(0).getAttacker().getAbility2() == "Vampiric Fang" && attack.get(0).getAbility() != "drain")
                        {
                            attack.get(0).getAttacker().changeHP(1);
                        }
                    }
                    getWorld().addObject(new DamageDisplay(amount),getX(),getY()-30);
                }
            }
            else if (invulTurns > 0 && amount < 0)
            {
                getWorld().addObject(new DamageDisplay("Block"),getX(),getY()-30);
            }
            else if (invisTurns > 0 && amount < 0)
            {
                getWorld().addObject(new DamageDisplay("Miss"),getX(),getY()-30);
            }
            else if (amount > 0)
            {
                health+=amount;
                if(attack.get(0).getAttacker() != null)
                {
                    if(attack.get(0).getAttacker().getAbility2() == "Vampiric Fang" && attack.get(0).getAbility() != "drain")
                    {
                        attack.get(0).getAttacker().changeHP(1);
                    }
                }
                getWorld().addObject(new DamageDisplay(amount),getX(),getY()-30);
            }
        }
    }

    /**
     * DeathDetection
     * 
     * Detects if the hero is at 0 or less health, "dead"
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void deathDetection()
    {
        if (health <= 0)
        {
            List<Buildings> builds = getWorld().getObjects(Buildings.class);
            for(int i = 0;i < builds.size();i++)
            {
                if(builds.get(i).getPlayer() == player)
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
            ((MyWorld)getWorld()).lose(player);
            getWorld().removeObject(this);
        }
    }

    /**
     * GetClassType
     * 
     * Gets which class the hero is
     * 
     * @param None There are no parameters
     * @return Returns a string
     */
    public String getClassType()
    {
        return classType;
    }

    /**
     * GetAbility1
     * 
     * Gets the active ability of the hero
     * 
     * @param None There are no parameters
     * @return Returns a string
     */
    public String getAbility1()
    {
        return ability1;
    }

    /**
     * GetAbility2
     * 
     * Gets the passive ability of the hero
     * 
     * @param None There are no parameters
     * @return Returns a string
     */
    public String getAbility2()
    {
        return ability2;
    }

    /**
     * SetCooldown
     * 
     * Sets the cooldown of the hero's active ability
     * 
     * @param c The duration of the cooldown
     * @return Returns nothing
     */
    public void setCooldown(int c)
    {
        abilityCooldown = c;
    }

    /**
     * GetCooldown
     * 
     * Gets the cooldown of the hero's active ability
     * 
     * @param None There are no parameters
     * @return Returns an integer
     */
    public int getCooldown()
    {
        return abilityCooldown;
    }

    /**
     * GetStun
     * 
     * Gets if the hero is stunned or not
     * 
     * @param None There are no parameters
     * @return Returns a boolean
     */
    public boolean getStun()
    {
        if(stunTurns > 0)
        {
            return true;
        }
        return false;
    }

    /**
     * UseAbility
     * 
     * Activates the hero's active ability
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void useAbility()
    {
        if(moved == false && stunTurns <= 0 && selected == false)
        {
            if(ability1 == "Flight")
            {
                List<Hexagon> hex = getWorld().getObjects(Hexagon.class);
                for(int i = 0;i < hex.size();i++)
                {
                    getWorld().addObject(new MovePosition(this,true),hex.get(i).getX(),hex.get(i).getY());
                }
                selected = true;
            }
        }
        if(attacked == false && stunTurns <= 0 && selected == false)
        {
            if(ability1 == "Earthquake")
            {
                List<Hero> heroes = getObjectsInRange(75,Hero.class);
                List<Soldier> soldiers = getObjectsInRange(75,Soldier.class);
                if(!heroes.isEmpty() || !soldiers.isEmpty())
                {
                    if(!heroes.isEmpty())
                    {
                        for(int i = 0;i < heroes.size();i++)
                        {
                            heroes.get(i).changeHP(-3);
                            getWorld().addObject(new AttackAnimation(),heroes.get(i).getX(),heroes.get(i).getY());
                        }
                    }
                    if(!soldiers.isEmpty())
                    {
                        for(int i = 0;i < soldiers.size();i++)
                        {
                            if(soldiers.get(i).getPlayer() != player)
                            {
                                soldiers.get(i).changeHP(-3);
                                getWorld().addObject(new AttackAnimation(),soldiers.get(i).getX(),soldiers.get(i).getY());
                            }
                        }
                    }
                    attacked = true;
                    abilityCooldown = 3;
                }
            }
            if(ability1 == "Gladiator Shield")
            {
                invulTurns = 1;
                abilityCooldown = 3;
            }
            if(ability1 == "Stunning Blow")
            {
                List<Hero> heroes = getObjectsInRange(75,Hero.class);
                List<Soldier> soldiers = getObjectsInRange(75,Soldier.class);
                if(!heroes.isEmpty() || !soldiers.isEmpty())
                {
                    ((MyWorld)getWorld()).unselectAll();
                    if(!heroes.isEmpty())
                    {
                        for(int i = 0;i < heroes.size();i++)
                        {
                            getWorld().addObject(new AttackPosition(heroes.get(i),this,"stun"),heroes.get(i).getX(),heroes.get(i).getY());
                        }
                    }
                    if(!soldiers.isEmpty())
                    {
                        for(int i = 0;i < soldiers.size();i++)
                        {
                            if(soldiers.get(i).getPlayer() != player)
                            {
                                getWorld().addObject(new AttackPosition(soldiers.get(i),this,"stun"),soldiers.get(i).getX(),soldiers.get(i).getY());
                            }
                        }
                    }
                    selected = true;
                }
            }
            if(ability1 == "Thunder Nova")
            {
                List<Hero> heroes = getObjectsInRange(150,Hero.class);
                List<Soldier> soldiers = getObjectsInRange(150,Soldier.class);
                if(!heroes.isEmpty() || !soldiers.isEmpty())
                {
                    if(!heroes.isEmpty())
                    {
                        for(int i = 0;i < heroes.size();i++)
                        {
                            heroes.get(i).changeHP(-2);
                            getWorld().addObject(new AttackAnimation(),heroes.get(i).getX(),heroes.get(i).getY());
                        }
                    }
                    if(!soldiers.isEmpty())
                    {
                        for(int i = 0;i < soldiers.size();i++)
                        {
                            if(soldiers.get(i).getPlayer() != player)
                            {
                                soldiers.get(i).changeHP(-2);
                                getWorld().addObject(new AttackAnimation(),soldiers.get(i).getX(),soldiers.get(i).getY());
                            }
                        }
                    }
                    attacked = true;
                    abilityCooldown = 3;
                }
            }
            if(ability1 == "Sunlit Inferno")
            {
                List<Hero> heroes = getObjectsInRange(150,Hero.class);
                List<Soldier> soldiers = getObjectsInRange(150,Soldier.class);
                if(!heroes.isEmpty() || !soldiers.isEmpty())
                {
                    ((MyWorld)getWorld()).unselectAll();
                    if(!heroes.isEmpty())
                    {
                        for(int i = 0;i < heroes.size();i++)
                        {
                            getWorld().addObject(new AttackPosition(heroes.get(i),this,"inferno"),heroes.get(i).getX(),heroes.get(i).getY());
                        }
                    }
                    if(!soldiers.isEmpty())
                    {
                        for(int i = 0;i < soldiers.size();i++)
                        {
                            if(soldiers.get(i).getPlayer() != player)
                            {
                                getWorld().addObject(new AttackPosition(soldiers.get(i),this,"inferno"),soldiers.get(i).getX(),soldiers.get(i).getY());
                            }
                        }
                    }
                    selected = true;
                }
            }
            if(ability1 == "Icicle")
            {
                List<Hero> heroes = getObjectsInRange(150,Hero.class);
                List<Soldier> soldiers = getObjectsInRange(150,Soldier.class);
                if(!heroes.isEmpty() || !soldiers.isEmpty())
                {
                    ((MyWorld)getWorld()).unselectAll();
                    if(!heroes.isEmpty())
                    {
                        for(int i = 0;i < heroes.size();i++)
                        {
                            getWorld().addObject(new AttackPosition(heroes.get(i),this,"icicle"),heroes.get(i).getX(),heroes.get(i).getY());
                        }
                    }
                    if(!soldiers.isEmpty())
                    {
                        for(int i = 0;i < soldiers.size();i++)
                        {
                            if(soldiers.get(i).getPlayer() != player)
                            {
                                getWorld().addObject(new AttackPosition(soldiers.get(i),this,"icicle"),soldiers.get(i).getX(),soldiers.get(i).getY());
                            }
                        }
                    }
                    selected = true;
                }
            }
            if(ability1 == "Drain")
            {
                List<Hero> heroes = getObjectsInRange(150,Hero.class);
                List<Soldier> soldiers = getObjectsInRange(150,Soldier.class);
                if(!heroes.isEmpty() || !soldiers.isEmpty())
                {
                    ((MyWorld)getWorld()).unselectAll();
                    if(!heroes.isEmpty())
                    {
                        for(int i = 0;i < heroes.size();i++)
                        {
                            getWorld().addObject(new AttackPosition(heroes.get(i),this,"drain"),heroes.get(i).getX(),heroes.get(i).getY());
                        }
                    }
                    if(!soldiers.isEmpty())
                    {
                        for(int i = 0;i < soldiers.size();i++)
                        {
                            if(soldiers.get(i).getPlayer() != player)
                            {
                                getWorld().addObject(new AttackPosition(soldiers.get(i),this,"drain"),soldiers.get(i).getX(),soldiers.get(i).getY());
                            }
                        }
                    }
                    selected = true;
                }
            }
            if(ability1 == "Mind Control")
            {
                List<Soldier> soldiers = getObjectsInRange(150,Soldier.class);
                if(!soldiers.isEmpty())
                {
                    ((MyWorld)getWorld()).unselectAll();
                    for(int i = 0;i < soldiers.size();i++)
                    {
                        if(soldiers.get(i).getPlayer() != player)
                        {
                            getWorld().addObject(new AttackPosition(soldiers.get(i),this,"mcontrol"),soldiers.get(i).getX(),soldiers.get(i).getY());
                        }
                    }
                    selected = true;
                }
            }
            if(ability1 == "Skeletal Warrior")
            {
                List<Hexagon> hex = getObjectsInRange(75,Hexagon.class);
                if(!hex.isEmpty())
                {
                    ((MyWorld)getWorld()).unselectAll();
                    for(int i = 0;i < hex.size();i++)
                    {
                        getWorld().addObject(new BuildPosition("skeleton",player),hex.get(i).getX(),hex.get(i).getY());
                    }
                    selected = true;
                }
            }
            if(ability1 == "Backstab")
            {
                List<Hero> heroes = getObjectsInRange(75,Hero.class);
                List<Soldier> soldiers = getObjectsInRange(75,Soldier.class);
                if(!heroes.isEmpty() || !soldiers.isEmpty())
                {
                    ((MyWorld)getWorld()).unselectAll();
                    if(!heroes.isEmpty())
                    {
                        for(int i = 0;i < heroes.size();i++)
                        {
                            getWorld().addObject(new AttackPosition(heroes.get(i),this,"backstab"),heroes.get(i).getX(),heroes.get(i).getY());
                        }
                    }
                    if(!soldiers.isEmpty())
                    {
                        for(int i = 0;i < soldiers.size();i++)
                        {
                            if(soldiers.get(i).getPlayer() != player)
                            {
                                getWorld().addObject(new AttackPosition(soldiers.get(i),this,"backstab"),soldiers.get(i).getX(),soldiers.get(i).getY());
                            }
                        }
                    }
                    selected = true;
                }
            }
            if(ability1 == "Smoke Bomb")
            {
                invisTurns = 3;
                abilityCooldown = 5;
            }
            if(ability1 == "Poison Stab")
            {
                List<Hero> heroes = getObjectsInRange(75,Hero.class);
                List<Soldier> soldiers = getObjectsInRange(75,Soldier.class);
                if(!heroes.isEmpty() || !soldiers.isEmpty())
                {
                    ((MyWorld)getWorld()).unselectAll();
                    if(!heroes.isEmpty())
                    {
                        for(int i = 0;i < heroes.size();i++)
                        {
                            getWorld().addObject(new AttackPosition(heroes.get(i),this,"poisonstab"),heroes.get(i).getX(),heroes.get(i).getY());
                        }
                    }
                    if(!soldiers.isEmpty())
                    {
                        for(int i = 0;i < soldiers.size();i++)
                        {
                            if(soldiers.get(i).getPlayer() != player)
                            {
                                getWorld().addObject(new AttackPosition(soldiers.get(i),this,"poisonstab"),soldiers.get(i).getX(),soldiers.get(i).getY());
                            }
                        }
                    }
                    selected = true;
                }
            }
            if(ability1 == "Arrow Volley")
            {
                List<Hero> heroes = getObjectsInRange(197,Hero.class);
                List<Soldier> soldiers = getObjectsInRange(197,Soldier.class);
                if(!heroes.isEmpty() || !soldiers.isEmpty())
                {
                    ((MyWorld)getWorld()).unselectAll();
                    if(!heroes.isEmpty())
                    {
                        for(int i = 0;i < heroes.size();i++)
                        {
                            getWorld().addObject(new AttackPosition(heroes.get(i),this,"arrowvolley"),heroes.get(i).getX(),heroes.get(i).getY());
                        }
                    }
                    if(!soldiers.isEmpty())
                    {
                        for(int i = 0;i < soldiers.size();i++)
                        {
                            if(soldiers.get(i).getPlayer() != player)
                            {
                                getWorld().addObject(new AttackPosition(soldiers.get(i),this,"arrowvolley"),soldiers.get(i).getX(),soldiers.get(i).getY());
                            }
                        }
                    }
                    selected = true;
                }
            }
            if(ability1 == "Fatiuged Arrow")
            {
                List<Hero> heroes = getObjectsInRange(197,Hero.class);
                List<Soldier> soldiers = getObjectsInRange(197,Soldier.class);
                if(!heroes.isEmpty() || !soldiers.isEmpty())
                {
                    ((MyWorld)getWorld()).unselectAll();
                    if(!heroes.isEmpty())
                    {
                        for(int i = 0;i < heroes.size();i++)
                        {
                            getWorld().addObject(new AttackPosition(heroes.get(i),this,"slowarrow"),heroes.get(i).getX(),heroes.get(i).getY());
                        }
                    }
                    if(!soldiers.isEmpty())
                    {
                        for(int i = 0;i < soldiers.size();i++)
                        {
                            if(soldiers.get(i).getPlayer() != player)
                            {
                                getWorld().addObject(new AttackPosition(soldiers.get(i),this,"slowarrow"),soldiers.get(i).getX(),soldiers.get(i).getY());
                            }
                        }
                    }
                    selected = true;
                }
            }
            if(ability1 == "Poison Arrow")
            {
                List<Hero> heroes = getObjectsInRange(197,Hero.class);
                List<Soldier> soldiers = getObjectsInRange(197,Soldier.class);
                if(!heroes.isEmpty() || !soldiers.isEmpty())
                {
                    ((MyWorld)getWorld()).unselectAll();
                    if(!heroes.isEmpty())
                    {
                        for(int i = 0;i < heroes.size();i++)
                        {
                            getWorld().addObject(new AttackPosition(heroes.get(i),this,"poisonarrow"),heroes.get(i).getX(),heroes.get(i).getY());
                        }
                    }
                    if(!soldiers.isEmpty())
                    {
                        for(int i = 0;i < soldiers.size();i++)
                        {
                            if(soldiers.get(i).getPlayer() != player)
                            {
                                getWorld().addObject(new AttackPosition(soldiers.get(i),this,"poisonarrow"),soldiers.get(i).getX(),soldiers.get(i).getY());
                            }
                        }
                    }
                    selected = true;
                }
            }
            if(ability1 == "Flame Torrent")
            {
                List<Hero> heroes = getObjectsInRange(197,Hero.class);
                List<Soldier> soldiers = getObjectsInRange(197,Soldier.class);
                if(!heroes.isEmpty() || !soldiers.isEmpty())
                {
                    ((MyWorld)getWorld()).unselectAll();
                    if(!heroes.isEmpty())
                    {
                        for(int i = 0;i < heroes.size();i++)
                        {
                            getWorld().addObject(new AttackPosition(heroes.get(i),this,"flametorrent"),heroes.get(i).getX(),heroes.get(i).getY());
                        }
                    }
                    if(!soldiers.isEmpty())
                    {
                        for(int i = 0;i < soldiers.size();i++)
                        {
                            if(soldiers.get(i).getPlayer() != player)
                            {
                                getWorld().addObject(new AttackPosition(soldiers.get(i),this,"flametorrent"),soldiers.get(i).getX(),soldiers.get(i).getY());
                            }
                        }
                    }
                    selected = true;
                }
            }
            if(ability1 == "Crushing Claw")
            {
                List<Hero> heroes = getObjectsInRange(75,Hero.class);
                List<Soldier> soldiers = getObjectsInRange(75,Soldier.class);
                if(!heroes.isEmpty() || !soldiers.isEmpty())
                {
                    ((MyWorld)getWorld()).unselectAll();
                    if(!heroes.isEmpty())
                    {
                        for(int i = 0;i < heroes.size();i++)
                        {
                            getWorld().addObject(new AttackPosition(heroes.get(i),this,"crushingclaw"),heroes.get(i).getX(),heroes.get(i).getY());
                        }
                    }
                    if(!soldiers.isEmpty())
                    {
                        for(int i = 0;i < soldiers.size();i++)
                        {
                            if(soldiers.get(i).getPlayer() != player)
                            {
                                getWorld().addObject(new AttackPosition(soldiers.get(i),this,"crushingclaw"),soldiers.get(i).getX(),soldiers.get(i).getY());
                            }
                        }
                    }
                    selected = true;
                }
            }
        }
    }

    /**
     * LowerCooldown
     * 
     * Lowers the cooldown of the hero's passive and active abilities
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void lowerCooldown()
    {
        if(abilityCooldown > 0)
        {
            abilityCooldown--;
        }
        if(passiveCooldown > 0)
        {
            passiveCooldown--;
        }
    }

    /**
     * LowerInvul
     * 
     * Decreases the duration of invulnerability by one
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void lowerInvul()
    {
        if(invulTurns > 0)
        {
            invulTurns--;
        }
    }

    /**
     * LowerStun
     * 
     * Decreases the duration of stun by one
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
     * StunAttack
     * 
     * Stuns the hero for two turns
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void stunAttack()
    {
        if(invulTurns <= 0 & invisTurns <= 0)
        {
            stunTurns = 2;
        }
    }

    /**
     * SetFire
     * 
     * Sets the hero on fire for three turns
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void setFire()
    {
        if(invulTurns <= 0 & invisTurns <= 0)
        {
            fireTurns = 3;
        }
    }

    /**
     * Fire
     * 
     * Decreases the duration of fire by one and damages the hero
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void fire()
    {
        if(fireTurns > 0)
        {
            health--;
            getWorld().addObject(new DamageDisplay(-1),getX(),getY()-30);
            fireTurns--;
        }
    }

    /**
     * Invis
     * 
     * Decreases the duration of invisibility by one
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void invis()
    {
        if(invisTurns > 0)
        {
            invisTurns--;
        }
    }

    /**
     * BreakInvis
     * 
     * Sets the duration of invisibility to zero
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void breakInvis()
    {
        invisTurns = 0;
    }

    /**
     * PoisonAttack
     * 
     * Poisons the hero for three turns
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void poisonAttack()
    {
        if(invulTurns <= 0 & invisTurns <= 0)
        {
            poisonTurns = 3;
        }
    }

    /**
     * Poison
     * 
     * Decreases the duration of the poison status effect by one and damages the hero by 2
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void poison()
    {
        if(poisonTurns > 0)
        {
            health-=2;
            getWorld().addObject(new DamageDisplay(-2),getX(),getY()-30);
            poisonTurns--;
        }
    }

    /**
     * Cure
     * 
     * Removes all negative status effects
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void cure()
    {
        poisonTurns = 0;
        fireTurns = 0;
        stunTurns = 0;
        slowTurns = 0;
    }

    /**
     * Slow
     * 
     * Decreases the duration of the slow status effect by one
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
     * SlowAttack
     * 
     * Slows the hero for three turns
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void slowAttack()
    {
        if(invulTurns <= 0 & invisTurns <= 0)
        {
            slowTurns = 3;
        }
    }

    /**
     * YourTurn
     * 
     * Runs when the owner of the hero's turn starts
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
            passive();
            moved = false;
            attacked = false;
        }
        fire();
        slow();
        poison();
        invis();
        lowerCooldown();
        lowerInvul();
        if(health <= 0)
        {
            ((MyWorld)getWorld()).nextTurn();
        }
    }

    /**
     * Passive
     * 
     * Activates the hero's passive when their turn starts
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void passive()
    {
        if(ability2 == "Burning Field" || ability2 == "Oven")
        {
            List<Hero> heroes = getObjectsInRange(75,Hero.class);
            List<Soldier> soldiers = getObjectsInRange(75,Soldier.class);
            if(!heroes.isEmpty())
            {
                for(int i = 0;i < heroes.size();i++)
                {
                    heroes.get(i).setFire();
                }
            }
            if(!soldiers.isEmpty())
            {
                for(int i = 0;i < soldiers.size();i++)
                {
                    if(soldiers.get(i).getPlayer() != player)
                    {
                        soldiers.get(i).setFire();
                    }
                }
            }
        }
        if(ability2 == "Healing Aura")
        {
            changeHP(1);
            List<Soldier> soldiers = getObjectsInRange(75,Soldier.class);
            if(!soldiers.isEmpty())
            {
                for(int i = 0;i < soldiers.size();i++)
                {
                    if(soldiers.get(i).getPlayer() == player)
                    {
                        soldiers.get(i).changeHP(1);
                    }
                }
            }
        }
        if(ability2 == "Slowing Storm")
        {
            List<Hero> heroes = getObjectsInRange(75,Hero.class);
            List<Soldier> soldiers = getObjectsInRange(75,Soldier.class);
            if(!heroes.isEmpty())
            {
                for(int i = 0;i < heroes.size();i++)
                {
                    heroes.get(i).slowAttack();
                }
            }
            if(!soldiers.isEmpty())
            {
                for(int i = 0;i < soldiers.size();i++)
                {
                    if(soldiers.get(i).getPlayer() != player)
                    {
                        soldiers.get(i).slowAttack();
                    }
                }
            }
        }
        if(ability2 == "Draining Aura")
        {
            boolean heal = false;
            List<Hero> heroes = getObjectsInRange(75,Hero.class);
            List<Soldier> soldiers = getObjectsInRange(75,Soldier.class);
            if(!heroes.isEmpty())
            {
                for(int i = 0;i < heroes.size();i++)
                {
                    heroes.get(i).changeHP(-1);
                    heal = true;
                }
            }
            if(!soldiers.isEmpty())
            {
                for(int i = 0;i < soldiers.size();i++)
                {
                    if(soldiers.get(i).getPlayer() != player)
                    {
                        soldiers.get(i).changeHP(-1);
                        heal = true;
                    }
                }
            }
            if(heal == true)
            {
                changeHP(1);
            }
        }
        if(ability2 == "Mend" || ability2 == "Regeneration")
        {
            changeHP(1);
        }
    }

    /**
     * NearbyTerrain
     * 
     * Gets terrain next to the hero
     * 
     * @param None There are no parameters
     * @return Returns a list
     */
    public List nearbyTerrain()
    {
        return getObjectsInRange(75,Terrain.class);
    }

    /**
     * NearbyBuildings
     * 
     * Gets buildings next to the hero
     * 
     * @param None There are no parameters
     * @return Returns a list
     */
    public List nearbyBuildings()
    {
        return getObjectsInRange(75,Buildings.class);
    }

    /**
     * NearbyHex
     * 
     * Gets hexagons next to the hero
     * 
     * @param None There are no parameters
     * @return Returns a list
     */
    public List nearbyHex()
    {
        return getObjectsInRange(75,Hexagon.class);
    }

    /**
     * Heal
     * 
     * Passively heals the hero when next to a building
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void heal()
    {
        boolean heal = false;
        List<Buildings> nearbyBuildings = nearbyBuildings();
        if(!nearbyBuildings.isEmpty())
        {
            for(int i = 0;i < nearbyBuildings.size();i++)
            {
                if(nearbyBuildings.get(i).getPlayer() == player)
                {
                    heal = true;
                }
            }
            if(heal == true)
            {
                changeHP(1);
            }
        }
    }

    /**
     * Weak
     * 
     * Makes the hero do less damage when certain conditions are met
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
                if(nearbyHeroes.get(i).getAbility2() == "Disarming Field")
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
     * Makes the hero do more damage when certain conditions are met
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void strong()
    {
        boolean strong = false;
        if(ability2 == "War Banner")
        {
            strong = true;
        }
        if(invisTurns > 0)
        {
            strong = true;
        }
        if(ability2 == "Beastly Might")
        {
            strong = true;
        }
        if(strong == true)
        {
            damage++;
        }
    }
}
