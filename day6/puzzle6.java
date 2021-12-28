package day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class puzzle6 {
    public Long[] readInstructionInput(String fileName) throws FileNotFoundException {
        File input = new File(fileName);
        Scanner reader = new Scanner(input);
        Long[] fishes = { 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L };
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] splittedLine = line.split(",");
            for (String s : splittedLine) {
                int fish = Integer.parseInt(s);
                fishes[fish]++;
            }
        }
        reader.close();
        return fishes;
    }

    public Long[] makeFishesDayOlder(Long[] fishes) {
        Long[] newFishes = { 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L };
        newFishes[6] += fishes[0];
        newFishes[8] += fishes[0];
        for (int i = 1; i < 9; i++) {
            newFishes[i - 1] += fishes[i];
        }
        // System.out.println(Arrays.toString(fishes));
        return newFishes;
    }

    public Long[] makeFishesDaysOlder(Long[] fishes, int days) {
        Long[] newFishes = { 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L };
        while (days > 0) {
            newFishes = makeFishesDayOlder(fishes);
            fishes = newFishes;
            days--;
        }
        return newFishes;
    }

    public Long getNumberOfFishes(Long[] fishes) {
        Long result = 0L;
        for (int i = 0; i < 9; i++) {
            result += fishes[i];
        }
        return result;
    }

    public static void main(String[] args) {
        puzzle6 puzzle6 = new puzzle6();
        String fileName = "input.txt";
        try {
            Long[] fishes = puzzle6.readInstructionInput(fileName);
            Long[] newFishes = puzzle6.makeFishesDaysOlder(fishes, 80);
            System.out.println(
                    "Puzzle6 part1: there are " + puzzle6.getNumberOfFishes(newFishes) + " fishes alive after 80 days");
            Long[] newFishes2 = puzzle6.makeFishesDaysOlder(fishes, 256);
            System.out.println("Puzzle6 part2: there are " + puzzle6.getNumberOfFishes(newFishes2)
                    + " fishes alive after 256 days");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
