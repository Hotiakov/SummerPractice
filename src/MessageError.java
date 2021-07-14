import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MessageError extends JFrame{
    public static void throwMessageError(String errorName) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame message = new JFrame("Error");
        message.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        message.setSize(800, 100);
        message.setLocationRelativeTo(null);

        JPanel buttonPanel = new JPanel();
        JButton buttonOK = new JButton("Ok");
        buttonPanel.add(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message.setVisible(false);
                System.exit(1);
            }
        });

        JPanel labelPanel = new JPanel();
        JLabel errorLabel = new JLabel(errorName);
        labelPanel.add(errorLabel);

        message.getContentPane().add(BorderLayout.CENTER, labelPanel);
        message.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        message.setVisible(true);
    }
}
