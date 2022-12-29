package main.solutions.days.day09;

import main.solutions.days.allDays.Day;

public class Day09 extends Day {
    public Day09(boolean actual) {
        super(actual);
    }

    @Override
    public int getDay() {
        return 9;
    }

    Rope rope = new Rope(10);
    @Override
    public void parseInput() {
        while (input.hasNext()) {
            rope.moveHeadAndTail(input.nextLine());
        }
    }

    @Override
    public Integer getSolutionPart1() {
        return rope.getVisited(1).size();
    }

    @Override
    public Integer getSolutionPart2() {
        return rope.getVisited(9).size();
    }
}
