import java.lang.String;
import java.util.*;
import java.io.*;

public class Day4Puzzle1 {
    public static void main (String args[]) throws Exception {
        Scanner keyboard = new Scanner(System.in);
        System.out.print(">");
        String input;
        ArrayList<String> inputArrayList = new <String>ArrayList();

        do {
            input = keyboard.nextLine();
            inputArrayList.add(input);

        } while (!input.equals("0"));

        System.out.println(inputArrayList.size());

        Collections.sort(inputArrayList);
        inputArrayList.remove(0);
        System.out.println(inputArrayList);

        boolean[][] sleepLog = new boolean[inputArrayList.size()][60];
        for (boolean[] row : sleepLog)
            Arrays.fill(row, false);

        //HashMap<String, String> whenWho = new HashMap<String, String>();
        //Arrays.fill(whenWho, null);
        ArrayList<HashMap<String, String>> whenWho = new ArrayList();
        whenWho.ensureCapacity(60);

        for (int x = 0; x < inputArrayList.size(); x++) {
            StringBuffer sbLog = new StringBuffer(inputArrayList.get(x));

            // Parse out the date, time, and guard
            int openBracket = sbLog.indexOf("[");
            int closeBracket = sbLog.indexOf("]");

            String parsedDate = sbLog.substring(openBracket + 6, openBracket + 11);
            String parsedTime = sbLog.substring(closeBracket - 5, closeBracket);
            System.out.println(parsedDate + "~" + parsedTime);

            String parsedAction = sbLog.substring(closeBracket + 1);
            if (parsedAction.contains("Guard")) {
                StringBuffer sbAction = new StringBuffer(parsedAction);
                int poundSign = sbAction.indexOf("#");
                String guardNum = sbAction.substring(poundSign + 1, poundSign + 3);

                HashMap hm = new HashMap();
                hm.put("Guard", guardNum);
                whenWho.add(x,hm);
                System.out.println("Guard num: " + guardNum);
            }

            /*HashMap hm = new HashMap();
            hm.put("Date", parsedDate);
            whenWho.add(x, hm);
*/

        }
    }
}