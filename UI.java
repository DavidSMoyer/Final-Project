import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List; // List Functionality
public class UI extends Actor
{
    private boolean buildMenu = false; // Checks if the build menu is open
    private boolean trainMenu = false; // Checks if the train menu is open
    private boolean buyMenu = false; // Checks if the buy menu is open
    /**
     * Act - do whatever the UI wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
        GreenfootImage img;
        MyWorld world = ((MyWorld)getWorld());
        img = new GreenfootImage(getWorld().getWidth(),getWorld().getHeight()/4+50);
        img.setColor(Color.BLACK);
        img.fill();
        img.setColor(Color.WHITE);
        img.drawRect(0,0,img.getWidth()-1,img.getHeight()-1);
        img.setFont(new Font("Helvetica",25));
        img.drawString(world.getPlayerName(world.getTurn()) + "'s Turn",img.getWidth()/2-(15*15),30);
        img.setFont(new Font("Helvetica",20));
        img.drawString("Gold: " + world.get("gold",world.getTurn()),50,img.getHeight()/2-50);
        img.drawString("Wood: " + world.get("wood",world.getTurn()),50,img.getHeight()/2-20);
        img.drawString("Stone: " + world.get("stone",world.getTurn()),50,img.getHeight()/2+10);
        img.drawString("Iron: " + world.get("iron",world.getTurn()),50,img.getHeight()/2+38);
        setImage(img);
    }
    /**
     * ToggleBuild
     * 
     * toggles the build menu
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void toggleBuild()
    {
        if(buildMenu == false)
        {
            buildMenu = true;
            closeBuyMenu();
            closeTrainMenu();
            getWorld().addObject(new Button("buildSM"),getWorld().getWidth()/2+100,getWorld().getHeight()-200);
            getWorld().addObject(new Button("buildM"),getWorld().getWidth()/2+100,getWorld().getHeight()-130);
            getWorld().addObject(new Button("buildGM"),getWorld().getWidth()/2+100,getWorld().getHeight()-50);
            getWorld().addObject(new Button("buildA"),getWorld().getWidth()/2+270,getWorld().getHeight()-200);
            getWorld().addObject(new Button("buildB"),getWorld().getWidth()/2+270,getWorld().getHeight()-120);
            getWorld().addObject(new Button("buildT"),getWorld().getWidth()/2+270,getWorld().getHeight()-40);
        }
        else
        {
            closeBuildMenu();
        }
    }
    /**
     * CloseBuildMenu
     * 
     * Closes the build menu
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void closeBuildMenu()
    {
        buildMenu = false;
        getWorld().removeObjects(getWorld().getObjects(BuildPosition.class));
        List<Button> buttons = getWorld().getObjects(Button.class);
        for(int i = 0;i < buttons.size();i++)
        {
            if(buttons.get(i).getType() == "buildSM" || buttons.get(i).getType() == "buildM" || buttons.get(i).getType() == "buildGM" || buttons.get(i).getType() == "buildA" || buttons.get(i).getType() == "buildB" || buttons.get(i).getType() == "buildT")
            {
                getWorld().removeObject(buttons.get(i));
            }
        }
    }
    /**
     * ToggleBuy
     * 
     * Toggles the buy menu
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void toggleBuy()
    {
        if(buyMenu == false)
        {
            buyMenu = true;
            closeBuildMenu();
            closeTrainMenu();
            getWorld().addObject(new Button("buyW"),getWorld().getWidth()/2+100,getWorld().getHeight()-200);
            getWorld().addObject(new Button("buyS"),getWorld().getWidth()/2+100,getWorld().getHeight()-130);
            getWorld().addObject(new Button("buyI"),getWorld().getWidth()/2+100,getWorld().getHeight()-60);
            getWorld().addObject(new Button("sellW"),getWorld().getWidth()/2+270,getWorld().getHeight()-200);
            getWorld().addObject(new Button("sellS"),getWorld().getWidth()/2+270,getWorld().getHeight()-130);
            getWorld().addObject(new Button("sellI"),getWorld().getWidth()/2+270,getWorld().getHeight()-60);
        }
        else
        {
            closeBuyMenu();
        }
    }
    
    /**
     * CloseBuyMenu
     * 
     * Closes the buy menu
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void closeBuyMenu()
    {
        buyMenu = false;
        List<Button> buttons = getWorld().getObjects(Button.class);
        for(int i = 0;i < buttons.size();i++)
        {
            if(buttons.get(i).getType() == "buyW" || buttons.get(i).getType() == "buyS" || buttons.get(i).getType() == "buyI" || buttons.get(i).getType() == "sellW" || buttons.get(i).getType() == "sellS" || buttons.get(i).getType() == "sellI")
            {
                getWorld().removeObject(buttons.get(i));
            }
        }
    }
    /**
     * ToggleTrain
     * 
     * Toggles the train menu
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void toggleTrain()
    {
        if(trainMenu == false)
        {
            trainMenu = true;
            closeBuildMenu();
            closeBuyMenu();
            getWorld().addObject(new Button("trainK"),getWorld().getWidth()/2+170,getWorld().getHeight()-200);
            getWorld().addObject(new Button("trainA"),getWorld().getWidth()/2+170,getWorld().getHeight()-130);
            getWorld().addObject(new Button("trainM"),getWorld().getWidth()/2+170,getWorld().getHeight()-60);
        }
        else
        {
            closeTrainMenu();
        }
    }
    /**
     * CloseTrainMenu
     * 
     * Closes the train menu
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void closeTrainMenu()
    {
        trainMenu = false;
        getWorld().removeObjects(getWorld().getObjects(BuildPosition.class));
        List<Button> buttons = getWorld().getObjects(Button.class);
        for(int i = 0;i < buttons.size();i++)
        {
            if(buttons.get(i).getType() == "trainK" || buttons.get(i).getType() == "trainA" || buttons.get(i).getType() == "trainM")
            {
                getWorld().removeObject(buttons.get(i));
            }
        }
    }
}
