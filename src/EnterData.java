import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class EnterData {
    private Scanner scanner;
    private int vertexNum, edgesNum;
    private ArrayList<Pair<Pair<Integer, Integer>, Long>>  listOfEdges;

    public EnterData(String data_){
        scanner = new Scanner(data_);
        try{
            vertexNum = scanner.nextInt();
            edgesNum = scanner.nextInt();
            if(vertexNum < 1 || edgesNum < 1)
                throw new IllegalArgumentException("Некорректно введенные данные! Количество ребер и вершин должены быть натуральными числами");
        }
        catch (java.util.InputMismatchException e){
            System.out.println("Некорректно введенные данные! Количество ребер и вершин должны быть числами.");
            scanner.close();
            System.exit(1);
        }

        listOfEdges = new ArrayList<>();

        long tmpInt = 0;
        int tmpFirst = 0;
        int tmpSecond = 0;
        for (int i = 0; i < edgesNum; i++){
            if(!scanner.hasNextLine()){
                System.out.println("Некорректно введенные данные! Введено недостаточно ребер.");
                scanner.close();
                System.exit(1);
            }

            try{
                tmpFirst = scanner.nextInt();
                tmpSecond = scanner.nextInt();
                tmpInt = scanner.nextLong();
                if(tmpFirst >= vertexNum || tmpSecond >= vertexNum || tmpFirst < 0 || tmpSecond < 0)
                    throw new IllegalArgumentException("Некорректно введенные данные! Номер введенного ребра не удовлетроряет условию 0 <= x <= N-1");
                if(tmpInt < 1)
                    throw new IllegalArgumentException("Некорректно введенные данные! Вес ребра должен быть положительным");
            }
            catch (java.util.InputMismatchException e){
                System.out.println("Некорректно введенные данные! Требуется вводить числа.");
                scanner.close();
                System.exit(1);
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                scanner.close();
                System.exit(1);
            }
            listOfEdges.add(new Pair<>(new Pair<>(tmpFirst, tmpSecond), tmpInt));
        }
    }

    public void dataSort(){
        listOfEdges.sort(new Comparator<Pair<Pair<Integer, Integer>, Long>>() {
            @Override
            public int compare(Pair<Pair<Integer, Integer>, Long> o1, Pair<Pair<Integer, Integer>, Long> o2) {
                return o1.second > o2.second ? 1 : -1;
            }
        });
    }

    public final ArrayList<Pair<Pair<Integer, Integer>, Long>> getData(){
        return listOfEdges;
    }

    public Integer getVertexNum(){
        return vertexNum;
    }
    public Integer getEdgesNum(){
        return edgesNum;
    }
}
