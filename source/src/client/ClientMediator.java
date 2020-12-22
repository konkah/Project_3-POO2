package client;

import common.TrafficLightState;
import gui.Starter;
import network.Counterpart;
import network.Mediator;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.UUID;

/**
 * Controller of client program, calls UI and Network
 */
public class ClientMediator implements Mediator {
    private String code;
    private ClientNetworkManager network;
    private TrafficLightClientWindow window;
    private JFrame frame;

    /**
     * Start client GUI
     */
    public void start() {
        code = createNewCode();

        window = new TrafficLightClientWindow(code);
        frame = Starter.CreateAndShow(window.getPanel(), true);

        network = new ClientNetworkManager(this);
        network.start(code);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                network.end(code);
                super.windowClosing(windowEvent);
            }
        });
    }

    private String createNewCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * @param error to show to the user
     */
    @Override
    public void handleError(Exception error) {
        window.showError(error.getMessage());
        error.printStackTrace();
    }

    /**
     * @param counterpart is who is sending the message
     * @param message is the received text
     */
    @Override
    public void handleSuccessReceive(Counterpart counterpart, String message) {
        message = message.trim();
        if (message.equals(network.closeMessage)) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            return;
        }

        int number = Integer.parseInt(message);
        TrafficLightState state = TrafficLightState.parse(number);
        window.setState(state);
    }
}
