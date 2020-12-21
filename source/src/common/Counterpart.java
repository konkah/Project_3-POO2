package common;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static common.Settings.SERVER_IP;
import static common.Settings.SERVER_PORT;

public class Counterpart {
    private final InetAddress address;
    private final int port;

    public Counterpart() throws UnknownHostException {
        this(InetAddress.getByName(SERVER_IP), SERVER_PORT);
    }

    public Counterpart(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return address + ":" + port;
    }
}
