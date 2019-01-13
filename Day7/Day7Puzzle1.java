import java.io.*;
import java.util.*;
import java.lang.String;


public class Day7Puzzle1 {
    public static void main (String[] args) throws Exception {
        String filename = "sample.txt";
        File file = new File(filename);
        Scanner fileInput = new Scanner(file);

        ArrayList<StringBuilder> inputAR = new ArrayList<>();
        while (fileInput.hasNext()) {
            inputAR.add(new StringBuilder(fileInput.nextLine()));
        }

        ArrayList<Character> preAR = new ArrayList<>();
        ArrayList<Character> nextAR = new ArrayList<>();
        for (StringBuilder x : inputAR) {
            char pre = x.charAt(5);
            char next = x.charAt(36);
            preAR.add(pre);
            nextAR.add(next);
        }

        System.out.println(preAR);
        System.out.println(nextAR);

        char startingPoint = preAR.get(0);
        StringBuilder sequence = new StringBuilder(String.valueOf(startingPoint));
        ArrayList<Character> availableRoutes = new ArrayList<>();
        for (int x = 0; x < preAR.size(); x++) {

            if (preAR.get(x).equals(startingPoint)) {
                availableRoutes.add(nextAR.get(x));
            }
        }

        Collections.sort(availableRoutes);
        System.out.println(availableRoutes);

        ArrayList<Character> nextAvailableRoute = new ArrayList<>();
        for (char x : availableRoutes) {
            nextAvailableRoute.add(x);
            for (int y = 0; y < preAR.size(); y++) {
                if (preAR.get(y) == x) {
                    sequence.append(x);
                    nextAvailableRoute.add(nextAR.get(y));
                }
            }
        }

        System.out.println(nextAvailableRoute);
    }
}
