package client;

import common.TrafficLightState;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TrafficLightClientWindow {
    private JPanel panel;

    private JLabel red;
    private JLabel yellow;
    private JLabel green;

    private JLabel legend;

    private TrafficLightState currentState = TrafficLightState.NOT_WORKING;

    private TrafficLightClientWindow(String code) {
        legend.setText("code: " + code);
    }

    public static Container Create(String code) {
        TrafficLightClientWindow instance = new TrafficLightClientWindow(code);
        return instance.panel;
    }

    public void setState(TrafficLightState state) {
        currentState = state;
        turnOn();
    }

    private void turnOn() {
        setIcon(red, TrafficLightState.RED);
        setIcon(yellow, TrafficLightState.YELLOW);
        setIcon(green, TrafficLightState.GREEN);
    }

    private void setIcon(JLabel label, TrafficLightState state) {
        String color = state.toString().toLowerCase();
        String onOff = state == currentState ? "on" : "off";
        String file = color + "_" + onOff + ".png";

        Path path = Paths.get("src", "resources", file);
        String absolutePath = path.toAbsolutePath().toString();

        label.setIcon(new ImageIcon(absolutePath));
    }
}
