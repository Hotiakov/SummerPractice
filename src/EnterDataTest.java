import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class EnterDataTest{
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
        EnterData data = new EnterData(data_);
        Scanner scanner = new Scanner(data_);
        Integer VertexNum = new Integer(scanner.nextInt());//new Integer(8);
        Integer EdgesNum = new Integer(scanner.nextInt());
        assertEquals(data.getVertexNum(), VertexNum);
        assertEquals(data.getEdgesNum(), EdgesNum);
    }

    @Test
    public void test2(){
        String data_ =  "4 4\n" +
                "3 3 1\n" +
                "1 1 2\n" +
                "2 1 3\n" +
                "3 1 4";
        EnterData data = new EnterData(data_);
        Scanner scanner = new Scanner(data_);
        Integer VertexNum = new Integer(scanner.nextInt());//new Integer(8);
        Integer EdgesNum = new Integer(scanner.nextInt());
        assertEquals(data.getVertexNum(), VertexNum);
        assertEquals(data.getEdgesNum(), EdgesNum);
        //assertEquals(1, 1);
    }
}