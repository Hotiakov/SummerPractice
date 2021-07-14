import com.mxgraph.swing.mxGraphComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AlgorithmWindow extends JFrame{

    public void openWindow(String s){
        Kruscal algorithm = new Kruscal(s);
        if(!algorithm.getReadFlag()) {
            return;
        }

        Graph graph = new Graph(algorithm.getVertexNum(), algorithm.getData());
        mxGraphComponent graphComponent = graph.getGraphComponent();

        JFrame mainFrame = new JFrame("Algorithm Frame");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);

        JFrame logFrame = new JFrame("Logging Frame");
        logFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logFrame.setSize(800, 600);

        JMenu menu = new JMenu("Options");
        JMenuBar menuBar = new JMenuBar();
        JMenuItem menuHelp = new JMenuItem(new AbstractAction("Help!") {
            public void actionPerformed(ActionEvent e) {
                JFrame helpFrame = new JFrame("Help");

                JPanel helpPanel = new JPanel();
                JTextArea textHelp = new JTextArea(10,40);
                JScrollPane paneHelp = new JScrollPane(textHelp);

                textHelp.append("\nНазначения кнопок:\n\n" +
                        "Help! - справочная информация\n" +
                        "⟲ - перейти на начало работы алгоритма\n" +
                        "< - шаг алгоритма назад\n" +
                        "> - шаг алгоритма вперед\n" +
                        ">> - перейти в конец работы алгоритма");

                helpPanel.add(paneHelp);
                textHelp.setEditable(false);

                helpFrame.setSize(500, 200);
                helpFrame.getContentPane().add(helpPanel);
                helpFrame.setLocationRelativeTo(null);
                helpFrame.setVisible(true);
            }
        });
        menu.add(menuHelp);
        menuBar.add(menu);
        mainFrame.setJMenuBar(menuBar);

        JPanel dataPanel = new JPanel();
        JTextArea logArea = new JTextArea(32,70);
        JScrollPane pane = new JScrollPane(logArea);

        JTable table = new JTable(algorithm.getWeights(), algorithm.getColumns()){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };

        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.setPreferredSize(new Dimension(10, 60));
        contents.add(new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);


        JPanel buttonPanel = new JPanel();
        JButton buttonAgain = new JButton("⟲");
        buttonPanel.add(buttonAgain);

        buttonAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logArea.append(algorithm.toStart(graph) + "\n");
            }
        });

        JButton buttonPrev = new JButton("<");
        buttonPanel.add(buttonPrev);
        buttonPrev.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                logArea.append(algorithm.prevStep(graph) + "\n");
            }
        });

        JButton buttonNext = new JButton(">");
        buttonPanel.add(buttonNext);
        buttonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!algorithm.isEnd()) {
                    logArea.append(algorithm.nextStep(graph) + "\n");
                }
                else{
                    if(!algorithm.checkConnected()) {
                        logArea.append(algorithm.printResult(graph) + "\n");
                    }
                    else{
                        logArea.append("Не удалось получить МОД из заданного графа!" + "\n");
                    }
                }
            }
        });

        JButton buttonFinal = new JButton(">>");
        buttonPanel.add(buttonFinal);

        buttonFinal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algorithm.toFinal(graph);
                if(!algorithm.checkConnected()) {
                    logArea.append(algorithm.printResult(graph) + "\n");
                }
                else{
                    logArea.append("Не удалось получить МОД из заданного графа!" + "\n");
                }
            }
        });

        logArea.setEditable(false);
        dataPanel.add(pane);
        mainFrame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(graphComponent));
        mainFrame.getContentPane().add(BorderLayout.NORTH, contents);
        mainFrame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        logFrame.getContentPane().add(dataPanel);
        mainFrame.setLocationRelativeTo(null);
        logFrame.setVisible(true);
        mainFrame.setVisible(true);
    }
}
