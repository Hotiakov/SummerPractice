import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TestFrame extends JFrame {
    private int y;
    public TestFrame(){
        y = 5;
    }
    public void openInterface() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Entering Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);

        JMenu menu = new JMenu("Options");
        JMenuBar m1 = new JMenuBar();
        JMenuItem a1 = new JMenuItem(new AbstractAction("Help!") {
            public void actionPerformed(ActionEvent e) {
                JFrame helpFrame = new JFrame("Help");
                JLabel label;
                label = new JLabel("Help info will be there. Just wait...");
                label.setBounds(40, 40, 10, 20);
                helpFrame.add(BorderLayout.NORTH, label);
                helpFrame.setSize(400, 400);
                helpFrame.setLocationRelativeTo(null);
                helpFrame.setVisible(true);
            }
        });
        JMenuItem a2 = new JMenuItem("Import file");
        menu.add(a2);
        menu.add(a1);
        m1.add(menu);
        frame.setJMenuBar(m1);

        JPanel panel = new JPanel();

        JTextArea textArea = new JTextArea();
        textArea.setColumns(20);
        panel.add(textArea);

        JButton button1 = new JButton("Start");
        panel.add(button1);

        JButton button2 = new JButton("Restart");
        panel.add(button2);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textArea.getText();
                Kruscal algorithm = new Kruscal(s);
                frame.setVisible(false);
                JFrame mainFrame = new JFrame("Algorithm Frame");
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setSize(800, 600);

                JMenu menu = new JMenu("Options");
                JMenuBar m1 = new JMenuBar();
                JMenuItem a1 = new JMenuItem(new AbstractAction("Help!") {
                    public void actionPerformed(ActionEvent e) {
                        JFrame helpFrame = new JFrame("Help");
                        JLabel label;
                        label = new JLabel("Help info will be there. Just wait...");
                        label.setBounds(40, 40, 10, 20);
                        helpFrame.add(BorderLayout.NORTH, label);
                        helpFrame.setSize(400, 400);
                        helpFrame.setLocationRelativeTo(null);
                        helpFrame.setVisible(true);
                    }
                });
                JMenuItem a2 = new JMenuItem("Save to file");
                menu.add(a2);
                menu.add(a1);
                m1.add(menu);
                mainFrame.setJMenuBar(m1);

                JPanel dataPanel = new JPanel();
                dataPanel.setLayout(null);

                JPanel buttonPanel = new JPanel();
                JButton buttonAgain = new JButton("⟲");
                buttonPanel.add(buttonAgain);

                buttonAgain.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JLabel l1 = new JLabel(algorithm.toStart());
                        dataPanel.add(l1);
                        l1.setBounds(5, y, 1000, 15);
                        y+=20;
                    }
                });

                JButton buttonPrev = new JButton("<");
                buttonPanel.add(buttonPrev);
                buttonPrev.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JLabel l1 = new JLabel(algorithm.prevStep());
                        dataPanel.add(l1);
                        l1.setBounds(5, y, 1000, 15);
                        y+=20;
                    }
                });

                JButton buttonNext = new JButton(">");
                buttonPanel.add(buttonNext);
                buttonNext.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!algorithm.isEnd()) {
                            JLabel l1 = new JLabel(algorithm.nextStep());
                            dataPanel.add(l1);
                            l1.setBounds(5, y, 1000, 15);
                            y+=20;
                        }
                        else{
                            if(!algorithm.checkConnected()) {
                                JLabel l1 = new JLabel(algorithm.printResult());
                                dataPanel.add(l1);
                                l1.setBounds(5, y, 1000, 15);
                                y += 20;
                            }
                            else{
                                JLabel l1 = new JLabel("Не удалось получить МОД из заданного графа!");
                                dataPanel.add(l1);
                                l1.setBounds(5, y, 1000, 15);
                                y += 20;
                            }
                        }
                    }
                });

                JButton buttonFinal = new JButton(">>");
                buttonPanel.add(buttonFinal);

                buttonFinal.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        algorithm.toFinal();
                        if(!algorithm.checkConnected()) {
                            JLabel l1 = new JLabel(algorithm.printResult());
                            dataPanel.add(l1);
                            l1.setBounds(5, y, 1000, 15);
                            y += 20;
                        }
                        else{
                            JLabel l1 = new JLabel("Не удалось получить МОД из заданного графа!");
                            dataPanel.add(l1);
                            l1.setBounds(5, y, 1000, 15);
                            y += 20;
                        }
                    }
                });

                mainFrame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
                mainFrame.getContentPane().add(dataPanel);
                mainFrame.setLocationRelativeTo(null);
                mainFrame.setVisible(true);
            }
        });


        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("Enter data again!");
            }
        });

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(textArea);
        frame.setPreferredSize(new Dimension(800, 600));

        JScrollBar b = new JScrollBar();
        b.setBounds(50, 10, 0, 10);
        frame.getContentPane().add(BorderLayout.EAST, b);
        frame.setVisible(true);
    }
}