import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TestFrame extends JFrame {

    public static void main(String[] args) {
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
                menu.add(a1);
                m1.add(menu);
                mainFrame.setJMenuBar(m1);

                JPanel mainPanel = new JPanel();
                JButton buttonAgain = new JButton("⟲");
                mainPanel.add(buttonAgain);

                JButton buttonPrev = new JButton("<");
                mainPanel.add(buttonPrev);

                JButton buttonNext = new JButton(">");
                mainPanel.add(buttonNext);

                JButton buttonFinal = new JButton(">>");
                mainPanel.add(buttonFinal);

                JLabel stepNumber;
                stepNumber = new JLabel("Information");
                mainFrame.add(BorderLayout.NORTH, stepNumber);

                mainFrame.getContentPane().add(BorderLayout.SOUTH, mainPanel);
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