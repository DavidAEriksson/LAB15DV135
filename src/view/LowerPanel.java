package view;

import javax.swing.*;
import java.awt.*;

public class LowerPanel {
    public JButton clearButton;

    public LowerPanel () {

    }

    public JPanel buildLower () {
        JPanel lower = new JPanel();
        lower.setBorder(BorderFactory.createTitledBorder("Clear"));
        clearButton = new JButton("Clear");
        lower.add(clearButton);

        // Align panel content to center of layout.
        lower.setLayout(new FlowLayout(FlowLayout.CENTER));

        return lower;
    }
}
