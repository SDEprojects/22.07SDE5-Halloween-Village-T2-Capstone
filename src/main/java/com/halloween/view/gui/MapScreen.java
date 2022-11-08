package com.halloween.view.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class MapScreen {

  private final Font BUTTON_FONT = new Font("Times New Roman", Font.PLAIN, 10);
  private JLabel label;

  JButton backToGameMapScreenButton;

  public MapScreen() {
    label = new JLabel();
    label.setIcon(new ImageIcon("src/main/resources/small_map.png"));
    label.setBounds(20, 100, 1400, 500);

    backToGameMapScreenButton = new JButton("BACK TO GAME");
    backToGameMapScreenButton.setFont(BUTTON_FONT);
    backToGameMapScreenButton.setBounds(620, 450, 100, 50);
    backToGameMapScreenButton.setBackground(Color.black);
    label.add(backToGameMapScreenButton);
  }

  public JLabel getMapScreen() {
    return label;
  }

  public JButton getBackToGameMapScreenButton() {
    return backToGameMapScreenButton;
  }

}
