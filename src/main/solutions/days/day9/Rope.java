package main.solutions.days.day9;

import main.useful.classes.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;

class Rope {
    ArrayList<Coordinate> rope = new ArrayList<>();
    ArrayList<HashSet<Coordinate>> visited = new ArrayList<>();

    Rope(int ropeLength) {
        for (int i = 0; i < ropeLength; i++) {
            rope.add(new Coordinate(0, 0));
            visited.add(new HashSet<>());
            visited.get(i).add(new Coordinate(0, 0));
        }
    }

    public HashSet<Coordinate> getVisited(int link) {
        return visited.get(link);
    }

    public void moveHeadByOne(String direction) {
        switch (direction) {
            case "R" -> rope.set(0, new Coordinate(rope.get(0).getX() + 1, rope.get(0).getY()));
            case "L" -> rope.set(0, new Coordinate(rope.get(0).getX() - 1, rope.get(0).getY()));
            case "U" -> rope.set(0, new Coordinate(rope.get(0).getX(), rope.get(0).getY() + 1));
            case "D" -> rope.set(0, new Coordinate(rope.get(0).getX(), rope.get(0).getY() - 1));
        }
    }

    public Coordinate followNextItem(Coordinate follower, Coordinate leader) {
        int xgap = leader.getX() - follower.getX();
        int ygap = leader.getY() - follower.getY();
        int xbump = 0;
        int ybump = 0;
        if (Math.abs(xgap) >= 2 && ygap == 0) {
            xbump = xgap > 0 ? 1 : -1;
        } else if (Math.abs(xgap) >= 2) {
            xbump = xgap > 0 ? 1 : -1;
            ybump = ygap > 0 ? 1 : -1;
        } else if (xgap == 0 && Math.abs(ygap) >= 2) {
            ybump = ygap > 0 ? 1 : -1;
        } else if (Math.abs(ygap) >= 2) {
            xbump = xgap > 0 ? 1 : -1;
            ybump = ygap > 0 ? 1 : -1;
        }
        return new Coordinate(follower.getX() + xbump, follower.getY() + ybump);
    }

    public void moveHeadAndTail(String moveInstruction) {

        String direction = moveInstruction.split(" ")[0];
        int amount = Integer.parseInt(moveInstruction.split(" ")[1]);

        for (int i = 0; i < amount; i++) {
            moveHeadByOne(direction);
            visited.get(0).add(rope.get(0));
            for (int j = 1; j < rope.size(); j++) {
                rope.set(j, followNextItem(rope.get(j), rope.get(j - 1)));
                visited.get(j).add(rope.get(j));
            }
        }
    }
}
