package server;

import common.TrafficLightState;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TrafficLightServerWindow {
    private JPanel panel;

    private JCheckBox offCheckBox;
    private JTextField green;
    private JTextField yellow;
    private JTextField red;

    private JPanel lights;

    public static Container Create() {
        TrafficLightServerWindow instance = new TrafficLightServerWindow();
        return instance.panel;
    }

    int r = 0;
    int c = 0;
    int maxColumns = 7;

    public void addLight(String text, TrafficLightState state) {
        JLabel light = new JLabel(text);
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
        c++;
    }

    public void updateLight(String text, TrafficLightState state) {
        JLabel light = getLight(text);

        if (light == null) {
            addLight(text, state);
        } else {
            light.setForeground(state.getColor());
        }
    }

    public void removeLight(String text) {
        JLabel light = getLight(text);

        if (light != null) {
            lights.remove(light);
        }
    }

    private JLabel getLight(String text) {
        return Arrays.stream(lights.getComponents())
                .map(c -> (JLabel)c)
                .filter(c -> c.getText().equals(text))
                .findFirst().orElse(null);
    }
}
