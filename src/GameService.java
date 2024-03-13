import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 Executes Simple Bank Access Protocol commands
 from a socket.
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
                game.score(account);
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
            //game.heal(account);
            out.println(game.heal(account));
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
            //game.reflect(account); //prithee be careful
            out.println(account + " CHECKING STATUS:  " + game.reflect(account));
            out.flush();
        }
        else if (command.equals("TRAVEL"))
        {
            game.travel(account, 30);
            out.println(account + " traveling " + game.travel(account, in.nextInt()));
        }
        else
        {
            out.println("Invalid command");
            out.flush();
            return;
        }
        //out.println(account + " test " + game.reflect(account));
        out.flush();
    }
}