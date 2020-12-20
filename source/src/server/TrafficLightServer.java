package server;

import gui.Starter;

/**
 * Start point of the server program
 */
public class TrafficLightServer {
    public static void main(String[] args) {
        Starter.CreateAndShow(
                TrafficLightServerWindow.Create(),
                true
        );
    }
}
