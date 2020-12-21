package server;

import common.Counterpart;
import common.NetworkManager;
import common.TrafficLightState;

/**
 * Manager to Server Network Communications
 */
public class ServerNetworkManager extends NetworkManager {
    public ServerNetworkManager(ServerMediator mediator) {
        super(true, mediator);
    }

    /**
     * @param counterpart is the address of the client
     * @param state is the new state to client traffic light
     */
    public void sendLight(Counterpart counterpart, TrafficLightState state) {
        send(counterpart, String.valueOf(state.getNumber()));
    }
}
