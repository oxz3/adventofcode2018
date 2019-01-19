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
        ArrayList<Character> toProcess = new ArrayList<>();
        toProcess.add(startingPoint);  // InputSequence
        ArrayList<Character> sequence = new ArrayList<>();

        // Identify first step/starting point, place in list
        // prereq check, top down
        // process first valid step
        // find next steps, place in a list
        // sort list
        // prereq check, top down
        // process first valid step, and remove from list
        // find next steps from finished step, place in list
        // sort list


        char passedReq;

        while (!toProcess.isEmpty()) {

            passedReq = checkPreReq(toProcess, sequence, preAR, nextAR);
            sequence.add(passedReq);
            //System.out.println("passedReq: " + passedReq);
            toProcess.remove((Character) passedReq);

            for (int j = 0; j < preAR.size(); j++) {
                if (passedReq == preAR.get(j)) {
                    toProcess.add(nextAR.get(j));
                }
            }

            Collections.sort(toProcess);
            System.out.println("toProcess: " + toProcess);
            System.out.println("sequence: " + sequence);
        }

    }

    static char checkPreReq(ArrayList<Character> in, ArrayList<Character> seq,
                                            ArrayList<Character> pr, ArrayList<Character> ne) {
        ArrayList<Character> input = in;
        ArrayList<Character> sequence = seq;
        ArrayList<Character> pre = pr;
        ArrayList<Character> next = ne;

        char outputChar = '0';
        char inputChar = '0';
        int count = 0;
        int confirm = 0;

        if (!sequence.isEmpty()) {

            ArrayList<Character> matches = new ArrayList<>();
            // Go thru input list
            for (char ic : input) {
                inputChar = ic;
                // Match against the next steps
                for (int i = 0; i < next.size(); i++) {
                    // If you get a match, add to count
                    // That means toProcess matches next
                    if (next.get(i) == inputChar) {
                        count++;
                        matches.add(inputChar);
                        // If its in sequence, add to confirm
                        // That means, the prereq of this toProcess has been processed
                        if (sequence.contains(pre.get(i))) {
                            confirm++;
                            outputChar = inputChar;
                        }
                        else {
                            continue;
                        }
                    }
                }
                if (confirm == count)
                    outputChar = inputChar;
            }


            outputChar = matches.get(0);
        }

        else {
            outputChar = input.get(0);
        }

        return outputChar;

    }

}