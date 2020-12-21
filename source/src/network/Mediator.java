package network;

public interface Mediator {
    void handleError(Exception error);
    void handleSuccessReceive(Counterpart counterpart, String message);
}
