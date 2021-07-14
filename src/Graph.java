import javax.swing.*;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.util.ArrayList;

public class Graph extends JFrame {

    private mxGraph graph;
    private mxGraphComponent graphComponent;
    private ArrayList<Object> vertexArray;
    public Graph(Integer vertexNum, ArrayList<Pair<Pair<Integer, Integer>, Long>> data)
    {
        graph = new mxGraph();
        vertexArray = new ArrayList<>();
        Object parent = graph.getDefaultParent();
        String style = "rounded;strokeColor=black;fillColor=white";
        graph.getModel().beginUpdate();
        double angle = 2 * Math.PI / vertexNum;
        double alpha;
        try
        {
            for (int i = 0; i < vertexNum; i++){
                int R = 210;
                double r = Math.sqrt(R);
                alpha = i * angle;
                double x = 400 + R * Math.cos(alpha);
                double y = 215 + R * Math.sin(alpha);
                String value = Integer.toString(i);
                Object vertex = graph.insertVertex(parent, null, value, x, y, r, r, style);
                vertexArray.add(vertex);
            }
            for (Pair<Pair<Integer, Integer>, Long> edge: data) {
                if (data.size() <= 40){
                    String value = Long.toString(edge.second);
                    graph.insertEdge(parent, null, value, vertexArray.get(edge.first.first), vertexArray.get(edge.first.second), "strokeColor=#D1D1D1;endArrow=none");
                }
                else{
                    graph.insertEdge(parent, null, "", vertexArray.get(edge.first.first), vertexArray.get(edge.first.second), "strokeColor=#D1D1D1;endArrow=none");
                }
            }
        }
        finally
        {
            graph.getModel().endUpdate();
        }
        graphComponent = new mxGraphComponent(graph);
        graphComponent.setEnabled(false);
        graphComponent.setAutoScroll(true);
    }

    public void changeColor (String color, Integer start, Integer finish){
        String style = "strokeColor=" + color + ";endArrow=none";
        for(Object edge: graph.getEdgesBetween(vertexArray.get(start), vertexArray.get(finish)))
            graph.getModel().setStyle(edge, style);
    }

    public mxGraphComponent getGraphComponent() {
        return graphComponent;
    }
}
