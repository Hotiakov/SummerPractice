import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;

public class DataWindow extends JFrame {

    public void openWindow() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Entering Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);

        JMenu menu = new JMenu("Options");
        JMenuBar menuBar = new JMenuBar();
        JMenuItem helpMenu = new JMenuItem(new AbstractAction("Help!") {
            public void actionPerformed(ActionEvent e) {
                JFrame helpFrame = new JFrame("Help");

                JPanel helpPanel = new JPanel();
                JTextArea textHelp = new JTextArea(20,70);
                JScrollPane paneHelp = new JScrollPane(textHelp);
                textHelp.append("Ознакомьтесь с правилами корректного ввода данных:"+
                        "\n\n" +
                        "Первая строка входных данных содержит два числа N и M, разделенные пробелом — количество вершин и " +
                        "\n" +
                        "количество ребер графа. " +
                        "\n" +
                        "Далее следуют M строк, каждая из которых содержит по три целых числа, разделенные пробелами. " +
                        "\n" +
                        "Первые два из них разные, в пределах от 0 до N - 1 каждое, и обозначают концы соответствующего ребра, " +
                        "\n" +
                        "третье — в пределах от 1 до 1000000000 и обозначает длину этого ребра.\n" +
                        "\n" +
                        "Пример:\n" +
                        "3 3\n" +
                        "0 1 1\n" +
                        "0 2 2\n" +
                        "1 2 3" +
                        "\n\nНазначения кнопок:\n\n" +
                        "Import file - считать данные для алгоритма из файла\n" +
                        "Help! - справочная информация\n" +
                        "Start - начало работы алгоритма с введенными данными\n" +
                        "Restart - сброс введенных данных");
                helpPanel.add(paneHelp);
                textHelp.setEditable(false);

                helpFrame.setSize(800, 400);
                helpFrame.getContentPane().add(helpPanel);
                helpFrame.setLocationRelativeTo(null);
                helpFrame.setVisible(true);
            }
        });
        JMenuItem fileMenu = new JMenuItem(new AbstractAction("Import file"){
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileOpen = new JFileChooser();
                int ret = fileOpen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileOpen.getSelectedFile();
                    String data = "";
                    try {
                        data = EnterData.readDataFromFile(file);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    frame.setVisible(false);
                    AlgorithmWindow window = new AlgorithmWindow();
                    window.openWindow(data);
                }
            }
        });

        menu.add(fileMenu);
        menu.add(helpMenu);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        JPanel buttonPanel = new JPanel();
        JPanel textPanel = new JPanel();

        JTextArea textArea = new JTextArea(32,70);
        JScrollPane pane = new JScrollPane(textArea);
        textPanel.add(pane);

        JButton button1 = new JButton("Start");
        buttonPanel.add(button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textArea.getText();
                frame.setVisible(false);
                AlgorithmWindow window = new AlgorithmWindow();
                window.openWindow(s);
            }
        });

        JButton button2 = new JButton("Restart");
        buttonPanel.add(button2);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        frame.getContentPane().add(textPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setVisible(true);
    }
}
