package main.solutions.days;
import java.util.*;

class Day1 extends Day {
    Day1(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 1;
    }

    final private ArrayList<Integer> calorieNumbers = new ArrayList<>();

    @Override
    void parseInput() {
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
    Integer getSolutionPart1() {
        return calorieNumbers.get(0);
    }

    @Override
    Integer getSolutionPart2() {
        return (calorieNumbers.get(0) + calorieNumbers.get(1) + calorieNumbers.get(2));
    }
}
