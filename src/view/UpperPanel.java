package view;

import controller.ClassHandler;
import controller.RunTestClass;

import javax.swing.*;
import java.awt.*;

public class UpperPanel {

    private JTextField textField;
    private ClassHandler classHandler;
    private RunTestClass runTestClass;
    public UpperPanel () {

    }

    public JPanel buildUpper () {
        runTestClass = new RunTestClass();
        classHandler = new ClassHandler();
        JPanel upper = new JPanel();
        upper.setBorder(BorderFactory.createTitledBorder("Class input field"));
        textField = new JTextField("", 20);
        JButton runButton = new JButton("Run tests");
        runButton.addActionListener(e -> {
            String input = textField.getText();
            classHandler.addClassToHandler(input);
            
            runTestClass.runTest();
        });

        upper.add(textField, BorderLayout.CENTER);
        upper.add(runButton);
        upper.setLayout(new FlowLayout(FlowLayout.CENTER));
        return upper;
    }
}
