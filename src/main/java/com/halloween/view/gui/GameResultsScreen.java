package com.halloween.view.gui;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameResultsScreen {

  private static final Font BUTTON_FONT = new Font("Times New Roman", Font.PLAIN, 10);
  private static final ImageIcon WIN_IMAGE = new ImageIcon("src/main/resources/you-win.png");
  private static final ImageIcon LOSE_IMAGE = new ImageIcon("src/main/resources/you-lose.png");
  private JPanel gameResultsPanel;
  private JLabel winLabel;
  private JLabel loseLabel;
//  private JTextArea text;
  JButton quitGameButton;

  public GameResultsScreen() {
    gameResultsPanel = new JPanel();
    gameResultsPanel.setBounds(0, 0, 1400, 1000);

    winLabel = new JLabel();
    winLabel.setBounds(0, 0, 1400, 1000);
    winLabel.setIcon(WIN_IMAGE);
    winLabel.setVisible(false);

    loseLabel = new JLabel();
    loseLabel.setBounds(0, 0, 1400, 1000);
    loseLabel.setIcon(LOSE_IMAGE);
    loseLabel.setVisible(false);

    gameResultsPanel.add(winLabel);
    gameResultsPanel.add(loseLabel);

//    text = new JTextArea(View.getImportantDisplay("help"));
//    text.setBounds(250, 30, 1100, 500);
//    label.add(text);

    quitGameButton = new JButton("QUIT GAME");
    quitGameButton.setFont(BUTTON_FONT);
    quitGameButton.setBounds(680, 600, 200, 50);
    gameResultsPanel.add(quitGameButton);
  }

//  public JButton getBackToGameGameResultsScreenButton() {
//    return backToGameHelpScreenButton;
//  }

  public JButton getQuitGameButton() {
    return quitGameButton;
  }

  public JPanel getGameResultsPanel() {
    return gameResultsPanel;
  }

  public JLabel getWinLabel() {
    return winLabel;
  }

  public JLabel getLoseLabel() {
    return loseLabel;
  }

  //  public JTextArea getText() {
//    return text;
//  }

}
