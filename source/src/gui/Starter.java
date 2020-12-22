package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Starter {
    public static JFrame CreateAndShow(Container panel, Boolean exitOnClose) {
        JFrame frame = new JFrame("Traffic Light");
        frame.setContentPane(panel);

        if (exitOnClose) {
            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );
        }

        setMenu(frame);

        frame.pack();
        frame.setVisible(true);

        return frame;
    }

    private static void setMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = createMenu(menuBar, "File", KeyEvent.VK_F);
        createMenuItem(fileMenu, "Close", KeyEvent.VK_C);

        JMenu helpMenu = createMenu(menuBar, "Help", KeyEvent.VK_H);
        createMenuItem(helpMenu, "Help", KeyEvent.VK_H);
        createMenuItem(helpMenu, "Disclaimer", KeyEvent.VK_D);
        createMenuItem(helpMenu, "About", KeyEvent.VK_A);

        frame.setJMenuBar(menuBar);
    }

    private static JMenu createMenu(JMenuBar menuBar, String text, int shortcut) {
        JMenu fileMenu = new JMenu(text);
        fileMenu.setMnemonic(shortcut);
        menuBar.add(fileMenu);
        return fileMenu;
    }

    private static void createMenuItem(JMenu menu, String text, int shortcut) {
        JMenuItem close = new JMenuItem(text, shortcut);
        close.setAccelerator(KeyStroke.getKeyStroke(shortcut, InputEvent.ALT_MASK));
        menu.add(close);
    }
}
