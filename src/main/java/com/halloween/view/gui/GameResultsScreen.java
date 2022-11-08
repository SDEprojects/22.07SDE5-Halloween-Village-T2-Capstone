package com.halloween.view.gui;

import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameResultsScreen {

  private static final Font BUTTON_FONT = new Font("Serif", Font.PLAIN, 25);
  private static final ImageIcon WIN_IMAGE = new ImageIcon("src/main/resources/you-win.png");
  private static final ImageIcon LOSE_IMAGE = new ImageIcon("src/main/resources/you-lose.png");
  private JPanel gameResultsPanel;
  private JPanel gameResultImagePanel;
  private JPanel resultButtonspanel;
  private JLabel winLabel;
  private JLabel loseLabel;
  private JButton quitGameButton;

  public GameResultsScreen() {
    gameResultsPanel = createGameResultsPanel();
    gameResultImagePanel = createResultImagePanel();
    resultButtonspanel = createResultButtonsPanel();

    gameResultsPanel.add(gameResultImagePanel);
    gameResultsPanel.add(resultButtonspanel);
  }

  public JPanel createGameResultsPanel() {
    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 1400, 1000);
    return panel;
  }

  public JPanel createResultImagePanel() {
    JPanel resultImagePanel = new JPanel();
    resultImagePanel.setBounds(100, 50, 1000, 850);

    winLabel = new JLabel();
    winLabel.setBounds(200, 50, 800, 400);
    winLabel.setIcon(WIN_IMAGE);
    winLabel.setVisible(false);

    loseLabel = new JLabel();
    winLabel.setBounds(200, 50, 800, 400);
    loseLabel.setIcon(LOSE_IMAGE);
    loseLabel.setVisible(false);

    resultImagePanel.add(winLabel);
    resultImagePanel.add(loseLabel);
    return resultImagePanel;
  }

  public JPanel createResultButtonsPanel() {
    JPanel resultButtonsPanel = new JPanel();
    resultButtonsPanel.setBounds(500, 900, 400, 100);

    quitGameButton = new JButton("QUIT GAME");
    quitGameButton.setFont(BUTTON_FONT);
    quitGameButton.setBounds(600, 900, 200, 100);

    resultButtonsPanel.add(quitGameButton);
    return resultButtonsPanel;
  }

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

}
