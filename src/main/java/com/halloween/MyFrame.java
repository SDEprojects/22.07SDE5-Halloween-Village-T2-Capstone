package com.halloween;

import com.halloween.controller.Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class MyFrame extends JFrame {

  JButton newGameButton;  //Global component of button
  JButton loadGameButton;
  JButton saveGameButton;
  JButton helpButton;
  JButton quitButton;
  JButton mapButton;

  public MyFrame(Game game) {
    newGameButton = new JButton("NEW GAME");
    newGameButton.setBounds(200, 100, 100, 50);
    newGameButton.addActionListener(e -> System.out.println("Wanna Play a New Game?"));

    loadGameButton = new JButton("LOAD GAME");
    loadGameButton.setBounds(200, 100, 100, 50);
    loadGameButton.addActionListener(e -> game.loadGame());

    saveGameButton = new JButton("SAVE GAME");
    saveGameButton.setBounds(200, 100, 100, 50);
    saveGameButton.addActionListener(e -> game.saveGame());

    helpButton = new JButton("HELP");
    helpButton.setBounds(200, 100, 100, 50);
    helpButton.addActionListener(e -> System.out.println("Here You Go"));

    quitButton = new JButton("QUIT");
    quitButton.setBounds(200, 100, 100, 50);
    quitButton.addActionListener(e -> System.out.println("Leaving So Soon?"));

    this.setTitle("HALLOWEEN VILLAGE"); //Sets the title of the frame
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of the application
    //this.setResizable(false); //present frame from being resized
    this.setSize(600, 600); //sets the x-dimensions, and y-dimensions of the frame
    this.setVisible(true); //makes frame visible

    Container mainContainer = this.getContentPane();
    mainContainer.setLayout(new BorderLayout(8, 6));
    mainContainer.setBackground(Color.ORANGE);
    this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
//
//    ImageIcon image = new ImageIcon("./resource/Halloween_Village_Logo.png");
//    this.setIconImage(image.getImage());  //Change icon of the frame
//    this.getContentPane().setBackground(new Color(0, 51, 153)); //change color of the background

    /*
      TOP PANEL with Buttons
     */
    JPanel topPanel = new JPanel(); //Creates the top panel
    topPanel.setBorder(new LineBorder(Color.BLACK, 3)); //Sets the border
    topPanel.setBackground(Color.ORANGE); //Sets the background
    topPanel.setLayout(new FlowLayout(5)); //Aligns the layout
    topPanel.setBounds(100, 100, 100, 100);
    topPanel.add(newGameButton);
    topPanel.add(loadGameButton);
    topPanel.add(saveGameButton);
    topPanel.add(helpButton);
    topPanel.add(quitButton);
    mainContainer.add(topPanel, BorderLayout.NORTH);

    //Middle Panel
    JPanel middlePanel = new JPanel();
    middlePanel.setBorder(new LineBorder(Color.BLACK, 3));
    middlePanel.setLayout(new FlowLayout(4, 4, 4));
    middlePanel.setBackground(Color.CYAN);

    //Grid Layout
    JPanel gridPanel = new JPanel();
    gridPanel.setLayout(new GridLayout(4, 1, 5, 5));
    gridPanel.setBorder(new LineBorder(Color.BLACK, 3));
    gridPanel.setBackground(Color.RED);
    gridPanel.add(saveGameButton);

    JLabel label = new JLabel("Center Box", SwingConstants.CENTER);
    label.setOpaque(true);
    label.setBorder(new LineBorder(Color.BLACK, 3));

    middlePanel.add(gridPanel);
    mainContainer.add(label);
    mainContainer.add(middlePanel, BorderLayout.WEST);
  }

}
