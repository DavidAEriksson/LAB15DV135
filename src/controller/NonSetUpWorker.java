package controller;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * Class: <b>NonSetUpWorker</b>.
 * Responsible for running tests on test classes that do not have a setUp
 * or tearDown method. The worker will run all declared methods in the
 * given class and output their results and a summary to the application
 * window.
 *
 * Author: David Eriksson, id16den https://davidaeriksson.netlify.app
 */
public class NonSetUpWorker extends SwingWorker<HashMap<String, Object>, Void> {

    /*
        Test class and application JTextArea.
    */
    private final Class<?> cls;
    private final JTextArea textArea;

    /**
     * Method: <b>NonSetUpWorker</b> (public constructor).
     * Constructor method for NonSetUpWorker class.
     * @param cls       Test class which worker will invoke methods from.
     * @param textArea  JTextArea in application window where worker will display results and summary.
     */
    public NonSetUpWorker(Class<?> cls, JTextArea textArea) {
        this.cls = cls;
        this.textArea = textArea;
    }

    /**
     * Method: <b>doInBackground</b>.
     * SwingWorker method responsible for running test class methods on the separate SwingWorker thread.
     * @return resultMap: A HashMap containing the method name and its result.
     */
    @Override
    protected HashMap<String, Object> doInBackground() {
        /*
            Create new map to store test names and their results.
         */
        HashMap<String, Object> resultMap = new HashMap<>();

        /*
          Create new object to store test result. Being an object, this can store both boolean
          test results and exceptions.
         */
        Object t = null;

        /*
            Create new instance of Object t on test class.
         */
        try {
            t = cls.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            /*
                Error handling, in case of faulty constructor in class or unknown error with the new instance
                we need to give the user information of what happened with the constructor instance.
             */
            assert false;
            resultMap.put(t.getClass().getName(), "Failed to create new instance on class constructor due to exception: " + e);
            e.printStackTrace();
        }

        /*
            Iterate through methods in test class.
         */
        for (Method method: cls.getDeclaredMethods()) {
               try {
                   /*
                        Add failed or successful test to resultMap.
                    */
                    resultMap.put(method.getName(), method.invoke(t));
                } catch (IllegalAccessException | InvocationTargetException e) {
                   /*
                        Add failed test to resultMap(Fail due to exception with cause.)
                    */
                    resultMap.put(method.getName(), "FAILED: Generated exception: " + e.getCause());
                    e.printStackTrace();
                }
        }
        return resultMap;
    }

    /**
     * Method: <b>done</b>.
     * SwingWorker method to run GUI-operations on the EDT <i>after</i> <b>doInBackground</b> is finished.
     * The method will run through all of the entries in the resultMap and display the method names and their
     * results from the test class. The method will also run a tracker on all of the different types of
     * test results, passed tests, failed tests, tests failed due to exceptions and the total amount of tests
     * completed and display these in the JTextArea.
     */
    @Override
    protected void done() {
        /*
            Test result counters.
         */
        int totalTestCount = 0;
        int trueCount = 0;
        int falseCount = 0;
        int exceptionCount = 0;

        try {
            /*
                Iterate through all test test results.
             */
            for (var stringObjectEntry : get().entrySet()) {
                if(stringObjectEntry.getValue().equals(true)) {
                    trueCount++; // Count passed test.
                }
                else if(stringObjectEntry.getValue().equals(false)) {
                    falseCount++; // Count failed test.
                }
                else if(!stringObjectEntry.getValue().equals(true) || !stringObjectEntry.getValue().equals(false)) {
                    exceptionCount++; // Count test failed due to exception.
                }
                /*
                    Append test results to textArea.
                 */
                textArea.append(stringObjectEntry.getKey() + ": " + stringObjectEntry.getValue() + "\n");
                totalTestCount++; // Count all tests.
            }

            /*
                Append test counters to textArea.
             */
            textArea.append(
                    "\nTest summary: \n" +
                            totalTestCount + " tests completed where \n" +
                            trueCount + " tests succeeded.\n" +
                            falseCount + " tests failed.\n" +
                            exceptionCount + " tests failed because of an exception."
            );
        } catch (InterruptedException | ExecutionException e) {
            textArea.append("Failed due to EDT-exception: " + e);
            e.printStackTrace();
        }
    }
}
