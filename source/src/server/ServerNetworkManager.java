package server;

import network.NetworkManager;

import java.util.List;

/**
 * Manager to Server Network Communications
 */
public class ServerNetworkManager extends NetworkManager {
    public ServerNetworkManager(ServerMediator mediator) {
        super(true, mediator);
    }

    /**
     * Update client light
     * @param client that should receive the new status
     */
    public void sendLight(Client client) {
        send(
            client.getCounterpart(),
            String.valueOf(client.getCurrentState().getNumber())
        );
    }

    public void end(List<Client> clients) {
        for (Client client : clients) {
            send(
                client.getCounterpart(),
                closeMessage
            );
        }
    }
}
