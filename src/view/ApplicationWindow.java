package view;

import javax.swing.*;
import java.awt.*;

public class ApplicationWindow {
    private JFrame frame;
    private final UpperPanel upperPanel = new UpperPanel();
    private final MiddlePanel middlePanel = new MiddlePanel();
    private final LowerPanel lowerPanel = new LowerPanel();

    public ApplicationWindow(String title) {
        frame = new JFrame(title);
        // Set frame default close operation to EXIT_ON_CLOSE to stop process from running.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the frame height and width.
        frame.setPreferredSize(new Dimension(640, 480));

        JPanel upper = upperPanel.buildUpper();
        JPanel middle = middlePanel.buildMiddle();
        JPanel lower = lowerPanel.buildLower();
        frame.add(upper, BorderLayout.NORTH);
        frame.add(middle, BorderLayout.CENTER);
        frame.add(lower, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }



    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }
}
