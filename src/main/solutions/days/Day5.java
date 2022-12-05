package main.solutions.days;
import java.util.*;

class Day5 extends Day{
    Day5(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 5;
    }

    ArrayList<Stack<Character>> stacksList = new ArrayList<>();
    ArrayList<Integer[]> instructions = new ArrayList<>();
    @Override
    void parseInput() {
        ArrayList<String> rawStartingStacks = new ArrayList<>();
        int numberOfStacks = 0;
        // start by parsing the stack map
        while(input.hasNext()) {
            String row = input.nextLine();
            if (row.equals("")) {
                String[] stackNumbers = rawStartingStacks.get(rawStartingStacks.size() - 1).split(" ");
                numberOfStacks = Integer.parseInt(stackNumbers[stackNumbers.length - 1]);
                rawStartingStacks.remove(rawStartingStacks.size() - 1);
                break;
            }
            rawStartingStacks.add(row);
        }
        // now go in reverse and start creating the stacks from the bottom up
        Collections.reverse(rawStartingStacks);
        for (int i = 0; i <= numberOfStacks - 1; i++)
            stacksList.add(new Stack<>());
        for (String row: rawStartingStacks) {
            int stackNumber = 0;
            for (int i = 1; i < row.length(); i += 4) {
                if (row.charAt(i) != ' ') {
                    stacksList.get(stackNumber).add(row.charAt(i));
                }
                stackNumber++;
            }
        }
        // now parse the instructions - note minus 1 for stack index since we index from zero
        while(input.hasNext()) {
            String[] instructionLine = input.nextLine().split(" ");
            instructions.add(new Integer[]{ Integer.parseInt(instructionLine[1]),
                                            Integer.parseInt(instructionLine[3]) - 1,
                                            Integer.parseInt(instructionLine[5]) - 1});
        }
    }

    public static void execInstruction(ArrayList<Stack<Character>> stacksList, Integer[] instruction) {
        for (int i = 0; i < instruction[0]; i++)
            stacksList.get(instruction[2]).push(stacksList.get(instruction[1]).pop());
    }
    @Override
    StringBuilder getSolutionPart1() {
        // make copy so can keep original for part 2, or could re-run this function
        ArrayList<Stack<Character>> stacksListP1 = new ArrayList<>();
        for (Stack<Character> stack: stacksList) {
            stacksListP1.add((Stack<Character>) stack.clone());
        }
        for (Integer[] instruction: instructions) {
            execInstruction(stacksListP1, instruction);
        }
        StringBuilder topOfEach = new StringBuilder();
        for (Stack<Character> stack: stacksListP1)
            topOfEach.append(stack.peek());
        return topOfEach;
    }

    public static void execInstructionP2(ArrayList<Stack<Character>> stacksList, Integer[] instruction) {
        Stack<Character> temp = new Stack<>();
        for (int i = 0; i < instruction[0]; i++)
            temp.push(stacksList.get(instruction[1]).pop());
        for (int i = 0; i < instruction[0]; i++)
            stacksList.get(instruction[2]).push(temp.pop());
    }
    @Override
    StringBuilder getSolutionPart2() {
        ArrayList<Stack<Character>> stacksListP2 = new ArrayList<>();
        for (Stack<Character> stack: stacksList) {
            stacksListP2.add((Stack<Character>) stack.clone());
        }
        for (Integer[] instruction: instructions) {
            execInstructionP2(stacksListP2, instruction);
        }
        StringBuilder topOfEach = new StringBuilder();
        for (Stack<Character> stack: stacksListP2)
            topOfEach.append(stack.peek());
        return topOfEach;

    }
}


