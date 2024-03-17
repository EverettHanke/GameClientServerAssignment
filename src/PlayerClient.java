import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 This program tests the bank server.
 */
public class PlayerClient
{
    private static int turns = 0;
    private static int distance = 0;
    private static int heal_amounts = 5;
    private static final int SBAP_PORT = 8887;
    public static boolean stillPlaying = true;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", SBAP_PORT);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             Scanner in = new Scanner(inputStream);
             PrintWriter out = new PrintWriter(outputStream, true);
             Scanner userInput = new Scanner(System.in))
        {

            while (stillPlaying)
            {
                int choice = displayChoices(userInput);
                roleChoices(choice, in, out, userInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int displayChoices(Scanner userInput) {
        System.out.println("************************************************************************");
        System.out.println("What would you like to do?");
        System.out.println("1. Travel (Use your stamina and risk health to travel distance in km)");
        System.out.println("2. Check your status (view your health and stamina)");
        System.out.println("3. Rest (regain health and stamina)");
        System.out.println("4. Give up (quit)");
        System.out.println("************************************************************************");
        System.out.println();
        return userInput.nextInt();
    }

    public static void roleChoices(int choice, Scanner in, PrintWriter out, Scanner userInput) {
        String command;
        String response = "";
        switch (choice) {
            case 1:
                System.out.println("Traveling");
                System.out.println("test distance 30");

                //Now lets give the player choice of distance
                System.out.println("Enter in how far you wish to travel");
                int travelDistance = userInput.nextInt();

                command = "TRAVEL 2 " + travelDistance;
                out.println(command + "\n");
                out.flush();
                response = in.nextLine();
                System.out.println("Receiving: " + response);
                distance += travelDistance;
                turns++;
                break;
            case 2:
                System.out.println("Checking status");
                command = "STATUS 2";
                out.println(command );
                out.flush();
                response = in.nextLine() + " " + in.nextLine();
                System.out.println("Receiving: " + response);
                turns++;
                break;
            case 3:
                System.out.println("You take a rest and now feel refreshed");
                if (heal_amounts >= 0)
                {
                    System.out.println("Checking health and Stamina Status: ");
                    command = "HEAL 2";
                    out.println(command);
                    out.flush();
                    //response = in.nextLine() + in.nextLine();

                    //System.out.println(response);
                    System.out.println("You are now at full health and stamina");
                    heal_amounts--;
                    System.out.println("You have " + heal_amounts + " heals remaining");
                }
                else
                {
                    System.out.println("You no longer have any medkits supplies");
                    System.out.println("This causes a restless night of pain and sorrow");
                    System.out.println("By morning you must push on.");
                }

                turns++;
                break;
            case 4:
                System.out.println("Quitting");
                command = "QUIT 2";
                out.println(command);
                out.flush();

                stillPlaying = false;
                System.out.println("YOUR SCORE WAS");
                System.out.println("Distance: " + distance);
                System.out.println("Heals Remaining: " + heal_amounts);
                System.out.println("Turns used: " + turns);
                int total = distance + heal_amounts - turns;
                System.out.println("Total Score IS: " + total);

                break;
            default:
                System.out.println("Please enter a choice between 1 and 4");
        }
    }

    /*
    public static String getResponse(Scanner in, PrintWriter out)
    {
        out.flush();
        String response = "";
        while (in.hasNextLine())
        {
            response += in.nextLine();
        }
        return response;
    }
*/
/*

    public static void main(String[] args) throws IOException
    {
        final int GAME_PORT = 8887;
        try (Socket s = new Socket("localhost", GAME_PORT))
        {
            InputStream instream = s.getInputStream();
            OutputStream outstream = s.getOutputStream();
            Scanner in = new Scanner(instream);
            PrintWriter out = new PrintWriter(outstream);
            Scanner userInput = new Scanner(System.in);

            int choice;
            choice = displayChoices(userInput);

            RoleChoices(choice, in, out);


            String command = "DAMAGE 3 100";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            //String response = in.nextLine() + in.nextLine();
            //System.out.println("Receiving: " + response);

            command = "DAMAGE 3 12";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            //response = in.nextLine();
            //System.out.println("Receiving: " + response);

            command = "STATUS 3";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();


            String response = in.nextLine();
            while (in.hasNextLine())
            {
                response += in.nextLine();
            }
            System.out.println("Receiving: " + response);
            //********************************************************
            /*
            String command = "HEAL 3 100";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            String response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "DAMAGE 3 500";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "QUIT";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();


        }

    }
    public static int displayChoices(Scanner userInput)
    {
        System.out.println("What would ya like to do?");
        System.out.println("1. Travel");
        System.out.println("2. Check your status");
        System.out.println("3. Give up");
        int choice = userInput.nextInt();
        return choice;
    }

    public static void RoleChoices(int choice, Scanner in, PrintWriter out)
    {
        String command = "";
        String response = "";
        switch (choice)
        {
            case 1:
                System.out.println("traveling");
                break;
            case 2:
                System.out.println("checking");
                command = "STATUS 3";
                response = "";
                while (in.hasNextLine())
                {
                    response = in.nextLine();
                }
                out.print("Sending: " + command);
                out.flush();
                System.out.println(response);
                break;
            case 3:
                System.out.println("quitting");
                command = "QUIT";
                System.out.println("Sending: " + command);
                out.print(command + "\n");
                out.flush();
                break;
            default:
                System.out.println("Please enter a choice between 1 and 3");
        }
    }

 */
}