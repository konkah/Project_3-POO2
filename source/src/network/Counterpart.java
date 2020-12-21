package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static common.Settings.*;

/**
 * Information about UDP connection
 */
public class Counterpart {
    private final InetAddress address;
    private final int port;

    /**
     * Creates the server counterpart
     * @throws UnknownHostException if server ip is not known
     */
    public Counterpart() throws UnknownHostException {
        this(InetAddress.getByName(SERVER_IP), SERVER_PORT);
    }

    /**
     * Creates a counterpart from a ip and port
     * @param address to communicate with
     * @param port to communicate with
     */
    public Counterpart(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    /**
     * @return ip to communicate with
     */
    public InetAddress getAddress() {
        return address;
    }

    /**
     * @return port to communicate with
     */
    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return address + ":" + port;
    }
}
