package com.hendris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {

        //        Thread.sleep(2000);
        String de = "There is a easy way...";
        String para = "Existe um caminho fácil...";
        if (args.length > 0) {
            de = new String(args[0].getBytes(), "UTF-8");
            para = new String(args[1].getBytes(), "UTF-8");

        }

        JFrame frame = new JFrame();
        frame.setTitle("Translate");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        createComponentes(frame, de, para);
        setLocation(frame);
        frame.setVisible(true);


    }

    private static void createComponentes (final JFrame frame, final String de, final String para) {
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

        JTextArea deLabel = new JTextArea(de);
        deLabel.setLineWrap(true);
        JTextArea paraLabel = new JTextArea(para);
        paraLabel.setLineWrap(true);

        listPane.add(deLabel);
        listPane.add(Box.createRigidArea(new Dimension(0,20)));
        listPane.add(paraLabel);
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed (final ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        //Lay out the buttons from left to right.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(okButton);


        //Put everything together, using the content pane's BorderLayout.
        Container contentPane = frame.getContentPane();
        contentPane.add(listPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.PAGE_END);
    }

    /*public static void setLocation( int screen, JFrame frame ) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        if( screen > -1 && screen < gd.length ) {
            frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());
        } else if( gd.length > 0 ) {
            frame.setLocation(gd[0].getDefaultConfiguration().getBounds().x, frame.getY());
        } else {
            throw new RuntimeException( "No Screens Found" );
        }
    }*/

    public static void setLocation (JFrame frame) {
        final GraphicsDevice graphicsDevice = getMonitorWhereMouseIs();
        final Rectangle bounds = graphicsDevice.getDefaultConfiguration().getBounds();

        int x = (int) ((bounds.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((bounds.getHeight() - frame.getHeight()) / 2);

        frame.setLocation(x + graphicsDevice.getDefaultConfiguration().getBounds().x, y);
    }

    public static GraphicsDevice getMonitorWhereMouseIs() {
        Point location = MouseInfo.getPointerInfo().getLocation();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        for (int j = 0; j < gd.length; j++) {
            if (gd[j].getDefaultConfiguration().getBounds().contains(location)) {
                return gd[j];
            }

        }
        return null;
    }
}
