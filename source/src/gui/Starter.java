package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Starter {
    public static JFrame CreateAndShow(Container panel, Boolean mainWindow) {
        JFrame frame = new JFrame("Traffic Light");
        frame.setContentPane(panel);

        if (mainWindow) {
            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            setMenu(frame);
        }

        frame.pack();
        frame.setVisible(true);

        return frame;
    }

    private static void setMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = createMenu(menuBar, "File", KeyEvent.VK_F);
        createMenuItem(fileMenu, "Close", KeyEvent.VK_C);

        JMenu helpMenu = createMenu(menuBar, "Help", KeyEvent.VK_H);
        createHelpMenuItem(helpMenu, "Help", KeyEvent.VK_H);
        createHelpMenuItem(helpMenu, "Disclaimer", KeyEvent.VK_D);
        helpMenu.addSeparator();
        createHelpMenuItem(helpMenu, "About", KeyEvent.VK_A);

        frame.setJMenuBar(menuBar);
    }

    private static JMenu createMenu(JMenuBar menuBar, String text, int shortcut) {
        JMenu fileMenu = new JMenu(text);
        fileMenu.setMnemonic(shortcut);
        menuBar.add(fileMenu);
        return fileMenu;
    }

    private static void createHelpMenuItem(JMenu menu, String text, int shortcut) {
        JMenuItem menuItem = createMenuItem(menu, text, shortcut);
        menuItem.addActionListener(openHelp(text));
    }

    private static JMenuItem createMenuItem(JMenu menu, String text, int shortcut) {
        JMenuItem menuItem = new JMenuItem(text, shortcut);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(shortcut, InputEvent.ALT_MASK));
        menu.add(menuItem);
        return menuItem;
    }
    private static ActionListener openHelp(String text) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Starter.CreateAndShow(new Help(text).getPanel(), false);
            }
        };
    }
}
