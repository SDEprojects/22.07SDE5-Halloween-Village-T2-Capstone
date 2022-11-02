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
  private JPanel titlePanel;
  private JPanel buttonsPanel;

  public TitleScreen() {
    titlePanel = createTitlePanel();
    buttonsPanel = createButtonsPanel();
  }

  public JPanel createTitlePanel() {
    JPanel titlePanel = new JPanel();
    titlePanel.setBounds(100, 100, 600, 150);
    titlePanel.setBackground(Color.black);

    JLabel titleLabel = new JLabel("HALLOWEEN VILLAGE");
    titleLabel.setForeground(Color.white);
    titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));

    // add label to title panel
    titlePanel.add(titleLabel);

    return titlePanel;
  }

  public JPanel createButtonsPanel() {
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setBounds(300, 350, 200, 250);
    buttonsPanel.setBackground(Color.black);

    JButton newGameButton = new JButton("NEW GAME");
    newGameButton.setFont(TITLE_BUTTON_FONT);
    newGameButton.addActionListener(e -> {
      System.out.println("BACKGROUND STORY");
    });

    JButton loadGameButton = new JButton("LOAD GAME");
    loadGameButton.setFont(TITLE_BUTTON_FONT);
    loadGameButton.addActionListener(e -> {
      // TODO: IMPLEMENT LOAD GAME
      System.out.println("IMPLEMENT LOAD GAME");
    });

    JButton quitButton = new JButton("QUIT");
    loadGameButton.setFont(TITLE_BUTTON_FONT);
    quitButton.addActionListener(e -> {
      // TODO: IMPLEMENT QUIT
      System.out.println("IMPLEMENT QUIT");
    });

    // add buttons to buttons panel
    buttonsPanel.add(newGameButton);
    buttonsPanel.add(loadGameButton);
    buttonsPanel.add(quitButton);

    return buttonsPanel;
  }

  public JPanel getTitlePanel() {
    return titlePanel;
  }

  public JPanel getButtonsPanel() {
    return buttonsPanel;
  }

}
