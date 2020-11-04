import controller.ClassHandler;
import view.ApplicationWindow;
import javax.swing.*;

public class MyUnitTester {

    public static void main(String[] args) {
        ClassHandler classHandler = new ClassHandler();

        for (String arg : args) {
            classHandler.addClassToHandler(arg);
        }

        System.out.println("Check implementation: " + classHandler.checkClassImplementsTestClass(0)); //This cannot fail

        // Not an error of startUp or tearDown to not exist in the test-class.
/*
        for (String arg : args) {
            try {
                Class<?> cls= Class.forName(arg);
                System.out.println("Implements TestClass: " + TestClass.class.isAssignableFrom(cls)); //True?False
                Method[] classes = cls.getDeclaredMethods();

                for (Method aClass : classes) {
                    System.out.println("Class " + arg + " method: " + aClass);

                }

            } catch (ClassNotFoundException e) {
                System.out.println("Kunde inte hitta " + arg);
            }
        }*/


       SwingUtilities.invokeLater(() -> {
            ApplicationWindow applicationWindow = new ApplicationWindow("MyUnitTester");
            applicationWindow.show();
        });
    }
}
