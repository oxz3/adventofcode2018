import javax.net.ssl.HttpsURLConnection;
import java.lang.String;
import java.net.URL;
import java.util.*;
import java.io.*;

public class Day5Puzzle2 {
    public static void main (String args[]) throws Exception {
        //Scanner keyboard = new Scanner (System.in);
        File file = new File ("input.txt");
        Scanner fileInput = new Scanner (file);
        String origInput = "";
        int shortestPolymer = 10000;

        while (fileInput.hasNextLine()) {
            origInput = fileInput.nextLine();
            System.out.println(origInput);

            ArrayList<String> alphaList = new ArrayList<>();
            alphaList.add("a");
            alphaList.add("b");
            alphaList.add("c");
            alphaList.add("d");
            alphaList.add("e");
            alphaList.add("f");
            alphaList.add("g");
            alphaList.add("h");
            alphaList.add("i");
            alphaList.add("j");
            alphaList.add("k");
            alphaList.add("l");
            alphaList.add("m");
            alphaList.add("n");
            alphaList.add("o");
            alphaList.add("p");
            alphaList.add("q");
            alphaList.add("r");
            alphaList.add("s");
            alphaList.add("t");
            alphaList.add("u");
            alphaList.add("v");
            alphaList.add("w");
            alphaList.add("x");
            alphaList.add("y");
            alphaList.add("z");

            String input;
            int y = 0;
            while (y < alphaList.size()) {
                input = cleanse(origInput, alphaList.get(y));
                int x = 0;
                while (x < input.length() - 1) {
                    if (polarityCheck(input, x)) {
                        String newString = destroy(input, x);
                        input = newString;
                        x = -1;
                    }
                    x++;
                }
                if (input.length() < shortestPolymer)
                    shortestPolymer = input.length();
                y++;
            }
        }
        System.out.println(shortestPolymer);
    }

    public static String cleanse (String stringInput, String unit) {
        String tempString = "";
        String cleanedString = "";
        String[] lowerSplits = stringInput.split(unit.toLowerCase());
        for (int x = 0; x < lowerSplits.length; x++) {
            tempString = tempString.concat(lowerSplits[x]);
        }

        String[] upperSplits = tempString.split(unit.toUpperCase());
        for (int x = 0; x < upperSplits.length; x++) {
            cleanedString = cleanedString.concat(upperSplits[x]);
        }

        return cleanedString;
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