import java.lang.String;
import java.io.*;
import java.util.*;

public class Day2Puzzle1
{
    public static void main (String args[])
    {
        String input;

        Scanner keyboard = new Scanner(System.in);
        System.out.println(">");

        do {
            input = keyboard.next();
            HashMap inputHashMap = new HashMap (input.length());

            for (int i = 0; i < input.length(); i++)
            {
                System.out.println(input.charAt(i));
            }

            inputHashMap
        } while (!input.equals("0"));


    }
}