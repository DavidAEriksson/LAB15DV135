package listeners;

import java.lang.reflect.Method;
import controller.NonSetUpWorker;
import controller.SetUpWorker;
import se.umu.cs.unittest.TestClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class: <b>RunButtonListener</b>.
 * ActionListener implementation for the "run tests" button
 * Author: David Eriksson, id16den https://davidaeriksson.netlify.app
 */
public class RunButtonListener implements ActionListener {

    /*
        Application window JTextField and JTextArea.
     */
    private final JTextField textField;
    private final JTextArea textArea;

    /**
     * Method: <b>RunButtonListener</b> (public constructor).
     * Constructor method for RunButtonListener class.
     * @param textField Application JTextField.
     * @param textArea Application JTextArea.
     */
    public RunButtonListener(JTextField textField, JTextArea textArea) {
        this.textField = textField;
        this.textArea = textArea;
    }

    /**
     * Method: <b>actionPerformed</b>
     * Handles what happens when the application button runButton is pressed by user.
     * @param e ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        /*
            Boolean variable used to check if method setUp exists in class cls.
         */
        boolean setUpExists = false;
        /*
            Remove previous text from text area.
         */
        textArea.setText(null);
        /*
            Start worker thread.
         */
        try {
            /*
                Get Class cls from the JTextField.
             */
            Class<?> cls = Class.forName(textField.getText());

            /*
                Check if Class cls has method "setUp" so we know which worker to run.
             */
            for(Method method : cls.getDeclaredMethods()) {
                if (method.getName().equals("setUp")) {
                    setUpExists = true;
                    break;
                }
            }

            /*
                Check if input class is implementing TestClass interface before continuing.
             */
            if(TestClass.class.isAssignableFrom(cls)) {
                /*
                    Make sure that we don't run worker on an interface, primitive class, or class with no
                    declared methods.
                 */
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
            /*
                Error handling, if class does not exist user needs to be noticed.
             */
            textArea.append("Class " + textField.getText() + " was not found.");
            classNotFoundException.printStackTrace();
        }

    }
}
