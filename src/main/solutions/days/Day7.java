package main.solutions.days;
import java.util.*;

class Day7 extends Day{
    Day7(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 7;
    }

    Node root = new AocDir("/");
    @Override
    void parseInput() throws ParsingException {
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
    Integer getSolutionPart1() {
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
    Integer getSolutionPart2() {
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

abstract class Node {
    final String name;
    Node parent;
    HashSet<Node> children;

    // version without parent for root - root as parent can be added straight after
    Node(String name) {
        this.name = name;
        children = new HashSet<>();
    }

    Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
        children = new HashSet<>();
    }

    String getName() {
        return this.name;
    }

    Node getParent() {
        return this.parent;
    }

    void addParent(Node parent) {
        this.parent = parent;
    }

    HashSet<Node> getChildren() {
        return this.children;
    }


    Node getChild(String name) {
        for (Node child : this.getChildren()) {
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }

    // So can check if added file without needing to override getHashcode etc
    HashSet<String> getChildrenNames() {
        HashSet<String> childrenNames = new HashSet<>();
        for (Node child : this.getChildren()) {
            childrenNames.add(child.getName());
        }
        return childrenNames;
    }

    void addChild(Node child) {
        children.add(child);
    }

    abstract int getSize();

    abstract String getType();

    HashSet<Node> getChildrenRecursive(HashSet<Node> allChildren) {
        for (Node child: this.children) {
            allChildren.add(child);
            if (child.getType().equals("Dir")) {
                child.getChildrenRecursive(allChildren);
            }
        }
        return allChildren;
    }
}

// Aoc prefix to avoid confusion with existing File class
class AocFile extends Node {
    final int size;
    AocFile(String name, Node parent, int size) {
        super(name, parent);
        this.size = size;
    }
    int getSize() {
        return this.size;
    }

    @Override
    String getType() {
        return "File";
    }
}
class AocDir extends Node {
    // option without parent for root
    AocDir(String name) {
        super(name);
    }
    AocDir(String name, Node parent) {
        super(name, parent);
    }
    int getSize() {
        int sizeCounter = 0;
        for (Node child: children) {
            sizeCounter += child.getSize();
        }
        return sizeCounter;
    }

    @Override
    String getType() {
        return "Dir";
    }
}
