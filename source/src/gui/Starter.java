package gui;

import javax.swing.*;
import java.awt.*;

public class Starter {
    public static void CreateAndShow(Container panel, Boolean exitOnClose) {
        JFrame frame = new JFrame("Traffic Light");
        frame.setContentPane(panel);

        if (exitOnClose) {
            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );
        }

        frame.pack();
        frame.setVisible(true);
    }
}
