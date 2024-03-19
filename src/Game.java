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
            players[i] = new PlayerRoster(i);
        }
    }

    /**
     Allows player to heal to full health and stamina
     @param clientNumber the player client number
     */
    public String heal(int clientNumber)
    {
        PlayerRoster account = players[clientNumber];
        return account.heal();
    }


    /**
     Gets the health & Stamina of a player character.
     @param clientNumber the player client number
     @return the health and stamina of a player character
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

    /**
     * getHealth calls to collect what the player health is of a certain client.
     * @param clientNumber
     * @return clients health
     */
    private double getHealth(int clientNumber)
    {
        PlayerRoster account = players[clientNumber];
        return account.getHealth();
    }

    /**
     * getStamina calls to collect what the player stamina is of a certain client.
     * @param clientNumber
     * @return clients stamina
     */
    private double getStamina(int clientNumber)
    {
        PlayerRoster account = players[clientNumber];
        return account.getStamina();
    }

    /**
     * Tells the player they travel a certain distance
     * @param clientNumber
     * @param distance
     * @return String of characters experience during travel along with what damage they might've taken.
     */
    public String travel(int clientNumber, double distance)
    {
        PlayerRoster account = players[clientNumber];
        return account.travel(distance);
    }

    /*
    DEPRECATED CODE
    One day I'll reinstate this to get the player score to print out based on client number. For now it is collective score

    public String score(int clientNumber)
    {
        PlayerRoster account = players[clientNumber];
        return account.score();
    }

     */
}