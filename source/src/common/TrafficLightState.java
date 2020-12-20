package common;

import java.awt.*;
import java.util.Arrays;

public enum TrafficLightState {
    NOT_WORKING(0, 0, Color.GRAY),
    RED(1, 2, Color.RED),
    GREEN(2, 3, Color.GREEN),
    YELLOW(3, 1, Color.YELLOW),
    ;

    private final int number;
    private final int next;
    private final Color color;

    TrafficLightState(int number, int next, Color color) {
        this.number = number;
        this.next = next;
        this.color = color;
    }

    public TrafficLightState getNext() {
        return Arrays.stream(values())
            .filter(s -> s.number == this.next)
            .findFirst()
            .orElse(TrafficLightState.NOT_WORKING);
    }

    public Color getColor() {
        return color;
    }
}
