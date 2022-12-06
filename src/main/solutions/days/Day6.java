package main.solutions.days;
import java.util.*;

class Day6 extends Day{
    Day6(boolean actual) {
        super(actual);
    }

    @Override
    int getDay() {
        return 6;
    }

    char[] datastream;
    @Override
    void parseInput() {
        datastream = input.nextLine().toCharArray();
    }

    public static int findFirstMaker(char[] datastream, int numberDistinct) throws NoMarkerFound {
        int marker = 0;
        for (int i = numberDistinct - 1; i < datastream.length; i++) {
            HashSet<Character> currentGroup = new HashSet<>();
            for (int j = 0; j < numberDistinct; j++) {
                currentGroup.add(datastream[i-j]);
            }
            if (currentGroup.size() == numberDistinct) {
                marker = i;
                break;
            }
        }
        if (marker == 0) {
            throw new NoMarkerFound();
        }
        // plus 1 when returning because need char number not index
        return marker + 1;
    }
    @Override
    Integer getSolutionPart1() throws NoSolutionFoundException  {
        try {
            return findFirstMaker(datastream, 4);
        } catch (Exception NoMarkerFound ){
            throw new NoSolutionFoundException(1);
        }
    }

    @Override
    Integer getSolutionPart2() throws NoSolutionFoundException {
        try {
            return findFirstMaker(datastream, 14);
        } catch (Exception NoMarkerFound ){
            throw new NoSolutionFoundException(2);
        }
    }
}

class NoMarkerFound extends Exception {
    public NoMarkerFound() {
        super("No marker found in datastream.");
    }
}

