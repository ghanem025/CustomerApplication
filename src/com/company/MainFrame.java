package com.company;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainFrame extends JFrame {
    private DetailsPanel detailsPanel; // detail panel object
    private keeptoo.KButton button;
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
        button = new keeptoo.KButton(); // create the "add" button\
        button.setText("Display Customers");
        button.setFont(new java.awt.Font("Roboto", Font.PLAIN, 12));
        button.setkForeGround(new Color(75, 75, 75));
        button.setkHoverForeGround(new Color(75,75,75));
        button.setkHoverEndColor(new Color(0,0,0));
        button.setkHoverColor(new Color(0,0,0));
        button.setkHoverStartColor(new Color(0,191,255));


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
        textArea.setBounds(0,0,200,50);
        a.add(textArea);
        button.setBounds(0,0,50,50);
        a.add(button , BorderLayout.EAST);
        a.add(detailsPanel, BorderLayout.NORTH);

        //Contact listener

        button.addActionListener(new ActionListener(){//ActionListener for bottom button
            // tells the computer when there button is clicked and what events to fire when clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                if(click == false){
                    try{
                        BufferedReader br = new BufferedReader(new FileReader("customers.txt"));
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