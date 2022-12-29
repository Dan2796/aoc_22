package main.solutions.days.day7;

import java.util.HashSet;

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
        for (Node child : this.children) {
            allChildren.add(child);
            if (child.getType().equals("Dir")) {
                child.getChildrenRecursive(allChildren);
            }
        }
        return allChildren;
    }
}
