package com.halloween;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MyFrame extends JFrame {

  JButton button1;  //Global component of button
  JButton button2;
  JButton button3;
  JButton button4;
  JButton button5;
  JButton button6;
  JButton button7;
  JButton button8;

  MyFrame() {
    //JFrame = a GUI window to add components to

    button1 = new JButton("NEW GAME");
    button1.setBounds(200, 100, 100, 50);
    button1.addActionListener(e -> System.out.println("Wanna Play a New Game?"));

    button2 = new JButton("LOAD GAME");
    button2.setBounds(200, 100, 100, 50);
    button2.addActionListener(e -> System.out.println("Back in Action"));

    button3 = new JButton("HELP");
    button3.setBounds(200, 100, 100, 50);
    button3.addActionListener(e -> System.out.println("Here You Go"));

    button4 = new JButton("QUIT");
    button4.setBounds(200, 100, 100, 50);
    button4.addActionListener(e -> System.out.println("Leaving So Soon?"));

    button5 = new JButton("SAVE GAME");
    button5.setBounds(200, 100, 100, 50);
    button5.addActionListener(e -> System.out.println("Come Back"));

    this.setTitle("HALLOWEEN VILLAGE"); //Sets the title of the frame
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of the application
    //this.setResizable(false); //present frame from being resized
    this.setSize(600, 600); //sets the x-dimensions, and y-dimensions of the frame
    this.setVisible(true); //makes frame visible

    Container mainContainer = this.getContentPane();
    mainContainer.setLayout(new BorderLayout(8,6));
    mainContainer.setBackground(Color.ORANGE);
    this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.BLACK));
//
//    ImageIcon image = new ImageIcon("./resource/Halloween_Village_Logo.png");
//    this.setIconImage(image.getImage());  //Change icon of the frame
//    this.getContentPane().setBackground(new Color(0, 51, 153)); //change color of the background

//    //TOP PANEL with Buttons
    JPanel topPanel = new JPanel(); //Creates the top panel
    topPanel.setBorder(new LineBorder(Color.BLACK, 3)); //Sets the border
    topPanel.setBackground(Color.ORANGE); //Sets the background
    topPanel.setLayout(new FlowLayout(5)); //Aligns the layout
    topPanel.setBounds(100, 100, 100, 100);
    topPanel.add(button1);
    topPanel.add(button2);
    topPanel.add(button3);
    topPanel.add(button4);
    mainContainer.add(topPanel, BorderLayout.NORTH);

    //Middle Panel
    JPanel middlePanel = new JPanel();
    middlePanel.setBorder(new LineBorder(Color.BLACK, 3));
    middlePanel.setLayout(new FlowLayout(4,4,4));
    middlePanel.setBackground(Color.CYAN);

    //Grid Layout
    JPanel gridPanel = new JPanel();
    gridPanel.setLayout( new GridLayout(4,1,5,5));
    gridPanel.setBorder(new LineBorder(Color.BLACK, 3));
    gridPanel.setBackground(Color.RED);
    gridPanel.add(button5);

    JLabel label =new JLabel("Center Box", SwingConstants.CENTER);
    label.setOpaque(true);
    label.setBorder(new LineBorder(Color.BLACK, 3));

    middlePanel.add(gridPanel);
    mainContainer.add(label);
    mainContainer.add(middlePanel, BorderLayout.WEST);
  }

}
