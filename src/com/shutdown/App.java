package com.shutdown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class App {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Shutdown Computer");
        frame.setSize(300, 100);

        JButton buttonSet = new JButton("Set");
        final JTextField textHours = new JTextField(15);
        final JTextField textMinutes = new JTextField(15);

        numOnly(textHours);
        numOnly(textMinutes);

        buttonSet.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    shutdown(Integer.parseInt(textHours.getText()) * 3600 + Integer.parseInt(textMinutes.getText()) * 60);
                    JOptionPane.showMessageDialog(null, "\n" +
                            "Your computer will be turned off in " + (Integer.parseInt(textHours.getText()) * 60) + Integer.parseInt(textMinutes.getText()) + " minutes. ");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

        });

        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(textHours);
        frame.getContentPane().add(textMinutes);
        frame.getContentPane().add(buttonSet);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void shutdown(Integer time) throws RuntimeException, IOException {
        String shutdownCommand;
        String operatingSystem = System.getProperty("os.name");

        if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem)) {
            shutdownCommand = "shutdown -h +" + time + "";
        } else if ("Windows 10".equals(operatingSystem)) {
            shutdownCommand = "shutdown.exe -s -t " + time + "";
        } else {
            throw new RuntimeException("Unsupported operating system.");
        }

        Runtime.getRuntime().exec(shutdownCommand);
        System.exit(0);
    }

    public static void numOnly(Object objSource) {
        ((Component) objSource).addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                String filterStr = "0123456789.";
                char c = (char) e.getKeyChar();
                if (filterStr.indexOf(c) < 0) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
    }

}
