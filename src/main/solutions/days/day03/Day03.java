package main.solutions.days.day03;
import main.solutions.days.allDays.Day;

import java.util.*;

public class Day03 extends Day {
    public Day03(boolean actual) {
        super(actual);
    }

    @Override
    public int getDay() {
        return 3;
    }

    ArrayList<Rucksack> allRucksacks = new ArrayList<>();
    @Override
    public void parseInput() {
        while (input.hasNext()) {
            allRucksacks.add(new Rucksack(input.nextLine()));
        }
    }

    @Override
    public Integer getSolutionPart1() {
        int priorityCounter = 0;
        for (Rucksack rucksack: allRucksacks) {
            priorityCounter += Rucksack.getPriority(rucksack.getDuplicateItem());
        }
        return priorityCounter;
    }

    @Override
    public Integer getSolutionPart2() {
        int priorityCounter = 0;
        for (int i = 0; i < allRucksacks.size() - 2; i+= 3) {
            priorityCounter += Rucksack.getPriority(
                    Rucksack.getBadge(allRucksacks.get(i), allRucksacks.get(i + 1), allRucksacks.get(i + 2)));
        }
        return priorityCounter;
    }
}
