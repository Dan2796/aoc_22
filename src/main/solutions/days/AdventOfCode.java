package main.solutions.days;
import java.util.*;
import java.io.*;

public class AdventOfCode {
    public static void main(String[] args) throws NoSolutionFoundException {
        main.solutions.days.Day.printSolutions(new Day6(true));
    }
}

abstract class Day {
    Scanner input;
    Day(boolean actual) {
        File file;
        if (actual) {
            file = new File("inputs/actual/day_" + this.getDay() + ".txt");
        }
        else {
            file = new File("inputs/example/day_" + this.getDay() + ".txt");
        }
        try {
            this.input = new Scanner(file);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException();
        }
    }

    abstract void parseInput();
    abstract int getDay();
    abstract Object getSolutionPart1() throws NoSolutionFoundException;
    abstract Object getSolutionPart2() throws NoSolutionFoundException;

    static void printSolutions(Day day) throws NoSolutionFoundException {
        day.parseInput();
        System.out.println("Day " + day.getDay() + " solution to part 1: " + day.getSolutionPart1().toString());
        System.out.println("Day " + day.getDay() + " solution to part 2: " + day.getSolutionPart2().toString());
    }
}

class NoSolutionFoundException extends Exception {
    public NoSolutionFoundException(int part) {
        super("No solution found to part " + part);
     }
}

