import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Customization here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customization extends Actor
{
    private int player; // The player that owns the menu
    // The array of images for displaying
    private static GreenfootImage classImage[] = {new GreenfootImage("knight.png"), new GreenfootImage("mage.png"), new GreenfootImage("necromancer.png"), new GreenfootImage("thief.png"), new GreenfootImage("archer.png"), new GreenfootImage("dragon.png"), new GreenfootImage("gorgon.png"), new GreenfootImage("summoner.png"), new GreenfootImage("gambler.png"), new GreenfootImage("warlord.png")};
    private String classType[] = {"Knight","Mage","Necromancer","Thief","Archer","Dragon","Gorgon", "Summoner", "Gambler","Warlord"}; // The array of class types
    private int currentClass = Greenfoot.getRandomNumber(classType.length); // The current class being displayed
    private String ability1[] = {"Earthquake","Gladiator Shield","Stunning Blow"}; // The array of active abilities
    private int currentAbility1 = Greenfoot.getRandomNumber(3); // The active ability being displayed
    private String ability2[] = {"War Banner","Protective Aura","Thorned Armor"}; // The array of passive abilities
    private int currentAbility2 = Greenfoot.getRandomNumber(3); // The passive ability being displayed
    /**
     * The constructor for the Customization object
     * 
     * @param p The player that owns the object
     */
    public Customization(int p)
    {
        for(int i = 0;i < classImage.length;i++)
        {
            classImage[i].scale(150,150);
        }
        player = p;
        updateAbilities();
    }
    /**
     * Act - do whatever the Customization wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateAbilities();
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
        GreenfootImage img = new GreenfootImage(170,400);
        if(player == 1)
        {
            img.setColor(new Color(255,0,0,150));
            img.fillRect(0,0,img.getWidth(),img.getHeight());
            img.setColor(new Color(255,50,50,150));
            img.drawRect(0,0,img.getWidth()-2,img.getHeight()-2);
        }
        if(player == 2)
        {
            img.setColor(new Color(0,255,0,150));
            img.fillRect(0,0,img.getWidth(),img.getHeight());
            img.setColor(new Color(50,255,50,150));
            img.drawRect(0,0,img.getWidth()-2,img.getHeight()-2);
        }
        if(player == 3)
        {
            img.setColor(new Color(0,0,255,150));
            img.fillRect(0,0,img.getWidth(),img.getHeight());
            img.setColor(new Color(50,50,255,150));
            img.drawRect(0,0,img.getWidth()-2,img.getHeight()-2);
        }
        if(player == 4)
        {
            img.setColor(new Color(200,200,200,150));
            img.fillRect(0,0,img.getWidth(),img.getHeight());
            img.setColor(new Color(255,255,255,150));
            img.drawRect(0,0,img.getWidth()-2,img.getHeight()-2);
        }
        img.setColor(Color.WHITE);
        img.setFont(new Font("Helvetica",15));
        img.drawString(((MyWorld)getWorld()).getPlayerName(player),img.getWidth()/2-((MyWorld)getWorld()).getPlayerName(player).length()*10/2,20);
        img.drawString("Class:\n" + classType[currentClass],img.getWidth()/2-40,50);
        img.drawString("Ability 1:\n" + ability1[currentAbility1],img.getWidth()/2-40,120); 
        img.drawString("Ability 2:\n" + ability2[currentAbility2],img.getWidth()/2-40,190);
        img.drawImage(classImage[currentClass],img.getWidth()/2-classImage[currentClass].getWidth()/2,img.getHeight()-classImage[currentClass].getHeight());
        
        setImage(img);
    }
    /**
     * PreviousClass
     * 
     * Switches to the previous class, and to the last class if no more previous classes are available
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void previousClass()
    {
        if(currentClass > 0)
        {
            currentClass--;
        }
        else
        {
            currentClass = classType.length-1;
        }
    }
    /**
     * NextClass
     * 
     * Switches to the next class, and the first one if no more are available
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void nextClass()
    {
        if(currentClass < classType.length-1)
        {
            currentClass++;
        }
        else
        {
            currentClass = 0;
        }
    }
    /**
     * PreviousAbility
     * 
     * Switches the set ability to the previous one, and to the last one if no more are available
     * 
     * @param id The ability to change
     * @return Returns nothing
     */
    public void previousAbility(int id)
    {
        if(id == 1)
        {
            if(currentAbility1 > 0)
            {
                currentAbility1--;
            }
            else
            {
                currentAbility1 = 2;
            }
        }
        else
        {
            if(currentAbility2 > 0)
            {
                currentAbility2--;
            }
            else
            {
                currentAbility2 = 2;
            }
        }
    }
    /**
     * NextAbility
     * 
     * Switches the set ability to the next one, and to the first one if no more are available
     * 
     * @param id The ability to change
     * @return Returns nothing
     */
    public void nextAbility(int id)
    {
        if(id == 1)
        {
            if(currentAbility1 < 2)
            {
                currentAbility1++;
            }
            else
            {
                currentAbility1 = 0;
            }
        }
        else
        {
            if(currentAbility2 < 2)
            {
                currentAbility2++;
            }
            else
            {
                currentAbility2 = 0;
            }
        }
    }
    /**
     * GetPlayer
     * 
     * Gets the owner of the menu
     * 
     * @param None There are no parameters
     * @return Returns the value of the "player" variable
     */
    public int getPlayer()
    {
        return player;
    }
    /**
     * UpdateAbilities
     * 
     * Updates the abilities depending on which class is selected
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void updateAbilities()
    {
        if(classType[currentClass] == "Knight")
        {
            ability1[0] = "Earthquake";
            ability1[1] = "Gladiator Shield";
            ability1[2] = "Stunning Blow";
            
            ability2[0] = "War Banner";
            ability2[1] = "Protective Aura";
            ability2[2] = "Thorned Armour";
        }
        else if (classType[currentClass] == "Mage")
        {
            ability1[0] = "Thunder Nova";
            ability1[1] = "Sunlit Inferno";
            ability1[2] = "Icicle";
            
            ability2[0] = "Healing Aura";
            ability2[1] = "Burning Field";
            ability2[2] = "Slowing Storm";
        }
        else if (classType[currentClass] == "Necromancer")
        {
            ability1[0] = "Skeletal Warrior";
            ability1[1] = "Drain";
            ability1[2] = "Mind Control";
            
            ability2[0] = "Vampiric Fang";
            ability2[1] = "Draining Aura";
            ability2[2] = "Disarming Field";
        }
        else if (classType[currentClass] == "Thief")
        {
            ability1[0] = "Backstab";
            ability1[1] = "Smoke Bomb";
            ability1[2] = "Poison Stab";
            
            ability2[0] = "Thievery";
            ability2[1] = "Vanishing Act";
            ability2[2] = "Evade";
        }
        else if (classType[currentClass] == "Archer")
        {
            ability1[0] = "Arrow Volley";
            ability1[1] = "Fatiuged Arrow";
            ability1[2] = "Poison Arrow";
            
            ability2[0] = "Haste";
            ability2[1] = "Return Fire";
            ability2[2] = "Mend";
        }
        else if (classType[currentClass] == "Dragon")
        {
            ability1[0] = "Flight";
            ability1[1] = "Flame Torrent";
            ability1[2] = "Crushing Claw";
            
            ability2[0] = "Oven";
            ability2[1] = "Regeneration";
            ability2[2] = "Beastly Might";
        }
        else if (classType[currentClass] == "Gorgon")
        {
            ability1[0] = "Toxic Bite";
            ability1[1] = "Cursed Gaze";
            ability1[2] = "Coil";
            
            ability2[0] = "Quick Tail";
            ability2[1] = "Drugged Arrows";
            ability2[2] = "Sharpened Claws";
        }
        else if (classType[currentClass] == "Summoner")
        {
            ability1[0] = "Summon Golem";
            ability1[1] = "Summon Phoenix";
            ability1[2] = "Summon Serpent";
            
            ability2[0] = "Rapid Summoning";
            ability2[1] = "War Constructs";
            ability2[2] = "Tough Runes";
        }
        else if (classType[currentClass] == "Gambler")
        {
            ability1[0] = "Dice Roll";
            ability1[1] = "Blackjack";
            ability1[2] = "Russian Roulette";
            
            ability2[0] = "Healing Roulette";
            ability2[1] = "Effect Slots";
            ability2[2] = "Double or Nothing";
        }
        else if (classType[currentClass] == "Warlord")
        {
            ability1[0] = "Shattering Blow";
            ability1[1] = "Pinning Strike";
            ability1[2] = "Leap";
            
            ability2[0] = "Beserk";
            ability2[1] = "Inspire";
            ability2[2] = "Grace of God";
        }
    }
    /**
     * GetClassType
     * 
     * Gets the current class type
     * 
     * @param None There are no parameters
     * @return Returns the seleted index of the "classType" array
     */
    public String getClassType()
    {
        return classType[currentClass];
    }
    /**
     * GetAbility
     * 
     * Gets the set ability
     * 
     * @param id The ability to get
     * @return Returns the ability selected by the player from the designated array
     */
    public String getAbility(int id)
    {
        if (id == 1)
        {
            return ability1[currentAbility1];
        }
        return ability2[currentAbility2];
    }
}
