package main.solutions.days.day05;
import main.solutions.days.allDays.Day;

import java.util.*;

public class Day05 extends Day {
    public Day05(boolean actual) {
        super(actual);
    }

    @Override
    public int getDay() {
        return 5;
    }

    ArrayList<Stack<Character>> stacksList = new ArrayList<>();
    ArrayList<Instruction> instructions = new ArrayList<>();
    @Override
    public void parseInput() {
        ArrayList<String> rawStartingStacks = new ArrayList<>();
        // start by parsing the stack map
        String row = input.nextLine();
        while(!row.equals("")) {
            rawStartingStacks.add(row);
            row = input.nextLine();
        }
        // while loop leaves row as the numbering of the stacks - can use to get stack number
        String[] stackNumbers = rawStartingStacks.get(rawStartingStacks.size() - 1).split(" ");
        int numberOfStacks = Integer.parseInt(stackNumbers[stackNumbers.length - 1]);
        rawStartingStacks.remove(rawStartingStacks.size() - 1);
        // now go in reverse and start creating the stacks from the bottom up
        Collections.reverse(rawStartingStacks);
        for (int i = 0; i <= numberOfStacks - 1; i++) {
            stacksList.add(new Stack<>());
        }
        for (String line: rawStartingStacks) {
            int stackNumber = 0;
            for (int i = 1; i < line.length(); i += 4) {
                if (line.charAt(i) != ' ') {
                    stacksList.get(stackNumber).add(line.charAt(i));
                }
                stackNumber++;
            }
        }
        // now parse the instructions - note minus 1 for stack index since we index from zero
        while(input.hasNext()) {
            instructions.add(new Instruction(input.nextLine().split(" ")));
        }
    }

    @Override
    public StringBuilder getSolutionPart1() {
        // make copy so can keep original for part 2, or could re-run this function
        ArrayList<Stack<Character>> stacksListP1 = new ArrayList<>();
        for (Stack<Character> stack: stacksList) {
            stacksListP1.add((Stack<Character>) stack.clone());
        }
        for (Instruction instruction: instructions) {
            instruction.crateMover9000(stacksListP1);
        }
        StringBuilder topOfEach = new StringBuilder();
        for (Stack<Character> stack: stacksListP1) {
            topOfEach.append(stack.peek());
        }
        return topOfEach;
    }

    @Override
    public StringBuilder getSolutionPart2() {
        ArrayList<Stack<Character>> stacksListP2 = new ArrayList<>();
        for (Stack<Character> stack: stacksList) {
            stacksListP2.add((Stack<Character>) stack.clone());
        }
        for (Instruction instruction: instructions) {
            instruction.crateMover9001(stacksListP2);
        }
        StringBuilder topOfEach = new StringBuilder();
        for (Stack<Character> stack: stacksListP2) {
            topOfEach.append(stack.peek());
        }
        return topOfEach;

    }
}
