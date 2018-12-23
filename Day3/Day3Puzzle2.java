import java.util.*;
import java.io.*;
import java.lang.String;

public class Day3Puzzle2 {
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
        for (int[] row : fabric)
            Arrays.fill(row, 0);

        int num = 0;
        int coordinatex = 0;
        int coordinatey = 0;
        int dimensionx = 0;
        int dimensiony = 0;
        int claimed = 0;

        for (int x = 0; x < inputArrayList.size() - 1; x++) {
            String statement = inputArrayList.get(x);

            if (x % 4 == 0) {
                String[] line = statement.split("#");
                num = Integer.parseInt(line[1]);
            }
            else if (x % 4 == 2) {
                String[] coords = statement.split(",");
                String coordx = coords[0];
                coords = coords[1].split(":");
                String coordy = coords[0];

                coordinatex = Integer.parseInt(coordx);
                coordinatey = Integer.parseInt(coordy);
            }
            else if (x % 4 == 3) {
                String[] dimensions = statement.split("x");
                String dimenx = dimensions[0];
                String dimeny = dimensions[1];

                dimensionx = Integer.parseInt(dimenx);
                dimensiony = Integer.parseInt(dimeny);

                for (int i = 0; i < dimensionx; i++) {
                    for (int j = 0; j < dimensiony; j++) {

                        if (fabric[coordinatex + i][coordinatey + j] == 0) {
                            fabric[coordinatex + i][coordinatey + j] = num;
                        }
                        else if (fabric[coordinatex + i][coordinatey + j] == -1) {}
                        else {
                            fabric[coordinatex + i][coordinatey + j] = -1;
                            claimed++;
                        }
                    }
                }

            }


        }

        System.out.println("Claimed: " + claimed);

        for (int x = 0; x < inputArrayList.size() - 1; x++) {
            String statement = inputArrayList.get(x);

            if (x % 4 == 0) {
                String[] line = statement.split("#");
                num = Integer.parseInt(line[1]);
            }
            else if (x % 4 == 2) {
                String[] coords = statement.split(",");
                String coordx = coords[0];
                coords = coords[1].split(":");
                String coordy = coords[0];

                coordinatex = Integer.parseInt(coordx);
                coordinatey = Integer.parseInt(coordy);
            }
            else if (x % 4 == 3) {
                String[] dimensions = statement.split("x");
                String dimenx = dimensions[0];
                String dimeny = dimensions[1];

                dimensionx = Integer.parseInt(dimenx);
                dimensiony = Integer.parseInt(dimeny);

                int overlap = 0;

                for (int i = 0; i < dimensionx; i++) {
                    for (int j = 0; j < dimensiony; j++) {

                        if (fabric[coordinatex + i][coordinatey + j] == num) {}
                        else if (fabric[coordinatex + i][coordinatey + j] == -1) {
                            overlap++;
                        }
                        else {
                            fabric[coordinatex + i][coordinatey + j] = -1;
                            claimed++;
                        }
                    }
                }

                if (overlap == 0)
                    System.out.println("Untouched Claim: " + num);

            }


        }

    }

}