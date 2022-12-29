package main.solutions.days.day08;

import main.useful.classes.Coordinate;

import java.util.HashMap;

class Forest {
    HashMap<Coordinate, Integer> forestMap;
    int maxX, maxY;

    Forest() {
        forestMap = new HashMap<>();
    }

    public void addTree(Coordinate location, Integer height) {
        forestMap.put(location, height);
        this.maxX = Math.max(this.maxX, location.getX());
        this.maxY = Math.max(this.maxY, location.getY());
    }

    public boolean isVisible(Coordinate tree) {
        int treeHeight = this.forestMap.get(tree);
        boolean visibleLeft = true, visibleRight = true, visibleTop = true, visibleBottom = true;
        for (int x = 0; x < tree.getX(); x++) {
            Coordinate toCompare = new Coordinate(x, tree.getY());
            if (this.forestMap.get(toCompare) >= treeHeight) {
                visibleLeft = false;
            }
        }
        for (int x = tree.getX() + 1; x <= maxX; x++) {
            Coordinate toCompare = new Coordinate(x, tree.getY());
            if (this.forestMap.get(toCompare) >= treeHeight) {
                visibleRight = false;
            }
        }
        for (int y = 0; y < tree.getY(); y++) {
            Coordinate toCompare = new Coordinate(tree.getX(), y);
            if (this.forestMap.get(toCompare) >= treeHeight) {
                visibleTop = false;
            }
        }
        for (int y = tree.getY() + 1; y <= maxY; y++) {
            Coordinate toCompare = new Coordinate(tree.getX(), y);
            if (this.forestMap.get(toCompare) >= treeHeight) {
                visibleBottom = false;
            }
        }
        return visibleLeft | visibleRight | visibleTop | visibleBottom;
    }

    public Integer scenicScore(Coordinate tree) {
        int treeHeight = this.forestMap.get(tree);
        int visibleLeft = 0, visibleRight = 0, visibleTop = 0, visibleBottom = 0;
        for (int x = tree.getX() - 1; x >= 0; x--) {
            Coordinate toCompare = new Coordinate(x, tree.getY());
            visibleLeft++;
            if (this.forestMap.get(toCompare) >= treeHeight) {
                break;
            }
        }
        for (int x = tree.getX() + 1; x <= maxX; x++) {
            Coordinate toCompare = new Coordinate(x, tree.getY());
            visibleRight++;
            if (this.forestMap.get(toCompare) >= treeHeight) {
                break;
            }
        }
        for (int y = tree.getY() - 1; y >= 0; y--) {
            Coordinate toCompare = new Coordinate(tree.getX(), y);
            visibleTop++;
            if (this.forestMap.get(toCompare) >= treeHeight) {
                break;
            }
        }
        for (int y = tree.getY() + 1; y <= maxY; y++) {
            Coordinate toCompare = new Coordinate(tree.getX(), y);
            visibleBottom++;
            if (this.forestMap.get(toCompare) >= treeHeight) {
                break;
            }
        }
        return visibleLeft * visibleRight * visibleTop * visibleBottom;
    }
}
