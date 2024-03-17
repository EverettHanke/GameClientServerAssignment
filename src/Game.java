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
    public String heal(int clientNumber)
    {
        PlayerRoster account = players[clientNumber];
        return account.heal();
    }

    /**
     Removes Health from a player character.
     @param clientNumber the account number
     */
    /*
    public void damage(int clientNumber)
    {
        PlayerRoster account = players[clientNumber]; //get player that's getting hurt
        double damage = (Math.random() * 10) + 5; //create random damage number pool.
        account.damage(damage); //cast damage number
    } */

    /**
     Gets the health & Stamina of a player character.
     @param clientNumber the account number
     @return the account balance
     */
    public String reflect(int clientNumber)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Current Health: ");
        sb.append(getHealth(clientNumber) + " ");
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

    public String travel(int clientNumber, double distance)
    {
        PlayerRoster account = players[clientNumber];
        return account.travel(distance);
    }

    public String score(int clientNumber)
    {
        PlayerRoster account = players[clientNumber];
        return account.score();
    }
}