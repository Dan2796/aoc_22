package main.solutions.days.day01;
import main.solutions.days.allDays.Day;

import java.util.*;

public class Day01 extends Day {
    public Day01(boolean actual) {
        super(actual);
    }

    @Override
    public int getDay() {
        return 1;
    }

    final private ArrayList<Integer> calorieNumbers = new ArrayList<>();

    @Override
    public void parseInput() {
        int calorieCounter = 0;
        while (input.hasNext()) {
            String calorie = input.nextLine();
            if (!Objects.equals(calorie, "")) {
                calorieCounter += Integer.parseInt(calorie);
            } else {
                calorieNumbers.add(calorieCounter);
                calorieCounter = 0;
            }
        }
        // add last number in list:
        calorieNumbers.add(calorieCounter);
        calorieNumbers.sort(Collections.reverseOrder());
    }

    @Override
    public Integer getSolutionPart1() {
        return calorieNumbers.get(0);
    }

    @Override
    public Integer getSolutionPart2() {
        return (calorieNumbers.get(0) + calorieNumbers.get(1) + calorieNumbers.get(2));
    }
}
