package listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Class: <b>ClearButtonListener</b>.
 * ActionListener implementation for the "clear" button used to clear the JTextArea of any text.
 * Author: David Eriksson, id16den https://davidaeriksson.netlify.app
 */
public class ClearButtonListener implements ActionListener {

    /*
        Application window JTextArea.
     */
    private final JTextArea textArea;


    /**
     * Method: <b>ClearButtonListener</b> (public constructor).
     * Constructor method for ClearButtonListener class.
     * @param textArea Application JTextArea.
     */
    public ClearButtonListener(JTextArea textArea) {
        this.textArea = textArea;
    }

    /**
     * Method: <b>actionPerformed</b>
     * Handles what happens when the application button clearButton is pressed by user.
     * @param e ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /*
            Clear application JTextArea.
         */
        textArea.setText(null);
    }
}
