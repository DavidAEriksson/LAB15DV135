import view.ApplicationWindow;
import javax.swing.*;

/**
 * Class: <b>MyUnitTester</b>.
 * Main class for MyUnitTester, responsible for running applicationWindow on invokeLater.
 * Author: David Eriksson, id16den https://davidaeriksson.netlify.app
 */
public class MyUnitTester {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ApplicationWindow applicationWindow = new ApplicationWindow("MyUnitTester");
        });
    }
}
