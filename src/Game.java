/**
 A Game session should be consisting of multiple player accounts.
 */
public class Game
{
    private BankAccount[] players;

    /**
     Constructs a bank account with a given number of accounts.
     @param size the number of accounts
     */
    public Game(int size)
    {
        players = new BankAccount[size];
        for (int i = 0; i < players.length; i++)
        {
            players[i] = new BankAccount();
        }
    }

    /**
     Deposits money into a bank account.
     @param clientNumber the account number
     @param amount the amount to deposit
     */
    public void heal(int clientNumber, double amount)
    {
        BankAccount account = players[clientNumber];
        account.heal(amount);
    }

    /**
     Withdraws money from a bank account.
     @param clientNumber the account number
     @param amount the amount to withdraw
     */
    public void hurt(int clientNumber, double amount)
    {
        BankAccount account = players[clientNumber];
        account.hurt(amount);
    }

    /**
     Gets the balance of a bank account.
     @param clientNumber the account number
     @return the account balance
     */
    public double getHealth(int clientNumber)
    {
        BankAccount account = players[clientNumber];
        return account.getHealth();
    }
}