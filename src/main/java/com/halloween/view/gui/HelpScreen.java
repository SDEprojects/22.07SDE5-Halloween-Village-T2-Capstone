package com.halloween.view.gui;

import com.halloween.view.View;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class HelpScreen {

  private static final Font BUTTON_FONT = new Font("Serif", Font.PLAIN, 25);
  private static final Font HELP_TEXT_FONT = new Font("Serif", Font.PLAIN, 25);

  private JLabel label;

  private JTextArea text;

  JButton backToGameHelpScreenButton;

  public HelpScreen() {
    label = new JLabel();
    label.setBounds(0, 0, 1400, 1000);

    text = new JTextArea(View.getImportantDisplay("help"));
    text.setBounds(100, 200, 1200, 700);
    text.setFont(HELP_TEXT_FONT);
    label.add(text);

    backToGameHelpScreenButton = new JButton("BACK TO GAME");
    backToGameHelpScreenButton.setFont(BUTTON_FONT);
    backToGameHelpScreenButton.setBounds(600, 50, 200, 100);
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
