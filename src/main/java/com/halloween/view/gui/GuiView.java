package com.halloween.view.gui;

import com.halloween.controller.Game;
import com.halloween.model.House;
import com.halloween.model.Neighborhood;
import com.halloween.model.Player;
import com.halloween.model.State;
import com.halloween.view.View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GuiView {

  JFrame window;
  private Container container;
  private TitleScreen titleScreen;
  private GameInfoScreen gameInfoScreen;
  private GameScreen gameScreen;
  private MapScreen mapScreen;
  private HelpScreen helpScreen;
  private GameResultsScreen gameResultsScreen;

  public GuiView() {
    window = createWindow();
    container = window.getContentPane();
    titleScreen = new TitleScreen();
    gameInfoScreen = new GameInfoScreen();
    gameScreen = new GameScreen();
    mapScreen = new MapScreen();
    helpScreen = new HelpScreen();
    gameResultsScreen = new GameResultsScreen();
  }

  public JFrame createWindow() {
    JFrame window = new JFrame("Halloween Village");
    window.setSize(1400, 1000); //sets the x-dimensions, and y-dimensions of the frame
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of the application
    window.setResizable(false); //present frame from being resized
    window.setLayout(null);
    window.getContentPane().setBackground(Color.black);
    window.setVisible(true); //makes frame visible
    return window;
  }

  public void displayTitleScreen() {
    container.add(titleScreen.getTitlePanel());
    container.add(titleScreen.getTitleScreenButtonsPanel());
    container.repaint();
    container.revalidate();
  }

  public void displayBackgroundStoryScreen() {
    container.removeAll();
    container.add(getGameInfoScreen().getInfoTextPanel());
    container.add(getGameInfoScreen().getInfoScreenButtonPanel());
    container.repaint();
    container.revalidate();
  }

  public void displayInstructionsScreen() {
    getGameInfoScreen().buildInstructionsScreen();
    container.repaint();
    container.revalidate();
  }

//  public void displayGetUsernameScreen() {
//    getGameInfoScreen().buildGetUsernameScreen();
//    container.repaint();
//    container.revalidate();
//  }

  public void displayGameScreen(Player player, Neighborhood neighborhood) {
    container.removeAll();
    container.add(getGameScreen().getTopPanel(), BorderLayout.NORTH);
    container.add(getGameScreen().getSidePanel(), BorderLayout.WEST);
    container.add(getGameScreen().getMainPanel(), BorderLayout.CENTER);
    container.add(getGameScreen().getBottomPanel(), BorderLayout.SOUTH);
    updateGameScreenMainPanel(player, neighborhood);
    updateGameScreenSidePanel(player);
    updateGameScreenBottomPanel(player, neighborhood);

    container.revalidate();
    container.repaint();
  }

  public void updateGameScreenMainPanel(Player player, Neighborhood neighborhood) {
    String playerPosition = player.getPosition();
    String[] residents = neighborhood.getNeighborhood().get(playerPosition).getResidents();

    getGameScreen().getLocationLabel().setText("Current Location:\n" + playerPosition);
    getGameScreen().getNpcLabel().setText("Resident(s):\t" + Arrays.toString(residents));

    if (neighborhood.getNeighborhood().get(playerPosition).isKnocked()) {
      getGameScreen().getGameTextArea().setText(View.getGreetings(playerPosition));
      getGameScreen().getGameTextArea().setVisible(true);
    } else {
      getGameScreen().getGameTextArea().setVisible(false);
    }

    container.revalidate();
    container.repaint();
  }

  public void updateGameScreenSidePanel(Player player) {
    getGameScreen().getInventoryTextArea().setText("Inventory:\n" + player.getItems().toString());
    getGameScreen().getRemainingMovesLabel()
        .setText("Remaining Moves:\t" + player.getUserMovesCounter());

    container.revalidate();
    container.repaint();
  }

  public void displayGameResult(Game game) {
    container.removeAll();
    container.add(getGameResultsScreen().getGameResultsPanel());
    if (game.getState().equals(State.WIN)) {
      getGameResultsScreen().getWinLabel().setVisible(true);
    } else if (game.getState().equals(State.LOSE)) {
      gameResultsScreen.getLoseLabel().setVisible(true);
    }
    container.revalidate();
    container.repaint();
  }

  public void displayMapScreen() {
    container.removeAll();
    container.add(getMapScreen().getMapScreen());
    container.repaint();
    container.revalidate();
  }

  public void displayHelpScreen() {
    container.removeAll();
    container.add(getHelpScreen().getHelpScreen());
    container.repaint();
    container.revalidate();
  }

  public void displayLoadFailPane() {
    JOptionPane.showMessageDialog(null,
        "ERROR: FAILED TO LOAD GAME DATA.\nPLEASE START A NEW GAME.");
  }

  public void updateGameScreenBottomPanel(Player player,
      Neighborhood neighborhood) {
    JButton goNorthButton = getGameScreen().getGoNorthButton();
    JButton goEastButton = getGameScreen().getGoEastButton();
    JButton goSouthButton = getGameScreen().getGoSouthButton();
    JButton goWestButton = getGameScreen().getGoWestButton();

    String[] directions = new String[]{"north", "east", "south", "west"};
    JButton[] buttons = new JButton[]{goNorthButton, goEastButton, goSouthButton, goWestButton};
    House currentPosition = neighborhood.getNeighborhood().get(player.getPosition());

    for (int i = 0; i < directions.length; i++) {
      if (neighborhood.isValidDirection(directions[i], currentPosition).isEmpty()) {
        buttons[i].setVisible(false);
      } else {
        buttons[i].setVisible(true);
      }
    }
  }

  /*
    GETTER & SETTER METHODS
   */
  public TitleScreen getTitleScreen() {
    return titleScreen;
  }

  public GameInfoScreen getGameInfoScreen() {
    return gameInfoScreen;
  }

  public GameScreen getGameScreen() {
    return gameScreen;
  }

  public MapScreen getMapScreen() {
    return mapScreen;
  }

  public HelpScreen getHelpScreen() {
    return helpScreen;
  }

  public GameResultsScreen getGameResultsScreen() {
    return gameResultsScreen;
  }

}
