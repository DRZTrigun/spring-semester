package geekbrains.client.swing;

import javax.swing.*;

public class ChatSwingApp {

    private static geekbrains.client.swing.ChatMainWindow chatMainWindow;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                chatMainWindow = new ChatMainWindow();
            }
        });
    }
}
