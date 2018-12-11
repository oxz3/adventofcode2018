import java.lang.String.*;
import java.io.*;
import java.util.*;

public class Day1Puzzle1 {
    public static void main (String args[]) throws Exception
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.print(">");

            ArrayList <Integer>listInput = new ArrayList();
            int input = 0;
            int tally = 0;
            do {
                input = keyboard.nextInt();

                tally = tally + input;

            } while (input != 0);

            System.out.println("Resulting frequency: " + tally);

        }
}