package server;

import common.TrafficLightState;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Main GUI of the client program
 */
public class TrafficLightServerWindow {
    private JPanel panel;

    private JCheckBox offCheckBox;
    private JTextField green;
    private JTextField yellow;
    private JTextField red;

    private JPanel lights;

    int r = 0;
    int c = 0;
    int maxColumns = 7;

    /**
     * @return the main panel of this window
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Create new or update existent traffic light
     * @param code of the client
     * @param state to change the light
     */
    public void addOrUpdateLight(String code, TrafficLightState state) {
        JLabel light = getLight(code);

        if (light == null) {
            addLight(code, state);
        } else {
            light.setForeground(state.getColor());
        }
    }

    private JLabel getLight(String text) {
        return Arrays.stream(lights.getComponents())
                .map(c -> (JLabel)c)
                .filter(c -> c.getText().equals(text))
                .findFirst().orElse(null);
    }

    private void addLight(String code, TrafficLightState state) {
        JLabel light = new JLabel(code);
        light.setForeground(state.getColor());
        light.setVisible(true);
        light.setHorizontalAlignment(JLabel.CENTER);

        if (c == maxColumns) {
            c = 0;
            r++;
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = c;
        constraints.gridy = r;
        constraints.ipadx = 10;
        constraints.ipady = 10;

        lights.add(light, constraints);
        lights.updateUI();

        c++;
    }

    /**
     * Remove a client from the panel of traffic lights
     * @param code of client to remove
     */
    public void removeLight(String code) {
        JLabel light = getLight(code);

        if (light != null) {
            lights.remove(light);
        }
    }
}
