package common;

import java.util.HashMap;
import java.util.Map;

import static common.TrafficLightState.*;

/**
 * System Parameters
 */
public class Settings {
    /**
     * Server Public IP
     */
    public static final String SERVER_IP = "192.168.0.10";

    /**
     * Server Public Port
     */
    public static final int SERVER_PORT = 2932;

    /**
     * Communication Buffer Size
     */
    public static final int BUFFER_SIZE = 9;

    /**
     * Seconds the lights are shown
     */
    public static Map<TrafficLightState, Integer> seconds =
        new HashMap<TrafficLightState, Integer>() {{
            put(RED, 3);
            put(YELLOW, 1);
            put(GREEN, 2);
            put(NOT_WORKING, 0);
        }};

    public static final int ONE_SECOND = 1000;
}
