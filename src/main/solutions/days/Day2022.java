package main.solutions.days;
import java.util.*;
import java.io.*;

public abstract class Day2022 {
    public static void main(String[] args) {
        printSolutions(new Day1());
        //printSolutions(new Day2());
    }

    Scanner input;
    int day;

    public Day2022(boolean actual, int day) {
        this.day = day;
        File file;
        if (actual) {
            file = new File("inputs/actual/day_" + day + ".txt");
        }
        else {
            file = new File("inputs/example/day_" + day + ".txt");
        }
        try {
            this.input = new Scanner(file);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException();
        }
    }

    public int getDay() {
        return this.day;
    }

    public abstract void parseInput();
    public abstract String getSolutionPart1();
    public abstract String getSolutionPart2();

    public static void printSolutions(Day2022 day) {
        day.parseInput();
        System.out.println("Day " + day.getDay() + " solution to part 1: " + day.getSolutionPart1());
        System.out.println("Day " + day.getDay() + " solution to part 2: " + day.getSolutionPart2());
    }
}


