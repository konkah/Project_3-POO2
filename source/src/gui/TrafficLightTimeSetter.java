package gui;

import common.Settings;
import common.TrafficLightState;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TrafficLightTimeSetter extends KeyAdapter {
    private final JTextField field;
    private final TrafficLightState state;

    public TrafficLightTimeSetter(JTextField field, TrafficLightState state) {
        this.field = field;
        this.state = state;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        char character = keyEvent.getKeyChar();

        boolean notANumber = character < '0'
                || character > '9';

        if (notANumber) {
            String remove = new String(new char[] {character});
            field.setText(field.getText().replace(remove, ""));
        }

        int number = Integer.parseInt(field.getText());
        Settings.seconds.put(state, number);

        super.keyReleased(keyEvent);
    }

    public static void initialize(JTextField field, TrafficLightState state) {
        field.setText(String.valueOf(Settings.seconds.get(state)));
        field.addKeyListener(new TrafficLightTimeSetter(field, state));
    }
}
