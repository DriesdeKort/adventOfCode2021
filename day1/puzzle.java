package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class puzzle {
    /**
     * returns a list of integers from a given input file.
     * 
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public List<Integer> readIntegerInput(String fileName) throws FileNotFoundException {
        File input = new File(fileName);
        Scanner reader = new Scanner(input);
        List<Integer> result = new ArrayList<>();
        while (reader.hasNextLine()) {
            result.add(Integer.parseInt(reader.nextLine()));
        }
        reader.close();
        return result;
    }

    /**
     * Compares two depths to see it the depth has increased, decreased, or stayed
     * the same.
     * 
     * @param previousInt the previous depth
     * @param currentInt  the current depth
     * @return
     */
    public String getDepthDescription(int previousInt, int currentInt) {
        if (previousInt < currentInt) {
            return "increased";
        } else if (previousInt > currentInt) {
            return "decreased";
        }
        return "same";

    }

    /**
     * Returns a list where every element is the sum of the elements frame.
     * A frame is a sublist that has the size of the frameSize and starts at the
     * index of the current element.
     * 
     * @param input
     * @param frameSize
     * @return
     */
    public List<Integer> getSumListByFrameSize(List<Integer> input, int frameSize) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.size() - frameSize + 1; i++) {
            // first create sublist, map to int, sum, add to result
            int sumOfFrame = input.subList(i, i + frameSize).stream().mapToInt(Integer::intValue).sum();
            result.add(sumOfFrame);
        }
        return result;

    }

    /**
     * counts how many times the depth has increased, decreased, or stayed the same
     * for a given list.
     * The first element of the list is not comparable and thus ignored.
     * 
     * @param input
     * @param description
     * @return
     */
    public int countDepthDescription(List<Integer> input, String description) {
        int result = 0;
        for (int i = 0; i < input.size() - 1; i++) {
            int previousInt = input.get(i);
            int currentInt = input.get(i + 1);
            String descriptionValue = getDepthDescription(previousInt, currentInt);
            if (descriptionValue.equals(description)) {
                result++;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        puzzle puzzle1 = new puzzle();
        String fileName = "input.txt";
        List<Integer> input = null;
        try {
            input = puzzle1.readIntegerInput(fileName);
            System.out.println("Part 1: " + puzzle1.countDepthDescription(input, "increased"));
            List<Integer> sumList = puzzle1.getSumListByFrameSize(input, 3);
            System.out.println("Part 2: " + puzzle1.countDepthDescription(sumList, "increased"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }
}
