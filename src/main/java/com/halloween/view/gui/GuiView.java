package com.halloween.view.gui;

import com.halloween.controller.Game;
import com.halloween.model.House;
import com.halloween.model.Neighborhood;
import com.halloween.model.Player;
import com.halloween.model.State;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
    //this.setResizable(false); //present frame from being resized
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

  public void displayGetUsernameScreen() {
    getGameInfoScreen().buildGetUsernameScreen();
    container.repaint();
    container.revalidate();
  }

  public void displayGameScreen(Player player, Neighborhood neighborhood) {
    container.removeAll();

    container.add(gameScreen.getTopPanel(), BorderLayout.NORTH);
    container.add(gameScreen.getSidePanel(), BorderLayout.WEST);
    container.add(gameScreen.getMainPanel(), BorderLayout.CENTER);
    container.add(gameScreen.getFirstPanelBelowMain(), BorderLayout.CENTER);
    container.add(gameScreen.getBottomPanel(), BorderLayout.SOUTH);
    showValidDirectionButtons(gameScreen, player, neighborhood);

    gameScreen.getLocationLabel().setText("Current Location:\t\t" + player.getPosition());
    gameScreen.getGameTextLabel().setText("game text label");
    gameScreen.getNpcLabel().setText("npc label");
    gameScreen.getInventoryLabel().setText("Inventory:\t\t" + player.getItems().toString());
    gameScreen.getRemainingMovesLabel()
        .setText("Remaining Moves:\t\t" + player.getUserMovesCounter());

    container.revalidate();
    container.repaint();
  }

  public void displayGameReulst(Game game) {
    container.removeAll();
    if (game.getState().equals(State.WIN)) {
      // TODO: Display Game Win Screen
      //  The line below is for testing purposes. Replace it with an actual win screen
      JOptionPane.showMessageDialog(null,
          "CONGRATULATIONS, YOU WON! (SAVED GAME DATA WILL BE DELETED)");

    } else if (game.getState().equals(State.LOSE)) {
      // TODO: Display Game Lose Screen
      //  The line below is for testing purposes. Replace it with an actual lose screen
      JOptionPane.showMessageDialog(null, "UH-OH, YOU LOST! (SAVED GAME DATA WILL BE DELETED)");
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

  public void displayGameResultsScreen(){
    container.removeAll();
    container.add(getGameResultsScreen().getGameResultsScreen());
    container.repaint();
    container.revalidate();
  }

  public void displayLoadFailPane() {
    JOptionPane.showMessageDialog(null,
        "ERROR: FAILED TO LOAD GAME DATA.\nPLEASE START A NEW GAME.");
  }

  public void showValidDirectionButtons(GameScreen gameScreen, Player player,
      Neighborhood neighborhood) {
    JButton goNorthButton = gameScreen.getGoNorthButton();
    JButton goEastButton = gameScreen.getGoEastButton();
    JButton goSouthButton = gameScreen.getGoSouthButton();
    JButton goWestButton = gameScreen.getGoWestButton();

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

  public GameResultsScreen getGameResultsScreen(){
    return gameResultsScreen;
  }

}
