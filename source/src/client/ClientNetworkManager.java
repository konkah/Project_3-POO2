package client;

import common.NetworkManager;

/**
 * Manager to Client Network Communications
 */
public class ClientNetworkManager extends NetworkManager {
    public ClientNetworkManager(ClientMediator mediator) {
        super(false, mediator);
    }

    public void start(String code) {
        send("+" + code);
    }
}
