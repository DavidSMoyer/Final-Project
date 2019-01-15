import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List; // Imports List functionality
import javax.swing.JOptionPane; // Imports message panes
/** 
 * Name: David Moyer
 * Class: Software Development
 * Teacher: Scott Hardman
 * Date: 15/1/2019
 */
public class MyWorld extends World
{
    private int gold[] = {5,5,5,5}; // The starting gold for all players
    private int wood[] = {5,5,5,5}; // The starting wood for all players
    private int stone[] = {5,5,5,5}; // The starting stone for all players
    private int iron[] = {0,0,0,0}; // The starting iron for all players

    private boolean playerLose[]; // An array to hold which players have lost
    private boolean startRound = true; // Holds if the first set of rounds is still running
    private int turn = 1; // The current player's turn
    private String playerNames[]; // The names of the players
    private int players; // The amount of players playing
    private boolean started = false; // Sees if all the players have finished customizing their hero
    private boolean customMenu = false; // Sees if the customization menu is open
    /**
     * MyWorld
     * 
     * Constructor for objects of class MyWorld.
     */
    public MyWorld()
    {    
        // Create a new world with 700x750 cells with a cell size of 1x1 pixels.
        super(700, 750, 1); 
        getBackground().setColor(Color.BLACK);
        getBackground().fill();
        setPaintOrder(DamageDisplay.class,BuildPosition.class,AttackPosition.class,MovePosition.class,Hero.class,Soldier.class,Buildings.class,Terrain.class,Hexagon.class);
        initialize();
        Greenfoot.start();
    }

    /**
     * WinDetection
     * 
     * Controls the detection of a player winning.
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void winDetection()
    {
        if(players > 0)
        {
            int loseAmount = 0;
            int player = 0;
            for(int i = 0;i < playerLose.length;i++)
            {
                if(playerLose[i] == true)
                {
                    loseAmount++;
                }
                else
                {
                    player = i;
                }
            }
            if(loseAmount == players-1)
            {
                removeObjects(getObjects(null));
                getBackground().setColor(Color.BLACK);
                getBackground().fill();
                showText(playerNames[player] + " wins!",getWidth()/2,getHeight()/2);
            }
        }
    }
        
    /**
     * NoneSelected
     * 
     * Checks to see if no soldiers or heroes are selected.
     * 
     * @param None There are no parameters
     * @return Returns a boolean
     */
    public boolean noneSelected()
    {
        boolean noneSelected = true;
        List<Hero> heroes = getObjects(Hero.class);
        List<Soldier> soldiers = getObjects(Soldier.class);
        for(int i = 0;i < heroes.size();i++)
        {
            if(heroes.get(i).getSelected() == true)
            {
                noneSelected = false;
            }
        }
        for(int i = 0;i < soldiers.size();i++)
        {
            if(soldiers.get(i).getSelected() == true)
            {
                noneSelected = false;
            }
        }
        return noneSelected;
    }
    
    /**
     * Get
     * 
     * Gets a resource type of the selected player.
     * 
     * @param t The resource type
     * @param p The player
     * @return Returns nothing
     */
    public int get(String t, int p)
    {
        if(t == "gold")
        {
            return gold[p-1];
        }
        else if (t == "wood")
        {
            return wood[p-1];
        }
        else if (t == "stone")
        {
            return stone[p-1];
        }
        else if (t == "iron")
        {
            return iron[p-1];
        }
        return 0;
    }

    /**
     * Change
     * 
     * Changes a player's resource amount
     * 
     * @param t The resource type
     * @param p The player
     * @param amount The amount
     */
    public void change(String t, int p, int amount)
    {
        if(t == "gold")
        {
            gold[p-1]+=amount;
        }
        else if (t == "wood")
        {
            wood[p-1]+=amount;
        }
        else if (t == "stone")
        {
            stone[p-1]+=amount;
        }
        else if (t == "iron")
        {
            iron[p-1]+=amount;
        }
    }

    /**
     * GetTurn
     * 
     * Gets the current player's turn
     * 
     * @param None There are no parameters
     * @return Returns an int
     */
    public int getTurn()
    {
        return turn;
    }

