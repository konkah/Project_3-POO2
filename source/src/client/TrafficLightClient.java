package client;

import gui.Starter;

public class TrafficLightClient {
    public static void main(String[] args) {
        Starter.CreateAndShow(
                TrafficLightClientWindow.Create(),
                true
        );
    }
}
