import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 A server that executes the Simple Bank Access Protocol.
 */
public class GameServer
{
    public static void main(String[] args) throws IOException
    {
        final int ACCOUNTS_LENGTH = 10;
        Game game = new Game(ACCOUNTS_LENGTH);
        final int SBAP_PORT = 8888;
        ServerSocket server = new ServerSocket(SBAP_PORT);
        System.out.println("Waiting for clients to connect...");

        while (true)
        {
            Socket s = server.accept();
            System.out.println("Player connected.");
            GameService service = new GameService(s, game);
            Thread t = new Thread(service);
            t.start();
        }
    }
}