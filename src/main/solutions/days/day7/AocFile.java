package main.solutions.days.day7;

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
