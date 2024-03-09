import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 A bank account has a balance that can be changed by
 deposits and withdrawals.
 */
public class PlayerRoster
{
    private double health;
    private double stamina;
    private Lock playerChangeLock;

    /**
     Constructs a player character with 100 health and 100 stamina.
     */
    public PlayerRoster()
    {
        health = 100;
        stamina = 100;
        playerChangeLock = new ReentrantLock();
    }

    /**
     Deposits money into the bank account.
     @param amount the amount to deposit
     */
    public void heal(double amount) //takes in the random number we have running off the game
    {
        playerChangeLock.lock();
        try
        {
            double newHealth = health + amount;
            health = newHealth;
        }
        finally
        {
            playerChangeLock.unlock();
        }
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
            //retur;
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
    public void travel(double distance)
    {
        System.out.println("Traveling " + distance);
        if (distance > stamina)
        {
            System.out.println("You are traveling with great risk");
            //run major risk system and take possible damage
        }
        else
        {
            System.out.println("You are traveling with minor risk");
            //run small risk system and take possible damage
        }
    }


}