package com.halloween.view.gui;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class MapScreen {

  private static final Font BUTTON_FONT = new Font("Serif", Font.PLAIN, 25);
  private JLabel label;

  JButton backToGameMapScreenButton;

  public MapScreen() {
    label = new JLabel();
    label.setIcon(new ImageIcon("src/main/resources/small_map.png"));
    label.setBounds(0, 0, 1400, 900);

    backToGameMapScreenButton = new JButton("BACK TO GAME");
    backToGameMapScreenButton.setFont(BUTTON_FONT);
    backToGameMapScreenButton.setBounds(600, 50, 200, 100);
    label.add(backToGameMapScreenButton);
  }

  public JLabel getMapScreen() {
    return label;
  }

  public JButton getBackToGameMapScreenButton() {
    return backToGameMapScreenButton;
  }

}
