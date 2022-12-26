package main.solutions.days;
import java.util.*;
import main.useful.classes.Coordinate;

class Day9 extends Day{
    Day9(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 9;
    }

    RopePath ropePath = new RopePath();
    @Override
    void parseInput() {
        while (input.hasNext()) {
            ropePath.moveHeadAndTail(input.nextLine());
        }
    }

    @Override
    Integer getSolutionPart1() {
        return ropePath.getTailPath().keySet().size();
    }

    @Override
    String getSolutionPart2() {
        return "tbd";
    }
}

class RopePath {
    Coordinate head;
    Coordinate tail;

    HashMap<Coordinate, Integer> tailPath;
    RopePath() {
        head = new Coordinate(0,0);
        tail = new Coordinate(0,0);
        tailPath = new HashMap<>();
        tailPath.put(tail, 1);
    }

    public HashMap<Coordinate, Integer> getTailPath() {
        return tailPath;
    }

    public void moveHeadByOne(String direction) {
        switch (direction) {
            case "R" -> head = new Coordinate(head.getX() + 1, head.getY());
            case "L" -> head = new Coordinate(head.getX() - 1, head.getY());
            case "U" -> head = new Coordinate(head.getX(), head.getY() + 1);
            case "D" -> head = new Coordinate(head.getX(), head.getY() - 1);
        }
    }
    public void catchUpTail() {
        int headx = head.getX();
        int heady = head.getY();
        int tailx = tail.getX();
        int taily = tail.getY();

        if (headx == tailx && heady - taily == 2) {
            tail = new Coordinate(tailx, taily + 1);
        } else if (headx == tailx && heady - taily == -2) {
            tail = new Coordinate(tailx, taily - 1);
        } else if (headx - tailx == 2 && heady == taily) {
            tail = new Coordinate(tailx + 1, taily);
        } else if (headx - tailx == -2 && heady == taily) {
            tail = new Coordinate(tailx - 1, taily);
        // vertical diaganols:
        } else if (headx - tailx == 2 && heady - taily == 1) {
            tail = new Coordinate(tailx + 1, taily + 1);
        } else if (headx - tailx == -2 && heady - taily == 1) {
            tail = new Coordinate(tailx - 1, taily + 1);
        } else if (headx - tailx == 2 && heady - taily == -1) {
            tail = new Coordinate(tailx + 1, taily - 1);
        } else if (headx - tailx == -2 && heady - taily == -1) {
            tail = new Coordinate(tailx - 1, taily - 1);
        // horizontal diaganols:
        } else if (headx - tailx == 1 && heady - taily == 2) {
            tail = new Coordinate(tailx + 1, taily + 1);
        } else if (headx - tailx == 1 && heady - taily == -2) {
            tail = new Coordinate(tailx + 1, taily - 1);
        } else if (headx - tailx == -1 && heady - taily == 2) {
            tail = new Coordinate(tailx - 1, taily + 1);
        } else if (headx - tailx == -1 && heady - taily == -2) {
            tail = new Coordinate(tailx - 1, taily - 1);
        }
    }
    public void moveHeadAndTail(String moveInstruction) {

        String direction = moveInstruction.split(" ")[0];
        int amount = Integer.parseInt(moveInstruction.split(" ")[1]);

        for (int i = 0; i < amount; i++) {
            moveHeadByOne(direction);
            catchUpTail();
            if (tailPath.containsKey(tail)) {
                tailPath.put(tail, tailPath.get(tail) + 1);
            } else{
                tailPath.put(tail, 1);
            }
        }
    }
}