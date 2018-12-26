import java.util.*;

public class Day4Puzzle2 {
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
                StringBuffer sbExtra = new StringBuffer(sbAction.substring(poundSign + 1));
                int space = sbExtra.indexOf(" ");
                String guardNum = sbExtra.substring(0, space);


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
                    sleepLog[logIndex-1][x] = 1;
                }
            }

            else {
                System.out.println("Shouldn't hit");
            }

        }

        System.out.println(whenWho);

        HashMap<String, Integer> sleepTotals = new HashMap<String, Integer>();

        for (int i = 0; i < whenWho.size(); i++) {
            HashMap<String, String> hm = new HashMap<>();
            hm = whenWho.get(i);

            int timeAsleep = 0;
            for (int j = 0; j < 60; j++) {
                timeAsleep = timeAsleep + sleepLog[i][j];
            }

            System.out.println(hm.get("Guard") + " slept " + timeAsleep);
            if (sleepTotals.containsKey(hm.get("Guard"))) {
                int newSleep = sleepTotals.get(hm.get("Guard"));
                newSleep = newSleep + timeAsleep;
                sleepTotals.replace(hm.get("Guard"), newSleep);
            }
            else {
                sleepTotals.putIfAbsent(hm.get("Guard"), timeAsleep);
            }

        }

        System.out.println(sleepTotals);

        String sleepyGuard = "";
        int maxAsleep = 0;
        for (Map.Entry<String, Integer> asleep : sleepTotals.entrySet()) {
            if (asleep.getValue() > maxAsleep) {
                maxAsleep = asleep.getValue();
                sleepyGuard = asleep.getKey();
            }
        }

        System.out.println("Sleepy " + sleepyGuard + " slept for " + maxAsleep);

        ArrayList<Integer> sleepyDays = new ArrayList<>();
        int y = 0;
        while (y < whenWho.size()) {
            if (whenWho.get(y).get("Guard").equals(sleepyGuard))
                sleepyDays.add(y);
            y++;
        }

        int maxSleep = 0;
        int snooziestMinute = 0;
        for (int j = 0; j < 60; j++) {

            int subTotalSleep = 0;
            for (int i = 0; i < sleepyDays.size(); i++) {
                subTotalSleep = subTotalSleep + sleepLog[sleepyDays.get(i)][j];
            }

            if (maxSleep < subTotalSleep) {
                maxSleep = subTotalSleep;
                snooziestMinute = j;
            }

        }

        System.out.println("Max sleep" + maxSleep + " most snooze: " + snooziestMinute);

        System.out.println("Checksum: " + snooziestMinute * Integer.parseInt(sleepyGuard));


        // Puzzle 2
        // Iterate through unique guard names - from sleepTotals
        // Then nested within, iterate through sleepLog array, to find highest sleep count and guard name
        int sleepCount = 0;
        int sleepiestGuard = 0;
        for (int k = 0; k < sleepTotals.size(); k++) {
            String guard = whenWho.get(k).get("Guard");

            sleepyDays.clear();
            int l = 0;
            while (l < whenWho.size()) {
                if (whenWho.get(l).get("Guard").equals(guard))
                    sleepyDays.add(l);
                l++;
            }

            for (int m = 0; m < 60; m++) {
                int subCountSleep = 0;
                for (int n = 0; n < sleepyDays.size(); n++) {
                    subCountSleep = subCountSleep + sleepLog[sleepyDays.get(n)][m];
                }

                if (sleepCount < subCountSleep) {
                    sleepCount = subCountSleep;
                    sleepiestGuard = Integer.parseInt(guard);
                }
            }
        }

        System.out.println("Puzzle2 Checksum: " + sleepiestGuard * sleepCount);

    }
}