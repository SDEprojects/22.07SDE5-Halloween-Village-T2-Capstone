package com.halloween.view.GUI;

import com.halloween.controller.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class mapScreen {
  JFrame window;
  private final Font BUTTON_FONT = new Font("Times New Roman", Font.PLAIN, 10);

  public mapScreen(Game game) throws IOException {
    window = new JFrame();
    window.setSize(1500, 1500);
    window.setVisible(true);
//    window.setResizable(false);
//    window.setLocationRelativeTo(null);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of the application
    window.setLayout(null);
    window.getContentPane().setBackground(Color.black);

    JLabel label = new JLabel();
    label.setIcon(new ImageIcon("src/main/resources/small_map.png"));
    label.setBounds(80, 100, 1400, 500);

    JButton backToGameButton = new JButton("BACK TO GAME");
    backToGameButton.setFont(BUTTON_FONT);
    backToGameButton.setBounds(650, 650, 200, 75);
    backToGameButton.addActionListener(event -> {
      System.out.println("this button should bring user back to game screen");
    });

    window.add(label);
    window.add(backToGameButton);

  }
}
