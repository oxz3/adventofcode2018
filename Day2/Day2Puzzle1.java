import java.lang.String;
import java.io.*;
import java.util.*;

public class Day2Puzzle1
{
    public static void main (String args[])
    {
        String input;
        int twice = 0;
        int thrice = 0;

        Scanner keyboard = new Scanner(System.in);
        System.out.println(">");

        do {
            input = keyboard.next();
            HashMap<Character,Integer> inputHashMap = new HashMap<Character,Integer> (input.length());

            for (int i = 0; i < input.length(); i++)
            {

                if (inputHashMap.containsKey(input.charAt(i))) {
                    int currentValue = inputHashMap.get(input.charAt(i));
                    inputHashMap.put(input.charAt(i), currentValue + 1);
                }

                else {
                    inputHashMap.put(input.charAt(i), 1);
                }
            }

            if (inputHashMap.containsValue(2))
                twice++;
            if (inputHashMap.containsValue(3))
                thrice++;

        } while (!input.equals("0"));

        int checksum = twice * thrice;
        System.out.println("Checksum is " + checksum);
    }
}