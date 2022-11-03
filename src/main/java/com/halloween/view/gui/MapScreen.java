package com.halloween.view.gui;

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
    label.setBounds(80, 100, 1400, 500);

    backToGameMapScreenButton = new JButton("BACK TO GAME");
    backToGameMapScreenButton.setFont(BUTTON_FONT);
    backToGameMapScreenButton.setBounds(650, 650, 200, 75);
    backToGameMapScreenButton.addActionListener(event -> {
      System.out.println("this button should bring user back to game screen");
    });
  }

  public JLabel getMapScreen() {
    return label;
  }

  public JButton getBackToGameMapScreenButton() {
    return backToGameMapScreenButton;
  }

}
