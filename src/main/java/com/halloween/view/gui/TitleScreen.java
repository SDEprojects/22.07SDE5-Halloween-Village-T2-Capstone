package com.halloween.view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleScreen {

  public static final Font GAME_TITLE_FONT = new Font("Serif", Font.BOLD, 80);
  public static final Font TITLE_BUTTON_FONT = new Font("Serif", Font.PLAIN, 40);
  private JPanel titlePanel;
  private JPanel titleScreenButtonsPanel;
  private JButton newGameButton;
  private JButton loadGameButton;
  private JButton quitButton;

  public TitleScreen() {
    titlePanel = createTitlePanel();
    titleScreenButtonsPanel = createButtonsPanel();
  }

  public JPanel createTitlePanel() {
    JPanel panel = new JPanel();
    panel.setBounds(200, 200, 1000, 200);
    panel.setBackground(Color.black);

    JLabel titleLabel = new JLabel("HALLOWEEN VILLAGE");
    titleLabel.setForeground(Color.white);
    titleLabel.setFont(GAME_TITLE_FONT);

    // add label to title panel
    panel.add(titleLabel);

    return panel;
  }

    public JPanel createButtonsPanel() {
      JPanel panel = new JPanel();
      panel.setBounds(550, 550, 300, 400);
      panel.setBackground(Color.black);

      newGameButton = new JButton("NEW GAME");
      newGameButton.setFont(TITLE_BUTTON_FONT);

      loadGameButton = new JButton("LOAD GAME");
      loadGameButton.setFont(TITLE_BUTTON_FONT);

      quitButton = new JButton("QUIT GAME");
      quitButton.setFont(TITLE_BUTTON_FONT);

      // add buttons to buttons panel
      panel.add(newGameButton);
      panel.add(loadGameButton);
      panel.add(quitButton);

      return panel;
    }

    public JPanel getTitlePanel() {
      return titlePanel;
    }

    public JPanel getTitleScreenButtonsPanel() {
      return titleScreenButtonsPanel;
    }

    public JButton getNewGameButton() {
      return newGameButton;
    }

    public JButton getLoadGameButton() {
      return loadGameButton;
    }

    public JButton getQuitButton() {
      return quitButton;
    }

  }
