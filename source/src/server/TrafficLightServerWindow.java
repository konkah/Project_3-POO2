package server;

import javax.swing.*;
import java.awt.*;

public class TrafficLightServerWindow {
    private JPanel server;

    private JButton helpButton;
    private JButton creditsButton;

    private JCheckBox offCheckBox;
    private JTextField green;
    private JTextField yellow;
    private JTextField red;
    private JTable status;

    public static Container Create() {
        TrafficLightServerWindow instance = new TrafficLightServerWindow();
        return instance.server;
    }


}
