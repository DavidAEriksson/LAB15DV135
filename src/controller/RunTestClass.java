package controller;

import java.util.Arrays;

public class RunTestClass {



    public RunTestClass() {

    }



    private void handleClassSetUp() {

    }

    private void handleClassTearDown() {

    }

    public void runTest(ClassHandler classes) {
        System.out.println(Arrays.toString(classes.getClassByIndex(0).getMethods()));
    }
}
