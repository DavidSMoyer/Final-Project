import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List; // List functionality
/**
 * Write a description of class AttackPosition here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackPosition extends Actor
{
    private Hero hero = null; // The hero attacking
    private Soldier soldier = null; // The soldier attacking
    private Hero enemyHero = null; // The hero being attacked
    private Soldier enemySoldier = null; // The soldier being attacked
    private Buildings enemyBuild = null; // The building being attacked
    private String ability = null; // The ability being used
    private int damage; // The amount of damage to deal
    private GreenfootImage img = new GreenfootImage(60,60); // The image of the object
    /**
     * A constructor of the AttackPosition object
     * 
     * @param hero2 The hero attacking
     * @param hero1 The hero being attacked
     * @param d The amount of damage being dealt
     */
    public AttackPosition(Hero hero1, Hero hero2, int d)
    {
        hero = hero2;
        enemyHero = hero1;
        damage = d;
        img.setColor(new Color(255,0,0,100));
        img.fillOval(0,0,img.getWidth()-1,img.getHeight()-1);
        setImage(img);
    }

    /**
     * A constructor of the AttackPosition object
     * 
     * @param hero2 The hero attacking
     * @param hero1 The hero being attacked
     * @param a The ability being used
     */
    public AttackPosition(Hero hero1, Hero hero2, String a)
    {
        ability = a;
        hero = hero2;
        enemyHero = hero1;
        img.setColor(new Color(255,0,0,100));
        img.fillOval(0,0,img.getWidth()-1,img.getHeight()-1);
        setImage(img);
    }
    
    /**
     * A constructor of the AttackPosition object
     * 
     * @param hero2 The hero attacking
     * @param build The building being attacked
     * @param d The amount of damage being dealt
     */
    public AttackPosition(Buildings build, Hero hero2, int d)
    {
        hero = hero2;
        enemyBuild = build;
        damage = d;
        img.setColor(new Color(255,0,0,100));
        img.fillOval(0,0,img.getWidth()-1,img.getHeight()-1);
        setImage(img);
    }

    /**
     * A constructor of the AttackPosition object
     * 
     * @param hero2 The hero attacking
     * @param sold The soldier being attacked
     * @param d The amount of damage being dealt
     */
    public AttackPosition(Soldier sold, Hero hero2, int d)
    {
        hero = hero2;
        enemySoldier = sold;
        damage = d;
        img.setColor(new Color(255,0,0,100));
        img.fillOval(0,0,img.getWidth()-1,img.getHeight()-1);
        setImage(img);
    }

    /**
     * A constructor of the AttackPosition object
     * 
     * @param hero2 The hero attacking
     * @param sold The soldier being attacked
     * @param a The ability being used
     */
    public AttackPosition(Soldier sold, Hero hero2, String a)
    {
        ability = a;
        hero = hero2;
        enemySoldier = sold;
        img.setColor(new Color(255,0,0,100));
        img.fillOval(0,0,img.getWidth()-1,img.getHeight()-1);
        setImage(img);
    }

    /**
     * A constructor of the AttackPosition object
     * 
     * @param sold The soldier attacking
     * @param sold2 The soldier being attacked
     * @param d The amount of damage being dealt
     */
    public AttackPosition(Soldier sold2, Soldier sold, int d)
    {
        soldier = sold;
        enemySoldier = sold2;
        damage = d;
        img.setColor(new Color(255,0,0,100));
        img.fillOval(0,0,img.getWidth()-1,img.getHeight()-1);
        setImage(img);
    }

    /**
     * A constructor of the AttackPosition object
     * 
     * @param sold The soldier attacking
     * @param build The building being attacked
     * @param d The amount of damage being dealt
     */
    public AttackPosition(Buildings build, Soldier sold, int d)
    {
        soldier = sold;
        enemyBuild = build;
        damage = d;
        img.setColor(new Color(255,0,0,100));
        img.fillOval(0,0,img.getWidth()-1,img.getHeight()-1);
        setImage(img);
    }

    /**
     * A constructor of the AttackPosition object
     * 
     * @param sold The soldier attacking
     * @param hero The hero being attacked
     * @param d The amount of damage being dealt
     */
    public AttackPosition(Hero h, Soldier sold, int d)
    {
        soldier = sold;
        enemyHero = h;
        damage = d;
        img.setColor(new Color(255,0,0,100));
        img.fillOval(0,0,img.getWidth()-1,img.getHeight()-1);
        setImage(img);
    }

    /**
     * Act - do whatever the MovePosition wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    public void act() 
    {
        clickDetection();
    }    

    /**
     * ClickDetection
     * 
     * Detects if the object has been clicked
     * 
     * @param None There are no parameters
     * @return Returns nothing
     */
    private void clickDetection()
    {
        if(Greenfoot.mouseClicked(this))
        {
            if(enemyHero != null)
            {
                if(hero != null)
                {
                    if(ability == null)
                    {
                        hero.breakInvis();
                        if(hero.getClassType() != "Archer" && hero.getClassType() != "Dragon" && hero.getClassType() != "Gorgon")
                        {
                            enemyHero.changeHP(-damage);
                            if(enemyHero.getAbility2() == "Thorned Armour" || enemyHero.getAbility2() == "Return Fire")
                            {
                                hero.changeHP(-1);
                            }
                        }
                        else if (hero.getClassType() == "Archer")
                        {
                            List<Hero> heroes = getObjectsInRange(75,Hero.class);
                            boolean close = false;
                            for(int i = 0;i < heroes.size();i++)
                            {
                                if(heroes.get(i) == hero)
                                {
                                    close = true;
                                }
                            }
                            if(close == true)
                            {
                                damage--;
                                if(damage < 1)
                                {
                                    damage = 1;
                                }
                            }
                            enemyHero.changeHP(-damage);
                            if(enemyHero.getAbility2() == "Thorned Armour" || enemyHero.getAbility2() == "Return Fire")
                            {
                                hero.changeHP(-1);
                            }
                        }
                        else if (hero.getClassType() == "Dragon")
                        {
                            List<Hero> heroes1 = getObjectsInRange(75,Hero.class);
                            boolean fire = true;
                            if(!heroes1.isEmpty())
                            {
                                for(int i = 0;i < heroes1.size();i++)
                                {
                                    if(heroes1.get(i) == hero)
                                    {
                                        fire = false;
                                    }
                                }
                            }
                            if(fire == true)
                            {
                                damage--;
                                enemyHero.setFire();
                            }
                            enemyHero.changeHP(-damage);
                            if(enemyHero.getAbility2() == "Thorned Armour" || enemyHero.getAbility2() == "Return Fire")
                            {
                                hero.changeHP(-1);
                            }
                        }
                        else if (hero.getClassType() == "Gorgon")
                        {
                            List<Hero> heroes = getObjectsInRange(75,Hero.class);
                            boolean close = false;
                            for(int i = 0;i < heroes.size();i++)
                            {
                                if(heroes.get(i) == hero)
                                {
                                    close = true;
                                }
                            }
                            if(close == true)
                            {
                                damage--;
                                if(damage < 1)
                                {
                                    damage = 1;
                                }
                            }
                            if(hero.getAbility2() == "Sharpened Claws" && close == true)
                            {
                                damage+=2;
                            }
                            if(hero.getAbility2() == "Drugged Arrows" && close == false)
                            {
                                enemyHero.slowAttack();
                            }
                            enemyHero.changeHP(-damage);
                            if(enemyHero.getAbility2() == "Thorned Armour" || enemyHero.getAbility2() == "Return Fire")
                            {
                                hero.changeHP(-1);
                            }
                        }
                        hero.setAttacked(true);
                        hero.unselect();
                    }
                    else
                    {
                        hero.breakInvis();
                        if(ability == "stun")
                        {
                            enemyHero.changeHP(-3);
                            enemyHero.stunAttack();
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "drain")
                        {
                            enemyHero.changeHP(-2);
                            hero.changeHP(2);
                            hero.setCooldown(3);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "inferno")
                        {
                            enemyHero.changeHP(-2);
                            enemyHero.setFire();
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "icicle")
                        {
                            enemyHero.changeHP(-1);
                            enemyHero.slowAttack();
                            hero.setCooldown(4);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "backstab")
                        {
                            enemyHero.changeHP(-5);
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "poisonstab")
                        {
                            enemyHero.changeHP(-1);
                            enemyHero.poisonAttack();
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "arrowvolley")
                        {
                            enemyHero.changeHP(-4);
                            List<Hero> nearbyHeroes = getObjectsInRange(75,Hero.class);
                            if(!nearbyHeroes.isEmpty())
                            {
                                for(int i = 0;i < nearbyHeroes.size();i++)
                                {
                                    if(nearbyHeroes.get(i) != enemyHero)
                                    {
                                        nearbyHeroes.get(i).changeHP(-2);
                                        getWorld().addObject(new EffectAnimation(),nearbyHeroes.get(i).getX(),nearbyHeroes.get(i).getY());
                                    }
                                }
                            }
                            List<Soldier> nearbySoldiers = getObjectsInRange(75,Soldier.class);
                            if(!nearbySoldiers.isEmpty())
                            {
                                for(int i = 0;i < nearbySoldiers.size();i++)
                                {
                                    nearbySoldiers.get(i).changeHP(-2);
                                    getWorld().addObject(new EffectAnimation(),nearbySoldiers.get(i).getX(),nearbySoldiers.get(i).getY());
                                }
                            }
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "slowarrow")
                        {
                            enemyHero.changeHP(-1);
                            enemyHero.slowAttack();
                            hero.setCooldown(4);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "poisonarrow")
                        {
                            enemyHero.changeHP(-1);
                            enemyHero.poisonAttack();
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "flametorrent")
                        {
                            enemyHero.changeHP(-2);
                            List<Hero> heroes = getObjectsInRange(75,Hero.class);
                            if(!heroes.isEmpty())
                            {
                                for(int i = 0;i < heroes.size();i++)
                                {
                                    if(heroes.get(i) != enemyHero)
                                    {
                                        heroes.get(i).setFire();
                                        getWorld().addObject(new EffectAnimation(),heroes.get(i).getX(),heroes.get(i).getY());
                                    }
                                }
                            }
                            List<Soldier> nearbySoldiers = getObjectsInRange(75,Soldier.class);
                            if(!nearbySoldiers.isEmpty())
                            {
                                for(int i = 0;i < nearbySoldiers.size();i++)
                                {
                                        nearbySoldiers.get(i).setFire();
                                        getWorld().addObject(new EffectAnimation(),nearbySoldiers.get(i).getX(),nearbySoldiers.get(i).getY());
                                }
                            }
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "crushingclaw")
                        {
                            enemyHero.changeHP(-2);
                            enemyHero.stunAttack();
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "gaze")
                        {
                            enemyHero.stunAttack();
                            hero.setCooldown(3);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "coil")
                        {
                            enemyHero.changeHP(-1);
                            enemyHero.slowAttack();
                            hero.setCooldown(3);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "dice")
                        {
                            int damage = Greenfoot.getRandomNumber(6)+1;
                            enemyHero.changeHP(-damage);
                            hero.setCooldown(3);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "blackjack")
                        {
                            int damage = -5;
                            int heal = Greenfoot.getRandomNumber(4)+1;
                            enemyHero.changeHP(damage+heal);
                            while(enemyHero.getHealth() + heal > 20)
                            {
                                heal--;
                            }
                            if(heal > 0)
                            {
                                hero.changeHP(heal);
                            }
                            hero.setCooldown(3);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "shatter")
                        {
                            enemyHero.changeHP(-2);
                            enemyHero.stunAttack();
                            List<Hero> heroes = getObjectsInRange(75,Hero.class);
                            if(!heroes.isEmpty())
                            {
                                for(int i = 0;i < heroes.size();i++)
                                {
                                    if(heroes.get(i) != enemyHero && heroes.get(i) != hero)
                                    {
                                        heroes.get(i).changeHP(-1);
                                        getWorld().addObject(new EffectAnimation(),heroes.get(i).getX(),heroes.get(i).getY());
                                    }
                                }
                            }
                            List<Soldier> nearbySoldiers = getObjectsInRange(75,Soldier.class);
                            if(!nearbySoldiers.isEmpty())
                            {
                                for(int i = 0;i < nearbySoldiers.size();i++)
                                {
                                        nearbySoldiers.get(i).changeHP(-1);
                                        getWorld().addObject(new EffectAnimation(),nearbySoldiers.get(i).getX(),nearbySoldiers.get(i).getY());
                                }
                            }
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "pin")
                        {
                            enemyHero.changeHP(-3);
                            enemyHero.slowAttack();
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                    }
                }
                else
                {
                    if(soldier.getType() != "archer" && soldier.getType() != "phoenix" && soldier.getType() != "serpent")
                    {
                        enemyHero.changeHP(-damage);
                        if(enemyHero.getAbility2() == "Thorned Armour" || enemyHero.getAbility2() == "Return Fire")
                        {
                            soldier.changeHP(-1);
                        }
                    }
                    else if(soldier.getType() == "archer")
                    {
                        List<Soldier> soldiers = getObjectsInRange(75,Soldier.class);
                        boolean close = false;
                        if(!soldiers.isEmpty())
                        {
                            for(int i = 0;i < soldiers.size();i++)
                            {
                                if(soldiers.get(i) == soldier)
                                {
                                    close = true;
                                }
                            }
                        }
                        if(close == true)
                        {
                            damage--;
                            if(damage < 1)
                            {
                                damage = 1;
                            }
                        }
                        enemyHero.changeHP(-damage);
                        if(enemyHero.getAbility2() == "Thorned Armour" || enemyHero.getAbility2() == "Return Fire")
                        {
                            soldier.changeHP(-1);
                        }
                    }
                    else if (soldier.getType() == "serpent")
                    {
                        enemyHero.changeHP(-damage);
                        enemyHero.slowAttack();
                        if(enemyHero.getAbility2() == "Thorned Armour" || enemyHero.getAbility2() == "Return Fire")
                        {
                            soldier.changeHP(-1);
                        }
                    }
                    else if (soldier.getType() == "phoenix")
                    {
                        enemyHero.changeHP(-damage);
                        enemyHero.setFire();
                        if(enemyHero.getAbility2() == "Thorned Armour" || enemyHero.getAbility2() == "Return Fire")
                        {
                            soldier.changeHP(-1);
                        }
                    }
                    soldier.setAttacked(true);
                    soldier.unselect();
                }
            }
            
            if(enemyBuild != null)
            {
                if(hero != null)
                {
                    hero.breakInvis();
                    if(hero.getClassType() != "Archer" && hero.getClassType() != "Dragon" && hero.getClassType() != "Gorgon")
                    {
                        enemyBuild.changeHP(-damage);
                    }
                    else if (hero.getClassType() == "Archer")
                    {
                        List<Hero> heroes = getObjectsInRange(75,Hero.class);
                        boolean close = false;
                        if(!heroes.isEmpty())
                        {
                            for(int i = 0;i < heroes.size();i++)
                            {
                                if(heroes.get(i) == hero)
                                {
                                    close = true;
                                }
                            }
                        }
                        if(close == true)
                        {
                            damage--;
                            if(damage < 1)
                            {
                                damage = 1;
                            }
                        }
                        enemyBuild.changeHP(-damage);
                    }
                    else if (hero.getClassType() == "Dragon")
                    {
                        List<Hero> heroes = getObjectsInRange(75,Hero.class);
                        boolean close = false;
                        if(!heroes.isEmpty())
                        {
                            for(int i = 0;i < heroes.size();i++)
                            {
                                if(heroes.get(i) == hero)
                                {
                                    close = true;
                                }
                            }
                        }
                        if(close == true)
                        {
                            enemyBuild.changeHP(-damage);
                        }
                        else
                        {
                            damage--;
                            if(damage < 1)
                            {
                                damage = 1;
                            }
                        }
                    }
                    else if (hero.getClassType() == "Gorgon")
                        {
                            List<Hero> heroes = getObjectsInRange(75,Hero.class);
                            boolean close = false;
                            for(int i = 0;i < heroes.size();i++)
                            {
                                if(heroes.get(i) == hero)
                                {
                                    close = true;
                                }
                            }
                            if(close == true)
                            {
                                damage--;
                                if(damage < 1)
                                {
                                    damage = 1;
                                }
                            }
                            enemyBuild.changeHP(-damage);
                        }
                    hero.setAttacked(true);
                    hero.unselect();
                }
                else
                {
                    if(soldier.getType() != "archer")
                    {
                        enemyBuild.changeHP(-damage);
                    }
                    else if(soldier.getType() == "archer")
                    {
                        List<Soldier> soldiers = getObjectsInRange(75,Soldier.class);
                        boolean close = false;
                        if(!soldiers.isEmpty())
                        {
                            for(int i = 0;i < soldiers.size();i++)
                            {
                                if(soldiers.get(i) == soldier)
                                {
                                    close = true;
                                }
                            }
                        }
                        if(close == true)
                        {
                            damage--;
                            if(damage < 1)
                            {
                                damage = 1;
                            }
                        }
                        enemyBuild.changeHP(-damage);
                    }
                    soldier.setAttacked(true);
                    soldier.unselect();
                }
            }
            if(enemySoldier != null)
            {
                if(hero != null)
                {
                    if(ability == null)
                    {
                        hero.breakInvis();
                        if(hero.getClassType() != "Archer" && hero.getClassType() != "Dragon" && hero.getClassType() != "Gorgon")
                        {
                            enemySoldier.changeHP(-damage);
                        }
                        else if (hero.getClassType() == "Archer")
                        {
                            List<Hero> heroes = getObjectsInRange(75,Hero.class);
                            boolean close = false;
                            if(!heroes.isEmpty())
                            {
                                for(int i = 0;i < heroes.size();i++)
                                {
                                    if(heroes.get(i) == hero)
                                    {
                                        close = true;
                                    }
                                }
                            }
                            if(close == true)
                            {
                                damage--;
                                if(damage < 1)
                                {
                                    damage = 1;
                                }
                            }
                            enemySoldier.changeHP(-damage);
                        }
                        else if (hero.getClassType() == "Dragon")
                        {
                            List<Hero> heroes1 = getObjectsInRange(75,Hero.class);
                            boolean fire = true;
                            if(!heroes1.isEmpty())
                            {
                                for(int i = 0;i < heroes1.size();i++)
                                {
                                    if(heroes1.get(i) == hero)
                                    {
                                        fire = false;
                                    }
                                }
                            }
                            if(fire == true)
                            {
                                damage--;
                                enemySoldier.setFire();
                                if(damage < 1)
                                {
                                    damage = 1;
                                }
                            }
                            enemySoldier.changeHP(-damage);
                        }
                        else if (hero.getClassType() == "Gorgon")
                        {
                            List<Hero> heroes = getObjectsInRange(75,Hero.class);
                            boolean close = false;
                            for(int i = 0;i < heroes.size();i++)
                            {
                                if(heroes.get(i) == hero)
                                {
                                    close = true;
                                }
                            }
                            if(close == true)
                            {
                                damage--;
                                if(damage < 1)
                                {
                                    damage = 1;
                                }
                            }
                            if(hero.getAbility2() == "Sharpened Claws" && close == true)
                            {
                                damage+=2;
                            }
                            if(hero.getAbility2() == "Drugged Arrows" && close == false)
                            {
                                enemySoldier.slowAttack();
                            }
                            enemySoldier.changeHP(-damage);
                        }
                        hero.setAttacked(true);
                        hero.unselect();
                    }
                    else
                    {
                        hero.breakInvis();
                        if(ability == "stun")
                        {
                            enemySoldier.changeHP(-3);
                            enemySoldier.stunAttack();
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "drain")
                        {
                            enemySoldier.changeHP(-2);
                            hero.changeHP(2);
                            hero.setCooldown(3);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "mcontrol")
                        {
                            enemySoldier.mindControl(((MyWorld)getWorld()).getTurn());
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "inferno")
                        {
                            enemySoldier.changeHP(-2);
                            enemySoldier.setFire();
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "icicle")
                        {
                            enemySoldier.changeHP(-1);
                            enemySoldier.slowAttack();
                            hero.setCooldown(4);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "backstab")
                        {
                            enemySoldier.changeHP(-5);
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "poisonstab")
                        {
                            enemySoldier.changeHP(-1);
                            enemySoldier.poisonAttack();
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "arrowvolley")
                        {
                            enemySoldier.changeHP(-4);
                            List<Hero> nearbyHeroes = getObjectsInRange(75,Hero.class);
                            if(!nearbyHeroes.isEmpty())
                            {
                                for(int i = 0;i < nearbyHeroes.size();i++)
                                {
                                    nearbyHeroes.get(i).changeHP(-2);
                                    getWorld().addObject(new EffectAnimation(),nearbyHeroes.get(i).getX(),nearbyHeroes.get(i).getY());
                                }
                            }
                            List<Soldier> nearbySoldiers = getObjectsInRange(75,Soldier.class);
                            if(!nearbySoldiers.isEmpty())
                            {
                                for(int i = 0;i < nearbySoldiers.size();i++)
                                {
                                    if(nearbySoldiers.get(i) != enemySoldier)
                                    {
                                        nearbySoldiers.get(i).changeHP(-2);
                                        getWorld().addObject(new EffectAnimation(),nearbySoldiers.get(i).getX(),nearbySoldiers.get(i).getY());
                                    }
                                }
                            }
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "slowarrow")
                        {
                            enemySoldier.changeHP(-1);
                            enemySoldier.slowAttack();
                            hero.setCooldown(4);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "poisonarrow")
                        {
                            enemySoldier.changeHP(-1);
                            enemySoldier.poisonAttack();
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "flametorrent")
                        {
                            enemySoldier.changeHP(-2);
                            List<Hero> heroes = getObjectsInRange(75,Hero.class);
                            if(!heroes.isEmpty())
                            {
                                for(int i = 0;i < heroes.size();i++)
                                {
                                    heroes.get(i).setFire();
                                    getWorld().addObject(new EffectAnimation(),heroes.get(i).getX(),heroes.get(i).getY());
                                }
                            }
                            List<Soldier> soldiers = getObjectsInRange(75,Soldier.class);
                            if(!soldiers.isEmpty())
                            {
                                for(int i = 0;i < soldiers.size();i++)
                                {
                                    if(soldiers.get(i) != enemySoldier)
                                    {
                                        soldiers.get(i).changeHP(-1);
                                        getWorld().addObject(new EffectAnimation(),soldiers.get(i).getX(),soldiers.get(i).getY());
                                    }
                                }
                            }
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "crushingclaw")
                        {
                            enemySoldier.changeHP(-2);
                            enemySoldier.stunAttack();
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "gaze")
                        {
                            enemySoldier.stunAttack();
                            hero.setCooldown(3);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "coil")
                        {
                            enemySoldier.changeHP(-1);
                            enemySoldier.slowAttack();
                            hero.setCooldown(3);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "dice")
                        {
                            int damage = Greenfoot.getRandomNumber(6)+1;
                            enemySoldier.changeHP(-damage);
                            hero.setCooldown(3);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "blackjack")
                        {
                            int damage = -5;
                            int heal = Greenfoot.getRandomNumber(4)+1;
                            enemySoldier.changeHP(damage+heal);
                            while(enemySoldier.getHealth() + heal > enemySoldier.getMaxHealth())
                            {
                                heal--;
                            }
                            if(heal > 0)
                            {
                                hero.changeHP(heal);
                            }
                            hero.setCooldown(3);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "shatter")
                        {
                            enemySoldier.changeHP(-2);
                            enemySoldier.stunAttack();
                            List<Hero> heroes = getObjectsInRange(75,Hero.class);
                            if(!heroes.isEmpty())
                            {
                                for(int i = 0;i < heroes.size();i++)
                                {
                                    if(heroes.get(i) != hero)
                                    {
                                        heroes.get(i).changeHP(-1);
                                        getWorld().addObject(new EffectAnimation(),heroes.get(i).getX(),heroes.get(i).getY());
                                    }
                                }
                            }
                            List<Soldier> nearbySoldiers = getObjectsInRange(75,Soldier.class);
                            if(!nearbySoldiers.isEmpty())
                            {
                                for(int i = 0;i < nearbySoldiers.size();i++)
                                {
                                    if(nearbySoldiers.get(i) != enemySoldier)
                                    {
                                        nearbySoldiers.get(i).changeHP(-1);
                                        getWorld().addObject(new EffectAnimation(),nearbySoldiers.get(i).getX(),nearbySoldiers.get(i).getY());
                                    }
                                }
                            }
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                        if(ability == "pin")
                        {
                            enemySoldier.changeHP(-3);
                            enemySoldier.slowAttack();
                            hero.setCooldown(5);
                            hero.setAttacked(true);
                            hero.unselect();
                        }
                    }
                }
                else if (soldier != null)
                {
                    if(soldier.getType() != "archer" && soldier.getType() != "serpent" && soldier.getType() != "phoenix")
                    {
                        enemySoldier.changeHP(-damage);
                    }
                    else if(soldier.getType() == "archer")
                    {
                        List<Soldier> soldiers = getObjectsInRange(75,Soldier.class);
                        boolean close = false;
                        if(!soldiers.isEmpty())
                        {
                            for(int i = 0;i < soldiers.size();i++)
                            {
                                if(soldiers.get(i) == soldier)
                                {
                                    close = true;
                                }
                            }
                        }
                        if(close == true)
                        {
                            damage--;
                            if(damage < 1)
                            {
                                damage = 1;
                            }
                        }
                        enemySoldier.changeHP(-damage);
                    }
                    else if (soldier.getType() == "serpent")
                    {
                        enemySoldier.changeHP(-damage);
                        enemySoldier.slowAttack();
                    }
                    else if (soldier.getType() == "phoenix")
                    {
                        enemySoldier.changeHP(-damage);
                        enemySoldier.setFire();
                    }
                    soldier.setAttacked(true);
                    soldier.unselect();
                }
            }
        }
    }
    /**
     * GetAttacker
     * 
     * Returns the attacking hero
     * 
     * @param None There are no parameters
     * @return Returns the value of the "hero" variable
     */
    public Hero getAttacker()
    {
        return hero;
    }
    /**
     * getAbility
     * 
     * Returns the ability being used
     * 
     * @param None There are no parameters
     * @return Returns the value of the "ability" variable
     */
    public String getAbility()
    {
        return ability;
    }
}
