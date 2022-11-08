package com.halloween.view.gui;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class MapScreen {

  private static final Font BUTTON_FONT = new Font("Serif", Font.PLAIN, 25);
  private JLabel label;
  JButton backToGameMapScreenButton;

  public MapScreen() throws IOException {
    label = new JLabel();
    InputStream stream = getClass().getClassLoader().getResourceAsStream("small_map.png");
    ImageIcon icon = new ImageIcon(ImageIO.read(stream));
    label.setIcon(icon);
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
