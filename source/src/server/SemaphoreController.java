package server;

import common.Interval;

import java.util.List;

import static common.Settings.WAIT_MILLISECONDS;

/**
 * Controller to advance traffic light of clients
 */
public class SemaphoreController {
    private final List<Client> clients;
    private ServerMediator mediator;

    public SemaphoreController(List<Client> clients, ServerMediator mediator) {
        this.clients = clients;
        this.mediator = mediator;
    }

    /**
     * start the process of advancing traffic lights
     */
    public void start() {
        Thread thread = new Thread(new Interval(WAIT_MILLISECONDS) {
            @Override
            protected void execute() {
                boolean on = mediator.isServerOn();

                for(Client client : clients) {
                    boolean changed = client.nextSecond(on);
                    if (changed) {
                        mediator.change(client);
                    }
                }
            }
        });

        thread.start();
    }
}
