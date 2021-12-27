package day2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class puzzle2 {

    public List<String> readInstructionInput(String fileName) throws FileNotFoundException {
        File input = new File(fileName);
        Scanner reader = new Scanner(input);
        List<String> instructions = new ArrayList<String>();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            instructions.add(line);
        }
        reader.close();
        return instructions;
    }

    public Point2 updatePosition(String instruction, int value, Point2 startPoint) {
        switch (instruction) {
            case "forward":
                startPoint.x += value;
                break;
            case "down":
                startPoint.y += value;
                break;
            case "up":
                startPoint.y -= value;
                break;
        }
        return startPoint;
    }

    public Point2 updatePositionWithAim(String instruction, int value, Point2 startPoint) {
        switch (instruction) {
            case "forward":
                startPoint.x += value;
                startPoint.y += value * startPoint.aim;
                break;
            case "down":
                startPoint.aim += value;
                break;
            case "up":
                startPoint.aim -= value;
                break;
        }
        return startPoint;
    }

    public static void main(String[] args) {
        puzzle2 puzzle2 = new puzzle2();
        String fileName = "day2/input.txt";
        try {
            List<String> instructions = puzzle2.readInstructionInput(fileName);
            Point2 startPoint = new Point2(0, 0, 0);
            Point2 startPoint2 = new Point2(0, 0, 0);

            for (String instruction : instructions) {
                String[] instructionParts = instruction.split(" ");
                String instructionType = instructionParts[0];
                int value = Integer.parseInt(instructionParts[1]);
                startPoint = puzzle2.updatePosition(instructionType, value, startPoint);
                startPoint2 = puzzle2.updatePositionWithAim(instructionType, value, startPoint2);
            }
            System.out.println("Part1: horizontal position: " + startPoint.x + " depth: " + startPoint.y);
            System.out.println("Part1: " + startPoint.x * startPoint.y);
            System.out.println("Part2: horizontal position: " + startPoint2.x + " depth: " + startPoint2.y + " aim: "
                    + startPoint2.aim);
            System.out.println("Part2: " + startPoint2.x * startPoint2.y);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
