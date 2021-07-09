import org.junit.Test;

import static org.junit.Assert.*;

public class KruscalTest {
    @Test
    public void test1(){
        String data_ =  "8 13\n" +
                "0 1 1\n" +
                "1 3 3\n" +
                "3 2 7\n" +
                "2 5 9\n" +
                "4 5 6\n" +
                "5 6 4\n" +
                "6 7 2\n" +
                "7 0 1\n" +
                "7 1 13\n" +
                "0 5 2\n" +
                "6 3 5\n" +
                "0 2 3\n" +
                "1 2 5";

        Kruscal kruscal = new Kruscal(data_);
        assertEquals(kruscal.getCost(),0);
        kruscal.toFinal();
        assertEquals(kruscal.getCost(), 18);
    }

    @Test
    public void test2(){
        String data_ =  "3 3\n" +
                "0 1 1\n" +
                "0 2 2\n" +
                "1 2 3";

        Kruscal kruscal = new Kruscal(data_);
        assertEquals(kruscal.getCost(),0);
        kruscal.toFinal();
        assertEquals(kruscal.getCost(), 3);
    }

}