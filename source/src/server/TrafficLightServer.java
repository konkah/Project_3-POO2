package server;

import gui.Starter;

public class TrafficLightServer {
    public static void main(String[] args) {
        Starter.CreateAndShow(
                TrafficLightServerWindow.Create(),
                true
        );
    }
}
