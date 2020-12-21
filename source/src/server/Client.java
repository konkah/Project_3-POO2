package server;

import common.Settings;
import common.TrafficLightState;
import network.Counterpart;

public class Client {
    private String code;
    private Counterpart counterpart;
    private TrafficLightState currentState;
    private int secondsFromLastChange;

    public Client(String code, Counterpart counterpart, TrafficLightState state) {
        this.code = code;
        this.counterpart = counterpart;
        this.currentState = state;
    }

    public String getCode() {
        return code;
    }

    public Counterpart getCounterpart() {
        return counterpart;
    }

    public TrafficLightState getCurrentState() {
        return currentState;
    }

    public Boolean advance() {
        System.out.println(currentState + " for " + secondsFromLastChange + "sec.");
        if (secondsFromLastChange >= Settings.seconds.get(currentState)) {
            currentState = currentState.getNext();
            secondsFromLastChange = 0;
            return true;
        } else {
            secondsFromLastChange++;
            return false;
        }
    }
}
