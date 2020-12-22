package gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static common.Settings.*;

public class Help {
    private JPanel panel;
    private JTextArea content;

    public Help(String fileName) {
        String path = getClass()
                .getResource("/" + fileName.toLowerCase() + ".txt")
                .getFile();

        File file = new File(path);
        StringBuilder content = new StringBuilder();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine())
                        .append("\n");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            content.append("Ops, could not find ")
                    .append(path);
        }

        String text = content.toString()
                .replace("{{SERVER_IP}}", SERVER_IP)
                .replace("{{SERVER_PORT}}", String.valueOf(SERVER_PORT))
                .replace("{{CLIENTS_LIMIT}}", String.valueOf(CLIENTS_LIMIT));

        this.content.setText(text);
        this.content.setBackground(panel.getBackground());
        this.content.setCaretPosition(0);
    }

    public Container getPanel() {
        return panel;
    }
}
