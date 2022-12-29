package main.solutions.days.day11;

import java.util.ArrayList;

class Monkey {
    final private ArrayList<Long> items;
    final private int checkDivider, ifTrueThrowTo, ifFalseThrowTo;
    final private String operator, operand;
    final private int id;
    private int handledItems = 0;

    Monkey(int id, String operator, String operand, int checkDivider, int ifTrueThrowTo, int ifFalseThrowTo) {
        items = new ArrayList<>();
        this.id = id;
        this.operator = operator;
        this.operand = operand;
        this.checkDivider = checkDivider;
        this.ifTrueThrowTo = ifTrueThrowTo;
        this.ifFalseThrowTo = ifFalseThrowTo;
    }

    public int getId() {
        return this.id;
    }
    public int getCheckDivider() {
        return this.checkDivider;
    }

    public int getHandledItems() {
        return this.handledItems;
    }

    public void addItem(long item) {
        items.add(item);
    }

    public long doOperation(long worryLevel) {
        long operandNum;
        if (operand.equals("old")) {
            operandNum = worryLevel;
        } else {
            operandNum = Integer.parseInt(operand);
        }

        if (operator.equals("+")) {
            return worryLevel + operandNum;
        } else { // only other option is multiplication
            return worryLevel * operandNum;
        }
    }

    public void throwItemP1(long worryLevel, ArrayList<Monkey> monkeyList) {
        worryLevel = doOperation(worryLevel);
        worryLevel = (int) Math.floor((float) worryLevel / 3);
        if (worryLevel % this.checkDivider == 0) {
            monkeyList.get(this.ifTrueThrowTo).addItem(worryLevel);
        } else {
            monkeyList.get(this.ifFalseThrowTo).addItem(worryLevel);
        }
        this.items.remove(0);
        this.handledItems++;
    }

    public void throwItemP2(long worryLevel, ArrayList<Monkey> monkeyList, int checkMultiple) {
        worryLevel = doOperation(worryLevel);
        if (worryLevel > checkMultiple) {
            worryLevel = worryLevel % checkMultiple;
        }
        // manage worry level:
        if (worryLevel % this.checkDivider == 0) {
            monkeyList.get(this.ifTrueThrowTo).addItem(worryLevel);
        } else {
            if (worryLevel > checkMultiple) {
                worryLevel = worryLevel % checkMultiple;
            }
            monkeyList.get(this.ifFalseThrowTo).addItem(worryLevel);
        }
        this.items.remove(0);
        this.handledItems++;
    }

    public void throwAllItems(ArrayList<Monkey> monkeyList, boolean part1, int checkMultiple) {
        while (!items.isEmpty()) {
            if (part1) {
                throwItemP1(items.get(0), monkeyList);
            } else {
                throwItemP2(items.get(0), monkeyList, checkMultiple);
            }
        }
    }


}
