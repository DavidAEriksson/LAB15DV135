package controller;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class NonSetUpWorker extends SwingWorker<HashMap<String, Object>, Void> {

    private final Class<?> cls;
    private final JTextArea textArea;

    public NonSetUpWorker(Class<?> cls, JTextArea textArea) {
        this.cls = cls;
        this.textArea = textArea;
    }

    @Override
    protected HashMap<String, Object> doInBackground() {
        // Create new map to store test names and their results.
        HashMap<String, Object> resultMap = new HashMap<>();
        /*
          Create new object to store test result. Being an object, this can store both boolean
          test results and exceptions.
         */
        Object t = null;

        try {
            t = cls.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        for (Method method: cls.getDeclaredMethods()) {
               try {
                    resultMap.put(method.getName(), method.invoke(t));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("testFailingByException: " + e);
                    resultMap.put(method.getName(), "FAILED: Generated exception: " + e);
                    e.printStackTrace();
                }
        }
        return resultMap;
    }

    @Override
    protected void done() {
        int totalTestCount = 0;
        int trueCount = 0;
        int falseCount = 0;
        int exceptionCount = 0;

        try {
            for (var stringObjectEntry : get().entrySet()) {
                if(stringObjectEntry.getValue().equals(true)) {
                    trueCount++;
                }
                else if(stringObjectEntry.getValue().equals(false)) {
                    falseCount++;
                }
                else if(!stringObjectEntry.getValue().equals(true) || !stringObjectEntry.getValue().equals(false)) {
                    exceptionCount++;
                }
                textArea.append(stringObjectEntry.getKey() + ": " + stringObjectEntry.getValue() + "\n");
                totalTestCount++;
            }
            textArea.append(
                    "\nTest summary: \n" +
                            totalTestCount + " tests completed where \n" +
                            trueCount + " tests succeeded.\n" +
                            falseCount + " tests failed.\n" +
                            exceptionCount + " tests failed because of an exception."
            );

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
