package client;

import common.Interval;
import common.TrafficLightState;

import javax.swing.*;
import java.net.URL;

import static common.Settings.WAIT_MILLISECONDS;
import static common.TrafficLightState.NOT_WORKING;
import static common.TrafficLightState.YELLOW;

/**
 * Main GUI of the server program
 */
public class TrafficLightClientWindow {
    private JPanel panel;

    private JLabel red;
    private JLabel yellow;
    private JLabel green;

    private JLabel legend;

    private TrafficLightState currentState = NOT_WORKING;
    boolean blinkOn = false;

    public TrafficLightClientWindow(String code) {
        legend.setText("code: " + code);
        blinkIfNotWorking();
    }

    private void blinkIfNotWorking() {
        Thread thread = new Thread(new Interval(WAIT_MILLISECONDS/2) {
            @Override
            protected void execute() {
                if (currentState != NOT_WORKING)
                    return;

                setIcon(yellow, YELLOW, blinkOn);
                blinkOn = !blinkOn;
            }
        });

        thread.start();
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
        setIcon(label, state, state == currentState);
    }

    private void setIcon(JLabel label, TrafficLightState state, boolean on) {
        String color = state.toString().toLowerCase();
        String onOff = on ? "on" : "off";
        String file = "/" + color + "_" + onOff + ".png";

        URL url = getClass().getResource(file);

        ImageIcon image = new ImageIcon(url);
        label.setIcon(image);
    }
}
