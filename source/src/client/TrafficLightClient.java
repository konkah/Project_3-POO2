package client;

/**
 * Start point of the client program
 */
public class TrafficLightClient {
    public static void main(String[] args) {
        ClientMediator mediator = new ClientMediator();
        mediator.start();
    }
}
