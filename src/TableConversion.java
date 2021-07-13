import java.util.ArrayList;
import java.util.Arrays;

public class TableConversion {
    private Object[] columnsHeaderEdges;
    private Object[][] arrayOfWeight;

    public TableConversion() {
        arrayOfWeight = new Object[1][];
    }

    public void convert(ArrayList<Pair<Pair<Integer, Integer>, Long>> listOfEdges){
        ArrayList<String> edgesNames = new ArrayList<>();
        ArrayList<Long> weight = new ArrayList<>();
        for(Pair<Pair<Integer, Integer>, Long> edge: listOfEdges){
            edgesNames.add(edge.first.first + " - " + edge.first.second);
            weight.add(edge.second);
        }
        columnsHeaderEdges = edgesNames.toArray();
        arrayOfWeight[0] = weight.toArray();
    }

    public final Object[] getColumns(){
        return columnsHeaderEdges;
    }

    public Object[][] getWeights(){
        return arrayOfWeight;
    }
}
