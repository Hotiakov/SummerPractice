import org.junit.Test;

import static org.junit.Assert.*;

public class KruscalTest {
    @Test
    public void test1_check_toFinal(){
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
        Graph graph = new Graph(kruscal.getVertexNum(), kruscal.getData());
        kruscal.toFinal(graph);
        assertEquals(kruscal.toFinal(graph), "Суммарный вес МОД: 18; Список ребер, входящих в МОД: 7 - 0; 0 - 1; 0 - 5; 6 - 7; 0 - 2; 1 - 3; 4 - 5; ");
        assertEquals(kruscal.getCost(), 18);
        //assertEquals(kruscal.toFinal(graph), "18");


    }

    @Test
    public void test2_check_toFinal(){
        String data_ =  "3 3\n" +
                "0 1 1\n" +
                "0 2 2\n" +
                "1 2 3";

        Kruscal kruscal = new Kruscal(data_);
        assertEquals(kruscal.getCost(),0);
        Graph graph = new Graph(kruscal.getVertexNum(), kruscal.getData());
        kruscal.toFinal(graph);
        assertEquals(kruscal.toFinal(graph), "Суммарный вес МОД: 3; Список ребер, входящих в МОД: 0 - 1; 0 - 2; ");
        assertEquals(kruscal.getCost(), 3);
    }

    @Test
    public void test1_check_checkConnected(){
        //проверка с графом, у которого можно найти МОД
        String data_ =  "3 3\n" +
                "0 1 1\n" +
                "0 2 2\n" +
                "1 2 3";
        Kruscal kruscal = new Kruscal(data_);
        Graph graph = new Graph(kruscal.getVertexNum(), kruscal.getData());
        kruscal.toFinal(graph);
        assertEquals(kruscal.checkConnected(),false);
    }

    @Test
    public void test2_check_checkConnected(){
        //проверка с графом, у которого нельзя найти МОД
        String data_ =  "3 1\n" +
                "1 2 4";
        Kruscal kruscal = new Kruscal(data_);
        Graph graph = new Graph(kruscal.getVertexNum(), kruscal.getData());
        kruscal.toFinal(graph);
        assertEquals(kruscal.checkConnected(),true);
    }

    @Test
    //одношаговая - "автоматическая" проверка с помощью toFinal()
    public void test1_check_isEnd(){
        String data_ =  "3 3\n" +
                "0 1 1\n" +
                "0 2 2\n" +
                "1 2 3";
        Kruscal kruscal = new Kruscal(data_);
        assertEquals(kruscal.isEnd(), false);  //до прохождения алгоритма - "не конец"
        Graph graph = new Graph(kruscal.getVertexNum(), kruscal.getData());
        kruscal.toFinal(graph);
        assertEquals(kruscal.isEnd(), true);   //после прохождения алгоритма - "конец"
    }

    @Test
    public void test2_check_isEnd(){
        String data_ =   "8 13\n" +
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
        assertEquals(kruscal.isEnd(), false);  //до прохождения алгоритма - "не конец"
        Graph graph = new Graph(kruscal.getVertexNum(), kruscal.getData());
        kruscal.toFinal(graph);
        assertEquals(kruscal.isEnd(), true);   //после прохождения алгоритма - "конец"
    }


    @Test
    //многошаговая проверка с помощью nextStep()
    public void test3_check_isEnd(){
        String data_ =  "3 3\n" +
                "0 1 1\n" +
                "0 2 2\n" +
                "1 2 3";
        Kruscal kruscal = new Kruscal(data_);
        Graph graph = new Graph(kruscal.getVertexNum(), kruscal.getData());
        assertEquals(kruscal.isEnd(), false);  //до прохождения алгоритма - "не конец"
        kruscal.nextStep(graph);
        assertEquals(kruscal.isEnd(), false);
        kruscal.nextStep(graph);
        assertEquals(kruscal.isEnd(), false);
        kruscal.nextStep(graph);
        assertEquals(kruscal.isEnd(), true);   //после прохождения алгоритма - "конец"
    }

