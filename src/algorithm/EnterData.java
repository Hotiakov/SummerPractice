import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class EnterData {
    private Scanner scanner;
    private int vertexNum, edgesNum;
    private ArrayList<Pair<Pair<Integer, Integer>, Integer>>  listOfEdges;

    public EnterData(){
        scanner = new Scanner(System.in);
        if(scanner.hasNextInt()){
            vertexNum = scanner.nextInt();
        }
        else{
            System.out.println("Извините, но данные введены некорректно:(");
            scanner.close();
            System.exit(1);
        }
        if(scanner.hasNextInt()){
            edgesNum = scanner.nextInt();
        }
        else{
            System.out.println("Извините, но данные введены некорректно:(");
            scanner.close();
            System.exit(1);
        }

        listOfEdges = new ArrayList<>(edgesNum);

        int tmpInt = 0;
        int tmpCharFirst = 0;
        int tmpCharSecond = 0;
        for (int i = 0; i < edgesNum; i++){
            if(scanner.hasNextInt()){
                tmpCharFirst = scanner.nextInt() - 1;
            }
            else{
                System.out.println("Извините, но данные введены некорректно:(");
                scanner.close();
                System.exit(1);
            }
            if(scanner.hasNextInt()){
                tmpCharSecond = scanner.nextInt() - 1;
            }
            else{
                System.out.println("Извините, но данные введены некорректно:(");
                scanner.close();
                System.exit(1);
            }
            if(scanner.hasNextInt()){
                tmpInt = scanner.nextInt();
            }
            else {
                System.out.println("Извините, но данные введены некорректно:(");
                scanner.close();
                System.exit(1);
            }
            listOfEdges.add(new Pair<>(new Pair<>(tmpCharFirst, tmpCharSecond), tmpInt));
        }
    }

    public void dataSort(){
        listOfEdges.sort(new Comparator<Pair<Pair<Integer, Integer>, Integer>>() {
            @Override
            public int compare(Pair<Pair<Integer, Integer>, Integer> o1, Pair<Pair<Integer, Integer>, Integer> o2) {
                return o1.second > o2.second ? 1 : -1;
            }
        });
    }

    public final ArrayList<Pair<Pair<Integer, Integer>, Integer>> getData(){
        return listOfEdges;
    }

    public Integer getVertexNum(){
        return vertexNum;
    }
    public Integer getEdgesNum(){
        return edgesNum;
    }
}
