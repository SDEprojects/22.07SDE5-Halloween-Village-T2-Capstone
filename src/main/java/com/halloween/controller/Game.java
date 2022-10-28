package com.halloween.controller;

import static com.halloween.view.SoundEffects.playSound;

import com.google.gson.Gson;
import com.halloween.model.House;
import com.halloween.model.Neighborhood;
import com.halloween.model.Player;
import com.halloween.model.State;
import com.halloween.view.PlayMusic;
import com.halloween.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * This Game class initializes the game, manages the user status, and creates the Win scenarios for
 * the game.
 */
public class Game {

  private State state;
  private View display = new View();
  private Player player = new Player();
  private Neighborhood neighborhood = new Neighborhood();
  private StoreGame storeGame = new StoreGame();
  private PlayMusic musicPlayer = new PlayMusic();

  /**
   * Initializes an instance of {@link Game}.
   */
  public Game() {
    // game position starts at "your house" in game initially
    player.setPosition("your house");
  }

  /**
   * Initializes an instance of {@link Game} by reading in saved data.
   *
   * @param state        State of the game.
   * @param player       An instance of the player where the player's data is stored.
   * @param neighborhood An instance of the neighborhood, which represents the world in the game.
   */
  public Game(State state, Player player, Neighborhood neighborhood) {
    this.state = state;
    this.player = player;
    this.neighborhood = neighborhood;
  }

  /**
   * Greets the user at the beginning of the game.
   *
   * @throws IOException Thrown if an I/O error is produced by failed or interrupted I/O
   *                     operations.
   */
  public void greetPlayer() throws IOException {
    if (player.getName() != null) { // If player name exists
      display.printWelcomeBackMessage(player.getName());
    } else {
      BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
      display.printAskName();
      player.setName(buffer.readLine().trim());
      if (player.getName().equals("quit")) {
        quitGame();
      }
      display.printWelcomeMessage(player.getName());
    }
  }

  /**
   * Displays the current status of the game.
   */
  public void showStatus() {
    House currentPosition = neighborhood.getNeighborhood().get(player.getPosition());
    String playerItems = player.getItems().isEmpty() ? "nothing" : player.getItems().toString();
    //Displays message if the house has nothing to collect
    String houseItems = currentPosition.getHouseItems().isEmpty() ? "a whole lot of nothing"
        : currentPosition.getHouseItems().toString();
    // Displays item in current house
    display.printHouseItem(currentPosition.getHouseName(), houseItems);
    // Displays items in inventory to user.
    display.printItemInInventory(player.getName(), player.getPosition(), playerItems);
    showValidMoves();
  }

  /**
   * Displays the game menu.
   */
  public void showMenu() {
    display.printMenu();
  }

  /**
   * Displays the game title (flash screen).
   */
  public void showTitle() {
    display.printTitle();
  }

  /**
   * Displays the game's background story.
   */
  public void showBackstory() {
    display.printBackStory();
  }

  /**
   * Displays the game's basic instructions.
   */
  public void showInstructions() {
    display.printInstructions();
  }

  /**
   * Displays all available commands.
   */
  public void showHelp() {
    display.printHelp();
  }

  /**
   * Displays the player's inventory.
   */
  public void showInventory() {
    display.printInventoryMessage(player.getItems());
  }

  /**
   * Displays the game's world map (neighborhood).
   */
  public void showMap() {
    display.printMap();
  }

  /**
   * Displays valid locations that the player can visit from the current location.
   */
  public void showValidMoves() {
    House currentPosition = neighborhood.getNeighborhood().get(player.getPosition());
    String north =
        currentPosition.getNorth() != null ? "\nnorth: " + currentPosition.getNorth() : "";
    String east = currentPosition.getEast() != null ? "\neast: " + currentPosition.getEast() : "";
    String south =
        currentPosition.getSouth() != null ? "\nsouth: " + currentPosition.getSouth() : "";
    String west = currentPosition.getWest() != null ? "\nwest: " + currentPosition.getWest() : "";
    display.printValidMoves(north, east, south, west);
  }

