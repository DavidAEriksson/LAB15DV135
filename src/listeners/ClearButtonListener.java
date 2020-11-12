package listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButtonListener implements ActionListener {


    private final JTextArea textArea;

    public ClearButtonListener(JTextArea textArea) {
        this.textArea = textArea;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.setText(null);
    }
}
