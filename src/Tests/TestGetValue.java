package Tests;

import classes.Map;

import java.util.Random;

public class TestGetValue {

    public boolean testGetValue() {

        Random randomGenerator = new Random();
        Map<Integer, Integer> map = new Map<>();
        int bound = 16;

        for (int i = bound; i > 0; i--) {
            map.setValue(i, i);
        }

        if (map.getValue(randomGenerator.nextInt(bound)) != null && map.getValue(randomGenerator.nextInt(bound) + bound) == null)
            return true;
        else return false;

    }
}