    /**
     * Act
     * 
     * Runs every time 'act' or 'run' is called.
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void act()
    {
        if(started == false && customMenu == true)
        {
            startDetection();
        }
        winDetection();
    }

    /**
     * Initialize
     * 
     * Initializes the title menu
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void initialize()
    {
        getBackground().setColor(Color.WHITE);
        getBackground().setFont(new Font("Helvetica",60));
        getBackground().drawString("The Art of\n     War",getWidth()/2-getWidth()/4+45,getHeight()/4);

        addObject(new Button("start1"),getWidth()/2,getHeight()/2);
        addObject(new Button("start2"),getWidth()/2,getHeight()/2+75);
        addObject(new Button("start3"),getWidth()/2,getHeight()/2+150);
    }

    /**
     * Start
     * 
     * Creates the customization menu for all the players playing.
     * 
     * @param p The amount of players
     * @return Returns nothing
     */
    public void start(int p)
    {
        players = p;
        playerNames = new String[players];
        String input;
        for(int i = 0;i < players;i++)
        {
            input = JOptionPane.showInputDialog(null,"Insert player " + (i+1) + "'s name.");
            playerNames[i] = input;
        }
        playerLose = new boolean[players];
        for(int i = 0;i < playerLose.length;i++)
        {
            playerLose[i] = false;
        }
        removeObjects(getObjects(Button.class));
        if(players == 2)
        {
            addObject(new Customization(1),getWidth()/2-90,getHeight()-200);
            addObject(new Customization(2),getWidth()/2+90,getHeight()-200);
        }
        else if (players == 3)
        {
            addObject(new Customization(1),getWidth()/2-180,getHeight()-200);
            addObject(new Customization(2),getWidth()/2,getHeight()-200);
            addObject(new Customization(3),getWidth()/2+180,getHeight()-200);
        }
        else
        {
            addObject(new Customization(1),90,getHeight()-200);
            addObject(new Customization(2),getWidth()/2-87,getHeight()-200);
            addObject(new Customization(3),getWidth()/2+87,getHeight()-200);
            addObject(new Customization(4),getWidth()-90,getHeight()-200);
        }
        List<Customization> custom = getObjects(Customization.class);
        for(int i = 0;i < custom.size();i++)
        {
            addObject(new Button("precl",custom.get(i).getPlayer()),custom.get(i).getX()-25,custom.get(i).getY()-110);
            addObject(new Button("nextcl",custom.get(i).getPlayer()),custom.get(i).getX()+25,custom.get(i).getY()-110);
            addObject(new Button("preab1",custom.get(i).getPlayer()),custom.get(i).getX()-25,custom.get(i).getY()-40);
            addObject(new Button("nextab1",custom.get(i).getPlayer()),custom.get(i).getX()+25,custom.get(i).getY()-40);
            addObject(new Button("preab2",custom.get(i).getPlayer()),custom.get(i).getX()-25,custom.get(i).getY()+30);
            addObject(new Button("nextab2",custom.get(i).getPlayer()),custom.get(i).getX()+25,custom.get(i).getY()+30);
            addObject(new Button("done",custom.get(i).getPlayer()),custom.get(i).getX(),custom.get(i).getY()-220);
        }
        customMenu = true;
    }

