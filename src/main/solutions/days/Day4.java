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
            String[] rawInput;
            String[] firstPersonRaw;
            String[] secondPersonRaw;
            rawInput = input.nextLine().split(",");
            firstPersonRaw = rawInput[0].split("-");
            secondPersonRaw = rawInput[1].split("-");
            int[] firstPerson = {Integer.parseInt(firstPersonRaw[0]), Integer.parseInt(firstPersonRaw[1])};
            int[] secondPerson = {Integer.parseInt(secondPersonRaw[0]), Integer.parseInt(secondPersonRaw[1])};
            pairTasks.put(firstPerson, secondPerson);
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


