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
     from a socket for a bank.
     @param aSocket the socket
     @param aGame the bank
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
        while (true)
        {
            if (!in.hasNext()) { return; }
            String command = in.next();
            if (command.equals("QUIT")) { return; }
            else { executeCommand(command); }
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
            double amount = in.nextDouble();
            game.heal(account);
        }
        else if (command.equals("DAMAGE"))
        {
            double amount = in.nextDouble();
            game.hurt(account);
        }
        else if (!command.equals("STATUS"))
        {
            out.println("Invalid command");
            out.flush();
            return;
        }
        out.println(account + " " + game.reflect(account));
        out.flush();
    }
}