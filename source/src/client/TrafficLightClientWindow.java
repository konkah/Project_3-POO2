package client;

import javax.swing.*;
import java.awt.*;

public class TrafficLightClientWindow {
    private JPanel panel;

    private JButton helpButton;
    private JButton creditsButton;
    private JLabel code;
    private JLabel red;
    private JLabel yellow;
    private JLabel green;

    public static Container Create() {
        TrafficLightClientWindow instance = new TrafficLightClientWindow();
        return instance.panel;
    }
}