    /**
     * StartDetection
     * 
     * Detects if all the players are done creating their heroes
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void startDetection()
    {
        int readyPlayers = 0;
        List<Button> done = getObjects(Button.class);
        for(int i = 0;i < done.size();i++)
        {
            if(done.get(i).getDone() == true)
            {
                readyPlayers++;
            }
        }
        if(readyPlayers == players)
        {
            showText("Loading . . .",getWidth()/2,getHeight()/2);
            createWorld();
        }
    }

    /**
     * CreateWorld
     * 
     * Creates the world and user interface
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void createWorld()
    {
        getBackground().setColor(new Color(0,150,0));
        getBackground().fill();
        addHex();
        addTerrain();
        addHero();
        addObject(new UI(),getWidth()/2,getHeight()-getHeight()/4/2-50/2);
        addObject(new Button("ability1"),getWidth()/2-100,getHeight()-130);
        addObject(new Button("turn"),70,getHeight()-35);
        addObject(new Button("build"),40,getHeight()-210);
        addObject(new Button("buy"),100,getHeight()-210);
        addObject(new Button("train"),160,getHeight()-35);
        showText("",getWidth()/2,getHeight()/2);
        customMenu = false;
    }

    /**
     * AddTerrain
     * 
     * Creates all the terrain in the world
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void addTerrain()
    {
        int remainingForests = 40;
        int remainingRocks = 35;
        int remainingMountains = 20;
        int random;
        List<Hexagon> hex = getObjects(Hexagon.class);
        for(int i = 0;i < hex.size();i++)
        {
            random = Greenfoot.getRandomNumber(4);
            if(random == 0 && remainingForests > 0)
            {
                addObject(new Terrain("forest"),hex.get(i).getX(),hex.get(i).getY());
                remainingForests--;
            }
            if(random == 1 && remainingRocks > 0)
            {
                addObject(new Terrain("rocks"),hex.get(i).getX(),hex.get(i).getY());
                remainingRocks--;
            }
            else if (random == 2 && remainingMountains > 0)
            {
                addObject(new Terrain("mountain"),hex.get(i).getX(),hex.get(i).getY());
                remainingMountains--;
            }
        }
    }

    /**
     * AddHex
     * 
     * Creates all the hexagons in the world
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void addHex()
    {
        int x = getWidth()/2;
        int y = getHeight()/2;

        // 10
        addObject(new Hexagon(),x,y+100);
        addObject(new Hexagon(),x+65,y+100);
        addObject(new Hexagon(),x-65,y+100);
        addObject(new Hexagon(),x+130,y+100);
        addObject(new Hexagon(),x-130,y+100);
        addObject(new Hexagon(),x-195,y+100);
        addObject(new Hexagon(),x+195,y+100);
        addObject(new Hexagon(),x+260,y+100);
        addObject(new Hexagon(),x-260,y+100);
        addObject(new Hexagon(),x-325,y+100);
        addObject(new Hexagon(),x+325,y+100);

        // 9
        addObject(new Hexagon(),x+33,y+50);
        addObject(new Hexagon(),x-33,y+50);
        addObject(new Hexagon(),x+98,y+50);
        addObject(new Hexagon(),x-98,y+50);
        addObject(new Hexagon(),x+163,y+50);
        addObject(new Hexagon(),x-163,y+50);
        addObject(new Hexagon(),x-228,y+50);
        addObject(new Hexagon(),x+228,y+50);
        addObject(new Hexagon(),x+293,y+50);
        addObject(new Hexagon(),x-293,y+50);

        // 8
        addObject(new Hexagon(),x,y);
        addObject(new Hexagon(),x+65,y);
        addObject(new Hexagon(),x-65,y);
        addObject(new Hexagon(),x+130,y);
        addObject(new Hexagon(),x-130,y);
        addObject(new Hexagon(),x-195,y);
        addObject(new Hexagon(),x+195,y);
        addObject(new Hexagon(),x+260,y);
        addObject(new Hexagon(),x-260,y);
        addObject(new Hexagon(),x-325,y);
        addObject(new Hexagon(),x+325,y);

        // 7
        addObject(new Hexagon(),x+33,y-50);
        addObject(new Hexagon(),x-33,y-50);
        addObject(new Hexagon(),x+98,y-50);
        addObject(new Hexagon(),x-98,y-50);
        addObject(new Hexagon(),x+163,y-50);
        addObject(new Hexagon(),x-163,y-50);
        addObject(new Hexagon(),x-228,y-50);
        addObject(new Hexagon(),x+228,y-50);
        addObject(new Hexagon(),x+293,y-50);
        addObject(new Hexagon(),x-293,y-50);

        // 6
        addObject(new Hexagon(),x,y-100);
        addObject(new Hexagon(),x+65,y-100);
        addObject(new Hexagon(),x-65,y-100);
        addObject(new Hexagon(),x+130,y-100);
        addObject(new Hexagon(),x-130,y-100);
        addObject(new Hexagon(),x-195,y-100);
        addObject(new Hexagon(),x+195,y-100);
        addObject(new Hexagon(),x+260,y-100);
        addObject(new Hexagon(),x-260,y-100);
        addObject(new Hexagon(),x-325,y-100);
        addObject(new Hexagon(),x+325,y-100);

        // 5
        addObject(new Hexagon(),x+33,y-150);
        addObject(new Hexagon(),x-33,y-150);
        addObject(new Hexagon(),x+98,y-150);
        addObject(new Hexagon(),x-98,y-150);
        addObject(new Hexagon(),x+163,y-150);
        addObject(new Hexagon(),x-163,y-150);
        addObject(new Hexagon(),x-228,y-150);
        addObject(new Hexagon(),x+228,y-150);
        addObject(new Hexagon(),x+293,y-150);
        addObject(new Hexagon(),x-293,y-150);

        // 4
        addObject(new Hexagon(),x,y-200);
        addObject(new Hexagon(),x+65,y-200);
        addObject(new Hexagon(),x-65,y-200);
        addObject(new Hexagon(),x+130,y-200);
        addObject(new Hexagon(),x-130,y-200);
        addObject(new Hexagon(),x-195,y-200);
        addObject(new Hexagon(),x+195,y-200);
        addObject(new Hexagon(),x+260,y-200);
        addObject(new Hexagon(),x-260,y-200);
        addObject(new Hexagon(),x-325,y-200);
        addObject(new Hexagon(),x+325,y-200);

        // 3
        addObject(new Hexagon(),x+33,y-250);
        addObject(new Hexagon(),x-33,y-250);
        addObject(new Hexagon(),x+98,y-250);
        addObject(new Hexagon(),x-98,y-250);
        addObject(new Hexagon(),x+163,y-250);
        addObject(new Hexagon(),x-163,y-250);
        addObject(new Hexagon(),x-228,y-250);
        addObject(new Hexagon(),x+228,y-250);
        addObject(new Hexagon(),x+293,y-250);
        addObject(new Hexagon(),x-293,y-250);

        // 2
        addObject(new Hexagon(),x,y-300);
        addObject(new Hexagon(),x+65,y-300);
        addObject(new Hexagon(),x-65,y-300);
        addObject(new Hexagon(),x+130,y-300);
        addObject(new Hexagon(),x-130,y-300);
        addObject(new Hexagon(),x-195,y-300);
        addObject(new Hexagon(),x+195,y-300);
        addObject(new Hexagon(),x+260,y-300);
        addObject(new Hexagon(),x-260,y-300);
        addObject(new Hexagon(),x-325,y-300);
        addObject(new Hexagon(),x+325,y-300);

        // 1
        addObject(new Hexagon(),x+33,y-350);
        addObject(new Hexagon(),x-33,y-350);
        addObject(new Hexagon(),x+98,y-350);
        addObject(new Hexagon(),x-98,y-350);
        addObject(new Hexagon(),x+163,y-350);
        addObject(new Hexagon(),x-163,y-350);
        addObject(new Hexagon(),x-228,y-350);
        addObject(new Hexagon(),x+228,y-350);
        addObject(new Hexagon(),x+293,y-350);
        addObject(new Hexagon(),x-293,y-350);
    }

    /**
     * AddHero
     * 
     * Adds heroes and fortresses to the world in different locations depending on the amount of players
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void addHero()
    {
        if(players == 2)
        {
            Button done1 = null;
            Button done2 = null;
            for(int i = 0; i < players;i++)
            {
                if(getObjects(Button.class).get(i).getType() == "done" && getObjects(Button.class).get(i).getPlayer() == 1)
                {
                    done1 = getObjects(Button.class).get(i);
                }
                if(getObjects(Button.class).get(i).getType() == "done" && getObjects(Button.class).get(i).getPlayer() == 2)
                {
                    done2 = getObjects(Button.class).get(i);
                }
            }
            addObject(new Hero(done1.getPlayer(),done1.getHero("class"),done1.getHero("ability1"),done1.getHero("ability2")),getWidth()/2-163,getHeight()/2-150);
            addObject(new Buildings("fortress",1),getWidth()/2-228,getHeight()/2-150);
            addObject(new Hero(done2.getPlayer(),done2.getHero("class"),done2.getHero("ability1"),done2.getHero("ability2")),getWidth()/2+163,getHeight()/2-150);
            addObject(new Buildings("fortress",2),getWidth()/2+228,getHeight()/2-150);
        }
        else if(players == 3)
        {
            Button done1 = null;
            Button done2 = null;
            Button done3 = null;
            for(int i = 0; i < players;i++)
            {
                if(getObjects(Button.class).get(i).getType() == "done" && getObjects(Button.class).get(i).getPlayer() == 1)
                {
                    done1 = getObjects(Button.class).get(i);
                }
                if(getObjects(Button.class).get(i).getType() == "done" && getObjects(Button.class).get(i).getPlayer() == 2)
                {
                    done2 = getObjects(Button.class).get(i);
                }
                if(getObjects(Button.class).get(i).getType() == "done" && getObjects(Button.class).get(i).getPlayer() == 3)
                {
                    done3 = getObjects(Button.class).get(i);
                }
            }
            addObject(new Hero(done1.getPlayer(),done1.getHero("class"),done1.getHero("ability1"),done1.getHero("ability2")),getWidth()/2-163,getHeight()/2+50);
            addObject(new Buildings("fortress",1),getWidth()/2-228,getHeight()/2+50);
            addObject(new Hero(done2.getPlayer(),done2.getHero("class"),done2.getHero("ability1"),done2.getHero("ability2")),getWidth()/2+163,getHeight()/2+50);
            addObject(new Buildings("fortress",2),getWidth()/2+228,getHeight()/2+50);
            addObject(new Hero(done3.getPlayer(),done3.getHero("class"),done3.getHero("ability1"),done3.getHero("ability2")),getWidth()/2+33,getHeight()/2-250);
            addObject(new Buildings("fortress",3),getWidth()/2,getHeight()/2-300);
        }
        else
        {
            Button done1 = null;
            Button done2 = null;
            Button done3 = null;
            Button done4 = null;
            for(int i = 0; i < players;i++)
            {
                if(getObjects(Button.class).get(i).getType() == "done" && getObjects(Button.class).get(i).getPlayer() == 1)
                {
                    done1 = getObjects(Button.class).get(i);
                }
                if(getObjects(Button.class).get(i).getType() == "done" && getObjects(Button.class).get(i).getPlayer() == 2)
                {
                    done2 = getObjects(Button.class).get(i);
                }
                if(getObjects(Button.class).get(i).getType() == "done" && getObjects(Button.class).get(i).getPlayer() == 3)
                {
                    done3 = getObjects(Button.class).get(i);
                }
                if(getObjects(Button.class).get(i).getType() == "done" && getObjects(Button.class).get(i).getPlayer() == 4)
                {
                    done4 = getObjects(Button.class).get(i);
                }
            }
            addObject(new Hero(done1.getPlayer(),done1.getHero("class"),done1.getHero("ability1"),done1.getHero("ability2")),getWidth()/2-195,getHeight()/2-300);
            addObject(new Buildings("fortress",1),getWidth()/2-260,getHeight()/2-300);
            addObject(new Hero(done2.getPlayer(),done2.getHero("class"),done2.getHero("ability1"),done2.getHero("ability2")),getWidth()/2+195,getHeight()/2-300);
            addObject(new Buildings("fortress",2),getWidth()/2+260,getHeight()/2-300);
            addObject(new Hero(done3.getPlayer(),done3.getHero("class"),done3.getHero("ability1"),done3.getHero("ability2")),getWidth()/2-163,getHeight()/2+50);
            addObject(new Buildings("fortress",3),getWidth()/2-228,getHeight()/2+50);
            addObject(new Hero(done4.getPlayer(),done4.getHero("class"),done4.getHero("ability1"),done4.getHero("ability2")),getWidth()/2+163,getHeight()/2+50);
            addObject(new Buildings("fortress",4),getWidth()/2+228,getHeight()/2+50);
        }
        removeObjects(getObjects(Button.class));
        
    }

    /**
     * NextTurn
     * 
     * Changes the turn by one, resetting and telling buildings, soldiers and heroes that it's their turn.
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void nextTurn()
    {
        turn++;
        List<Hero> heroes = getObjects(Hero.class);
        List<Buildings> builds = getObjects(Buildings.class);
        List<Soldier> soldiers = getObjects(Soldier.class);
        removeObjects(getObjects(MovePosition.class));
        removeObjects(getObjects(AttackPosition.class));
        removeObjects(getObjects(BuildPosition.class));
        unselectAll();
        if(turn > players)
        {
            turn = 1;
            startRound = false;
        }
        getObjects(UI.class).get(0).closeBuildMenu();
        getObjects(UI.class).get(0).closeBuyMenu();
        getObjects(UI.class).get(0).closeTrainMenu();
        
        
        for(int i = 0;i < builds.size();i++)
        {
            boolean steal = false;
            int playerSteal = 0;
            List<Hero> nearbyHeroes = builds.get(i).nearbyHeroes();
            if(builds.get(i).getPlayer() == turn)
            {
                builds.get(i).yourTurn();
                if(startRound == false)
                {
                    if(!nearbyHeroes.isEmpty())
                    {
                        for(int j = 0;j < nearbyHeroes.size();j++)
                        {
                            if(nearbyHeroes.get(j).getPlayer() != turn && nearbyHeroes.get(j).getAbility2() == "Thievery")
                            {
                                steal = true;
                                playerSteal = nearbyHeroes.get(j).getPlayer();
                            }
                        }
                    }
                    if(builds.get(i).getType() == "fortress")
                    {
                        if(steal == false)
                        {
                            gold[turn-1]++;
                        }
                        else
                        {
                            gold[playerSteal-1]++;
                        }
                    }
                    if(builds.get(i).getType() == "lumberMill")
                    {
                        if(steal == false)
                        {
                            wood[turn-1]++;
                        }
                        else
                        {
                            wood[playerSteal-1]++;
                        }
                    }
                    if(builds.get(i).getType() == "rockMine")
                    {
                        if(steal == false)
                        {
                            stone[turn-1]++;
                        }
                        else
                        {
                            stone[playerSteal-1]++;
                        }
                        if(Greenfoot.getRandomNumber(3) == 0)
                        {
                            if(steal == false)
                            {
                                iron[turn-1]++;
                            }
                            else
                            {
                                iron[playerSteal-1]++;
                            }
                        }
                    }
                    if(builds.get(i).getType() == "goldMine")
                    {
                        if(steal == false)
                        {
                            gold[turn-1]++;
                        }
                        else
                        {
                            gold[playerSteal-1]++;
                        }
                    }
                }
            }
        }
        for(int i = 0;i < heroes.size();i++)
        {
            if(heroes.get(i).getPlayer() == turn)
            {
                heroes.get(i).yourTurn();
            }
        }
        for(int i = 0;i < soldiers.size();i++)
        {
            if(soldiers.get(i).getPlayer() == turn)
            {
                soldiers.get(i).yourTurn();
            }
        }
        if(playerLose[turn-1] == true)
        {
            nextTurn();
        }
    }

    /**
     * GetPlayerName
     * 
     * Gets the player's name at the current index of the playerNames array
     * 
     * @param num The index
     * @return Returns a string
     */
    public String getPlayerName(int num)
    {
        return playerNames[num-1];
    }

    /**
     * Lose
     * 
     * Sets a value at the set index of the loseArray to true
     * 
     * @param p The index
     * @return Returns nothing
     */
    public void lose(int p)
    {
        playerLose[p-1] = true;
    }

    /**
     * UnselectAll
     * 
     * Unselects all Soldiers and Heroes
     * 
     * @paarm None There are no parameters
     * @return Returns nothing
     */
    public void unselectAll()
    {
        List<Hero> heroes = getObjects(Hero.class);
        for(int i = 0;i < heroes.size();i++)
        {
            heroes.get(i).unselect();
        }
        List<Soldier> soldiers = getObjects(Soldier.class);
        if(!soldiers.isEmpty())
        {
            for(int i = 0;i < soldiers.size();i++)
            {
                soldiers.get(i).unselect();
            }
        }
    }
}
