/**
 A Game session should be consisting of multiple player accounts.
 */
public class Game
{
    private PlayerRoster[] players;

    /**
     Constructs a game session with a given number of players
     @param size the number of players
     */
    public Game(int size)
    {
        players = new PlayerRoster[size];
        for (int i = 0; i < players.length; i++)
        {
            players[i] = new PlayerRoster();
        }
    }

    /**
     Deposits money into a bank account.
     @param clientNumber the account number
     */
    public void heal(int clientNumber)
    {
        PlayerRoster account = players[clientNumber];
        double currentHealth = account.getHealth();
        double range = 100 - currentHealth; //sets a range if health is 60 the range should be 40. ensure player doesnt overheal.
        double healthAmount = Math.random() * range;
        account.heal(healthAmount);
    }

    /**
     Removes Health from a player character.
     @param clientNumber the account number
     */
    public void damage(int clientNumber)
    {
        PlayerRoster account = players[clientNumber]; //get player that's getting hurt
        double damage = (Math.random() * 10) + 5; //create random damage number pool.
        account.damage(damage); //cast damage number
    }

    /**
     Gets the health & Stamina of a player character.
     @param clientNumber the account number
     @return the account balance
     */
    public String reflect(int clientNumber)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Current Health: ");
        sb.append(getHealth(clientNumber) + "\n");
        sb.append("Current Stamina: ");
        sb.append(getStamina(clientNumber));
        return sb.toString();
    }


    private double getHealth(int clientNumber)
    {
        PlayerRoster account = players[clientNumber];
        return account.getHealth();
    }
    private double getStamina(int clientNumber)
    {
        PlayerRoster account = players[clientNumber];
        return account.getStamina();
    }
}