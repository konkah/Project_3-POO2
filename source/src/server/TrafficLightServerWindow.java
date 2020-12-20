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
        c++;
    }

    public void removeLight(String code) {
        JLabel light = getLight(code);

        if (light != null) {
            lights.remove(light);
        }
    }
}
