package client;

import common.TrafficLightState;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class TrafficLightClientWindow {
    private JPanel panel;

    private JLabel red;
    private JLabel yellow;
    private JLabel green;

    private final String code;
    private JLabel legend;

    private TrafficLightState currentState = TrafficLightState.RED;

    public TrafficLightClientWindow() {
        code = UUID.randomUUID().toString().substring(0, 8);
        legend.setText("code: " + code);
    }

    public static Container Create() {
        TrafficLightClientWindow instance = new TrafficLightClientWindow();
        instance.turnOn();
        return instance.panel;
    }

    public void next() {
        currentState = currentState.getNext();
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
