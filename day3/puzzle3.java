package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class puzzle3 {
    public Integer[] readInstructionInput(String fileName) throws FileNotFoundException {
        File input = new File(fileName);
        Scanner reader = new Scanner(input);
        String[] firstLine = reader.nextLine().split("");
        Integer[] bitCounter = new Integer[firstLine.length];
        initialiseBitCounter(bitCounter, firstLine);
        updateBitCounter(bitCounter, firstLine);

        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split("");
            updateBitCounter(bitCounter, line);
        }
        reader.close();
        return bitCounter;
    }

    private void initialiseBitCounter(Integer[] bitCounter, String[] firstLine) {
        for (int i = 0; i < bitCounter.length; i++) {
            bitCounter[i] = 0;
        }
    }

    private void updateBitCounter(Integer[] bitCounter, String[] line) {
        for (int i = 0; i < bitCounter.length; i++) {
            if (line[i].equals("1")) {
                bitCounter[i] += 1;
            } else {
                bitCounter[i] -= 1;
            }
        }
    }

    public List<Integer> getMostCommonBits(Integer[] bitCounter) {
        List<Integer> mostCommonBits = new ArrayList<>();
        for (int i = 0; i < bitCounter.length; i++) {
            if (bitCounter[i] >= 0) {
                mostCommonBits.add(1);
            } else {
                mostCommonBits.add(0);
            }
        }
        return mostCommonBits;
    }

    public List<Integer> getLeastCommonBits(List<Integer> mostCommonBits) {
        List<Integer> leastCommonBits = new ArrayList<>();
        for (int i = 0; i < mostCommonBits.size(); i++) {
            if (mostCommonBits.get(i) == 1) {
                leastCommonBits.add(0);
            } else {
                leastCommonBits.add(1);
            }
        }
        return leastCommonBits;
    }

    public Double getDecimalFromBinary(List<Integer> binary) {
        Double decimal = 0.0;
        Collections.reverse(binary);
        for (int i = 0; i < binary.size(); i++) {
            if (binary.get(i) == 1) {
                decimal += Math.pow(2, i);
            }
        }
        return decimal;
    }

    public static void main(String[] args) {
        puzzle3 puzzle = new puzzle3();
        try {
            Integer[] bitCounter = puzzle.readInstructionInput("input.txt");
            List<Integer> mostCommonBits = puzzle.getMostCommonBits(bitCounter);
            List<Integer> leastCommonBits = puzzle.getLeastCommonBits(mostCommonBits);
            Double leastDecimal = puzzle.getDecimalFromBinary(leastCommonBits);
            Double mostDecimal = puzzle.getDecimalFromBinary(mostCommonBits);
            System.out.println("Puzzle 3 part 1: most common bits: " + mostCommonBits + " with decimal value: "
                    + mostDecimal);
            System.out.println("Puzzle 3 part 1: least common bits: " + leastCommonBits + " with decimal value: "
                    + leastDecimal);
            System.out.println("Puzzle 3 part 1: " + (mostDecimal * leastDecimal));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
