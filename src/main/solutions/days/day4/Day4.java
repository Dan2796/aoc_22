package main.solutions.days.day4;
import main.solutions.days.allDays.Day;

import java.util.*;

public class Day4 extends Day {
    public Day4(boolean actual) {
        super(actual);
    }

    @Override
    public int getDay() {
        return 4;
    }

    HashMap<int[], int[]> pairTasks = new HashMap<>();
    @Override
    public void parseInput() {
        while (input.hasNext()) {
            String[] rawInput = input.nextLine().split("(,)|(-)");
            pairTasks.put(new int[]{Integer.parseInt(rawInput[0]), Integer.parseInt(rawInput[1])},
                          new int[]{Integer.parseInt(rawInput[2]), Integer.parseInt(rawInput[3])});
        }
    }

    @Override
    public Integer getSolutionPart1() {
        int properSubsetCounter = 0;
        for (int[] key: pairTasks.keySet()) {
            if (key[0] <= pairTasks.get(key)[0] && key[1] >= pairTasks.get(key)[1] ||
                    key[0] >= pairTasks.get(key)[0] && key[1] <= pairTasks.get(key)[1] ) {
                properSubsetCounter++;
            }
        }
        return properSubsetCounter;
    }

    @Override
    public Integer getSolutionPart2() {
        int overlapCounter = 0;
        for (int[] key: pairTasks.keySet()) {
            if (!(key[0] > pairTasks.get(key)[1] || key[1] < pairTasks.get(key)[0])) {
                overlapCounter++;
            }
        }
        return overlapCounter;
    }
}
