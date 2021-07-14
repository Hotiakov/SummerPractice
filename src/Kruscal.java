import java.util.ArrayList;
import java.util.Stack;

public class Kruscal {
    private ArrayList<Integer> tree_id;
    private ArrayList<Pair<Integer, Integer>> result;
    private EnterData data;
    private long cost;
    private int currentStep;
    private boolean readFlag;
    private Stack<Pair<Integer, Integer>> saveStack;
    private ArrayList<Boolean> circleFlag;

    //table
    private Object[] columnsHeaderEdges;
    private Object[][] arrayOfWeight;

    public Kruscal(String data_){
        cost = 0;
        currentStep = 0;
        circleFlag = new ArrayList<>();
        result = new ArrayList<>();
        saveStack = new Stack<>();
        tree_id = new ArrayList<>();

        data = new EnterData();
        readFlag = data.readDataFromText(data_);
        data.dataSort();

        //table
        TableConversion tableData = new TableConversion();
        tableData.convert(data.getData());
        columnsHeaderEdges = tableData.getColumns();
        arrayOfWeight = tableData.getWeights();

        int vertexNum = data.getVertexNum();
        for (int i = 0; i < vertexNum; i++) {
            tree_id.add(i);
        }
    }

    public final boolean getReadFlag(){
        return readFlag;
    }

    private void union(int start, int finish){
        int old_id = tree_id.get(finish), new_id = tree_id.get(start);
        for (int i = 0; i < data.getVertexNum(); ++i)
            if (tree_id.get(i) == old_id)
                tree_id.set(i, new_id);
    }


    public boolean checkConnected(){
        int setIndex = tree_id.get(0);
        for (int i = 1; i < data.getVertexNum(); i++)
            if (tree_id.get(i) != setIndex) {
                return true;
            }
        return false;
    }

    public String nextStep(Graph graph){
        if(saveStack.isEmpty()) {
            int start, finish;
            long weight;
            Pair<Pair<Integer, Integer>, Long> edge = data.getData().get(currentStep);
            start = edge.first.first;
            finish = edge.first.second;
            weight = edge.second;
            if (tree_id.get(start) != tree_id.get(finish)) {
                cost += weight;
                result.add(new Pair<>(start, finish));
                union(start, finish);
                currentStep++;
                circleFlag.add(false);
                graph.changeColor("green", start, finish);
                return ("Было добавлено ребро " + start + " - " + finish);
            } else {
                circleFlag.add(true);
                result.add(new Pair<>(start, finish));
                currentStep++;
                graph.changeColor("#fcb6b7", start, finish);
                return ("Circle was found: start Vertex is " + start + ", final is " + finish);
            }
        }
        else{
            if(circleFlag.get(currentStep++)){
                graph.changeColor("#fcb6b7", saveStack.peek().first, saveStack.peek().second);
                return ("Circle was found: start Vertex is " + saveStack.peek().first + ", final is " + saveStack.pop().second);
            }
            else{
                graph.changeColor("green", saveStack.peek().first, saveStack.peek().second);
                return ("Было добавлено ребро " + saveStack.peek().first + " - " + saveStack.pop().second);
            }
        }
    }

    public String prevStep(Graph graph){
        if(currentStep != 0 && !circleFlag.get(currentStep-1)) {
            currentStep--;
            saveStack.push(result.get(currentStep));
            graph.changeColor("#d1d1d1", result.get(currentStep).first, result.get(currentStep).second);
            return ("Ребро " + result.get(currentStep).first + " - " + result.get(currentStep).second + " было удалено из МОД");
        }
        else if(currentStep != 0 && !circleFlag.isEmpty() && circleFlag.get(currentStep-1)){
            currentStep--;
            saveStack.push(result.get(currentStep));
            graph.changeColor("#d1d1d1", result.get(currentStep).first, result.get(currentStep).second);
            return ("На предыдущем шаге был выявлен цикл");
        }
        else {
            return ("Возврат на предыдущий шаг невозможен - Вы на первом шаге");
        }
    }

    public String toFinal(Graph graph){
        ArrayList<Pair<Pair<Integer, Integer>, Long>> listOfEdges = data.getData();
        for(int i = currentStep; i < listOfEdges.size(); i++){
            nextStep(graph);
        }
        return printResult(graph);
    }

    public String toStart(Graph graph){
        while(currentStep != 0){
            prevStep(graph);
        }
        return "Вы на начале.";
    }


    public String printResult(Graph graph){
        String result_string = new String();
        if(!checkConnected()) {
            result_string += "Суммарный вес МОД: " + cost + "; ";
            result_string += "Список ребер, входящих в МОД: ";
            for(int i = 0; i < result.size(); i++){
                if(!circleFlag.get(i))
                    result_string += result.get(i).first + " - " + result.get(i).second + "; ";
            }
        }
        return result_string;
    }

    public boolean isEnd(){
        if(currentStep == data.getData().size())
            return true;
        return false;
    }

    public long getCost(){return cost;}
    public final Object[] getColumns(){
        return columnsHeaderEdges;
    }
    public Object[][] getWeights(){
        return arrayOfWeight;
    }

    public Integer getVertexNum(){ return data.getVertexNum(); }
    public final ArrayList<Pair<Pair<Integer, Integer>, Long>> getData(){ return data.getData(); }
}
