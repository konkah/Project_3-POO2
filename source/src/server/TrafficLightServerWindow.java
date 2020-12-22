package server;

import common.TrafficLightState;
import gui.TrafficLightTimeSetter;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static common.TrafficLightState.*;

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

    int maxRows = 15;
    int maxCols = 5;
    String empty = "_: ________";
    JLabel[][] occupied = new JLabel[maxRows][maxCols];

    public TrafficLightServerWindow() {
        TrafficLightTimeSetter.initialize(red, RED);
        TrafficLightTimeSetter.initialize(yellow, YELLOW);
        TrafficLightTimeSetter.initialize(green, GREEN);
    }

    /**
     * @return the main panel of this window
     */
    public JPanel getPanel() {
        for (int r = 0; r < maxRows; r++) {
            for (int c = 0; c < maxCols; c++) {
                replaceLight(empty, Color.BLACK, r, c);
            }
        }

        return panel;
    }

    /**
     * Create new traffic light
     * @param code of the client
     * @param state to change the light
     */
    public void addLight(String code, TrafficLightState state) {
        int r,c;
        do{
            r = getRandom(maxRows);
            c = getRandom(maxCols);
            System.out.println(code+": "+r+","+c);
        }
        while (!occupied[r][c].getText().equals(empty));

        replaceLight(code, state.getColor(), r, c);
    }

    private int getRandom(int max) {
        double random;

        // to avoid returning max
        do {
           random = Math.random();
        } while (random == 1);

        return (int) Math.floor(random * max);
    }

    private void replaceLight(String text, Color color, int row, int col) {
        JLabel light = new JLabel(text);

        light.setForeground(color);
        light.setVisible(true);
        light.setHorizontalAlignment(JLabel.CENTER);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = col;
        constraints.gridy = row;
        constraints.ipadx = 10;
        constraints.ipady = 10;

        if (occupied[row][col] != null)
            lights.remove(occupied[row][col]);

        lights.add(light, constraints);
        lights.updateUI();

        occupied[row][col] = light;
    }

    /**
     * Update existent traffic light
     * @param code of the client
     * @param state to change the light
     */
    public void updateLight(String code, TrafficLightState state) {
        JLabel light = getLight(code);

        if (light != null) {
            light.setForeground(state.getColor());
        }
    }

    /**
     * Remove a client from the panel of traffic lights
     * @param code of client to remove
     */
    public void removeLight(String code) {
        JLabel light = getLight(code);
        if (light == null) return;

        for (int r = 0; r < maxRows; r++) {
            for (int c = 0; c < maxCols; c++) {
                if (occupied[r][c] == light) {
                    replaceLight("_: ________", Color.BLACK, r, c);
                    occupied[r][c] = null;
                    return;
                }
            }
        }
    }

    private JLabel getLight(String text) {
        return Arrays.stream(lights.getComponents())
                .map(c -> (JLabel)c)
                .filter(c -> c.getText().equals(text))
                .findFirst().orElse(null);
    }
}
