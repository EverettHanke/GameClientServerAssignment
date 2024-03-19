import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 Runs a Game program and handles the players choices based on respective clients
 */
public class GameService implements Runnable
{
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private Game game;

    /**
     Constructs a service object that processes commands
     from a socket for a game server
     @param aSocket the socket
     @param aGame the game
     */
    public GameService(Socket aSocket, Game aGame)
    {
        s = aSocket;
        game = aGame;
    }

    public void run()
    {
        try
        {
            try
            {
                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream());
                doService();
            }
            finally
            {
                s.close();
            }
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     Executes all commands until the QUIT command or the
     end of input.
     */
    public void doService() throws IOException
    {
        while (true) //get rid of soon. this will auto run the service. might not be ideal
        {
            if (!in.hasNext())
            {
                return;
            }
            String command = in.next();
            if (command.equals("QUIT"))
            {
                int account = in.nextInt();
                //out.println(game.score(account)); would run deprecated code for score printing
                out.println("Thanks for playing :)");
                out.flush();
                return;
            }
            else
            {
                executeCommand(command);
            }
        }
    }

    /**
     Executes a single command.
     @param command the command to execute
     */
    public void executeCommand(String command)
    {
        int account = in.nextInt();
        if (command.equals("HEAL"))
        {
            game.heal(account);
            out.flush();

        }
        /*
        else if (command.equals("DAMAGE"))
        {
            game.damage(account);
            out.println("DAMAGED for Random Amount: " + account);
            out.flush();
        } */
        else if (command.equals("STATUS"))
        {

            out.println(account + " CHECKING STATUS:  " + game.reflect(account));
            out.flush();

        }
        else if (command.equals("TRAVEL"))
        {
            int distance = in.nextInt();
            game.travel(account, distance);
            out.println(account + " traveling " + game.travel(account, distance));
            out.flush();
        }
        else
        {
            out.println("Invalid command");
            out.flush();
            return;
        }
        out.flush();
    }
}