import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 A player account roster
 */
public class PlayerRoster
{
    private double health;
    private double stamina;
    private int turns;
    private double distance;
    private int accountNum;
    private Lock playerChangeLock;

    /**
     Constructs a player character with 100 health and 100 stamina.
     */
    public PlayerRoster(int accountNum)
    {
        health = 100;
        stamina = 100;
        distance = 0;
        turns = 0;
        this.accountNum = accountNum;
        playerChangeLock = new ReentrantLock();
    }

    /**
     Resets health and stamina back to 100 each
     */
    public String heal() //takes in the random number we have running off the game
    {
        playerChangeLock.lock();
        try
        {
            health = 100;
            stamina = 100;
        }
        finally
        {
            playerChangeLock.unlock();
        }
        return "Health: " + health + " Stamina: " + stamina;
    }

    /**
     Damage players Health from their health pool.
     @param amount the health needed to remove
     */
    public void damage(double amount)
    {
        playerChangeLock.lock();
        try
        {
            double newHealth = health - amount;
            health = newHealth;
        }
        finally
        {
            playerChangeLock.unlock();
        }
    }

    /**
     Gets the current Health of the Player.
     @return the current Health
     */
    public double getHealth()
    {
        return health;
    }
    /**
     Gets the current Stamina of the Player.
     @return the current Stamina
     */
    public double getStamina()
    {
        return stamina;
    }

    /**
     * Traveling
     * @param distance the players distance they wish to travel
     * create risks the further you travel the higher a chance of getting hurt becomes
     * for each distance remove 1 stamina
     * if the player runs out of stamina and they get hurt for 5 times more the damage.
     */
    public String travel(double distance)
    { //sum gud stuff
        System.out.println("Traveling " + distance);
        StringBuilder sb = new StringBuilder();
        if (distance > stamina *2)
        {
            sb.append("You are traveling with extreme danger ");
            if ((Math.floor(1 + (Math.random()*10))) > 2)
            {
                damage(50);
                sb.append( " You took damage of 50");
            }
            if (health > 0)
            {
                this.distance = distance;
                sb.append(" You successfully traveled: " + distance);
            }
            else
            {
                sb.append(" YOU DIED");
            }
        }
        else if (distance > stamina && distance < stamina*2)
        {
            sb.append("You are traveling with great risk: ");
            //run major risk system and take possible damage
            if ((Math.floor(1 + (Math.random() * 10))) > 6)
            {
                damage(20);
                sb.append(" You took damage of 20");
            }
            if (health > 0)
            {
                this.distance = distance;
                sb.append(" You successfully traveled" + distance);
            }
            else
            {
                sb.append(" YOU DIED");
            }
        }
        else
        {
            sb.append(" You are traveling with minor risk");
            //run small risk system and take possible damage
            if ((Math.floor(1 + (Math.random() * 10))) == 6)
            {
                damage(10);
                sb.append(" You took damage of 10");
            }
            if (health > 0)
            {
                this.distance = distance;
                sb.append(" You successfully traveled " + distance);
            }
            else
            {
                sb.append(" YOU DIED");
            }
        }
        this.stamina = this.stamina-distance;
        return sb.toString();
    }

/*
DEPRECATED CODE BELOW
One day I will reinstate this code so that scores are unique to players
    public String score()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("You traveled distance ");
        sb.append(distance);
        sb.append(" with ");
        sb.append(turns);
        sb.append(" amount of turns.");
        return sb.toString();
    }
 */



}