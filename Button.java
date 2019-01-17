import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List; // List functionality
/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private GreenfootImage img; // The image of the object
    private String type; // The type of button
    
    // V Customization buttons only V
    private int player; // The player that owns the button
    private boolean done = false; // If the player is done or not
    private String classType; // The class type of the designated hero
    private String ability1; // The first ability of the designated hero
    private String ability2; // The second ability of the designated hero
    /**
     * A constructor for the Button class
     * 
     * @param t The type of button
     */
    public Button(String t)
    {
        type = t;
        if(type == "start1")
        {
            img = new GreenfootImage("2 Player",50,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "start2")
        {
            img = new GreenfootImage("3 Player",50,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "start3")
        {
            img = new GreenfootImage("4 Player",50,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "turn")
        {
            img = new GreenfootImage("Next Turn",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "build")
        {
            img = new GreenfootImage("Build",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "ability1")
        {
            img = new GreenfootImage(150,150);
            img.setColor(Color.BLACK);
            img.fill();
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "buildSM")
        {
            img = new GreenfootImage("Build Sawmill\n5 Stone",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "buildM")
        {
            img = new GreenfootImage("Build Mine\n5 Wood",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "buildGM")
        {
            img = new GreenfootImage("Build GoldMine\n5 Stone\n5 Wood\n2 Iron",25,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "buildA")
        {
            img = new GreenfootImage("Build Armoury\n3 Iron\n8 Stone",25,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "buildB")
        {
            img = new GreenfootImage("Build Barracks\n3 Iron\n8 Wood",25,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "buildT")
        {
            img = new GreenfootImage("Build Tower\n3 Iron\n8 Gold",25,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "buy")
        {
            img = new GreenfootImage("Buy",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "buyW")
        {
            img = new GreenfootImage("Buy 1 Wood\n2 Gold",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "buyS")
        {
            img = new GreenfootImage("Buy 1 Stone\n2 Gold",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "buyI")
        {
            img = new GreenfootImage("Buy 1 Iron\n4 Gold",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "sellW")
        {
            img = new GreenfootImage("Sell 1 Wood\n2 Gold",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "sellS")
        {
            img = new GreenfootImage("Sell 1 Stone\n2 Gold",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "sellI")
        {
            img = new GreenfootImage("Sell 1 Iron\n4 Gold",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "train")
        {
            img = new GreenfootImage("Train",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "trainA")
        {
            img = new GreenfootImage("Train Archer\n3 Gold",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "trainK")
        {
            img = new GreenfootImage("Train Knight\n4 Gold",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "trainM")
        {
            img = new GreenfootImage("Train Mage\n3 Gold",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        setImage(img);
    }

    /**
     * A constructor for the Button class
     * 
     * @param t The type of button
     * @param p The player that owns the button
     */
    public Button(String t, int p)
    {
        type = t;
        player = p;
        if(type == "nextab2" || type == "nextab1" || type == "nextcl")
        {
            img = new GreenfootImage(" > ",20,Color.WHITE,new Color(0,0,0,0));
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "preab2" || type == "preab1" || type == "precl")
        {
            img = new GreenfootImage(" < ",20,Color.WHITE,new Color(0,0,0,0));
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        if(type == "done")
        {
            img = new GreenfootImage("Done",30,Color.WHITE,Color.BLACK);
            img.setColor(Color.WHITE);
            img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        }
        setImage(img);
    }

    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateAbility();
        clickDetection();
    }   

    /**
     * ClickDetection
     * 
     * Detects if the button was clicked
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void clickDetection()
    {
        if(Greenfoot.mouseClicked(this))
        {
            MyWorld world = ((MyWorld)getWorld());
            if(type == "start1")
            {
                world.start(2);
            }
            if (type == "start2")
            {
                world.start(3);
            }
            if(type == "start3")
            {
                world.start(4);
            }
            if(type == "precl")
            {
                List<Customization> custom = world.getObjects(Customization.class);
                for(int i = 0;i < custom.size();i++)
                {
                    if(custom.get(i).getPlayer() == player)
                    {
                        custom.get(i).previousClass();
                    }
                }
            }
            if(type == "nextcl")
            {
                List<Customization> custom = world.getObjects(Customization.class);
                for(int i = 0;i < custom.size();i++)
                {
                    if(custom.get(i).getPlayer() == player)
                    {
                        custom.get(i).nextClass();
                    }
                }
            }
            if(type == "nextab1")
            {
                List<Customization> custom = world.getObjects(Customization.class);
                for(int i = 0;i < custom.size();i++)
                {
                    if(custom.get(i).getPlayer() == player)
                    {
                        custom.get(i).nextAbility(1);
                    }
                }
            }
            if(type == "preab1")
            {
                List<Customization> custom = world.getObjects(Customization.class);
                for(int i = 0;i < custom.size();i++)
                {
                    if(custom.get(i).getPlayer() == player)
                    {
                        custom.get(i).previousAbility(1);
                    }
                }
            }
            if(type == "nextab2")
            {
                List<Customization> custom = world.getObjects(Customization.class);
                for(int i = 0;i < custom.size();i++)
                {
                    if(custom.get(i).getPlayer() == player)
                    {
                        custom.get(i).nextAbility(2);
                    }
                }
            }
            if(type == "preab2")
            {
                List<Customization> custom = world.getObjects(Customization.class);
                for(int i = 0;i < custom.size();i++)
                {
                    if(custom.get(i).getPlayer() == player)
                    {
                        custom.get(i).previousAbility(2);
                    }
                }
            }
            if(type == "done")
            {
                List<Customization> custom = world.getObjects(Customization.class);
                for(int i = 0;i < custom.size();i++)
                {
                    if(custom.get(i).getPlayer() == player)
                    {
                        done = true;
                        classType = custom.get(i).getClassType();
                        ability1 = custom.get(i).getAbility(1);
                        ability2 = custom.get(i).getAbility(2);
                        img.clear();
                        world.removeObject(custom.get(i));
                        List<Button> button = world.getObjects(Button.class);
                        for(int j = 0;j < button.size();j++)
                        {
                            if(button.get(j).getType() == "precl" || button.get(j).getType() == "nextcl" || button.get(j).getType() == "preab1" || button.get(j).getType() == "preab2" || button.get(j).getType() == "nextab1" || button.get(j).getType() == "nextab2")
                            {
                                if(button.get(j).getPlayer() == player)
                                {
                                    world.removeObject(button.get(j));
                                }
                            }
                        }
                    }
                }
            }
            if(type == "turn")
            {
                world.nextTurn();
            }
            if(type == "ability1")
            {
                for(int i = 0;i < getWorld().getObjects(Hero.class).size();i++)
                {
                    if(getWorld().getObjects(Hero.class).get(i).getPlayer() == ((MyWorld)getWorld()).getTurn())
                    {
                        if(getWorld().getObjects(Hero.class).get(i).getCooldown() <= 0)
                        {
                            getWorld().getObjects(Hero.class).get(i).useAbility();
                        }
                    }
                }
            }
            if(type == "build")
            {
                getWorld().getObjects(UI.class).get(0).toggleBuild();
            }
            if(type == "buildSM")
            {
                build("SM");
            }
            if(type == "buildM")
            {
                build("M");
            }
            if(type == "buildGM")
            {
                build("GM");
            }
            if(type == "buildA")
            {
                build("A");
            }
            if(type == "buildB")
            {
                build("B");
            }
            if(type == "buildT")
            {
                build("T");
            }
            if(type == "buy")
            {
                getWorld().getObjects(UI.class).get(0).toggleBuy();
            }
            if(type == "buyW")
            {
                if(((MyWorld)getWorld()).get("gold",((MyWorld)getWorld()).getTurn()) >= 2)
                {
                    ((MyWorld)getWorld()).change("gold",((MyWorld)getWorld()).getTurn(),-2);
                    ((MyWorld)getWorld()).change("wood",((MyWorld)getWorld()).getTurn(),1);
                }
            }
            if(type == "buyS")
            {
                if(((MyWorld)getWorld()).get("gold",((MyWorld)getWorld()).getTurn()) >= 2)
                {
                    ((MyWorld)getWorld()).change("gold",((MyWorld)getWorld()).getTurn(),-2);
                    ((MyWorld)getWorld()).change("stone",((MyWorld)getWorld()).getTurn(),1);
                }
            }
            if(type == "buyI")
            {
                if(((MyWorld)getWorld()).get("gold",((MyWorld)getWorld()).getTurn()) >= 4)
                {
                    ((MyWorld)getWorld()).change("gold",((MyWorld)getWorld()).getTurn(),-4);
                    ((MyWorld)getWorld()).change("iron",((MyWorld)getWorld()).getTurn(),1);
                }
            }
            if(type == "sellW")
            {
                if(((MyWorld)getWorld()).get("wood",((MyWorld)getWorld()).getTurn()) >= 1)
                {
                    ((MyWorld)getWorld()).change("gold",((MyWorld)getWorld()).getTurn(),2);
                    ((MyWorld)getWorld()).change("wood",((MyWorld)getWorld()).getTurn(),-1);
                }
            }
            if(type == "sellS")
            {
                if(((MyWorld)getWorld()).get("stone",((MyWorld)getWorld()).getTurn()) >= 1)
                {
                    ((MyWorld)getWorld()).change("gold",((MyWorld)getWorld()).getTurn(),2);
                    ((MyWorld)getWorld()).change("stone",((MyWorld)getWorld()).getTurn(),-1);
                }
            }
            if(type == "sellI")
            {
                if(((MyWorld)getWorld()).get("iron",((MyWorld)getWorld()).getTurn()) >= 1)
                {
                    ((MyWorld)getWorld()).change("gold",((MyWorld)getWorld()).getTurn(),4);
                    ((MyWorld)getWorld()).change("iron",((MyWorld)getWorld()).getTurn(),-1);
                }
            }
            if(type == "train")
            {
                getWorld().getObjects(UI.class).get(0).toggleTrain();
            }
            if(type == "trainA")
            {
                train("archer");
            }
            if(type == "trainK")
            {
                train("knight");
            }
            if(type == "trainM")
            {
                train("mage");
            }
        }
    }

    /**
     * Updates the image of the button depending on the current hero's first ability
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void updateAbility()
    {
        if(type == "ability1")
        {
            Hero hero = null;
            for(int i = 0;i < getWorld().getObjects(Hero.class).size();i++)
            {
                if(getWorld().getObjects(Hero.class).get(i).getPlayer() == ((MyWorld)getWorld()).getTurn())
                {
                    hero = getWorld().getObjects(Hero.class).get(i);
                }
            }
            img = new GreenfootImage(120,120);
            img.setColor(Color.BLACK);
            img.fill();
            GreenfootImage img2 = new GreenfootImage(1,1);
            if(hero != null)
            {
                if(hero.getAbility1() == "Earthquake")
                {
                    img2 = new GreenfootImage("earthquake.png");
                }
                if(hero.getAbility1() == "Stunning Blow")
                {
                    img2 = new GreenfootImage("stun.png");
                }
                if(hero.getAbility1() == "Gladiator Shield")
                {
                    img2 = new GreenfootImage("shield.png");
                }
                if(hero.getAbility1() == "Thunder Nova")
                {
                    img2 = new GreenfootImage("lightning.png");
                }
                if(hero.getAbility1() == "Sunlit Inferno")
                {
                    img2 = new GreenfootImage("sunlight.png");
                }
                if(hero.getAbility1() == "Icicle")
                {
                    img2 = new GreenfootImage("icicle.png");
                }
                if(hero.getAbility1() == "Skeletal Warrior")
                {
                    img2 = new GreenfootImage("skeleton.png");
                }
                if(hero.getAbility1() == "Drain")
                {
                    img2 = new GreenfootImage("drain.png");
                }
                if(hero.getAbility1() == "Mind Control")
                {
                    img2 = new GreenfootImage("mindcontrol.png");
                }
                if(hero.getAbility1() == "Gladiator Shield")
                {
                    img2 = new GreenfootImage("shield.png");
                }
                if(hero.getAbility1() == "Backstab")
                {
                    img2 = new GreenfootImage("backstab.png");
                }
                if(hero.getAbility1() == "Smoke Bomb")
                {
                    img2 = new GreenfootImage("smokebomb.png");
                }
                if(hero.getAbility1() == "Poison Stab")
                {
                    img2 = new GreenfootImage("poisonstab.png");
                }
                if(hero.getAbility1() == "Arrow Volley")
                {
                    img2 = new GreenfootImage("arrowvolley.png");
                }
                if(hero.getAbility1() == "Fatiuged Arrow")
                {
                    img2 = new GreenfootImage("slowarrow.png");
                }
                if(hero.getAbility1() == "Poison Arrow")
                {
                    img2 = new GreenfootImage("poisonarrow.png");
                }
                if(hero.getAbility1() == "Flight")
                {
                    img2 = new GreenfootImage("flight.png");
                }
                if(hero.getAbility1() == "Flame Torrent")
                {
                    img2 = new GreenfootImage("firetorrent.png");
                }
                if(hero.getAbility1() == "Crushing Claw")
                {
                    img2 = new GreenfootImage("claw.png");
                }
                if(hero.getAbility1() == "Toxic Bite")
                {
                    img2 = new GreenfootImage("toxicbite.png");
                }
                if(hero.getAbility1() == "Coil")
                {
                    img2 = new GreenfootImage("coil.png");
                }
                if(hero.getAbility1() == "Cursed Gaze")
                {
                    img2 = new GreenfootImage("cursedgaze.png");
                }
                if(hero.getAbility1() == "Summon Golem")
                {
                    img2 = new GreenfootImage("golem.png");
                }
                if(hero.getAbility1() == "Summon Phoenix")
                {
                    img2 = new GreenfootImage("phoenix.png");
                }
                if(hero.getAbility1() == "Summon Serpent")
                {
                    img2 = new GreenfootImage("serpent.png");
                }
                if(hero.getAbility1() == "Dice Roll")
                {
                    img2 = new GreenfootImage("dice.png");
                }
                if(hero.getAbility1() == "Blackjack")
                {
                    img2 = new GreenfootImage("blackjack.png");
                }
                if(hero.getAbility1() == "Russian Roulette")
                {
                    img2 = new GreenfootImage("roulette.png");
                }
                if(hero.getAbility1() == "Leap")
                {
                    img2 = new GreenfootImage("leap.png");
                }
                if(hero.getAbility1() == "Shattering Blow")
                {
                    img2 = new GreenfootImage("shatter.png");
                }
                if(hero.getAbility1() == "Pinning Strike")
                {
                    img2 = new GreenfootImage("pin.png");
                }
                img2.scale(120,120);
                img.drawImage(img2,0,0);
                img.setColor(Color.WHITE);
                img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
                if(hero.getCooldown() > 0 && hero.getCooldown() <= 9)
                {
                    img.setTransparency(155);
                    img.setFont(new Font("Helvetica",120));
                    img.drawString("" + hero.getCooldown(),img.getWidth()/2,img.getHeight());
                }
                if(hero.getCooldown() > 9)
                {
                    img.setTransparency(155);
                    img.setFont(new Font("Helvetica",120));
                    img.drawString("" + hero.getCooldown(),0,img.getHeight());
                }
                setImage(img);
            }
        }
    }

    /**
     * GetType
     * 
     * Gets the current button type
     * 
     * @param None There are no parameters
     * @return Returns string
     */
    public String getType()
    {
        return type;
    }

    /**
     * GetPlayer
     * 
     * Gets the button's owner
     * 
     * @param None There are no parameters
     * @return Returns an int
     */
    public int getPlayer()
    {
        return player;
    }

    /**
     * GetDone
     * 
     * Checks if the done button was clicked
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public boolean getDone()
    {
        return done;
    }

    /**
     * GetHero
     * 
     * Gets a set aspect of the hero being currently saved
     * 
     * @param t The aspect to get
     * @return Returns string
     */
    public String getHero(String t)
    {
        if(t == "class")
        {
            return classType;
        }
        else if(t == "ability1")
        {
            return ability1;
        }
        else if(t == "ability2")
        {
            return ability2;
        }
        return null;
    }

    /**
     * Build
     * 
     * Creates BuildPosition objects nearby the hero depending on what's being built
     * 
     * @param t The type of BuildPosition to create
     * @return Returns nothing
     */
    private void build(String t)
    {
        if(t == "SM")
        {
            boolean selected = false;
            List<BuildPosition> buildPos = getWorld().getObjects(BuildPosition.class);
            if(!buildPos.isEmpty())
            {
                for(int i = 0;i < buildPos.size();i++)
                {
                    if(buildPos.get(i).getType() == "SM")
                    {
                        selected = true;
                    }
                }
            }
            getWorld().removeObjects(buildPos);
            if(selected == false)
            {
                List<Hero> heroes = getWorld().getObjects(Hero.class);
                for(int i = 0;i < heroes.size();i++)
                {
                    if(heroes.get(i).getPlayer() == ((MyWorld)getWorld()).getTurn() && heroes.get(i).getStun() == false)
                    {
                        List<Terrain> nearbyTerrain = heroes.get(i).nearbyTerrain();
                        for(int j = 0;j < nearbyTerrain.size();j++)
                        {
                            if(nearbyTerrain.get(j).getType() == "forest")
                            {
                                getWorld().addObject(new BuildPosition("SM",((MyWorld)getWorld()).getTurn()),nearbyTerrain.get(j).getX(),nearbyTerrain.get(j).getY());
                            }
                        }
                    }
                }
            }
        }
        if(t == "M")
        {
            boolean selected = false;
            List<BuildPosition> buildPos = getWorld().getObjects(BuildPosition.class);
            if(!buildPos.isEmpty())
            {
                for(int i = 0;i < buildPos.size();i++)
                {
                    if(buildPos.get(i).getType() == "M")
                    {
                        selected = true;
                    }
                }
            }
            getWorld().removeObjects(buildPos);
            if(selected == false)
            {
                List<Hero> heroes = getWorld().getObjects(Hero.class);
                for(int i = 0;i < heroes.size();i++)
                {
                    if(heroes.get(i).getPlayer() == ((MyWorld)getWorld()).getTurn() && heroes.get(i).getStun() == false)
                    {
                        List<Terrain> nearbyTerrain = heroes.get(i).nearbyTerrain();
                        for(int j = 0;j < nearbyTerrain.size();j++)
                        {
                            if(nearbyTerrain.get(j).getType() == "rocks")
                            {
                                getWorld().addObject(new BuildPosition("M",((MyWorld)getWorld()).getTurn()),nearbyTerrain.get(j).getX(),nearbyTerrain.get(j).getY());
                            }
                        }
                    }
                }
            }
        }
        if(t == "GM")
        {
            boolean selected = false;
            List<BuildPosition> buildPos = getWorld().getObjects(BuildPosition.class);
            if(!buildPos.isEmpty())
            {
                for(int i = 0;i < buildPos.size();i++)
                {
                    if(buildPos.get(i).getType() == "GM")
                    {
                        selected = true;
                    }
                }
            }
            getWorld().removeObjects(buildPos);
            if(selected == false)
            {
                List<Hero> heroes = getWorld().getObjects(Hero.class);
                for(int i = 0;i < heroes.size();i++)
                {
                    if(heroes.get(i).getPlayer() == ((MyWorld)getWorld()).getTurn() && heroes.get(i).getStun() == false)
                    {
                        List<Terrain> nearbyTerrain = heroes.get(i).nearbyTerrain();
                        for(int j = 0;j < nearbyTerrain.size();j++)
                        {
                            if(nearbyTerrain.get(j).getType() == "mountain")
                            {
                                getWorld().addObject(new BuildPosition("GM",((MyWorld)getWorld()).getTurn()),nearbyTerrain.get(j).getX(),nearbyTerrain.get(j).getY());
                            }
                        }
                    }
                }
            }
        }
        if(t == "A")
        {
            boolean selected = false;
            List<BuildPosition> buildPos = getWorld().getObjects(BuildPosition.class);
            if(!buildPos.isEmpty())
            {
                for(int i = 0;i < buildPos.size();i++)
                {
                    if(buildPos.get(i).getType() == "A")
                    {
                        selected = true;
                    }
                }
            }
            getWorld().removeObjects(buildPos);
            if(selected == false)
            {
                List<Hero> heroes = getWorld().getObjects(Hero.class);
                for(int i = 0;i < heroes.size();i++)
                {
                    if(heroes.get(i).getPlayer() == ((MyWorld)getWorld()).getTurn() && heroes.get(i).getStun() == false)
                    {
                        List<Hexagon> nearbyHex = heroes.get(i).nearbyHex();
                        for(int j = 0;j < nearbyHex.size();j++)
                        {
                            getWorld().addObject(new BuildPosition("A",((MyWorld)getWorld()).getTurn()),nearbyHex.get(j).getX(),nearbyHex.get(j).getY());
                        }
                    }
                }
            }
        }
        if(t == "B")
        {
            boolean selected = false;
            List<BuildPosition> buildPos = getWorld().getObjects(BuildPosition.class);
            if(!buildPos.isEmpty())
            {
                for(int i = 0;i < buildPos.size();i++)
                {
                    if(buildPos.get(i).getType() == "B")
                    {
                        selected = true;
                    }
                }
            }
            getWorld().removeObjects(buildPos);
            if(selected == false)
            {
                List<Hero> heroes = getWorld().getObjects(Hero.class);
                for(int i = 0;i < heroes.size();i++)
                {
                    if(heroes.get(i).getPlayer() == ((MyWorld)getWorld()).getTurn() && heroes.get(i).getStun() == false)
                    {
                        List<Hexagon> nearbyHex = heroes.get(i).nearbyHex();
                        for(int j = 0;j < nearbyHex.size();j++)
                        {
                            getWorld().addObject(new BuildPosition("B",((MyWorld)getWorld()).getTurn()),nearbyHex.get(j).getX(),nearbyHex.get(j).getY());
                        }
                    }
                }
            }
        }
        if(t == "T")
        {
            boolean selected = false;
            List<BuildPosition> buildPos = getWorld().getObjects(BuildPosition.class);
            if(!buildPos.isEmpty())
            {
                for(int i = 0;i < buildPos.size();i++)
                {
                    if(buildPos.get(i).getType() == "T")
                    {
                        selected = true;
                    }
                }
            }
            getWorld().removeObjects(buildPos);
            if(selected == false)
            {
                List<Hero> heroes = getWorld().getObjects(Hero.class);
                for(int i = 0;i < heroes.size();i++)
                {
                    if(heroes.get(i).getPlayer() == ((MyWorld)getWorld()).getTurn() && heroes.get(i).getStun() == false)
                    {
                        List<Hexagon> nearbyHex = heroes.get(i).nearbyHex();
                        for(int j = 0;j < nearbyHex.size();j++)
                        {
                            getWorld().addObject(new BuildPosition("T",((MyWorld)getWorld()).getTurn()),nearbyHex.get(j).getX(),nearbyHex.get(j).getY());
                        }
                    }
                }
            }
        }
    }

    /**
     * Train
     * 
     * Creates BuildPosition objects nearby unit producing structures depending on which unit is being trained
     * 
     * @param t Thre type of unit to train
     * @return Returns nothing
     */
    private void train(String t)
    {
        if(t == "archer")
        {
            boolean selected = false;
            List<BuildPosition> buildPos = getWorld().getObjects(BuildPosition.class);
            if(!buildPos.isEmpty())
            {
                for(int i = 0;i < buildPos.size();i++)
                {
                    if(buildPos.get(i).getType() == t)
                    {
                        selected = true;
                    }
                }
            }
            getWorld().removeObjects(buildPos);
            if(selected == false)
            {
                List<Buildings> builds = getWorld().getObjects(Buildings.class);
                for(int i = 0;i < builds.size();i++)
                {
                    if(builds.get(i).getPlayer() == ((MyWorld)getWorld()).getTurn() && builds.get(i).getType() == "barracks")
                    {
                        List<Hexagon> nearbyHex = builds.get(i).nearbyHex();
                        for(int j = 0;j < nearbyHex.size();j++)
                        {
                            getWorld().addObject(new BuildPosition(t,((MyWorld)getWorld()).getTurn()),nearbyHex.get(j).getX(),nearbyHex.get(j).getY());
                        }
                    }
                }
            }
        }
        if(t == "knight")
        {
            boolean selected = false;
            List<BuildPosition> buildPos = getWorld().getObjects(BuildPosition.class);
            if(!buildPos.isEmpty())
            {
                for(int i = 0;i < buildPos.size();i++)
                {
                    if(buildPos.get(i).getType() == t)
                    {
                        selected = true;
                    }
                }
            }
            getWorld().removeObjects(buildPos);
            if(selected == false)
            {
                List<Buildings> builds = getWorld().getObjects(Buildings.class);
                for(int i = 0;i < builds.size();i++)
                {
                    if(builds.get(i).getPlayer() == ((MyWorld)getWorld()).getTurn() && builds.get(i).getType() == "armoury")
                    {
                        List<Hexagon> nearbyHex = builds.get(i).nearbyHex();
                        for(int j = 0;j < nearbyHex.size();j++)
                        {
                            getWorld().addObject(new BuildPosition(t,((MyWorld)getWorld()).getTurn()),nearbyHex.get(j).getX(),nearbyHex.get(j).getY());
                        }
                    }
                }
            }
        }
        if(t == "mage")
        {
            boolean selected = false;
            List<BuildPosition> buildPos = getWorld().getObjects(BuildPosition.class);
            if(!buildPos.isEmpty())
            {
                for(int i = 0;i < buildPos.size();i++)
                {
                    if(buildPos.get(i).getType() == t)
                    {
                        selected = true;
                    }
                }
            }
            getWorld().removeObjects(buildPos);
            if(selected == false)
            {
                List<Buildings> builds = getWorld().getObjects(Buildings.class);
                for(int i = 0;i < builds.size();i++)
                {
                    if(builds.get(i).getPlayer() == ((MyWorld)getWorld()).getTurn() && builds.get(i).getType() == "tower")
                    {
                        List<Hexagon> nearbyHex = builds.get(i).nearbyHex();
                        for(int j = 0;j < nearbyHex.size();j++)
                        {
                            getWorld().addObject(new BuildPosition(t,((MyWorld)getWorld()).getTurn()),nearbyHex.get(j).getX(),nearbyHex.get(j).getY());
                        }
                    }
                }
            }
        }
    }

}
