package main.solutions.days;
import java.util.*;

class Day4 extends Day{
    Day4(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 4;
    }

    HashMap<int[], int[]> pairTasks = new HashMap<>();
    @Override
    void parseInput() {
        while (input.hasNext()) {
            String[] rawInput = input.nextLine().split("(,)|(-)");
            pairTasks.put(new int[]{Integer.parseInt(rawInput[0]), Integer.parseInt(rawInput[1])},
                          new int[]{Integer.parseInt(rawInput[2]), Integer.parseInt(rawInput[3])});
        }
    }

    @Override
    Integer getSolutionPart1() {
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
    Integer getSolutionPart2() {
        int overlapCounter = 0;
        for (int[] key: pairTasks.keySet()) {
            if (!(key[0] > pairTasks.get(key)[1] || key[1] < pairTasks.get(key)[0])) {
                overlapCounter++;
            }
        }
        return overlapCounter;
    }
}


