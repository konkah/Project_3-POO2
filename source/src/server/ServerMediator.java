package server;

import gui.Starter;

/**
 * Controller of server program, calls UI and Network
 */
public class ServerMediator {
    /**
     * Start server GUI
     */
    public void start() {
        Starter.CreateAndShow(
                TrafficLightServerWindow.Create(),
                true
        );
    }
}
