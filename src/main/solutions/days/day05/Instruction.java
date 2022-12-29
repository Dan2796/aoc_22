package main.solutions.days.day05;

import java.util.ArrayList;
import java.util.Stack;

class Instruction {
    final private int nToMove, moveTo, moveFrom;

    Instruction(String[] instructionString) {
        nToMove = Integer.parseInt(instructionString[1]);
        // note subtracting one so that we can use them as indices starting at 0
        moveFrom = Integer.parseInt(instructionString[3]) - 1;
        moveTo = Integer.parseInt(instructionString[5]) - 1;
    }

    public void crateMover9000(ArrayList<Stack<Character>> stacksList) {
        for (int i = 0; i < this.nToMove; i++) {
            stacksList.get(this.moveTo).push(stacksList.get(this.moveFrom).pop());
        }
    }

    public void crateMover9001(ArrayList<Stack<Character>> stacksList) {
        Stack<Character> temp = new Stack<>();
        for (int i = 0; i < this.nToMove; i++) {
            temp.push(stacksList.get(this.moveFrom).pop());
        }
        for (int i = 0; i < this.nToMove; i++) {
            stacksList.get(this.moveTo).push(temp.pop());
        }
    }
}
