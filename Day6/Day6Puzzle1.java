import java.lang.String;
import java.io.*;
import java.util.*;

public class Day6Puzzle1 {
    public static void main (String args[]) throws Exception {
        File file = new File ("sample.txt");
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


        int[][] grid = new int[biggestX + 1][biggestY + 1];
        for (int i = 0; i < xCoords.size(); i++) {
            grid[xCoords.get(i)][yCoords.get(i)] = i;
        }




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
    public int getDistance (Coordinates c1, Coordinates c2) {
        int xDistance = Math.abs(c1.xCoord - c2.xCoord);
        int yDistance = Math.abs(c1.yCoord - c2.yCoord);
        int distance = xDistance + yDistance;
        return distance;
    }
}
