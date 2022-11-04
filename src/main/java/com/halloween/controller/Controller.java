package com.halloween.controller;

import com.halloween.model.Neighborhood;
import com.halloween.model.Player;
import com.halloween.model.State;
import com.halloween.view.GUI.GameInfoScreen;
import com.halloween.view.GUI.GameScreen;
import com.halloween.view.GUI.GuiView;
import com.halloween.view.SoundEffects;
import javax.swing.JButton;

public class Controller {

  Game game;
  GuiView view;

  public Controller(Game game, GuiView view) {
    this.game = game;
    this.view = view;
  }

  public void startProgram() {
    view.displayTitleScreen();
    game.startMusic();
    addTitleScreenButtonHandlers();
  }

  public void loadGame() {
    State state = StoreGame.loadGame("state.json", State.class);
    Player player = StoreGame.loadGame("player.json", Player.class);
    Neighborhood neighborhood = StoreGame.loadGame("neighborhood.json", Neighborhood.class);
    if (state == null || player == null || neighborhood == null) {
      view.displayNoSavedGamePane();
      startGame(true);
    } else {
      game = new Game(state, player, neighborhood);
    }
  }

  public void startGame(boolean isNewGame) {
    if (isNewGame) {
      // TODO: IMPLEMENT STARTING A NEW GAME
    } else {
      setGame(game.loadGame());

    }
  }

  public void addTitleScreenButtonHandlers() {
    JButton newGameButton = view.getTitleScreen().getNewGameButton();
    JButton loadGameButton = view.getTitleScreen().getLoadGameButton();
    JButton quitButton = view.getTitleScreen().getQuitButton();

    newGameButton.addActionListener(e -> {
      view.displayBackgroundStoryScreen();
      addGameInfoScreenButtonHandlers();
    });
    loadGameButton.addActionListener(e -> {
      loadGame();
    });
    quitButton.addActionListener(e -> System.exit(0));
  }

  public void addGameInfoScreenButtonHandlers() {
    GameInfoScreen infoScreen = view.getGameInfoScreen();
    JButton backStoryNextButton = infoScreen.getBackStoryNextButton();
    JButton instructionsNextButton = infoScreen.getInstructionsNextButton();
    JButton startGameButton = infoScreen.getStartGameButton();

    backStoryNextButton.addActionListener(e -> view.displayInstructionsScreen());
    instructionsNextButton.addActionListener(e -> view.displayGetUsernameScreen());
    startGameButton.addActionListener(e -> {
      game.getPlayer().setName(infoScreen.getTextArea().getText());
      view.displayGameScreen();
      addGameScreenButtonHandlers();
    });
  }

  public void addGameScreenButtonHandlers() {
    GameScreen gameScreen = view.getGameScreen();
    JButton helpButton = gameScreen.getHelpButton();
    JButton mapButton = gameScreen.getMapButton();
    JButton musicButton = gameScreen.getMusicButton();
    JButton fxButton = gameScreen.getFxButton();
    JButton saveGameButton = gameScreen.getSaveGameButton();
    JButton quitButton = gameScreen.getQuitButton();

    helpButton.addActionListener(e -> view.displayHelpScreen());
    mapButton.addActionListener(e -> view.displayMapScreen());
    // TODO: Extend musicButton and fxButton handlers so that it can turn on/off sound
    musicButton.addActionListener(e -> game.stopMusic()); // Can only mute the sound at the moment
    fxButton.addActionListener(e -> SoundEffects.muteSoundEffects()); // Can only mute fx
    saveGameButton.addActionListener(e -> game.saveGame());
    quitButton.addActionListener(e -> System.exit(0));
  }

  public void setGame(Game game) {
    this.game = game;
  }

}
