import java.lang.String;
import java.util.*;

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

        int[][] sleepLog = new int[inputArrayList.size()][60];
        for (int[] row : sleepLog)
            Arrays.fill(row, 0);

        //HashMap<String, String> whenWho = new HashMap<String, String>();
        //Arrays.fill(whenWho, null);
        ArrayList<HashMap<String, String>> whenWho = new ArrayList();

        int logIndex = 0;
        int sleepIndex = 0;
        for (String entry : inputArrayList) {
            StringBuffer sbLog = new StringBuffer(entry);

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
                if (parsedTime.contains("23:")) {
                    String nextDate = parsedDate.substring(0, 4) + (Integer.parseInt(parsedDate.substring(4)) + 1);
                    hm.put("Date", nextDate);
                }
                else {
                    hm.put("Date", parsedDate);
                }
                whenWho.add(logIndex,hm);
                System.out.println("Guard num: " + guardNum);

                logIndex++;
            }

            else if (parsedAction.contains("falls")) {
                StringBuffer sbTime = new StringBuffer(parsedTime);
                int colon = sbTime.indexOf(":");
                String minutes = sbTime.substring(colon + 1);
                sleepIndex = Integer.parseInt(minutes);
            }

            else if (parsedAction.contains("wakes")) {
                StringBuffer sbTime = new StringBuffer(parsedTime);
                int colon = sbTime.indexOf(":");
                String minutes = sbTime.substring(colon + 1);
                int wakeIndex = Integer.parseInt(minutes);

                for (int x = sleepIndex; x < wakeIndex; x++) {
                    sleepLog[logIndex][x] = 1;
                }
            }

            else {
                System.out.println("Shouldn't hit");
            }

        }

        System.out.println(whenWho);

        for (int i = 0; i < whenWho.size(); i++) {
            HashMap<String, String> hm = new HashMap();
            whenWho.
        }
    }
}