package client;

import network.Counterpart;
import network.Mediator;
import common.TrafficLightState;
import gui.Starter;

import java.util.UUID;

/**
 * Controller of client program, calls UI and Network
 */
public class ClientMediator implements Mediator {
    private String code;
    private ClientNetworkManager network;
    private TrafficLightClientWindow window;

    /**
     * Start client GUI
     */
    public void start() {
        code = createNewCode();

        window = new TrafficLightClientWindow(code);
        Starter.CreateAndShow(window.getPanel(), true);

        network = new ClientNetworkManager(this);
        network.start(code);
    }

    private String createNewCode() {
        return UUID.randomUUID().toString().substring(0, 8);
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
        int number = Integer.parseInt(message.trim());
        TrafficLightState state = TrafficLightState.parse(number);
        window.setState(state);
    }
}
