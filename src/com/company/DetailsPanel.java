package com.company;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.EventListenerList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;


public class DetailsPanel extends JPanel implements DocumentListener {
    public static Hashtable <Integer,String> hash = new Hashtable<Integer,String>();
    public static BufferedReader br;
    private EventListenerList listenerList = new EventListenerList();//created an object list for events
    private JTextField nameField , phoneField , priceField ,condField , findField;
    public JLabel nameLabel,phoneLabel,priceLabel,condLabel,findLabel,noteLabel ;
    JTextArea textArea;
    private JButton addbtn;
    private JButton find;
    @SuppressWarnings("deprecation")

    public DetailsPanel(){
        Dimension size = getPreferredSize();
        size.width = 700; // size
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Details"));
        // if (nameField.getText().equals("") || phoneField.getText().equals("") || condField.getText().equals("") || priceField.getText().equals("")){
        //addbtn.setEnabled(false);
        // }
        //  else {
        //     addbtn.setEnabled(true);
        //}
        // Contact event listener
        define();
        nameField.getDocument().addDocumentListener(this);
        phoneField.getDocument().addDocumentListener(this);
        condField.getDocument().addDocumentListener(this);
        priceField.getDocument().addDocumentListener(this);
        Panel();
        button();
        search();
        AddCustomer();
        Layout();

        // change method
    }
    public void Panel(){
        JPanel panel = new keeptoo.KGradientPanel();
        panel.setPreferredSize(new Dimension(300,400));
    }
    public void button(){
        addbtn = new keeptoo.KButton(); // create the "add" button\
        addbtn.setFont(new java.awt.Font("Roboto", 0, 12));
        addbtn.setText("Add Customer");
        addbtn.setPreferredSize(new Dimension(100,40));
        addbtn.setForeground(Color.WHITE);
        addbtn.setBackground(Color.RED);
        addbtn.setForeground(new java.awt.Color(23, 56, 100));
        addbtn.setBackground(new java.awt.Color(134,123,43));
        //addbtn.
    }
    private void search(){
        find = new JButton("Find");
        JButton addbtn2 = new JButton("Add"); // create the "add" button
        find.setPreferredSize(new Dimension ( 100,40));
        find.addActionListener(new ActionListener(){ // ActionListener for the Search button i created
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    load();
                    BufferedReader br = new BufferedReader(new FileReader("logic.txt"));
                    String name = findField.getText();
                    System.out.println(name);
                    String [] stat = null;
                    String n = null;
                    String line = null;
                    int i = 0;

                    if( hash.get(0) == null){
                        System.out.println("There are no customers in the database");
                    }
                    else{
                        while((line= br.readLine()) != null){
                            //hash.keys();
                            stat = hash.get(i).split(",");
                            i++;
                            n = stat[0];
                            if (stat[0].equals(name) ){
                                String text = "here are " + name + "'s files: " + Arrays.toString(stat);
                                commandDetailEvent(new DetailEvent(this,text));
                                //System.out.println(hash);
                                break;
                            }
                        }
                        if(!stat[0].equals(name) && hash.size() == i){
                            String msg = "Sorry there is no name " + name +  " in the database";
                            commandDetailEvent(new DetailEvent(this,msg));
                            //searchfile();
                        }
                    }
                }
                catch(IOException g) {
                }
            }

        });
    }
    public void AddCustomer(){
        addbtn.addActionListener(new ActionListener(){ // ActionListener for the "add" button i created
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().equals(null) || nameField.getText().length() <= 2) {
                    addbtn.setEnabled(false);
                    Windowerror();
                } else {
                    addbtn.setEnabled(true);

                    String name = nameField.getText(); // get the information we have inputed
                    String phone = phoneField.getText();
                    String price = priceField.getText();
                    String cond = condField.getText();
                    String date = "";
                    final String note = textArea.getText();
                    try {
                        FileWriter fstream = new FileWriter("logic.txt", true);
                        BufferedWriter out = new BufferedWriter(fstream);
                        date = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date());
                        out.newLine();
                        out.write(name + ",");
                        out.write(phone + ",");
                        out.write(price + ",");
                        out.write("Date: " + date + ",");
                        out.write(cond);
                        //out.write(".");
                        out.newLine();
                        out.write(note);
                        out.close();
                    } catch (IOException g) {

                    }

                    String text = name + "\n\n" + phone + "\n\n" + date + "\n\n" + price + "\n\n" + cond + "\n\n" + note; // combine them into one string
                    commandDetailEvent(new DetailEvent(this, text));// fire an event to take the inputed information , send it to the DetailEvent class and shoot it back to MainFrame for rendering
                    System.out.println(text);
                    nameField.setText(" ");
                    phoneField.setText(" ");
                    condField.setText(" ");
                    priceField.setText(" ");
                    textArea.setText("");
                }
            }
        });
    }

    public void define(){
        String font = "<html><span style='font-size:30px'>";
        nameLabel = new JLabel(font +"Name:"); // different  labels to render on the details panel
        phoneLabel = new JLabel(font + "Phone Model:");
        priceLabel = new JLabel(font+ "Service Charge:");
        condLabel = new JLabel(font + "Condition In:");
        findLabel = new JLabel(font + "Search :");
        noteLabel = new JLabel(font + "Note:");

        //text fields
        nameField = new JTextField(15); // fields for the users to input there information
        phoneField = new JTextField(15);
        priceField = new JTextField(15);
        condField = new JTextField(15);
        findField = new JTextField(15);

        textArea = new JTextArea(8,30);
        textArea.setFont(new Font("Serif", Font.CENTER_BASELINE, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    private void Windowerror(){
        JFrame frame = new JFrame("Name Error");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"Make sure you have entered a valid name","Name Error",JOptionPane.WARNING_MESSAGE);
    }

    private void Layout(){
        //GridBagLayout
        setLayout(new GridBagLayout());// lets you add controls using the GridBagConstraints class
        GridBagConstraints gc = new GridBagConstraints();// class where you can choose where you want each control to go
        //column one
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.5;
        gc.weighty = 0.5; //space of cell
//name
        gc.gridx = 0;
        gc.gridy = 0;
        add(nameLabel,gc);

//phone
        gc.gridy = 1 ;
        add(phoneLabel,gc);
//Price

        gc.gridy = 2;
        add(priceLabel,gc);

//Condition
        gc.gridy = 4 ;
        add(condLabel,gc);
// notes label
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weighty = 1;
        gc.gridx=0;
        gc.gridy = 5;
        add(noteLabel,gc);
        //Search label
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.1;
        gc.weighty = 0.1; //space of cell
        gc.gridx = 0;
        gc.gridy = 8;
        add(findLabel,gc);


        //column two
        gc.anchor = GridBagConstraints.LINE_START;
//name
        gc.gridx = 1 ;
        gc.gridy = 0;
        add(nameField,gc);
//phone
        gc.gridy = 1;
        add(phoneField,gc);

//price
        gc.gridy = 2;
        add(priceField,gc);
//Condition
        gc.gridy = 4;
        add(condField,gc);

        //last row
        gc.weighty = 1;
        //gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 1 ;
        gc.gridy = 7;
        add(addbtn,gc);

//Search Field
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 8;
        add(findField,gc);

//search button

        gc.weighty = 0.1;
        gc.gridx=1;
        gc.gridy = 9;
        add(find,gc);


// notes text Area
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weighty = 0.5;
        gc.gridx=1;
        gc.gridy = 5;
        add(textArea,gc);

    }

    public void commandDetailEvent(DetailEvent event) {

        Object [] listeners = listenerList.getListenerList();// create an object Array list called listener , we currently have one Event stored in this list
        for(int i = 0 ; i<listeners.length;i+=2) {
            System.out.println("hi");
            if(listeners[i]==DetailListener.class) {
                ((DetailListener)listeners[i+1]).detailEventoccured(event);
            }
        }
    }
    public void addDetailListener(DetailListener listener) {
        listenerList.add(DetailListener.class, listener);//Adds the listener as a listener of the specified type.

    }
    public void removeDetailListener(DetailListener listener) {
        listenerList.remove(DetailListener.class, listener);

    }
    public static void load()throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("logic.txt"));
        String line = null;
        int i =0;

        while((line= br.readLine()) != null){
            hash.put(i,line);
            i++;
        }
    }


    @Override
    public void insertUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        changed();
    }
    @Override
    public void removeUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        changed();
    }
    @Override
    public void changedUpdate(DocumentEvent e) {
        // TODO Auto-generated method stub
        changed();
    }
    public void changed() {
        if (phoneField.getText().equals("") || condField.getText().equals("")|| priceField.getText().equals("")|| nameField.getText().equals("")){
            addbtn.setEnabled(false);
        }
        else {
            addbtn.setEnabled(true);
        }
    }
}