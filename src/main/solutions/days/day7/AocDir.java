package main.solutions.days.day7;

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
        for (Node child : children) {
            sizeCounter += child.getSize();
        }
        return sizeCounter;
    }

    @Override
    String getType() {
        return "Dir";
    }
}
