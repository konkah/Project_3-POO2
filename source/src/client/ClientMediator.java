package client;

import gui.Starter;

import java.util.UUID;

/**
 * Controller of client program, calls UI and Network
 */
public class ClientMediator {
    private String code;

    /**
     * Start client GUI
     */
    public void start() {
        code = createNewCode();

        Starter.CreateAndShow(
                TrafficLightClientWindow.Create(code),
                true
        );
    }

    private String createNewCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
