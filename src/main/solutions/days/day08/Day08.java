package main.solutions.days.day08;

import main.solutions.days.allDays.Day;
import main.useful.classes.Coordinate;

public class Day08 extends Day {
    public Day08(boolean actual) {
        super(actual);
    }

    @Override
    public int getDay() {
        return 8;
    }

    Forest forest;
    @Override
    public void parseInput() {
        forest = new Forest();
        int row = 0;
        while (input.hasNext()) {
            String line = input.nextLine();
            for (int col = 0; col < line.length(); col++) {
                Coordinate treeLocation = new Coordinate(row, col);
                Integer treeHeight = Integer.parseInt(String.valueOf(line.charAt(col)));
                forest.addTree(treeLocation, treeHeight);
            }
            row++;
        }
    }

    @Override
    public Integer getSolutionPart1() {
        int howManyVisible = 0;
        for (int y = 0; y <= forest.maxY; y++) {
                for (int x = 0; x <= forest.maxX; x++) {
                    if (forest.isVisible(new Coordinate(x, y))) {
                        howManyVisible++;
                    }
                }
        }
        return howManyVisible;
    }

    @Override
    public Integer getSolutionPart2() {
        int highestScenicScore = 0;
        for (int y = 0; y <= forest.maxY; y++) {
            for (int x = 0; x <= forest.maxX; x++) {
                highestScenicScore = Math.max(forest.scenicScore(new Coordinate(x, y)), highestScenicScore);
            }
        }
        return highestScenicScore;
    }
}
