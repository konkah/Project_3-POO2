package common;

import java.awt.*;
import java.util.Arrays;

/**
 * Possible states of a traffic light
 */
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

    /**
     * @return state that follows the current one
     */
    public TrafficLightState getNext() {
        return Arrays.stream(values())
            .filter(s -> s.number == this.next)
            .findFirst()
            .orElse(TrafficLightState.NOT_WORKING);
    }

    /**
     * @return color of the traffic light state
     */
    public Color getColor() {
        return color;
    }
}
