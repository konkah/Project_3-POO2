package network;

import java.io.IOException;
import java.net.*;

import static common.Settings.*;

/**
 * Class to manage generic UDP communication
 */
public abstract class NetworkManager {
    private final Mediator mediator;
    private Boolean initialized;
    private DatagramSocket socket;
    private Counterpart server;

    protected NetworkManager(Boolean isServer, Mediator mediator) {
        this.mediator = mediator;
        try {
            server = new Counterpart();
            socket = getSocket(isServer);
            start();
            initialized = true;
        } catch (SocketException | UnknownHostException e) {
            mediator.handleError(e);
            initialized = false;
        }
    }

    private DatagramSocket getSocket(Boolean isServer) throws SocketException {
        if (isServer) {
            return new DatagramSocket(server.getPort());
        } else {
            return new DatagramSocket();
        }
    }

    private void start() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    receive();
                }
            }
        });

        thread.start();
    }

    private void receive() {
        if (!initialized) {
            mediator.handleError(
                    new InstantiationException("Could not initialize communication")
            );
            return;
        }

        byte[] buffer = new byte[BUFFER_SIZE];
        DatagramPacket packet = new DatagramPacket(buffer, BUFFER_SIZE);

        try {
            socket.receive(packet);

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            Counterpart counterpart = new Counterpart(address, port);

            String message = new String(packet.getData());

            mediator.handleSuccessReceive(counterpart, message);
        } catch (IOException e) {
            mediator.handleError(e);
        }
    }

    protected void send(String message) {
        send(server, message);
    }

    protected void send(Counterpart counterpart, String message) {
        if (!initialized) {
            mediator.handleError(
                    new InstantiationException("Could not initialize communication")
            );
            return;
        }

        if (message.length() > BUFFER_SIZE) {
            mediator.handleError(
                    new IllegalArgumentException("Message must have up to " + BUFFER_SIZE + " characters")
            );
            return;
        }

        while(message.length() < BUFFER_SIZE) {
            message = message + " ";
        }

        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(
                buffer, BUFFER_SIZE,
                counterpart.getAddress(),
                counterpart.getPort()
        );

        try {
            socket.send(packet);
        } catch (IOException e) {
            mediator.handleError(e);
        }
    }
}
