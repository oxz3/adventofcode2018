import java.lang.String.*;
import java.io.*;
import java.util.*;


public class Day1Puzzle2
{
    public static void main (String args[]) throws Exception
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print(">");

        ArrayList <Integer> listInput = new ArrayList();
        int input;
        do {
            input = keyboard.nextInt();

            listInput.add(input);

        } while (input != 0);

        listInput.remove(listInput.size()-1);
        //System.out.println("Last input " + listInput.get(listInput.size()-1));

        Set <Integer>setTally = new HashSet<Integer>();
        int tally = 0;
        int counter = 0;
        setTally.add(tally);
            do {
                tally = tally + listInput.get(counter);
                if (setTally.contains(tally))
                {
                    System.out.println("The twice reached is: " + tally);
                    System.exit(0);
                }
                else
                    setTally.add(tally);
                counter++;
                //System.out.println(counter);
                if (listInput.size() == counter)
                    counter = 0;
            } while (true);

    }
}