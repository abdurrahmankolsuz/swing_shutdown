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


        JButton buttonSet = new JButton("Set");
        buttonSet.setBounds(150,150,30,30);


        JPanel panel = new JPanel();
        panel.add( buttonSet );
        panel.setBounds(150,250,30,30);

        JFrame frame = new JFrame("Shutdown Computer");
        frame.setSize(300,300);



        JLabel jLabelHours = new JLabel();
        jLabelHours.setBounds(50,50,100,20);
        jLabelHours.setText("Hours");
        final JTextField textHours = new JTextField(15);
        textHours.setBounds(100,50,100,20);
        JLabel jLabelMinutes = new JLabel();
        jLabelMinutes.setBounds(50,100,100,20);
        jLabelMinutes.setText("Minutes");
        final JTextField textMinutes = new JTextField(15);
        textMinutes.setBounds(100,100,100,20);

        numOnly(textHours);
        numOnly(textMinutes);

        frame.getContentPane().add(jLabelHours);
        frame.getContentPane().add(textHours);
        frame.getContentPane().add(jLabelMinutes);
        frame.getContentPane().add(textMinutes);
        frame.getContentPane().add(panel,BorderLayout.CENTER);
        frame.getContentPane().setBackground(Color.pink);

        frame.setLocationRelativeTo( null );
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        buttonSet.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if(textHours.getText().equalsIgnoreCase("") || textMinutes.getText().equalsIgnoreCase(""))
                    JOptionPane.showMessageDialog(null, "\n" +
                            "Please Enter time!");
                else{
                    try {
                        JOptionPane.showMessageDialog(null, "\n" +
                                "Your computer will be turned off in " + (Integer.parseInt(textHours.getText()) * 60) + Integer.parseInt(textMinutes.getText()) + " minutes. ");
                        shutdown(Integer.parseInt(textHours.getText()) * 3600 + Integer.parseInt(textMinutes.getText()) * 60);

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }


            }

        });
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
