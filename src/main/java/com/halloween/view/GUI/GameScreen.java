package com.halloween.view.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class GameScreen {

  private static final Font topButtonFont = new Font("Serif", Font.PLAIN, 40);
  private JPanel topPanel;
  private JPanel gridPanel;
  private JPanel mainPanel;
  private JButton helpButton;
  private JButton mapButton;
  private JButton musicButton;
  private JButton fxButton;
  private JButton saveGameButton;
  private JButton quitButton;
  private JLabel centerLabel;

  public GameScreen() {
    topPanel = createTopPanel();
    gridPanel = createGridPanel();
    mainPanel = createMainPanel();
    centerLabel = createCenterLabel();
  }

  public JPanel createTopPanel() {
    helpButton = new JButton("HELP");
    helpButton.setFont(topButtonFont);

    mapButton = new JButton("MAP");
    mapButton.setFont(topButtonFont);

    musicButton = new JButton("MUSIC");
    musicButton.setFont(topButtonFont);

    fxButton = new JButton("FX");
    fxButton.setFont(topButtonFont);

    saveGameButton = new JButton("SAVE GAME");
    saveGameButton.setFont(topButtonFont);

    quitButton = new JButton("QUIT GAME");
    quitButton.setBackground(Color.RED);
    quitButton.setOpaque(true);
    quitButton.setFont(topButtonFont);

    JPanel panel = new JPanel(); //Creates the top panel
    panel.add(helpButton);
    panel.add(mapButton);
    panel.add(musicButton);
    panel.add(fxButton);
    panel.add(saveGameButton);
    panel.add(quitButton);
    panel.setBorder(new LineBorder(Color.WHITE, 3)); //Sets the border
    panel.setBackground(Color.ORANGE); //Sets the background
    panel.setLayout(new FlowLayout(10)); //Aligns the layout
    panel.setBounds(100, 50, 1200, 100);
    return panel;
  }

  public JPanel createMainPanel() {
    JPanel panel = new JPanel();
    panel.setBorder(new LineBorder(Color.WHITE, 3));
    panel.setBounds(100, 150, 1200, 650);
    panel.setBackground(Color.CYAN);
    panel.add(getGridPanel());
    return panel;
  }
  
  public JLabel createCenterLabel() {
    JLabel label = new JLabel("Center Box", SwingConstants.CENTER);
    label.setOpaque(true);
    label.setBorder(new LineBorder(Color.WHITE, 3));
    label.setBounds(0, 550, 900, 300);
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

  public JButton getMusicButton() {
    return musicButton;
  }

  public JButton getFxButton() {
    return fxButton;
  }

  public JButton getSaveGameButton() {
    return saveGameButton;
  }

  public JButton getQuitButton() {
    return quitButton;
  }

}