  /**
   * Displays the game win message.
   */
  public void showWin() {
    display.printWinMessage();
  }

  /**
   * Displays the game lose message.
   */
  public void showLose() {
    display.printLoseMessage();
  }

  /**
   * Moves the player to a new location.
   *
   * @param direction The direction that the player desires to move to.
   */
  public void movePlayer(String direction) {
    House currentPosition = neighborhood.getNeighborhood().get(player.getPosition());
    String playersMove = neighborhood.isValidDirection(direction, currentPosition);
    // set the previous house knocked to false before moving
    currentPosition.setKnocked(false);
    if (playersMove.isEmpty()) {
      display.printInvalidDirectionsMessage(direction);
      showValidMoves();
    } else {
      player.setPosition(playersMove);
      display.printPlayersMove(player.getName(), direction, player.getPosition());
      playSound("/footsteps.wav");
    }
  }

  /**
   * Allows the player to get an item.
   */
  public void getItem() {
    House house = neighborhood.getNeighborhood().get(player.getPosition());
    if (house.isKnocked() && house.getHouseItems().size() > 0) {
      String temp = house.getHouseItems().get(0);
      player.addItem(temp);
      house.removeItem();
      display.printGetItemMessage(temp);
    } else if (house.isKnocked()) {
      display.printNoItemError();
    } else {
      display.knockDoorFirst();
      display.knockDoor();
    }
    house.setKnocked(false);
  }

  /**
   * Allows the player to knock on the door for the current location and have an interaction.
   */
  public void knockOnDoor() {
    House house = neighborhood.getNeighborhood().get(player.getPosition());
    house.setKnocked(true);

    String knock = "/door-knock.wav";
    playSound(knock);

    ArrayList<String> playerItems = player.getItems();
    // If we knock on karen's house or the saw house we need to have check for specific items in our inventory
    // If we do not have the items, then we lose the game
    // If we knock on karen's door
    if (house.getHouseName().equals("karen's house")) {
      knockOnKarenHouse(playerItems);
      // if knock on the saw house
    } else if (house.getHouseName().equals("saw house")) {
      knockOnSawHouse(playerItems);
      // for all other houses (besides karen's house and saw house) we do the following
    } else if (house.getHouseItems().isEmpty()) {
      display.noItem(player.getPosition());
    } else {
      display.greet(player.getPosition());
    }
  }

  /**
   * Allows the player to knock at the Saw House location.
   *
   * @param playerItems A list of items that the player has (inventory).
   */
  private void knockOnSawHouse(ArrayList<String> playerItems) {
    // check for "thing" in not in our items then we lose the game
    if (!playerItems.contains("thing")) {
      display.noItem(player.getPosition());
      setState(State.LOSE);
    } // otherwise, thing will free us from the trap, and be removed from the inventory
    else {
      display.greet(player.getPosition());
      player.removeItem("thing");
    }
  }

