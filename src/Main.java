import Tests.DiagramSetGetValue;
import Tests.TestGetValue;
import Tests.TestMapInteger;
import Tests.TestMapIntegerString;

public class Main {
    public static void main(String[] args) {
/*

        // Tworzenie danych do diagramów
        DiagramSetGetValue diagramSetGetValue = new DiagramSetGetValue();
        TestGetValue testGetValue = new TestGetValue();

        for (int i = 200000; i <=5000000; i+=200000){
            diagramSetGetValue.diagramSetGetValue(i);
        }
        //Test funkcji getValue()
        System.out.println("Wynik testu funkcji getValue:" + testGetValue.testGetValue());
*/

        //Test działania mapy
        System.out.println("Wynik testu działania mapy Integer Integer:");
        TestMapInteger testMap = new TestMapInteger();
        testMap.testMapInteger();

        System.out.println("Wynik testu działania mapy Integer String:");
        TestMapIntegerString testMapIntegerString = new TestMapIntegerString();
        testMapIntegerString.testMapIntegerString();

    }
}
