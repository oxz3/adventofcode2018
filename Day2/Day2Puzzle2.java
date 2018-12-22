import java.lang.String;
import java.util.*;
import java.io.*;

public class Day2Puzzle2 {
    public static void main (String args[]) {
        String input;

        Scanner keyboard = new Scanner (System.in);
        System.out.print(">");

        ArrayList<String> inputArrayList = new <String>ArrayList();

        do {
            input = keyboard.next();

            inputArrayList.add(input);
        } while (!input.equals("0"));

        System.out.println(inputArrayList.size());

        int offset = 0;
        int indexOfOffset = 0;

        for (int i = 0; i < inputArrayList.size() - 1; i++) {
            for (int j = i + 1; j < inputArrayList.size() - 1; j++) {

                int length = inputArrayList.get(i).length();
                for (int x = 0; x < inputArrayList.get(i).length(); x++) {

                    if (inputArrayList.get(i).charAt(x) == inputArrayList.get(j).charAt(x)) {
                        //System.out.println("boof");
                    }
                    else {
                        offset++;
                        indexOfOffset = x;
                        System.out.println("boof");
                    }
                }

                if (offset <= 1) {
                    System.out.println("Offset = " + offset);
                    System.out.println(inputArrayList.get(i));
                    System.out.println(inputArrayList.get(j));
                }
            }


        }



    }
}