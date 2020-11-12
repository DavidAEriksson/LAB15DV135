package controller;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class NonSetUpWorker extends SwingWorker<Map<String, Boolean>, Void> {


    private final Class<?> cls;
    private final JTextArea textArea;

    public NonSetUpWorker(Class<?> cls, JTextArea textArea) {
        this.cls = cls;
        this.textArea = textArea;
    }

    @Override
    protected Map<String, Boolean> doInBackground() {
        Map<String, Boolean> resultMap = new HashMap<>();

        Object t = null;

        try {
            t = cls.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        for (Method method: cls.getDeclaredMethods()) {
               try {
                    resultMap.put(method.getName(), (Boolean) method.invoke(t));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("testFailingByException: " + e);
                    e.printStackTrace();
                }
        }
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
