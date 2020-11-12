package listeners;

import controller.TestClassWorker;
import se.umu.cs.unittest.TestClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunButtonListener implements ActionListener {

    private final JTextField textField;
    private final JTextArea textArea;

    public RunButtonListener(JTextField textField, JTextArea textArea) {
        this.textField = textField;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Remove previous text from text area.
        textArea.setText(null);
        // Start worker thread.
        try {
            Class<?> cls = Class.forName(textField.getText());
            // Check if input class is implementing TestClass interface before continuing.
            if(TestClass.class.isAssignableFrom(cls)) {
                // Make sure that we don't run worker on an interface, primitive class, or class with no
                // declared methods.
                if(cls.getDeclaredMethods().length == 0) {
                    textArea.append("Class " + textField.getText() + " has no accessible methods, is an interface or primitive.");
                } else new TestClassWorker(cls, textArea).execute(); // Send class and textArea to TestClassWorker
            } else {
                textArea.append("The class " + textField.getText() + " does not implement required TestClass interface.") ;
            }
        } catch (ClassNotFoundException classNotFoundException) {
            textArea.append("Class " + textField.getText() + " was not found.");
            classNotFoundException.printStackTrace();
        }

    }
}