    @Test
    public void test1_check_toStart(){
        String data_ =  "3 3\n" +
                "0 1 1\n" +
                "0 2 2\n" +
                "1 2 3";
        Kruscal kruscal = new Kruscal(data_);
        Graph graph = new Graph(kruscal.getVertexNum(), kruscal.getData());
        kruscal.toFinal(graph);
        assertEquals(kruscal.toStart(graph), "Вы на начале.");
    }

    @Test
    public void test2_check_toStart(){
        String data_ =   "8 13\n" +
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
        Graph graph = new Graph(kruscal.getVertexNum(), kruscal.getData());
        kruscal.toFinal(graph);
        assertEquals(kruscal.toStart(graph), "Вы на начале.");
    }

    @Test
    public void test1_check_nextStep(){
        String data_ =  "3 3\n" +
                "0 1 1\n" +
                "0 2 2\n" +
                "1 2 3";
        Kruscal kruscal = new Kruscal(data_);
        Graph graph = new Graph(kruscal.getVertexNum(), kruscal.getData());
        assertEquals(kruscal.nextStep(graph), "Было добавлено ребро 0 - 1");
        assertEquals(kruscal.nextStep(graph), "Было добавлено ребро 0 - 2");
        assertEquals(kruscal.nextStep(graph), "Circle was found: start Vertex is 1, final is 2");
    }

    @Test
    public void test2_check_nextStep(){
        String data_ =   "8 13\n" +
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
        Graph graph = new Graph(kruscal.getVertexNum(), kruscal.getData());
        assertEquals(kruscal.nextStep(graph), "Было добавлено ребро 7 - 0");
        assertEquals(kruscal.nextStep(graph), "Было добавлено ребро 0 - 1");
        assertEquals(kruscal.nextStep(graph), "Было добавлено ребро 0 - 5");
        assertEquals(kruscal.nextStep(graph), "Было добавлено ребро 6 - 7");
        assertEquals(kruscal.nextStep(graph), "Было добавлено ребро 0 - 2");
        assertEquals(kruscal.nextStep(graph), "Было добавлено ребро 1 - 3");
        assertEquals(kruscal.nextStep(graph), "Circle was found: start Vertex is 5, final is 6");
        assertEquals(kruscal.nextStep(graph), "Circle was found: start Vertex is 1, final is 2");
        assertEquals(kruscal.nextStep(graph), "Circle was found: start Vertex is 6, final is 3");
        assertEquals(kruscal.nextStep(graph), "Было добавлено ребро 4 - 5");
        assertEquals(kruscal.nextStep(graph), "Circle was found: start Vertex is 3, final is 2");
        assertEquals(kruscal.nextStep(graph), "Circle was found: start Vertex is 2, final is 5");
        assertEquals(kruscal.nextStep(graph), "Circle was found: start Vertex is 7, final is 1");
    }

    @Test
    public void test1_check_prevStep(){
        String data_ =  "3 3\n" +
                "0 1 1\n" +
                "0 2 2\n" +
                "1 2 3";
        Kruscal kruscal = new Kruscal(data_);
        Graph graph = new Graph(kruscal.getVertexNum(), kruscal.getData());
        assertEquals(kruscal.prevStep(graph), "Возврат на предыдущий шаг невозможен - Вы на первом шаге");
        //делаем шаг вперед
        kruscal.nextStep(graph);
        assertEquals(kruscal.prevStep(graph), "Ребро 0 - 1 было удалено из МОД");
        //проходимся до конца алгоритма
        kruscal.toFinal(graph);
        assertEquals(kruscal.prevStep(graph), "На предыдущем шаге был выявлен цикл");
    }

    @Test
    public void test2_check_prevStep(){
        String data_ =   "8 13\n" +
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
        Graph graph = new Graph(kruscal.getVertexNum(), kruscal.getData());
        assertEquals(kruscal.prevStep(graph), "Возврат на предыдущий шаг невозможен - Вы на первом шаге");
        //делаем шаг вперед
        kruscal.nextStep(graph);
        assertEquals(kruscal.prevStep(graph), "Ребро 7 - 0 было удалено из МОД");
        //проходимся до конца алгоритма
        kruscal.toFinal(graph);
        assertEquals(kruscal.prevStep(graph), "На предыдущем шаге был выявлен цикл");
    }
}