import java.lang.String;
import java.io.*;
import java.util.*;

public class Day6Puzzle1 {
    public static void main (String args[]) throws Exception {
        File file = new File ("input.txt");
        Scanner fileInput = new Scanner (file);
        String input = "";
        ArrayList<String> inputAL = new ArrayList<>();
        ArrayList<Coordinates> coordsAL = new ArrayList<>();

        while (fileInput.hasNextLine()) {
            input = fileInput.nextLine();
            inputAL.add(input);
        }

        //Parse out string into ints
        //Find the 4 corners of the output
        //Place values into a 2-dim array of ints
        //Use nested loops to evaluate distance and populate array
        //Loop again through array to count the highest occurrences, that is not one of the 4 corners

        ArrayList<Integer> xCoords = new ArrayList<>();
        ArrayList<Integer> yCoords = new ArrayList<>();

        for (String coords : inputAL) {
            String[] xyCoords = coords.split(",");
            xyCoords[1] = xyCoords[1].trim();
            xCoords.add(Integer.parseInt(xyCoords[0]));
            yCoords.add(Integer.parseInt(xyCoords[1]));
            coordsAL.add(new Coordinates(Integer.parseInt(xyCoords[0]), Integer.parseInt(xyCoords[1])));
        }


        int biggestX = 0;
        int biggestY = 0;
        int smallestX = 1000;
        int smallestY = 1000;
        for (int xValue : xCoords) {
            if (biggestX < xValue)
                biggestX = xValue;
            if (smallestX > xValue)
                smallestX = xValue;
        }
        for (int yValue : yCoords) {
            if (biggestY < yValue)
                biggestY = yValue;
            if (smallestY > yValue)
                smallestY = yValue;
        }

        System.out.println(smallestX + ", " + yCoords.get(xCoords.indexOf(smallestX)));
        System.out.println(xCoords.get(yCoords.indexOf(smallestY)) + ", " + smallestY);
        System.out.println(biggestX + ", " + yCoords.get(xCoords.indexOf(biggestX)));
        System.out.println(xCoords.get(yCoords.indexOf(biggestY)) + ", " + biggestY);


        // Create grid array, populate with zeros, then populate main coordinates with input values.
        int[][] grid = new int[biggestX + 1][biggestY + 1];
        for (int[] row : grid)
            Arrays.fill(row, -5);
        for (int i = 0; i < xCoords.size(); i++)
            grid[xCoords.get(i)][yCoords.get(i)] = i;


        // Walk thru grid, then compare distance to each array entry, and fill accordingly
        for (int y = 0; y < biggestY + 1; y++) {
            for (int x = 0; x < biggestX + 1; x++) {

                Coordinates closestCoords = new Coordinates(0,0);
                int shortestDistance = 1000;

                loop:
                for (int z = 0; z < coordsAL.size(); z++) {
                    int tempDistance = 0;
                    Coordinates currentCoords = new Coordinates(x, y);
                    tempDistance = Coordinates.getDistance(currentCoords, coordsAL.get(z));

                    if (Coordinates.equals(currentCoords, coordsAL.get(z))) {
                        break loop;
                    }
                    else if (tempDistance == 0) {
                        break loop;
                    }
                    else if (tempDistance < shortestDistance) {
                        shortestDistance = tempDistance;
                        grid[x][y] = z;
                        closestCoords.xCoord = x;
                        closestCoords.yCoord = y;
                    }
                    else if (tempDistance == shortestDistance) {
                        if (Coordinates.getDistance(closestCoords, currentCoords) == 0)
                            grid[x][y] = -1;
                    }
                }

                //System.out.println("shortestDistance: " + shortestDistance);

            }
        }


        for (int i = 0; i < xCoords.size(); i++)
            grid[xCoords.get(i)][yCoords.get(i)] = i;


        int highestCount = -1;
        int highestValue = -1;
        for (int l = 0; l < coordsAL.size(); l++) {
            int tempCount = 0;

            if (coordsAL.get(l).equals(new Coordinates(smallestX, yCoords.get(xCoords.indexOf(smallestX)))))
                continue;
            else if (coordsAL.get(l).equals(new Coordinates(xCoords.get(yCoords.indexOf(smallestY)), smallestY)))
                continue;
            else if (coordsAL.get(l).equals(new Coordinates(biggestX, yCoords.get(xCoords.indexOf(biggestX)))))
                continue;
            else if (coordsAL.get(l).equals(new Coordinates(xCoords.get(yCoords.indexOf(biggestY)), biggestY)))
                continue;
            else {

                for (int n = 0; n < biggestY + 1; n++) {
                    for (int m = 0; m < biggestX + 1; m++) {
                        if (l == grid[m][n])
                            tempCount++;
                    }
                }
            }

            if (tempCount > highestCount) {
                highestCount = tempCount;
                highestValue = l;
            }
        }

        for (int q = 0; q < biggestY + 1; q++) {
            for (int p = 0; p < biggestX + 1; p++) {
                System.out.print(grid[p][q] + " ");
            }
            System.out.println("");
        }

        System.out.println("Highest Value: " + highestValue);
        System.out.println("Highest Count: " + highestCount);
    }


}


class Coordinates {

    int xCoord;
    int yCoord;

    // Constructor
    public Coordinates (int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    // getDistance method
    public static int getDistance (Coordinates c1, Coordinates c2) {
        int xDistance = Math.abs(c1.xCoord - c2.xCoord);
        int yDistance = Math.abs(c1.yCoord - c2.yCoord);
        int distance = xDistance + yDistance;
        return distance;
    }

    public static boolean equals (Coordinates c1, Coordinates c2) {
        if (c1.xCoord == c2.xCoord && c1.yCoord == c2.yCoord)
            return true;
        else
            return false;
    }
}
