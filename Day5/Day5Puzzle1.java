import javax.net.ssl.HttpsURLConnection;
import java.lang.String;
import java.net.URL;
import java.util.*;
import java.io.*;

public class Day5Puzzle1 {
    public static void main (String args[]) throws Exception {
        //Scanner keyboard = new Scanner (System.in);
        File file = new File ("input.txt");
        Scanner fileInput = new Scanner (file);
        String input = "";

        while (fileInput.hasNextLine()) {
            input = fileInput.nextLine();
            System.out.println(input);

            int x = 0;
            while (x < input.length() - 1) {
                if (polarityCheck(input, x)) {
                    String newString = destroy(input, x);
                    input = newString;
                    x = -1;
                }
                x++;
            }
        }
        System.out.println(input.length());
    }

    public static boolean polarityCheck (String stringInput, int index) {
        Character first = stringInput.charAt(index);
        Character second = stringInput.charAt(index + 1);
        if (first.equals(second))
            return false;
        else if (first.equals(Character.toUpperCase(second)))
            return true;
        else if (first.equals(Character.toLowerCase(second)))
            return true;
        else
            return false;
    }

    public static String destroy (String stringInput, int index) {
        StringBuffer sbNewString = new StringBuffer(stringInput);
        String firstHalf = sbNewString.substring(0,index);
        String secondHalf = sbNewString.substring(index + 2, sbNewString.length());
        String destroyed = firstHalf + secondHalf;
        return destroyed;
    }
}