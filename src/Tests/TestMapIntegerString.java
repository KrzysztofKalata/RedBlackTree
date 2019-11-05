package Tests;

import classes.Map;

public class TestMapIntegerString {
    public void testMapIntegerString() {
        Map<Integer, String> map = new Map<>();
        String string = new String();
        for (int i = 16; i > 0; i--) {
            string = "";
            string += "Iteracja:" +  i;
            map.setValue(i,string);
        }
        map.write();
    }
}
