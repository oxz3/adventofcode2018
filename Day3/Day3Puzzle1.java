import java.util.*;
import java.io.*;
import java.lang.String;

public class Day3Puzzle1 {
    public static void main (String args[]) {

        Scanner keyboard = new Scanner(System.in);
        System.out.print(">");
        String input;
        ArrayList<String> inputArrayList = new <String>ArrayList();

        do {
            input = keyboard.next();
            inputArrayList.add(input);

        } while (!input.equals("0"));

        System.out.println(inputArrayList.size());

        int[][] fabric = new int[1000][1000];

        for (int x = 0; x < inputArrayList.size(); x++) {
            String statement = inputArrayList.get(x);

            if (x % 4 == 0)
                System.out.println(statement.substring(1));
            else if (x % 4 == 2) {
                String[] coords = statement.split(",");
                String coordx = coords[0];
                coords = coords[1].split(":");
                String coordy = coords[0];

                System.out.println(coordx + " " + coordy);
            }
            else if (x % 4 == 3) {
                String[] dimensions = statement.split("x");
                String dimenx = dimensions[0];
                String dimeny = dimensions[1];

                System.out.println(dimenx + " " + dimeny);
            }


        }

    }

}