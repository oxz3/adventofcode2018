import java.io.*;
import java.util.*;
import java.lang.String;


public class Day7Puzzle1 {
    public static void main (String[] args) throws Exception {
        String filename = "input.txt";
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

        ArrayList<Character> toProcess = new ArrayList<>();
        toProcess.add(findStart(preAR, nextAR));  // InputSequence
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



            //boolean found = false;
            // Need to add logic that only adds to this queue if ALL prereqs are satisfied
            toProcess.addAll(satisfiedPreReq(passedReq, toProcess, preAR, nextAR));
 /*           for (int j = 0; j < preAR.size(); j++) {
                if (passedReq == preAR.get(j)) {
                    if (!toProcess.contains(nextAR.get(j))) {
                        for (int k = 0; k < preAR.size(); k++) {
                            if (nextAR.get(k) == nextAR.get(j) && k != j) {
                                if (sequence.contains(preAR.get(k)))
                                    toProcess.add(nextAR.get(j));
                                found = true;
                            }
                        }
                        if (found == false)
                            toProcess.add(nextAR.get(j));
                    }
                }
            }
*/
            Collections.sort(toProcess);
            System.out.println("sequence: " + sequence);
            System.out.println("toProcess: " + toProcess);
        }

    }

    static ArrayList<Character> satisfiedPreReq(char seq, ArrayList<Character> tp, ArrayList<Character> pr,
                                                ArrayList<Character> ne) {
        ArrayList<Character> potentials = new ArrayList<>();
        for (int i = 0; i < pr.size(); i++) {
            if (seq == pr.get(i)) {
                if (!tp.contains(ne.get(i)))
                    potentials.add(ne.get(i));
            }
        }
        Collections.sort(potentials);

        return potentials;
    }

    static char findStart(ArrayList<Character> pr, ArrayList<Character> ne) {
        ArrayList<Character> candidates = new ArrayList<>();
        for (int i = 0; i < pr.size(); i++) {
            if (ne.indexOf(pr.get(i)) == -1)
                candidates.add(pr.get(i));
        }
        Collections.sort(candidates);
        return candidates.get(0);
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
            ArrayList<Character> misMatches = new ArrayList<>();
            // Go thru input list
            for (char ic : input) {
                inputChar = ic;
                confirm = 0;
                count = 0;
                // Match against the next steps
                for (int i = 0; i < next.size(); i++) {
                    // If you get a match, add to count
                    // That means toProcess matches something from next
                    if (next.get(i) == inputChar) {
                        count++;
                        matches.add(inputChar);
                        // If its in sequence, add to confirm
                        // That means, the prereq of this toProcess has been processed already
                        if (sequence.contains(pre.get(i))) {
                            confirm++;
                            continue;
                        }
                        else {
                            misMatches.add(inputChar);
                        }
                    }
                }
                // If confirm & count matchup and confirm isn't zero, then the first match is it
                if (confirm == count && confirm != 0)
                    outputChar = matches.get(0);
                else {
                    matches.removeAll(misMatches);
                    System.out.println("Matches: " + matches);
                }
            }

            if (outputChar == '0' && !matches.isEmpty())
            outputChar = matches.get(0);
        }

        else {
            outputChar = input.get(0);
        }

        return outputChar;

    }

}
