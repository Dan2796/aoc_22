package main.solutions.days.day11;

import main.solutions.days.allDays.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.math.BigInteger;

public class Day11 extends Day {
    public Day11(boolean actual) {
        super(actual);
    }

    @Override
    public int getDay() {
        return 11;
    }

    // easiest to just have two monkey lists for each part rather than try and copy them across
    ArrayList<Monkey> monkeysP1;
    ArrayList<Monkey> monkeysP2;
    int lcm;
    @Override
    public void parseInput() {
        monkeysP1 = new ArrayList<>();
        monkeysP2 = new ArrayList<>();
        int current = 0;
        while(input.hasNext()){
            // group lines together per monkey
            if (current != 0) {
                input.nextLine(); // Blank line
            }
            input.nextLine(); // just title
            String items = input.nextLine();
            String[] itemsList = items.replace("Starting items:", "").replace(" ", "").split(",");
            String[] operation = input.nextLine().replace("  Operation: new = old ", "").split(" ");
            String test = input.nextLine();
            String ifTrue = input.nextLine();
            String ifFalse = input.nextLine();
            monkeysP1.add(new Monkey(monkeysP1.size(),
                        operation[0],
                        operation[1],
                        Integer.parseInt(test.replace("  Test: divisible by ", "")),
                        Integer.parseInt(ifTrue.replace("    If true: throw to monkey ", "")),
                        Integer.parseInt(ifFalse.replace("    If false: throw to monkey ", ""))));
            monkeysP2.add(new Monkey(monkeysP2.size(),
                            operation[0],
                            operation[1],
                            Integer.parseInt(test.replace("  Test: divisible by ", "")),
                            Integer.parseInt(ifTrue.replace("    If true: throw to monkey ", "")),
                            Integer.parseInt(ifFalse.replace("    If false: throw to monkey ", ""))));
            for (String item: itemsList) {
                monkeysP1.get(current).addItem(Integer.parseInt(item));
                monkeysP2.get(current).addItem(Integer.parseInt(item));

            }
            current++;
        }
        // get lcm
        HashSet<Integer> allCheckDividers = new HashSet<>();
        for (Monkey monkey: monkeysP1) {
            allCheckDividers.add(monkey.getCheckDivider());
        }
        lcm = 1;
        while (true) {
            boolean foundLcm = true;
            for (int divider: allCheckDividers) {
                if (lcm % divider != 0) {
                    foundLcm = false;
                    break;
                }
            }
            if (foundLcm) {
                break;
            }
            lcm++;
        }
    }

    @Override
    public BigInteger getSolutionPart1() {
        for (int i = 0; i < 20; i++) {
            for (Monkey monkey: monkeysP1) {
                monkey.throwAllItems(monkeysP1, true, lcm);
            }
        }
        return getMonkeyBusiness(monkeysP1);
    }

    @Override
    public BigInteger getSolutionPart2() {
        for (int i = 0; i < 10000; i++) {
            for (Monkey monkey: monkeysP2) {
                monkey.throwAllItems(monkeysP2, false, lcm);
            }
        }
        return getMonkeyBusiness(monkeysP2);
    }

    BigInteger getMonkeyBusiness(ArrayList<Monkey> monkeys) {
        HashMap<Integer, Integer> handledTallies = new HashMap<>();
        for (Monkey monkey: monkeys) {
            handledTallies.put(monkey.getId(), monkey.getHandledItems());
        }
        int max = 0;
        int secondMax = 0;
        for (int score: handledTallies.values()) {
            if (score > max) {
                secondMax = max;
                max = score;
            } else if (score > secondMax) {
                secondMax = score;
            }
        }
        return BigInteger.valueOf(max).multiply(BigInteger.valueOf(secondMax));
    }
}
