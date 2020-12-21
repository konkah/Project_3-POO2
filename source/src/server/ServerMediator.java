package server;

import network.Counterpart;
import network.Mediator;
import common.TrafficLightState;
import gui.Starter;

import java.util.HashMap;
import java.util.Map;

import static common.TrafficLightState.RED;

/**
 * Controller of server program, calls UI and Network
 */
public class ServerMediator implements Mediator {
    private ServerNetworkManager network;
    private TrafficLightServerWindow window;
    private Map<String, Counterpart> clients;

    private final TrafficLightState start = RED;

    /**
     * Start server GUI
     */
    public void start() {
        network = new ServerNetworkManager(this);

        window = new TrafficLightServerWindow();
        Starter.CreateAndShow(window.getPanel(), true);

        clients = new HashMap<>();
    }

    /**
     * @param error to show to the user
     */
    @Override
    public void handleError(Exception error) {
        error.printStackTrace();
    }

    /**
     * @param counterpart is who is sending the message
     * @param message is the received text
     */
    @Override
    public void handleSuccessReceive(Counterpart counterpart, String message) {
        char operation = message.charAt(0);
        String code = message.substring(1);

        switch (operation) {
            case '+':
                clients.put(code, counterpart);
                window.addOrUpdateLight(code, start);
                network.sendLight(counterpart, start);
                break;
            case '-':
                clients.remove(code);
                window.removeLight(code);
        }
    }
}
