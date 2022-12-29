package main.solutions.days.allDays;

public class NoSolutionFoundException extends Exception {
    public NoSolutionFoundException(int part) {
        super("No solution found to part " + part);
    }
}
