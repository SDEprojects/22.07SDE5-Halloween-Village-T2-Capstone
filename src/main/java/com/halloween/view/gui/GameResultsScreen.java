package com.halloween.view.gui;

import com.halloween.view.View;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import com.halloween.controller.Controller;
import com.halloween.Main;

public class GameResultsScreen {

  private final Font BUTTON_FONT = new Font("Times New Roman", Font.PLAIN, 10);

  private JLabel label;

//  private JTextArea text;

  JButton backToGameHelpScreenButton;

  public GameResultsScreen() {
    label = new JLabel();
//    String winOrLoseImage = getcontroller.winOrLose();
//    label.setIcon(new ImageIcon("src/main/resources/small_map.png"));
    //add conditional showing one of the images

    label.setBounds(0, 0, 1400, 1000);

//    text = new JTextArea(View.getImportantDisplay("help"));
//    text.setBounds(250, 30, 1100, 500);
//    label.add(text);

    backToGameHelpScreenButton = new JButton("BACK TO GAME");
    backToGameHelpScreenButton.setFont(BUTTON_FONT);
    backToGameHelpScreenButton.setBounds(680, 600, 200, 50);
    label.add(backToGameHelpScreenButton);
  }

  public JButton getBackToGameGameResultsScreenButton() {
    return backToGameHelpScreenButton;
  }

  public JLabel getGameResultsScreen() {
    return label;
  }

//  public JTextArea getText() {
//    return text;
//  }

}
