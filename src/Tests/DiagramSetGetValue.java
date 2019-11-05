package Tests;

import classes.Map;

import java.util.Random;

public class DiagramSetGetValue {

    private Random randomGenerator = new Random();

    public void diagramSetGetValue(int amount){

        Map<Integer,Integer> map = new Map<>();

        long now  = System.currentTimeMillis();

        for (int i = 1; i <=amount; i++){
            map.setValue(i, i);
        }

        long after = System.currentTimeMillis();

        long newNow = System.nanoTime();
        map.getValue(randomGenerator.nextInt(amount));
        long newAfter = System.nanoTime();

        System.out.println(amount + ";" + (after-now) + ";" + (newAfter-newNow));
    }
}
