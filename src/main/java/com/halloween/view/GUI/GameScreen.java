package com.halloween.view.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class GameScreen {

  JPanel topPanel;
  JPanel gridPanel;
  JPanel mainPanel;
  JButton helpButton;
  JButton mapButton;
  JButton saveGameButton;
  JButton quitButton;
  JLabel centerLabel;

  public GameScreen() {
    topPanel = createTopPanel();
    gridPanel = createGridPanel();
    mainPanel = createMainPanel();
    centerLabel = createCenterLabel();
  }

  public JPanel createTopPanel() {
    helpButton = new JButton("HELP");
    helpButton.setFont(TitleScreen.TITLE_BUTTON_FONT);

    mapButton = new JButton("MAP");
    mapButton.setFont(TitleScreen.TITLE_BUTTON_FONT);

    saveGameButton = new JButton("SAVE GAME");
    saveGameButton.setFont(TitleScreen.TITLE_BUTTON_FONT);


    quitButton = new JButton("QUIT GAME");
    quitButton.setFont(TitleScreen.TITLE_BUTTON_FONT);

    JPanel panel = new JPanel(); //Creates the top panel
    panel.add(helpButton);
    panel.add(mapButton);
    panel.add(saveGameButton);
    panel.add(quitButton);
    panel.setBorder(new LineBorder(Color.WHITE, 3)); //Sets the border
    panel.setBackground(Color.ORANGE); //Sets the background
    panel.setLayout(new FlowLayout(10)); //Aligns the layout
    panel.setBounds(100, 50, 1200, 70);
    return panel;
  }

  public JPanel createMainPanel() {
    JPanel panel = new JPanel();
    panel.setBorder(new LineBorder(Color.WHITE, 3));
    panel.setLayout(new FlowLayout(4, 4, 4));
    panel.setBounds(100, 120, 1200, 160);
    panel.setBackground(Color.CYAN);
    panel.add(getGridPanel());
    return panel;
  }

  public JPanel createGridPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4, 1, 5, 5));
    panel.setBounds(100, 280, 1200, 300);
    panel.setBorder(new LineBorder(Color.WHITE, 3));
    panel.setBackground(Color.RED);
    return panel;
  }

  public JLabel createCenterLabel() {
    JLabel label = new JLabel("Center Box", SwingConstants.CENTER);
    label.setOpaque(true);
    label.setBorder(new LineBorder(Color.WHITE, 3));
    label.setBounds(100, 580, 1200, 300);
    return label;
  }

  public JPanel getTopPanel() {
    return topPanel;
  }

  public JPanel getGridPanel() {
    return gridPanel;
  }

  public JPanel getMainPanel() {
    return mainPanel;
  }

  public JLabel getCenterLabel() {
    return centerLabel;
  }

  public JButton getHelpButton() {
    return helpButton;
  }

  public JButton getMapButton() {
    return mapButton;
  }

  public JButton getSaveGameButton() {
    return saveGameButton;
  }

  public JButton getQuitButton() {
    return quitButton;
  }

}
