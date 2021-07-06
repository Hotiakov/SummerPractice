import java.util.ArrayList;

public class Kruscal {
    private ArrayList<Integer> tree_id;
    private ArrayList<Pair<Integer, Integer>> result;
    private EnterData data;
    private int cost;
    public Kruscal(){
        cost = 0;
        data = new EnterData();
        int vertexNum = data.getVertexNum();
        result = new ArrayList<>();
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

    private void checkConnected(){
        int setIndex = tree_id.get(0);
        for (int i = 1; i < data.getVertexNum(); i++)
            if (tree_id.get(i) != setIndex) {
                System.out.println("NON-CONNECTED");
                break;
            }
    }

    public void startAlg(){
        data.dataSort();
        ArrayList<Pair<Pair<Integer, Integer>, Integer>>  listOfEdges = data.getData();
        int start, finish, weight;
        for (Pair<Pair<Integer, Integer>, Integer> edge: listOfEdges) {
            start = edge.first.first;
            finish = edge.first.second;
            weight = edge.second;
            if(tree_id.get(start) != tree_id.get(finish)){
                cost += weight;
                result.add(new Pair<>(start, finish));
                union(start, finish);
            }
            else{
                System.out.println("Circle was found: start Vertex is " + start + ", final is " + finish);
            }
        }
        checkConnected();
        System.out.println(cost);
    }
}
