package server;

/**
 * Start point of the server program
 */
public class TrafficLightServer {
    public static void main(String[] args) {
        ServerMediator mediator = new ServerMediator();
        mediator.start();
    }
}
