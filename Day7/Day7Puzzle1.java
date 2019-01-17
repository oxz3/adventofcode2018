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


        char passedReq = checkPreReq(toProcess, sequence, preAR, nextAR);
        sequence.add(passedReq);
        //toProcess.remove(passedReq);

        for (int j = 0; j < preAR.size(); j++) {
            if (passedReq == preAR.get(j)) {
                toProcess.add(nextAR.get(j));
            }
        }

        Collections.sort(toProcess);

        passedReq = checkPreReq(toProcess, sequence, preAR, nextAR);
        sequence.add(passedReq);

        System.out.println(sequence);

    }

    static char checkPreReq(ArrayList<Character> in, ArrayList<Character> seq, ArrayList<Character> pr,
                                            ArrayList<Character> ne) {
        ArrayList<Character> input = in;
        ArrayList<Character> sequence = seq;
        ArrayList<Character> pre = pr;
        ArrayList<Character> next = ne;

        char outputChar = '0';
        char inputChar = '0';
        int count = 0;
        int confirm = 0;

        if (!sequence.isEmpty()) {

            for (char ic : input) {
                inputChar = ic;
                for (int i = 0; i < ne.size(); i++) {
                    if (next.get(i) == inputChar) {
                        count++;
                        if (sequence.contains(pre.get(i))) {
                            confirm++;
                        }
                        else {
                            continue;
                        }
                    }
                }
            }
            if (count == confirm)
                outputChar = inputChar;
        }

        else {
            outputChar = input.get(0);
        }

        return outputChar;

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
