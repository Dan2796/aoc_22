package main.solutions.days;
        import java.util.*;

class Day11 extends Day{
    Day11(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 11;
    }

    ArrayList<Monkey> monkeys;
    @Override
    void parseInput() {
        monkeys = new ArrayList<>();
        int current = 0;
        while(input.hasNext()){
            // groups of three for each monkey
            if (current != 0) {
                input.nextLine(); // Blank line
            }
            input.nextLine(); // just title
            monkeys.add(new Monkey(monkeys.size()));
            String items = input.nextLine();
            String[] itemsList = items.replace("Starting items:", "").replace(" ", "").split(",");
            for (String item: itemsList) {
                monkeys.get(current).addItem(Integer.parseInt(item));
            }
            String[] operation = input.nextLine().replace("  Operation: new = old ", "").split(" ");
            monkeys.get(current).setOperator(operation[0]);
            monkeys.get(current).setOperand(operation[1]);
            String test = input.nextLine();
            monkeys.get(current).setCheckDivider(Integer.parseInt(test.replace("  Test: divisible by ", "")));
            String ifTrue = input.nextLine();
            monkeys.get(current).setIfTrueThrowTo(Integer.parseInt(ifTrue.replace("    If true: throw to monkey ", "")));
            String ifFalse = input.nextLine();
            monkeys.get(current).setIfFalseThrowTo(Integer.parseInt(ifFalse.replace("    If false: throw to monkey ", "")));
            current++;
        }
    }

    @Override
    Integer getSolutionPart1() {
        for (int i = 0; i < 20; i++) {
            for (Monkey monkey: monkeys) {
                monkey.throwAllItems(monkeys);
            }
        }
        HashMap<Integer, Integer> handledTallies = new HashMap<>();
        for (Monkey monkey: monkeys) {
            handledTallies.put(monkey.getId(), monkey.getHandledItems());
        }
        int max = 0;
        int second_max = 0;
        for (int score: handledTallies.values()) {
            if (score > max) {
                second_max = max;
                max = score;
            } else if (score > second_max) {
                second_max = score;
            }
        }
        return max * second_max;
    }

    @Override
    String getSolutionPart2() {
        return "tbd";
    }
}

class Monkey {
    final private ArrayList<Integer> items;
    private int checkDivider, ifTrueThrowTo, ifFalseThrowTo;
    private String operator, operand;
    final private int id;
    private int handledItems = 0;
    Monkey(int id) {
        items = new ArrayList<>();
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    public int getHandledItems() {
        return this.handledItems;
    }
    public void setOperand(String operand) {
        this.operand = operand;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public void setCheckDivider(int checkDivider) {
        this.checkDivider = checkDivider;
    }
    public void setIfTrueThrowTo(int ifTrueThrowTo) {
        this.ifTrueThrowTo = ifTrueThrowTo;
    }
    public void setIfFalseThrowTo(int ifFalseThrowTo) {
        this.ifFalseThrowTo = ifFalseThrowTo;
    }
    public void addItem(int item) {
        items.add(item);
    }
    public void printMonkeyItems(){
        System.out.print("Monkey " + this.id + " has: ");
        for (int item: items) {
            System.out.print(item + ", ");
        }
        System.out.println();
    }

    public int doOperation(int worryLevel) {
        int operandNum;
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

    public void throwItem(int worryLevel, ArrayList<Monkey> monkeyList) {
        worryLevel = doOperation(worryLevel);
        worryLevel = (int) Math.floor(worryLevel / 3);
        if (worryLevel % this.checkDivider == 0) {
            monkeyList.get(this.ifTrueThrowTo).addItem(worryLevel);
        } else {
            monkeyList.get(this.ifFalseThrowTo).addItem(worryLevel);
        }
        this.items.remove(0);
        this.handledItems++;
    }

    public void throwAllItems(ArrayList<Monkey> monkeyList) {
        while (!items.isEmpty()) {
            throwItem(items.get(0), monkeyList);
        }
    }
}
