import GUI.LogicFrame;
import GUI.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LogicFrame().setVisible(true));
    }
}