package view;

import listeners.ClearButtonListener;
import listeners.RunButtonListener;

import javax.swing.*;
import java.awt.*;

public class ApplicationWindow {

    // Application event listeners.
    public RunButtonListener runButtonListener;
    public ClearButtonListener clearButtonListener;

    // Upper panel components.
    public JTextField textField;
    public JButton runButton;

    // Middle panel components.
    public JTextArea textArea;

    // Lower panel components.
    public JButton clearButton;

    public ApplicationWindow(String title) {
        // Application frame.
        JFrame frame = new JFrame(title);
        // Set frame default close operation to EXIT_ON_CLOSE to stop process from running.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the frame height and width.
        frame.setPreferredSize(new Dimension(640, 480));

        // Init all panels.
        JPanel upper = upperPanel();
        JPanel middle = middlePanel();
        JPanel lower = lowerPanel();

        // Add all panels to frame.
        frame.add(upper, BorderLayout.NORTH);
        frame.add(middle, BorderLayout.CENTER);
        frame.add(lower, BorderLayout.SOUTH);

        // Add listeners to buttons.
        runButtonListener = new RunButtonListener(textField, textArea);
        runButton.addActionListener(runButtonListener);

        clearButtonListener = new ClearButtonListener(textArea);
        clearButton.addActionListener(clearButtonListener);

        // Pack content in frame.
        frame.pack();

        // Show application frame.
        frame.setVisible(true);

    }

    private JPanel upperPanel() {
        JPanel upper = new JPanel();
        upper.setBorder(BorderFactory.createTitledBorder("Class input field"));
        textField = new JTextField("", 20);
        runButton = new JButton("Run tests");

        upper.add(textField, BorderLayout.CENTER);
        upper.add(runButton);

        upper.setLayout(new FlowLayout(FlowLayout.CENTER));
        return upper;
    }

    private JPanel middlePanel() {
        JPanel middle = new JPanel();
        middle.setBorder(BorderFactory.createTitledBorder("Test results"));
        textArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        middle.add(scrollPane, BorderLayout.CENTER);
        return middle;
    }

    private JPanel lowerPanel() {
        JPanel lower = new JPanel();
        lower.setBorder(BorderFactory.createTitledBorder("Clear"));
        clearButton = new JButton("Clear");
        lower.add(clearButton);

        // Align panel content to center of layout.
        lower.setLayout(new FlowLayout(FlowLayout.CENTER));

        return lower;
    }
}
