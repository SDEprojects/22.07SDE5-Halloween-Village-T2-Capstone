package com.halloween.view.gui;

import com.halloween.view.View;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;

public class GuiView {

  JFrame window;
  private Container container;
  private TitleScreen titleScreen;
  private GameInfoScreen gameInfoScreen;
  private GameScreen gameScreen;
  private MapScreen mapScreen;

  private HelpScreen helpScreen;

  public GuiView() {
    window = createWindow();
    container = window.getContentPane();
    titleScreen = new TitleScreen();
    gameInfoScreen = new GameInfoScreen();
    gameScreen = new GameScreen();
    mapScreen = new MapScreen();
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

  public void displayGameScreen() {
    container.removeAll();

    container.add(gameScreen.getTopPanel(), BorderLayout.NORTH);
    container.add(gameScreen.getLeftPanel(), BorderLayout.WEST);
    container.add(gameScreen.getMainPanel(), BorderLayout.CENTER);
    container.repaint();
    container.revalidate();
  }

  public void displayMapScreen() {
    // TODO: call map screen
    container.removeAll();
    getMapScreen();
    container.repaint();
    container.revalidate();
  }

  public void displayHelpScreen() {
    //TODO: call help screen
    container.removeAll();
    getHelpScreen();
    container.repaint();
    container.revalidate();
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

}
