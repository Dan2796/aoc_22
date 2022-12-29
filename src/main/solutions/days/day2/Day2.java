package main.solutions.days.day2;
import main.solutions.days.allDays.Day;

import java.util.*;
public class Day2 extends Day {
    public Day2(boolean actual) {
        super(actual);
    }

    @Override
    public int getDay() {
        return 2;
    }

    ArrayList<String> strategySheet = new ArrayList<>();
    @Override
    public void parseInput() {
        while (input.hasNext())
            strategySheet.add(input.nextLine());
    }

    static HashMap<String, Integer> scoreLookupP1 = new HashMap<>();
    static {
        scoreLookupP1.put("B X", 1);
        scoreLookupP1.put("C Y", 2);
        scoreLookupP1.put("A Z", 3);
        scoreLookupP1.put("A X", 4);
        scoreLookupP1.put("B Y", 5);
        scoreLookupP1.put("C Z", 6);
        scoreLookupP1.put("C X", 7);
        scoreLookupP1.put("A Y", 8);
        scoreLookupP1.put("B Z", 9);
    }
    static HashMap<String, Integer> scoreLookupP2 = new HashMap<>();
    static {
        scoreLookupP2.put("B X", 1);
        scoreLookupP2.put("C X", 2);
        scoreLookupP2.put("A X", 3);
        scoreLookupP2.put("A Y", 4);
        scoreLookupP2.put("B Y", 5);
        scoreLookupP2.put("C Y", 6);
        scoreLookupP2.put("C Z", 7);
        scoreLookupP2.put("A Z", 8);
        scoreLookupP2.put("B Z", 9);
    }

    @Override
    public Integer getSolutionPart1() {
        int scoreTallyP1 = 0;
        for (String strategy: strategySheet)
            scoreTallyP1 += scoreLookupP1.get(strategy);
        return scoreTallyP1;
    }

    @Override
    public Integer getSolutionPart2() {
        int scoreTallyP2 = 0;
        for (String strategy: strategySheet)
            scoreTallyP2 += scoreLookupP2.get(strategy);
        return scoreTallyP2;
    }
}
