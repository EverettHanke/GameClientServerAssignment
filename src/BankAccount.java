import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 A bank account has a balance that can be changed by
 deposits and withdrawals.
 */
public class BankAccount
{
    private double health;
    private Lock playerChangeLock;

    /**
     Constructs a bank account with a zero balance.
     */
    public BankAccount()
    {
        health = 100;
        playerChangeLock = new ReentrantLock();
    }

    /**
     Constructs a bank account with a given balance.
     @param initialHealth the initial balance
     */
    public BankAccount(double initialHealth)
    {
        health = initialHealth;
    }

    /**
     Deposits money into the bank account.
     @param amount the amount to deposit
     */
    public void heal(double amount)
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
     Withdraws money from the bank account.
     @param amount the amount to withdraw
     */
    public void hurt(double amount)
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
     Gets the current balance of the bank account.
     @return the current balance
     */
    public double getHealth()
    {
        return health;
    }
}