  /**
   * Allows the player to knock at the Karen's House location.
   *
   * @param playerItems A list of items that the player has (inventory).
   */
  private void knockOnKarenHouse(ArrayList<String> playerItems) {
    // if we have a badge, potion, or ruby, then do nothing
    if (playerItems.contains("badge") || playerItems.contains("potion") || playerItems.contains(
        "ruby")) {
      display.printKarenCallingCops();
    }
    // if we don't have a badge, potion, or ruby we lose the game
    else {
      display.greet(player.getPosition());
      display.printPlayerArrested();
      playSound("/evil-shreik.wav");
      try {
        TimeUnit.SECONDS.sleep(3);  // Wait 2 seconds
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      setState(State.LOSE);
    }
  }

  /**
   * Allows the player to exit the game.
   */
  public void quitGame() {
    display.printExitGameMessage();
    System.exit(0);
  }

  /**
   * Allows the player to save the current game.
   */
  public void saveGame() {
    storeGame.saveGame(state, player, neighborhood);
  }

  /**
   * Allows the player to load a previously saved game.
   *
   * @return Returns a new instance of {@link Game}, loaded with data from a previously save game.
   */
  public Game loadGame() {
    Gson gson = new Gson();
    State state = storeGame.loadGame("state.json", State.class, gson);
    Player player = storeGame.loadGame("player.json", Player.class, gson);
    Neighborhood neighborhood = storeGame.loadGame("neighborhood.json", Neighborhood.class, gson);
    if (state == null || player == null || neighborhood == null) {
      return new Game();
    }
    return new Game(state, player, neighborhood);
  }

  /**
   * Removes JSON files where game data is stored.
   */
  public void removeFiles() {
    storeGame.removeJsonFiles();
  }

  /**
   * Allows the player to use an item at the current location.
   *
   * @param item The item to be used at the current location.
   */
  public void useItem(String item) {
    // get the house the player is currently at
    House house = neighborhood.getNeighborhood().get(player.getPosition());
    boolean successfullyUsedItem = player.removeItem(item);
    // if the house is knocked then try to use the item
    if (house.isKnocked()) {
      showInventory();
      String response = successfullyUsedItem ? "remove_item" : "warning_remove_item";
      display.printRemoveItem(response, item);
      if (!successfullyUsedItem) {
        return;
      }
    } else {
      display.printKnockToUseItem();
      return;
    }
    // if we use the badge at karen's house then we win the game
    if (house.getHouseName().equals("karen's house")) {
      karenUseItem(item);
    } else if (house.getHouseName().equals("dracula's mansion") && item.equals("tooth")) {
      display.printDraculaTooth();
      // added dracula's ruby to our inventory
      // NOTE: dracula's tooth is a hidden item, so we don't store it in the house
      player.addItem("ruby");
    } else if (house.getHouseName().equals("witch's den")) {
      witchUseItem(item, house);
    }
  }

  /**
   * Allows the player to use items at the Witch's Den location.
   *
   * @param item  The item to be used at Witch's Den.
   * @param house An instance of {@link House}, representing the Witch's Den.
   */
  private void witchUseItem(String item, House house) {
    if (item.equals("cat-hair") || item.equals("beer") || item.equals("dentures")) {
      display.printGiveWitchItem(item);
      playSound("/bubbles.wav");
      house.addItem(item);
    } else {
      display.printIncorrectWitchItem(item);
    }
    ArrayList<String> witchHouseItems = house.getHouseItems();
    if (witchHouseItems.contains("cat-hair") && witchHouseItems.contains("beer")
        && witchHouseItems.contains("dentures")) {
      display.printCompleteWitchPotion();
      // NOTE: potion is a hidden item, so we don't store it in the house
      player.addItem("potion");
      playSound("/witch.wav");
    }
  }

  /**
   * Allows the player to use an item at the Karen's House location.
   *
   * @param item The item to be used at Karen's House.
   */
  public void karenUseItem(String item) {
    if (item.equals("badge") || item.equals("potion") || item.equals("ruby")) {
      display.printKarenDefeated(item);
      playSound("/girl_scream.wav");
    } else {
      return;
    }
    setState(State.WIN);
  }

  /**
   * Allows the player to play the background music.
   */
  public void startMusic() {
    String musicName = "/darkess.wav";
    musicPlayer.play(musicName);
  }

  /**
   * Allows the player to stop the background music.
   */
  public void stopMusic() {
    musicPlayer.stop();
  }

  /**
   * Allows the player to increase the volume of the background music.
   */
  public void increaseVolume() {
    musicPlayer.increaseVolume();
  }

  /**
   * Allows the player to decrease the volume of the background music.
   */
  public void decreaseVolume() {
    musicPlayer.decreaseVolume();
  }

  /*
    Getter and Setter Methods
   */
  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

}
