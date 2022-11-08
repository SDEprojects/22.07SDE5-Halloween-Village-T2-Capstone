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

  private Game game;
  private GuiView view;

  public Controller(Game game, GuiView view) {
    this.game = game;
    this.view = view;
  }

  public void startProgram() {
    getView().displayTitleScreen();
    getGame().startMusic();
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
        getView().displayLoadFailPane();
      } else {
        setGame(new Game(state, player, neighborhood));
        startGame();
      }
    } catch (IOException e) {
      getView().displayLoadFailPane();
    }
  }

  public void startGame() {
    playSound("/howl.wav");
    getView().displayGameScreen(getGame());
    addGameScreenButtonHandlers();
    getGame().setState(State.PLAY);
    addGameResultScreenButtonHandler();
  }

  public void updateScreen(boolean updateMainPanel, boolean updateSidePanel,
      boolean updateBottomPanel) {
    if (updateMainPanel) {
      getView().updateGameScreenMainPanel(getGame());
    }
    if (updateSidePanel) {
      getView().updateGameScreenSidePanel(getGame().getPlayer());
    }
    if (updateBottomPanel) {
      getView().updateGameScreenBottomPanel(getGame().getPlayer(), getGame().getNeighborhood());
    }
  }

  public void endGame() {
    // if game's state is terminal, display game result after 10 seconds
    if (getGame().getState().isTerminal()) {
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      getView().displayGameResult(getGame());
    }
  }

  public void quitProgram() {
    System.exit(0);
  }

  public void addTitleScreenButtonHandlers() {
    JButton newGameButton = getView().getTitleScreen().getNewGameButton();
    JButton loadGameButton = getView().getTitleScreen().getLoadGameButton();
    JButton quitButton = getView().getTitleScreen().getQuitButton();

    newGameButton.addActionListener(e -> {
      getView().displayBackgroundStoryScreen();
      addGameInfoScreenButtonHandlers();
    });
    loadGameButton.addActionListener(e -> loadGame());
    quitButton.addActionListener(e -> quitProgram());
  }

  public void addGameInfoScreenButtonHandlers() {
    GameInfoScreen infoScreen = getView().getGameInfoScreen();
    JButton backStoryNextButton = infoScreen.getBackStoryNextButton();
    JButton instructionsNextButton = infoScreen.getInstructionsNextButton();
    JButton startGameButton = infoScreen.getStartGameButton();

    backStoryNextButton.addActionListener(e -> getView().displayInstructionsScreen());
    instructionsNextButton.addActionListener(e -> getView().displayGetUsernameScreen());

    startGameButton.addActionListener(e -> {
      getGame().getPlayer().setName(infoScreen.getUserNameTextArea().getText().trim());
      startGame();
    });
  }

  public void addGameScreenButtonHandlers() {
    GameScreen gameScreen = getView().getGameScreen();
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
      getView().displayHelpScreen();
      addHelpScreenButtonHandlers();
    });
    mapButton.addActionListener(event -> {
      getView().displayMapScreen();
      addMapScreenButtonHandlers();
    });
    // TODO: Extend musicButton and fxButton handlers so that it can turn on/off sound
    musicButton.addActionListener(
        e -> getGame().stopMusic()); // Can only mute the sound at the moment
    fxButton.addActionListener(e -> SoundEffects.muteSoundEffects()); // Can only mute fx
    saveGameButton.addActionListener(e -> getGame().saveGame());
    quitButton.addActionListener(e -> quitProgram());

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
      game.knockOnDoor();
    });

    goNorthButton.addActionListener(e -> movePlayer("north"));
    goEastButton.addActionListener(e -> movePlayer("east"));
    goSouthButton.addActionListener(e -> movePlayer("south"));
    goWestButton.addActionListener(e -> movePlayer("west"));

    knockButton.addActionListener(e -> knockOnDoor());

    getItemButton.addActionListener(e -> {
      getGame().getItem();
      updateScreen(false, true, true);
    });
    useItemButton.addActionListener(e -> useItem());
  }

  public void movePlayer(String direction) {
    getGame().movePlayer(direction);
    updateScreen(true, true, true);
  }

  public void knockOnDoor() {
    // the current house player is located at
    House house = getGame().getNeighborhood().getNeighborhood()
        .get(getGame().getPlayer().getPosition());
    getGame().knockOnDoor();
    if (checkSpecialHouse(house.getHouseName())) { // check if it's Karen's House or Saw House
      knockSpecialHouse(house);
    }
    updateScreen(true, false, true);
  }

  public static boolean checkSpecialHouse(String houseName) {
    return (houseName.equals("karen's house") || houseName.equals("saw house"));
  }

  public void knockSpecialHouse(House house) {
    ArrayList<String> playerItems = getGame().getPlayer().getItems();
    if (house.getHouseName().equals("karen's house")) {
      if (!getGame().playerHasCorrectKarenItem(playerItems)) {
        endGame();
      }
    } else if (house.getHouseName().equals("saw house")) {
      if (!getGame().playerHasCorrectSawItem(playerItems)) {
        endGame();
      }
    }
  }

  public void useItem() {
    // the current house player is located at
    House house = getGame().getNeighborhood().getNeighborhood()
        .get(getGame().getPlayer().getPosition());
    String item = getView().getGameScreen().getUseItemTextField().getText().trim().toLowerCase();
    boolean validItemUsed = false;

    // check if house is knocked
    if (house.isKnocked()) {
      // remove item from inventory if the item is in the inventory. Otherwise, display an alert
      validItemUsed = removeValidItem(item);
    } else { // if house hasn't been knocked, display an alert
      getView().displayKnockToUseItemPane();
    }

    if (validItemUsed) {
      if (house.getHouseName().equals("dracula's mansion")) {
        getGame().draculaUseItem(item);
      }
      if (house.getHouseName().equals("witch's den")) {
        getGame().witchUseItem(item, house);
      }
      if (house.getHouseName().equals("karen's house")) {
        getGame().karenUseItem(item);
        if (checkGameWinningItem(item)) {
          endGame();
        }
      }
    }
    updateScreen(true, true, false);
  }

  public boolean removeValidItem(String item) {
    // check if the item could be removed from the inventory (if the item is in inventory)
    boolean itemUseSuccessful = getGame().getPlayer().removeItem(item);
    if (itemUseSuccessful) {
      updateScreen(true, true, true);
      return true;
    } else {
      getView().displayNoSuchItemPane();
      return false;
    }
  }

  private boolean checkGameWinningItem(String item) {
    return (item.equals("badge") || item.equals("potion") || item.equals("ruby"));
  }

  public void addMapScreenButtonHandlers() {
    MapScreen mapScreen = getView().getMapScreen();
    JButton backToGame = mapScreen.getBackToGameMapScreenButton();

    backToGame.addActionListener(event -> getView().displayGameScreen(getGame()));
  }

  public void addHelpScreenButtonHandlers() {
    HelpScreen helpScreen = getView().getHelpScreen();
    JButton backToGame = helpScreen.getBackToGameHelpScreenButton();
    backToGame.addActionListener(event -> getView().displayGameScreen(getGame()));
  }

  public void addGameResultScreenButtonHandler() {
    GameResultsScreen resultScreen = getView().getGameResultsScreen();
    JButton quitButton = resultScreen.getQuitGameButton();
    quitButton.addActionListener(e -> quitProgram());
  }

  /*
    GETTER & SETTER METHODS
   */
  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public GuiView getView() {
    return view;
  }

}


