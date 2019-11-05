package Tests;

import classes.Map;

public class TestMapInteger {

    public void testMapInteger() {
        Map<Integer, Integer> map = new Map<>();

        for (int i = 16; i > 0; i--) {
            map.setValue(i, i);
        }
        map.write();
    }

}
