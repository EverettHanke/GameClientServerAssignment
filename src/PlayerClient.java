import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 This program is the player client to server
 the game plays from here
 */
public class PlayerClient
{
    private static int turns = 0;
    private static int distance = 0;
    private static int heal_amounts = 5;
    private static final int GAME_PORT = 8887;
    public static boolean stillPlaying = true;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", GAME_PORT);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             Scanner in = new Scanner(inputStream);
             PrintWriter out = new PrintWriter(outputStream, true);
             Scanner userInput = new Scanner(System.in))
        {
            System.out.println("WELCOME TO THE TRAVEL GAME");
            System.out.println("--------------------------------");
            System.out.println("The goal of this game is to see how");
            System.out.println("you can travel without dying");
            System.out.println("you have 5 chances to heal and rest");
            System.out.println("once you have depleted all health kits");
            System.out.println("you will no longer be able to rest");
            System.out.println("traveling distances under your current stamina");
            System.out.println("is the safest way of travel");
            System.out.println("traveling distances greater than your current");
            System.out.println("stamina are risky and have higher chances of death");
            System.out.println("Good luck");
            System.out.println("           & Safe travels");
            while (stillPlaying)
            {
                int choice = displayChoices(userInput);
                roleChoices(choice, in, out, userInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Display choices for player
     * takes in user input between options 1-4
     * @param userInput
     * @return userInput choice made
     */
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

    /**
     * Gathers players choice from options 1-4 and reacts accordingly
     * options are to travel, check status, heal/rest, and quit.
     * @param choice
     * @param in
     * @param out
     * @param userInput
     */
    public static void roleChoices(int choice, Scanner in, PrintWriter out, Scanner userInput) {
        String command;
        String response = "";
        switch (choice) {
            case 1:
                System.out.println("Traveling");

                System.out.println("Enter in how far you wish to travel");
                int travelDistance = userInput.nextInt();

                command = "TRAVEL 2 " + travelDistance;
                out.println(command + "\n");
                out.flush();
                response = in.nextLine();
                System.out.println("Receiving: " + response);
                if (response.contains("DIED"))
                {
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
                }
                distance += travelDistance;
                turns++;
                break;
            case 2:
                System.out.println("Checking status");
                command = "STATUS 2";
                out.println(command);
                out.flush();
                response = in.nextLine();
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
}