package view;

import javax.swing.*;
import java.awt.*;

public class MiddlePanel {

    JTextPane textPane;

    public MiddlePanel () {

    }

    public JPanel buildMiddle () {
        JPanel middle = new JPanel(new BorderLayout());
        middle.setBorder(BorderFactory.createTitledBorder("Test results"));
        textPane = new JTextPane();
        textPane.setText("When 'Run tests' is clicked this should display the test results from the class that was tested by appending to textPane.setText()");
        textPane.setEditable(false);
        JScrollPane pane = new JScrollPane(textPane);
        middle.add(pane, BorderLayout.CENTER);
        // Align panel content in a row.

        return middle;
    }

    public void setTextPane(String text) {
        textPane.setText(text);
    }
}
