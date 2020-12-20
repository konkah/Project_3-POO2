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
    private TrafficLightState currentState = TrafficLightState.RED;

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
        System.out.println(label);
        System.out.println(state);
        System.out.println(this.currentState);

        String color = state.toString().toLowerCase();
        String onOff = state == this.currentState ? "on" : "off";
        String file = color + "_" + onOff + ".png";

        Path path = Paths.get("src", "resources", file);
        String absolutePath = path.toAbsolutePath().toString();

        label.setIcon(new ImageIcon(absolutePath));
    }
}
