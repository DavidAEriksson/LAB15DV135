package listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RunButtonListener implements ActionListener {

    private final JTextField textField;
    private JTextArea textArea;
    public RunButtonListener(JTextField textField, JTextArea textArea) {
        this.textField = textField;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Remove previous text from text area.
        textArea.setText(null);

        try {
            Class cls = Class.forName(textField.getText());
            System.out.println(Arrays.toString(cls.getMethods()));
            textArea.append(Arrays.toString(cls.getMethods()));
        } catch (ClassNotFoundException classNotFoundException) {
            textArea.append("Class " + textField.getText() + " does not exist.");
            classNotFoundException.printStackTrace();
        }
        // Add new text to text area. We're going to have to build a string here.
    }
}
