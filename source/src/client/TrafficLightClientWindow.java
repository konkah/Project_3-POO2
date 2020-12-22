package client;

import common.TrafficLightState;

import javax.swing.*;
import java.net.URL;

/**
 * Main GUI of the server program
 */
public class TrafficLightClientWindow {
    private JPanel panel;

    private JLabel red;
    private JLabel yellow;
    private JLabel green;

    private JLabel legend;

    private final int imageLoaded = 8;
    private TrafficLightState currentState = TrafficLightState.NOT_WORKING;

    public TrafficLightClientWindow(String code) {
        legend.setText("code: " + code);
    }

    /**
     * @return the main panel of this window
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Set the next state of the traffic light
     * @param state to traffic light
     */
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
        String file = "/" + color + "_" + onOff + ".png";

        URL url = getClass().getResource(file);

        ImageIcon image = new ImageIcon(url);
        label.setIcon(image);
    }
}
