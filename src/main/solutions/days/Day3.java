package main.solutions.days;
import java.util.*;

class Day3 extends Day{
    Day3(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 3;
    }

    ArrayList<Rucksack> allRucksacks = new ArrayList<>();
    @Override
    void parseInput() {
        while (input.hasNext()) {
            allRucksacks.add(new Rucksack(input.nextLine()));
        }
    }

    @Override
    Integer getSolutionPart1() {
        int priorityCounter = 0;
        for (Rucksack rucksack: allRucksacks) {
            priorityCounter += Rucksack.getPriority(rucksack.getDuplicateItem());
        }
        return priorityCounter;
    }

    @Override
    Integer getSolutionPart2() {
        int priorityCounter = 0;
        for (int i = 0; i < allRucksacks.size() - 2; i+= 3) {
            priorityCounter += Rucksack.getPriority(
                    Rucksack.getBadge(allRucksacks.get(i), allRucksacks.get(i + 1), allRucksacks.get(i + 2)));
        }
        return priorityCounter;
    }
}

class Rucksack {
    final private HashSet<Character> bothCompartments = new HashSet<>();
    final private HashSet<Character> contents1 = new HashSet<>();
    final private HashSet<Character> contents2 = new HashSet<>();

    Rucksack(String allContents) {
        for (int i = 0; i < allContents.length(); i++) {
            bothCompartments.add(allContents.charAt(i));
            if (i < 0.5 * allContents.length()) {
                contents1.add(allContents.charAt(i));
            } else {
                contents2.add(allContents.charAt(i));
            }
        }
    }

    public HashSet<Character> getBothCompartments() {
        return bothCompartments;
    }

    public char getDuplicateItem() {
        for (char item: this.contents1) {
            if (contents2.contains(item)) {
                return item;
            }
        }
        System.out.println("Error - could not find duplicate.");
        return '*';
    }

    public static int getPriority(char letter) {
        // will return 0 in case of non-duplicated item ('*' = 42)
        int priorityValue = 0;
        if (letter >= 65 && letter <= 90)
            priorityValue = letter - 38;
        else if (letter >= 97 && letter <= 122) {
            priorityValue = letter - 96;
        }
        return priorityValue;
    }

    public static char getBadge(Rucksack r0, Rucksack r1, Rucksack r2) {
        HashSet<Character> contents0 = r0.getBothCompartments();
        HashSet<Character> contents1 = r1.getBothCompartments();
        HashSet<Character> contents2 = r2.getBothCompartments();
        contents0.retainAll(contents1);
        contents0.retainAll(contents2);
        if (contents0.size() == 1) {
            return contents0.iterator().next();
        }
        System.out.println("Error - could not find badge.");
        return '*';
    }
}

