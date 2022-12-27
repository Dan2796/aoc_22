package main.solutions.days;
import java.util.*;

class Day10 extends Day{
    Day10(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 10;
    }

    HashMap<Integer, Integer> Xpath= new HashMap<>();
    @Override
    void parseInput() {
        int cycle = 0;
        int Xstatus = 1;
        Xpath.put(cycle, Xstatus);
        while (input.hasNext()) {
            String[] instruction = input.nextLine().split(" ");
            if (instruction[0].equals("noop")) {
                cycle++;
                Xpath.put(cycle, Xstatus);
            } else {
                cycle++;
                Xpath.put(cycle, Xstatus);
                cycle++;
                Xstatus += Integer.valueOf(instruction[1]);
                Xpath.put(cycle, Xstatus);
            }
        }
    }

    @Override
    Integer getSolutionPart1() {
        int signalStrengthSum = 0;
        // Starts on 19 because that gives the strength DURING the 20th cycle
        for (int i = 19; i < 220; i += 40) {
            signalStrengthSum += (i + 1) * Xpath.get(i);
        }
        return signalStrengthSum;
    }

    @Override
    String getSolutionPart2() {
        for (int j = 0; j < 220; j += 40) {
            for (int i = 0; i < 40; i++) {
                if (i == Xpath.get(i + j) | i == Xpath.get(i + j) - 1 | i == Xpath.get(i + j) + 1) {
                    System.out.print("# ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
        return "Solution has been printed above.";
    }
}


