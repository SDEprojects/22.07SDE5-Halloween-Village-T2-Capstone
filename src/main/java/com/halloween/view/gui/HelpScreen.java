package com.halloween.view.gui;

import com.halloween.view.View;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class HelpScreen {

  private final Font BUTTON_FONT = new Font("Times New Roman", Font.PLAIN, 10);

  private JLabel label;

  private JTextArea text;

  JButton backToGameHelpScreenButton;

  public HelpScreen() {
    label = new JLabel();
    label.setBounds(0, 0, 1400, 1000);

    text = new JTextArea(View.getImportantDisplay("help"));
    text.setBounds(250, 30, 1100, 500);
    label.add(text);

    backToGameHelpScreenButton = new JButton("BACK TO GAME");
    backToGameHelpScreenButton.setFont(BUTTON_FONT);
    backToGameHelpScreenButton.setBounds(680, 600, 200, 50);
    label.add(backToGameHelpScreenButton);
  }

  public JButton getBackToGameHelpScreenButton() {
    return backToGameHelpScreenButton;
  }

  public JLabel getHelpScreen() {
    return label;
  }

  public JTextArea getText() {
    return text;
  }

}
