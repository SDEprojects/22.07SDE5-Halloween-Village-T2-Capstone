package com.halloween.controller;

import static com.halloween.view.SoundEffects.playSound;

import com.halloween.model.House;
import com.halloween.model.Neighborhood;
import com.halloween.model.Player;
import com.halloween.model.State;
import com.halloween.view.SoundEffects;
import com.halloween.view.gui.GameInfoScreen;
import com.halloween.view.gui.GameResultsScreen;
import com.halloween.view.gui.GameScreen;
import com.halloween.view.gui.GuiView;
import com.halloween.view.gui.HelpScreen;
import com.halloween.view.gui.MapScreen;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    playSound("/howl.wav");
    view.displayGameScreen(game.getPlayer(), game.getNeighborhood());
    addGameScreenButtonHandlers();
    game.setState(State.PLAY);
    addGameResultScreenButtonHandler();
  }

  public void updateScreen(boolean updateMainPanel, boolean updateSidePanel,
      boolean updateBottomPanel) {
    if (!getGame().getState().isTerminal()) { // if game's state is not terminal (still playing)
      // update the main panel and side panel
      if (updateMainPanel) {
        view.updateGameScreenMainPanel(getGame().getPlayer(), getGame().getNeighborhood());
      }
      if (updateSidePanel) {
        view.updateGameScreenSidePanel(getGame().getPlayer());
      }
      if (updateBottomPanel) {
        view.updateGameScreenBottomPanel(getGame().getPlayer(), getGame().getNeighborhood());
      }
      // if game's state is terminal, update the game screen and display game result after 10 seconds
    } else {
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      view.displayGameResult(getGame());
    }
  }

  public void quitGame() {
    System.exit(0);
  }

  public void addTitleScreenButtonHandlers() {
    JButton newGameButton = view.getTitleScreen().getNewGameButton();
    JButton loadGameButton = view.getTitleScreen().getLoadGameButton();
    JButton quitButton = view.getTitleScreen().getQuitButton();

    newGameButton.addActionListener(e -> {
      view.displayBackgroundStoryScreen();
      addGameInfoScreenButtonHandlers();
    });
    loadGameButton.addActionListener(e -> loadGame());
    quitButton.addActionListener(e -> quitGame());
  }

  public void addGameInfoScreenButtonHandlers() {
    GameInfoScreen infoScreen = view.getGameInfoScreen();
    JButton backStoryNextButton = infoScreen.getBackStoryNextButton();
    JButton startGameButton = infoScreen.getStartGameButton();

    backStoryNextButton.addActionListener(e -> view.displayInstructionsScreen());
    startGameButton.addActionListener(e -> {
      game.getPlayer().setName(infoScreen.getTextArea().getText());
      startGame();
    });
  }

  public void addGameScreenButtonHandlers() {
    House currentHouse = getGame().getNeighborhood().getNeighborhood()
        .get(getGame().getPlayer().getPosition());
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
    quitButton.addActionListener(e -> quitGame());

    // BOTTOM PANEL BUTTON HANDLERS (USER CONTROL)
    goNorthButton.addActionListener(e -> {
      game.movePlayer("north");
      updateScreen(true, true, true);
    });
    goEastButton.addActionListener(e -> {
      game.movePlayer("east");
      updateScreen(true, true, true);
    });
    goSouthButton.addActionListener(e -> {
      game.movePlayer("south");
      updateScreen(true, true, true);
    });
    goWestButton.addActionListener(e -> {
      game.movePlayer("west");
      updateScreen(true, true, true);
    });
    knockButton.addActionListener(e -> {
      boolean isSpecialHouse = checkSpecialHouse(currentHouse);
      game.knockOnDoor();
      if (isSpecialHouse) { // if Karen's House or Saw House
        knockSpecialHouse(currentHouse);
        // TODO: Update screen with correct message when at Karen's House or Saw House
        updateScreen(true, false, true);
      } else {
        updateScreen(true, false, true);
      }
    });
    getItemButton.addActionListener(e -> {
      game.getItem();
      updateScreen(false, true, true);
    });
    useItemButton.addActionListener(e -> {
      // TODO: Alert the user if they try to use item without knocking
      if (currentHouse.isKnocked()) {
        String item = view.getGameScreen().getUseItemTextField().getText().trim().toLowerCase();
        useItem(item);
      } else {
        view.displayKnockToUseItemPane();
      }
    });
  }

  public boolean checkSpecialHouse(House house) {
    return (house.getHouseName().equals("karen's house") || house.getHouseName()
        .equals("saw house"));
  }

  public void knockSpecialHouse(House house) {
    ArrayList<String> playerItems = getGame().getPlayer().getItems();
    if (house.getHouseName().equals("karen's house")) {
      game.karenHouseKnockPlayerHasCorrectItem(playerItems);
    } else if (house.getHouseName().equals("saw house")) {
      game.sawHousePlayerHasCorrectItem(playerItems);
    }
  }

  public void useItem(String item) {
    // get the house the player is currently at
    House house = getGame().getNeighborhood().getNeighborhood()
        .get(getGame().getPlayer().getPosition());
    // check if house is knocked
    if (house.isKnocked()) {
      // check if the item could be removed from the inventory (if the item is in inventory)
      boolean itemUseSuccessful = getGame().getPlayer().removeItem(item);
      if (itemUseSuccessful) {
        updateScreen(true, true, true);
      } else {
        view.displayNoSuchItemPane();
      }
    }
    if (house.getHouseName().equals("karen's house")) {
      getGame().karenUseItem(item);
    }
    if (house.getHouseName().equals("dracula's mansion")) {
      getGame().draculaUseItem(item);
    }
    if (house.getHouseName().equals("witch's den")) {
      getGame().witchUseItem(item, house);
    }
    updateScreen(true, true, false);
  }

  public void addMapScreenButtonHandlers() {
    MapScreen mapScreen = view.getMapScreen();
    JButton backToGame = mapScreen.getBackToGameMapScreenButton();

    backToGame.addActionListener(event -> view.displayGameScreen(game.getPlayer(),
        game.getNeighborhood()));
  }

  public void addHelpScreenButtonHandlers() {
    HelpScreen helpScreen = view.getHelpScreen();
    JButton backToGame = helpScreen.getBackToGameHelpScreenButton();

    backToGame.addActionListener(
        event -> view.displayGameScreen(game.getPlayer(), game.getNeighborhood()));
  }

  public void addGameResultScreenButtonHandler() {
    GameResultsScreen resultScreen = view.getGameResultsScreen();
    JButton quitButton = resultScreen.getQuitGameButton();

    quitButton.addActionListener(e -> {
      view.displayGameResult(game);
      game.quitGame();
    });
  }

  /*
    GETTER & SETTER METHODS
   */
  public void setGame(Game game) {
    this.game = game;
  }

  public Game getGame() {
    return game;
  }

}


