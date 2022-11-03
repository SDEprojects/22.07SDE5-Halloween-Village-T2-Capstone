package com.halloween.controller;

import com.halloween.model.Neighborhood;
import com.halloween.model.Player;
import com.halloween.model.State;
import com.halloween.view.GUI.GameInfoScreen;
import com.halloween.view.GUI.GuiView;
import javax.swing.JButton;

public class Controller {

  Game game;
  GuiView view;
  Player player;
  Neighborhood neighborhood;
  State state;

  public Controller(Game game, GuiView view) {
    this.game = game;
    this.view = view;
  }

  public void startProgram() {
    view.displayTitleScreen();
    addTitleScreenButtonHandlers();
  }

  public void addTitleScreenButtonHandlers() {
    JButton newGameButton = view.getTitleScreen().getNewGameButton();
    newGameButton.addActionListener(e -> {
      view.displayBackgroundStoryScreen();
      addGameInfoScreenButtonHandlers();
    });
    // TODO: ADD LOAD GAME AND QUIT BUTTON HANDLERS
  }

  public void addGameInfoScreenButtonHandlers() {
    GameInfoScreen infoScreen = view.getGameInfoScreen();
    JButton backStoryNextButton = infoScreen.getBackStoryNextButton();
    JButton instructionsNextButton = infoScreen.getInstructionsNextButton();
    JButton startGameButton = infoScreen.getStartGameButton();

    backStoryNextButton.addActionListener(e -> view.displayInstructionsScreen());
    instructionsNextButton.addActionListener(e -> view.displayGetUsernameScreen());
    startGameButton.addActionListener(e -> {
      System.out.println("USERNAME = " + infoScreen.getTextArea().getText()); // TODO: DELETE BEFORE RELEASE
      game.getPlayer().setName(infoScreen.getTextArea().getText());
      view.displayGameScreen();
    });
  }

}