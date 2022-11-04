package com.halloween.view.GUI;

import com.halloween.view.View;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameInfoScreen {

  private static final Font INFO_TEXT_FONT = new Font("Serif", Font.PLAIN, 30);
  private JPanel infoTextPanel;
  private JTextArea textArea;
  private JPanel infoScreenButtonPanel;
  private JButton backStoryNextButton;
  private JButton instructionsNextButton;
  private JButton startGameButton;

  public GameInfoScreen() {
    infoTextPanel = createInfoTextPanel();
    infoScreenButtonPanel = createInfoScreenButtonPanel();
  }

  public JPanel createInfoTextPanel() {
    JPanel panel = new JPanel();
    panel.setBounds(200, 200, 1000, 600);
    panel.setBackground(Color.black);

    textArea = new JTextArea(View.getImportantDisplay("backstory"));
    textArea.setBounds(200, 200, 1000, 600);
    textArea.setBackground(Color.black);
    textArea.setForeground(Color.white);
    textArea.setFont(INFO_TEXT_FONT);
    textArea.setLineWrap(true);

    panel.add(textArea);

    return panel;
  }

  public JPanel createInfoScreenButtonPanel() {
    JPanel panel = new JPanel();
    panel.setBounds(550, 800, 300, 100);
    panel.setBackground(Color.black);

    backStoryNextButton = new JButton("NEXT");
    backStoryNextButton.setFont(TitleScreen.TITLE_BUTTON_FONT);

    instructionsNextButton = new JButton("NEXT");
    instructionsNextButton.setFont(TitleScreen.TITLE_BUTTON_FONT);

    startGameButton = new JButton("START GAME");
    startGameButton.setFont(TitleScreen.TITLE_BUTTON_FONT);

    panel.add(backStoryNextButton);

    return panel;
  }

  public void buildInstructionsScreen() {
    textArea.setText(View.getImportantDisplay("instruction"));

    JPanel infoScreenButtonPanel = getInfoScreenButtonPanel();
    infoScreenButtonPanel.removeAll();
    infoScreenButtonPanel.add(instructionsNextButton);
  }

  public void buildGetUsernameScreen() {
    textArea.setText("Enter Your Name: ");

    JPanel infoScreenButtonPanel = getInfoScreenButtonPanel();
    infoScreenButtonPanel.removeAll();
    infoScreenButtonPanel.add(startGameButton);
  }

  public JPanel getInfoTextPanel() {
    return infoTextPanel;
  }

  public JTextArea getTextArea() {
    return textArea;
  }

  public JPanel getInfoScreenButtonPanel() {
    return infoScreenButtonPanel;
  }

  public JButton getBackStoryNextButton() {
    return backStoryNextButton;
  }

  public JButton getInstructionsNextButton() {
    return instructionsNextButton;
  }

  public JButton getStartGameButton() {
    return startGameButton;
  }

}
