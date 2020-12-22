package server;

import network.Counterpart;
import network.Mediator;
import common.TrafficLightState;
import gui.Starter;

import java.util.ArrayList;
import java.util.List;

import static common.TrafficLightState.RED;

/**
 * Controller of server program, calls UI and Network
 */
public class ServerMediator implements Mediator {
    private ServerNetworkManager network;
    private TrafficLightServerWindow window;
    private List<Client> clients;
    private SemaphoreController semaphore;

    private boolean serverOn = false;

    private final TrafficLightState start = RED;

    /**
     * Start server GUI
     */
    public void start() {
        network = new ServerNetworkManager(this);
        serverOn = true;

        clients = new ArrayList<>();

        semaphore = new SemaphoreController(clients, this);
        semaphore.start();

        window = new TrafficLightServerWindow(this);
        Starter.CreateAndShow(window.getPanel(), true);
        window.turnOn();
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
            case '+': {
                Client client = new Client(code, counterpart, start);
                clients.add(client);

                int position = clients.indexOf(client);
                client.setPosition(position);

                window.addLight(client);

                network.sendLight(client);

                break;
            }
            case '-': {
                Client client = getClient(code);
                System.out.println(client);

                if (client != null) {
                    clients.remove(client);
                    window.removeLight(client);
                }
            }
        }
    }

    private Client getClient(String code) {
        return clients.stream()
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    /**
     * Advance the traffic light
     * @param client to advance traffic light
     */
    public void change(Client client) {
        window.updateLight(client);
        network.sendLight(client);
    }

    public void toggleState(boolean on) {
        serverOn = on;
    }

    public boolean isServerOn() {
        return serverOn;
    }
}
