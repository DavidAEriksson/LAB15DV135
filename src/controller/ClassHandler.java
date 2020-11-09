package controller;
import se.umu.cs.unittest.TestClass;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ClassHandler {

    private final ArrayList<Class> classes = new ArrayList<>();

    public ClassHandler() {

    }

    public void addClassToHandler(String className) {
        try {
            Class <?> cls = Class.forName(className);
            classes.add(cls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public Class getClassByIndex (int index) {
        return classes.get(index);
    }

    public Method[] getClassMethods (int index) {
        Method[] methods = classes.get(index).getDeclaredMethods();
        return methods;
    }

    public boolean classHasSetUp (int index) {
        Method setup;
        try {
            setup = classes.get(index).getMethod("setUp", (Class<?>[]) null);
            if(setup == null) {
                return false;
            } else return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean classHasTearDown (int index) {
        Method teardown;
        try {
            teardown = classes.get(index).getMethod("tearDown", (Class<?>[]) null);
            if(teardown == null) {
                return false;
            } else return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkClassImplementsTestClass (int index) {
        return TestClass.class.isAssignableFrom(classes.get(index));
    }
}
