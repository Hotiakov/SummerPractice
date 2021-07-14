import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class EnterData {
    private int vertexNum, edgesNum;
    private ArrayList<Pair<Pair<Integer, Integer>, Long>>  listOfEdges;

    public EnterData(){
        listOfEdges = new ArrayList<>();
    }

    public static String readDataFromFile(File file) throws FileNotFoundException {
        String s = "";
        try {
            Scanner in = new Scanner(file);
            while (in.hasNext())
                s += in.nextLine() + "\r\n";
            in.close();
        }
        catch (FileNotFoundException e){
            MessageError.throwMessageError("Файл не смог открыться.");
            return null;
        }
        return s;
    }

    public boolean readDataFromText(String data_){
        if(data_ == null)
            return false;

        Scanner scanner = new Scanner(data_);

        try{
            vertexNum = scanner.nextInt();
            edgesNum = scanner.nextInt();
            if(vertexNum < 1 || edgesNum < 1){
                throw new IllegalArgumentException("Некорректно введенные данные! Количество ребер и вершин должены быть натуральными числами");
            }

        }
        catch (java.util.InputMismatchException e){
            MessageError.throwMessageError("Некорректно введенные данные! Количество ребер и вершин должны быть числами.");
            return false;
        }
        catch (IllegalArgumentException e){
            MessageError.throwMessageError(e.getMessage());
            return false;
        }

        long tmpInt = 0;
        int tmpFirst = 0;
        int tmpSecond = 0;
        for (int i = 0; i < edgesNum; i++){
            if(!scanner.hasNextLine()){
                MessageError.throwMessageError("Некорректно введенные данные! Введено недостаточно ребер.");
                return false;
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
                MessageError.throwMessageError("Некорректно введенные данные! Требуется вводить числа.");
                return false;

            }
            catch (IllegalArgumentException e){
                MessageError.throwMessageError(e.getMessage());
                return false;
            }
            listOfEdges.add(new Pair<>(new Pair<>(tmpFirst, tmpSecond), tmpInt));
        }
        scanner.close();
        return  true;
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
