package client;

import gui.Starter;
import java.util.UUID;

/**
 * Start point of the client program
 */
public class TrafficLightClient {
    public static void main(String[] args) {
        String code = UUID.randomUUID().toString().substring(0, 8);

        Starter.CreateAndShow(
                TrafficLightClientWindow.Create(code),
                true
        );
    }
}
