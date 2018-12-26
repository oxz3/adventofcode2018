import java.lang.String;
import java.io.*;
import java.util.*;

public class Day6Puzzle1 {
    public static void main (String args[]) {
        File file = new File ("sample.txt");
        Scanner fileInput = new Scanner (file);
        String origInput = "";
        int shortestPolymer = 10000;

        while (fileInput.hasNextLine()) {
            origInput = fileInput.nextLine();
            System.out.println(origInput);
    }
}