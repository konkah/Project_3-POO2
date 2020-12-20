package server;

import javax.swing.*;

public class TrafficLightServer {
    public static void main(String[] args) {
        JFrame frame = new JFrame("TrafficLightServerWindow");
        frame.setContentPane(TrafficLightServerWindow.Create());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
