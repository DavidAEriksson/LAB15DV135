package controller;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class TestClassWorker extends SwingWorker<Map<String, Boolean>, Void> {

    private final Class<?> cls;
    private final JTextArea textArea;

    public TestClassWorker(Class<?> cls, JTextArea textArea) {
        this.cls = cls;
        this.textArea = textArea;
    }

    @Override
    protected Map<String, Boolean> doInBackground() {
        // Create new map to store test names and their results.
        Map<String, Boolean> resultMap = new HashMap<>();

        System.out.println("Methods in class " + cls.getName() + "\n" + Arrays.toString(cls.getDeclaredMethods()));


        java.lang.reflect.Method setUp = null;
        java.lang.reflect.Method tearDown = null;
        Object t = null;

        try {
             setUp = cls.getMethod("setUp");
             tearDown = cls.getMethod("tearDown");
             t = cls.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        for (Method method: cls.getDeclaredMethods()) {
            if(!method.getName().equals("setUp") && !method.getName().equals("tearDown")) {
                try {
                    setUp.invoke(t);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                try {
                    resultMap.put(method.getName(), (Boolean) method.invoke(t));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("testFailingByException: " + e);
                    e.printStackTrace();
                }
                try {
                    tearDown.invoke(t);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(resultMap.entrySet());
        return resultMap;
    }

    @Override
    protected void done() {
        try {
            for (Map.Entry<String, Boolean> stringBooleanEntry : get().entrySet()) {
                textArea.append(stringBooleanEntry.getKey() + ": " + stringBooleanEntry.getValue() + "\n");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
