package com.halloween.view.GUI;

import com.halloween.controller.Game;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TitleScreen {

  private final Font TITLE_BUTTON_FONT = new Font("Times New Roman", Font.PLAIN, 25);
  Container container;
  JButton newGameButton;
  JButton loadGameButton;
  JButton quitButton;

  public TitleScreen(Game game) {
    JFrame window = new JFrame();
    window.setSize(800, 600); //sets the x-dimensions, and y-dimensions of the frame
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of the application
    //this.setResizable(false); //present frame from being resized
    window.setLayout(null);
    window.getContentPane().setBackground(Color.black);
    container = window.getContentPane();

    JPanel titlePanel = new JPanel();
    titlePanel.setBounds(100, 100, 600, 150);
    titlePanel.setBackground(Color.black);

    JLabel titleLabel = new JLabel("HALLOWEEN VILLAGE");
    titleLabel.setForeground(Color.white);
    titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setBounds(300, 350, 200, 250);
    buttonsPanel.setBackground(Color.black);

    newGameButton = new JButton("NEW GAME");
    newGameButton.setFont(TITLE_BUTTON_FONT);
    newGameButton.addActionListener(e -> {
      System.out.println("BACKGROUND STORY");
    });

    loadGameButton = new JButton("LOAD GAME");
    loadGameButton.setFont(TITLE_BUTTON_FONT);
    loadGameButton.addActionListener(e -> game.loadGame());

    quitButton = new JButton("QUIT");
    loadGameButton.setFont(TITLE_BUTTON_FONT);
    quitButton.addActionListener(e -> game.quitGame());

    // add label to title panel
    titlePanel.add(titleLabel);

    // add buttons to buttons panel
    buttonsPanel.add(newGameButton);
    buttonsPanel.add(loadGameButton);
    buttonsPanel.add(quitButton);

    // add all panels to the container
    container.add(titlePanel);
    container.add(buttonsPanel);

    window.setVisible(true); //makes frame visible
    window.repaint();

//    Container mainContainer = this.getContentPane();
//    mainContainer.setLayout(new BorderLayout(8, 6));
//    mainContainer.setBackground(Color.ORANGE);
//    this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
////
////    ImageIcon image = new ImageIcon("./resource/Halloween_Village_Logo.png");
////    this.setIconImage(image.getImage());  //Change icon of the frame
////    this.getContentPane().setBackground(new Color(0, 51, 153)); //change color of the background
  }

  public void createGameScreen() {
    JPanel mainTextPanel = new JPanel();
    mainTextPanel.setBounds(100, 100, 600, 250);
    mainTextPanel.setBackground(Color.blue);
    container.add(mainTextPanel);

    JTextArea mainTextArea = new JTextArea();
    mainTextArea.setBounds(100, 100, 600, 250);
    mainTextArea.setBackground(Color.black);
    mainTextArea.setForeground(Color.white);
    mainTextArea.setFont(TITLE_BUTTON_FONT);
    mainTextArea.setLineWrap(true);

    mainTextPanel.add(mainTextArea);
  }

}
