package com.halloween.view.gui;

import com.halloween.view.View;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameInfoScreen {

  private static final Font INFO_TEXT_FONT = new Font("Serif", Font.PLAIN, 13);
  private JPanel infoTextPanel;
  private JTextArea infoTextArea;
  private JTextArea userNameTextArea;
  private JPanel infoScreenButtonPanel;
  private JButton backStoryNextButton;
  private JButton startGameButton;

  private JButton instructionsNextButton;


  public GameInfoScreen() {
    infoTextPanel = createInfoTextPanel();
    infoScreenButtonPanel = createInfoScreenButtonPanel();
  }

  public JPanel createInfoTextPanel() {
    JPanel panel = new JPanel();
    panel.setBounds(200, 200, 1000, 600);
    panel.setBackground(Color.black);

    infoTextArea = new JTextArea(View.getImportantDisplay("backstory"));
    infoTextArea.setBounds(200, 200, 1000, 600);
    infoTextArea.setBackground(Color.black);
    infoTextArea.setForeground(Color.white);
    infoTextArea.setFont(INFO_TEXT_FONT);
    infoTextArea.setLineWrap(true);
    infoTextArea.setEditable(false);

    panel.add(infoTextArea);

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
    infoTextArea.setText(View.getImportantDisplay("instruction"));

    JPanel infoScreenButtonPanel = getInfoScreenButtonPanel();
    infoScreenButtonPanel.removeAll();
    infoScreenButtonPanel.add(instructionsNextButton);
  }

  public void buildGetUsernameScreen() {
    infoTextArea.setText("Enter Your Name: ");
    userNameTextArea = new JTextArea("     ");
    userNameTextArea.setBounds(300, 400, 800, 100);
    userNameTextArea.setFont(INFO_TEXT_FONT);
    infoTextPanel.add(userNameTextArea);

    JPanel infoScreenButtonPanel = getInfoScreenButtonPanel();
    infoScreenButtonPanel.removeAll();
    infoScreenButtonPanel.add(startGameButton);
  }

  public JPanel getInfoTextPanel() {
    return infoTextPanel;
  }

  public JTextArea getInfoTextArea() {
    return infoTextArea;
  }

  public JTextArea getUserNameTextArea() {
    return userNameTextArea;
  }

  public JPanel getInfoScreenButtonPanel() {
    return infoScreenButtonPanel;
  }

  public JButton getBackStoryNextButton() {
    return backStoryNextButton;
  }

  public JButton getStartGameButton() {
    return startGameButton;
  }


  public JButton getInstructionsNextButton() {
    return instructionsNextButton;
  }
}
