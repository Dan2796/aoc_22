package main.solutions.days.day07;
import main.solutions.days.allDays.Day;
import main.solutions.days.allDays.ParsingException;

import java.util.*;

public class Day07 extends Day {
    public Day07(boolean actual) {
        super(actual);
    }

    @Override
    public int getDay() {
        return 7;
    }

    Node root = new AocDir("/");
    @Override
    public void parseInput() throws ParsingException {
        root.addParent(root);
        Node currentFile = root;
        while (input.hasNext()) {
            String nextLine = input.nextLine();
            if (nextLine.equals("$ ls")) {
                // do nothing
            } else if (nextLine.charAt(0) == '$') {
                String toFile = nextLine.split(" ")[2];
                if (toFile.equals("..")) {
                    currentFile = currentFile.getParent();
                } else if (!currentFile.getChildrenNames().contains(toFile)) {
                    Node toAdd = new AocDir(toFile, currentFile);
                    currentFile.addChild(toAdd);
                    currentFile = toAdd;
                } else {
                    currentFile = currentFile.getChild(toFile);
                }
            }  else if (nextLine.charAt(0) == 'd') { // dir
                Node toAdd = new AocDir(nextLine.substring(4), currentFile);
                currentFile.addChild(toAdd);
            } else {
                String[] newFile = nextLine.split(" ");
                try{
                    Node toAdd = new AocFile(newFile[1], currentFile.getParent(), Integer.parseInt(newFile[0]));
                    currentFile.addChild(toAdd);
                } catch (Exception ParsingException){
                    throw new ParsingException();
                }
            }
        }
    }

    @Override
    public Integer getSolutionPart1() {
        HashSet<Node> rootChildren = new HashSet<>();
        int sizeCounter = 0;
        if (root.getSize() <= 100000) {
            sizeCounter += root.getSize();
        }
        for (Node child : root.getChildrenRecursive(rootChildren)) {
            if (child.getSize() <= 100000 && child.getType().equals("Dir")) {
                sizeCounter += child.getSize();
            }
        }
        return sizeCounter;
    }

    @Override
    public Integer getSolutionPart2() {
        int needsFreeing = 30000000 - (70000000 - root.getSize());
        Node smallestButBigEnough = root;
        HashSet<Node> rootChildren = new HashSet<>();
        for (Node child : root.getChildrenRecursive(rootChildren)) {
            if (child.getType().equals("Dir") &&
                    child.getSize() >= needsFreeing &&
                    child.getSize() < smallestButBigEnough.getSize()) {
                smallestButBigEnough = child;
            }
        }
        return smallestButBigEnough.getSize();
    }
}
