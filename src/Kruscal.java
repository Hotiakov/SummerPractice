import java.util.ArrayList;
import java.util.Stack;

public class Kruscal {
    private ArrayList<Integer> tree_id;
    private ArrayList<Pair<Integer, Integer>> result;
    private EnterData data;
    private long cost;
    private int currentStep;
    private Stack<Pair<Integer, Integer>> saveStack;
    private ArrayList<Boolean> circleFlag;

    public Kruscal(String data_){
        cost = 0;
        currentStep = 0;
        circleFlag = new ArrayList<>();
        data = new EnterData(data_);
        data.dataSort();
        int vertexNum = data.getVertexNum();
        result = new ArrayList<>();
        saveStack = new Stack<>();
        tree_id = new ArrayList<>();
        for (int i = 0; i < vertexNum; i++) {
            tree_id.add(i);
        }
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

    public String nextStep(){
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
                return ("Было добавлено ребро " + start + " - " + finish);
            } else {
                circleFlag.add(true);
                result.add(new Pair<>(start, finish));
                currentStep++;
                return ("Circle was found: start Vertex is " + start + ", final is " + finish);
            }
        }
        else{
            if(circleFlag.get(currentStep++)){
                return ("Circle was found: start Vertex is " + saveStack.peek().first + ", final is " + saveStack.pop().second);
            }
            else{
                return ("Было добавлено ребро " + saveStack.peek().first + " - " + saveStack.pop().second);
            }
        }
    }

    public String prevStep(){
        if(currentStep != 0 && !circleFlag.get(currentStep-1)) {
            currentStep--;
            saveStack.push(result.get(currentStep));
            return ("Ребро " + result.get(currentStep).first + " - " + result.get(currentStep).second + " было удалено из МОД");
        }
        else if(currentStep != 0 && !circleFlag.isEmpty() && circleFlag.get(currentStep-1)){
            currentStep--;
            saveStack.push(result.get(currentStep));
            return ("На предыдущем шаге был выявлен цикл");
        }
        else {
            return ("Возврат на предыдущий шаг невозможен - Вы на первом шаге");
        }
    }

    public String toFinal(){
        ArrayList<Pair<Pair<Integer, Integer>, Long>> listOfEdges = data.getData();
        for(int i = currentStep; i < listOfEdges.size(); i++){
            nextStep();
        }
        return printResult();
    }

    public String toStart(){
        while(currentStep != 0){
            prevStep();
        }
        return "Вы на начале.";
    }


    public String printResult(){
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
}
