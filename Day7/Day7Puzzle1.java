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
        ArrayList<Character> processed = new ArrayList<>();
        processed.add(startingPoint);  // InputSequence


        ArrayList<Character> availableRoutes = new ArrayList<>();
        availableRoutes = processNext(processed, preAR, nextAR);
       // availableRoutes = processNext(availableRoutes, preAR, nextAR);

/*        for (int x = 0; x < preAR.size(); x++) {

            if (preAR.get(x).equals(startingPoint)) {
                availableRoutes.add(nextAR.get(x));
            }
        }

        Collections.sort(availableRoutes);
*/        System.out.println(availableRoutes);




    }

    static ArrayList<Character> processNext(ArrayList<Character> in, ArrayList<Character> pr,
                                            ArrayList<Character> ne) {
        ArrayList<Character> inputSequence = in;
        ArrayList<Character> pre = pr;
        ArrayList<Character> next = ne;

        ArrayList<Character> outputSequence = inputSequence;
        ArrayList<Character> queue = new ArrayList<>();

        for (char i : inputSequence) {
            if (inputSequence.size() < 3) {
                for (int k = 0; k < pre.size(); k++) {
                    if (i == pre.get(k)) {
                        queue.add(next.get(k));
                    }
                }
            }

            for (int j = 0; j < next.size(); j++) {
                if (i == next.get(j)) {
                    for (int k = 0; k < pre.size(); k++) {
                        if (i == pre.get(k)) {
                            queue.add(next.get(k));
                        }
                    }
                }
            }

        }

        Collections.sort(queue);
        System.out.println("Queue: " + queue);
        // After sorting, check to see if you can process it
        for (char m : queue) {
            for (char n : pre) {
                //
            }
            outputSequence.add(queue.get(0));
        }
        return outputSequence;
    }
}
