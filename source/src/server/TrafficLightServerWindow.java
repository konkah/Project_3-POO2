package server;

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
                turnOffLight(r, c);
            }
        }

        return panel;
    }

    /**
     * Create new traffic light
     * @param client to add light
     */
    public void addLight(Client client) {
        int r,c;
        do{
            r = getRandom(maxRows);
            c = getRandom(maxCols);
        }
        while (!occupied[r][c].getText().equals(empty));

        replaceLight(client.toString(), client.getCurrentState().getColor(), r, c);
    }

    /**
     * Update existent traffic light
     * @param client to add light
     */
    public void updateLight(Client client) {
        JLabel light = getLight(client);

        if (light != null) {
            light.setForeground(client.getCurrentState().getColor());
        }
    }

    /**
     * Remove a client from the panel of traffic lights
     * @param client to add light
     */
    public void removeLight(Client client) {
        JLabel light = getLight(client);
        if (light == null) return;

        for (int r = 0; r < maxRows; r++) {
            for (int c = 0; c < maxCols; c++) {
                if (occupied[r][c] == light) {
                    turnOffLight(r, c);
                    occupied[r][c] = null;
                    return;
                }
            }
        }
    }

    private int getRandom(int max) {
        double random;

        // to avoid returning max
        do {
           random = Math.random();
        } while (random == 1);

        return (int) Math.floor(random * max);
    }

    private void turnOffLight(int row, int col) {
        replaceLight(empty, Color.BLACK, row, col);
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

    private JLabel getLight(Client client) {
        return Arrays.stream(lights.getComponents())
                .map(c -> (JLabel)c)
                .filter(c -> c.getText().equals(client.toString()))
                .findFirst().orElse(null);
    }
}
