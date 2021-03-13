package com.company;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.io.IOException;
import java.awt.Color;
public class gui {

    public static void main (String[] args) throws IOException {
        final JFrame frame = new MainFrame("Customer database");
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                frame.setSize(1280,720);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setBackground(Color.BLACK);
                //frame.setVisible(true);
                //frame.setPreferredSize(new Dimension(400, 300));
                //frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}