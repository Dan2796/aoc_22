package main.solutions.days.allDays;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Day {
    public Scanner input;
    public Day(boolean actual) {
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

    public abstract void parseInput() throws ParsingException;
    public abstract int getDay();
    public abstract Object getSolutionPart1() throws NoSolutionFoundException;
    public abstract Object getSolutionPart2() throws NoSolutionFoundException;

    public static void printSolutions(Day day) throws NoSolutionFoundException, ParsingException {
        day.parseInput();
        System.out.println("Day " + day.getDay() + " solution to part 1: " + day.getSolutionPart1().toString());
        System.out.println("Day " + day.getDay() + " solution to part 2: " + day.getSolutionPart2().toString());
    }
}
