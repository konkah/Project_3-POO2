package server;

import common.Interval;

import java.util.List;

import static common.Settings.WAIT_MILLISECONDS;

public class SemaphoreController {
    private final List<Client> clients;
    private ServerMediator mediator;

    public SemaphoreController(List<Client> clients, ServerMediator mediator) {
        this.clients = clients;
        this.mediator = mediator;
    }

    public void start() {
        Thread thread = new Thread(new Interval(WAIT_MILLISECONDS) {
            @Override
            protected void execute() {
                for(Client client : clients) {
                    boolean changed = client.advance();
                    mediator.change(client);
                }
            }
        });

        thread.start();
    }
}
