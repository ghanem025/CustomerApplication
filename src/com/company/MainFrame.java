package com.company;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Container;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;


public class MainFrame extends JFrame {
    private DetailsPanel detailsPanel; // detail panel object
    boolean click = false;
    public MainFrame (String title){
        super(title);

// layout manager
        setLayout(new BorderLayout());
// Create Swing components
        final JTextArea textArea = new JTextArea();
        Font font = textArea.getFont();
        float size = font.getSize() + 12.0f;
        textArea.setFont( font.deriveFont(size) );
        textArea.setForeground(Color.BLUE);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JButton button = new JButton("Display Customers");

        detailsPanel = new DetailsPanel();
        detailsPanel.addDetailListener(new DetailListener(){//ActionListener for details panel
            // once we click the add button we created, it will tell the computer to display the information we have inputed
            public void detailEventoccured(DetailEvent event){
                String text = event.getText(); // using the getText method i made in the DetailEvent class , the computer can get the information we inputed
                textArea.setText(null);
                textArea.append(text);//program displays the information we have inputed.
            }
        });

        // Add Swing components to content pane
        Container a = getContentPane();

        a.add(textArea , BorderLayout.CENTER);
        a.add(button , BorderLayout.SOUTH);
        a.add(detailsPanel, BorderLayout.WEST);

        //Contact listener

        button.addActionListener(new ActionListener(){//ActionListener for bottom button
            // tells the computer when there button is clicked and what events to fire when clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                if(click == false){
                    try{
                        BufferedReader br = new BufferedReader(new FileReader("logic.txt"));
                        String line  = null;
                        while ((line = br.readLine())!= null) {
                            textArea.read(br, "File");//display the database
                        }

                    }
                    catch(IOException c){
                        c.printStackTrace();
                    }
                    click = true;
                    System.out.println("hi");
                }
                click = false;

            }
        });

    }
}