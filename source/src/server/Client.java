package server;

import common.Settings;
import common.TrafficLightState;
import network.Counterpart;

/**
 * Properties of traffic light clients
 */
public class Client {
    private String code;
    private int position;
    private final Counterpart counterpart;
    private TrafficLightState currentState;
    private int secondsFromLastChange;

    public Client(String code, Counterpart counterpart, TrafficLightState state) {
        this.code = code;
        this.counterpart = counterpart;
        this.currentState = state;
    }

    /**
     * @return unique code of the client
     */
    public String getCode() {
        return code;
    }

    /**
     * @return parameters to communicate with the client
     */
    public Counterpart getCounterpart() {
        return counterpart;
    }

    /**
     * @return current light state of the client
     */
    public TrafficLightState getCurrentState() {
        return currentState;
    }

    /**
     * Handle the next second state of client
     * - advance if the time of the current traffic light is over
     * - else, add one more second to the time
     * @return if the traffic light changed
     */
    public Boolean nextSecond() {
        if (secondsFromLastChange >= Settings.seconds.get(currentState)) {
            currentState = currentState.getNext();
            secondsFromLastChange = 0;
            return true;
        } else {
            secondsFromLastChange++;
            return false;
        }
    }

    /**
     * Add position to the code of the client
     * @param position of the client in the clients list
     */
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return position+1 + ": " + code;
    }
}
