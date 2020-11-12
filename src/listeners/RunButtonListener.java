package listeners;

import java.lang.reflect.Method;
import controller.NonSetUpWorker;
import controller.SetUpWorker;
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

        boolean setUpExists = false;

        // Remove previous text from text area.
        textArea.setText(null);
        // Start worker thread.
        try {
            Class<?> cls = Class.forName(textField.getText());
            // Check if Class clazz has method "setUp" so we know which worker to run.
            for(Method method : cls.getDeclaredMethods()) {
                if (method.getName().equals("setUp")) {
                    setUpExists = true;
                    break;
                }
            }

            // Check if input class is implementing TestClass interface before continuing.
            if(TestClass.class.isAssignableFrom(cls)) {
                // Make sure that we don't run worker on an interface, primitive class, or class with no
                // declared methods.
                if(cls.getDeclaredMethods().length == 0) {
                    textArea.append("Class " + textField.getText() + " has no accessible methods, is an interface or primitive.");
                }
                if(!setUpExists) { // Run NonSetUpWorker since we don't have a setUp-method to run.
                    new NonSetUpWorker(cls, textArea).execute();
                } else new SetUpWorker(cls, textArea).execute(); // Run SetUpWorker since we have a setUp-method to run.

            } else {
                textArea.append("The class " + textField.getText() + " does not implement required TestClass interface.") ;
            }
        } catch (ClassNotFoundException classNotFoundException) {
            textArea.append("Class " + textField.getText() + " was not found.");
            classNotFoundException.printStackTrace();
        }

    }
}
