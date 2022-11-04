package com.halloween.view.GUI;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class MapScreen {

  private final Font BUTTON_FONT = new Font("Times New Roman", Font.PLAIN, 10);
  private JLabel label;

  public MapScreen() {
    label = new JLabel();
    label.setIcon(new ImageIcon("src/main/resources/small_map.png"));
    label.setBounds(80, 100, 1400, 500);

    JButton backToGameButton = new JButton("BACK TO GAME");
    backToGameButton.setFont(BUTTON_FONT);
    backToGameButton.setBounds(650, 650, 200, 75);
    backToGameButton.addActionListener(event -> {
      System.out.println("this button should bring user back to game screen");
    });
  }

  public JLabel getMapScreen() {
    return label;
  }

}
