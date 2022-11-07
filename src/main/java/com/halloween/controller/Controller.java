package com.halloween.controller;

import com.halloween.model.Neighborhood;
import com.halloween.model.Player;
import com.halloween.model.State;
import com.halloween.view.SoundEffects;
import com.halloween.view.gui.GameInfoScreen;
import com.halloween.view.gui.GameScreen;
import com.halloween.view.gui.GuiView;
import com.halloween.view.gui.HelpScreen;
import com.halloween.view.gui.MapScreen;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    try (
        Reader stateReader = Files.newBufferedReader(Paths.get("./state.json"));
        Reader playerReader = Files.newBufferedReader(Paths.get("./player.json"));
        Reader neighborhoodReader = Files.newBufferedReader(Paths.get("./neighborhood.json"));
    ) {
      State state = StoreGame.GSON.fromJson(stateReader, State.class);
      Player player = StoreGame.GSON.fromJson(playerReader, Player.class);
      Neighborhood neighborhood = StoreGame.GSON.fromJson(neighborhoodReader, Neighborhood.class);

      if (state == null || player == null || neighborhood == null) {
        view.displayLoadFailPane();
      } else {
        game = new Game(state, player, neighborhood);
        startGame();
      }
    } catch (IOException e) {
      view.displayLoadFailPane();
    }
  }

  public void startGame() {
    view.displayGameScreen(game.getPlayer());
    addGameScreenButtonHandlers();
    game.setState(State.PLAY);
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
      startGame();
    });
  }

  public void addGameScreenButtonHandlers() {
    GameScreen gameScreen = view.getGameScreen();
    // TOP PANEL BUTTONS
    JButton helpButton = gameScreen.getHelpButton();
    JButton mapButton = gameScreen.getMapButton();
    JButton musicButton = gameScreen.getMusicButton();
    JButton fxButton = gameScreen.getFxButton();
    JButton saveGameButton = gameScreen.getSaveGameButton();
    JButton quitButton = gameScreen.getQuitButton();
    // BOTTOM PANEL BUTTONS
    JButton goNorthButton = gameScreen.getGoNorthButton();
    JButton goEastButton = gameScreen.getGoEastButton();
    JButton goSouthButton = gameScreen.getGoSouthButton();
    JButton goWestButton = gameScreen.getGoWestButton();
    JButton knockButton = gameScreen.getKnockButton();
    JButton getItemButton = gameScreen.getGetItemButton();
    JButton useItemButton = gameScreen.getUseItemButton();

    // TOP PANEL BUTTON HANDLERS
    helpButton.addActionListener(e -> {
      view.displayHelpScreen();
      addHelpScreenButtonHandlers();
    });
    mapButton.addActionListener(event -> {
      view.displayMapScreen();
      addMapScreenButtonHandlers();
    });
    // TODO: Extend musicButton and fxButton handlers so that it can turn on/off sound
    musicButton.addActionListener(e -> game.stopMusic()); // Can only mute the sound at the moment
    fxButton.addActionListener(e -> SoundEffects.muteSoundEffects()); // Can only mute fx
    saveGameButton.addActionListener(e -> game.saveGame());
    quitButton.addActionListener(e -> System.exit(0));

    // BOTTOM PANEL BUTTON HANDLERS (USER CONTROL)
    goNorthButton.addActionListener(e -> {
      game.movePlayer("north");
      view.displayGameScreen(game.getPlayer()); // displays new game screen with updated information
    });
    goEastButton.addActionListener(e -> {
      game.movePlayer("east");
      view.displayGameScreen(game.getPlayer());
    });
    goSouthButton.addActionListener(e -> {
      game.movePlayer("south");
      view.displayGameScreen(game.getPlayer());
    });
    goWestButton.addActionListener(e -> {
      game.movePlayer("west");
      view.displayGameScreen(game.getPlayer());
    });
    knockButton.addActionListener(e -> {
      game.knockOnDoor();
      view.displayGameScreen(game.getPlayer());
    });
    getItemButton.addActionListener(e -> {
      game.getItem();
      view.displayGameScreen(game.getPlayer());
    });
  }

  public void addMapScreenButtonHandlers() {
    MapScreen mapScreen = view.getMapScreen();
    JButton backToGame = mapScreen.getBackToGameMapScreenButton();

    backToGame.addActionListener(event -> view.displayGameScreen(game.getPlayer()));
  }

  public void addHelpScreenButtonHandlers() {
    HelpScreen helpScreen = view.getHelpScreen();
    JButton backToGame = helpScreen.getBackToGameHelpScreenButton();

    backToGame.addActionListener(event -> view.displayGameScreen(game.getPlayer()));
  }

  /*
    GETTER & SETTER METHODS
   */
  public void setGame(Game game) {
    this.game = game;
  }

}
