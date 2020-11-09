import view.ApplicationWindow;
import javax.swing.*;

public class MyUnitTester {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ApplicationWindow applicationWindow = new ApplicationWindow("MyUnitTester");
        });
    }
}
