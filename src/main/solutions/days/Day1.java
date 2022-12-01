package main.solutions.days;
import java.util.*;

public class Day1 extends Day2022 {
    Day1() {
        super(true, 1);
    }

    HashSet<Integer> calorieNumbers = new HashSet<>();

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
    }

    public String getSolutionPart1() {
        return Integer.toString(Collections.max(calorieNumbers));
    }

    public String getSolutionPart2() {
        int first = Collections.max(calorieNumbers);
        calorieNumbers.remove(first);
        int second = Collections.max(calorieNumbers);
        calorieNumbers.remove(second);
        int third = Collections.max(calorieNumbers);
        return Integer.toString(first + second + third);
    }
}
