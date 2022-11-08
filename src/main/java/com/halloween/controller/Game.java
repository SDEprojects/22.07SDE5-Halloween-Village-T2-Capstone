package com.halloween.controller;

import static com.halloween.view.SoundEffects.playSound;

import com.halloween.model.House;
import com.halloween.model.Neighborhood;
import com.halloween.model.Player;
import com.halloween.model.State;
import com.halloween.view.PlayMusic;
import com.halloween.view.View;
import java.util.ArrayList;

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
   * Moves the player to a new location.
   *
   * @param direction The direction that the player desires to move to.
   */
  public void movePlayer(String direction) {
    House currentPosition = neighborhood.getNeighborhood().get(player.getPosition());
    String playersMove = neighborhood.isValidDirection(direction, currentPosition);

    // set the previous house knocked to false before moving
    currentPosition.setKnocked(false);
    if (player.getUserMovesCounter() < 1) { // case where the player has less than 1 moves left
      setState(State.LOSE);
    } else {
      playSound("/footsteps.wav");
      player.setPosition(playersMove);
      player.setUserMovesCounter(player.getUserMovesCounter() - 1);
    }
  }

  /**
   * Allows the player to get an item.
   */
  public void getItem() {
    House house = neighborhood.getNeighborhood().get(player.getPosition());
    if (house.isKnocked() && house.getHouseItems().size() > 0) {
      String item = house.getHouseItems().get(0);
      player.addItem(item);
      house.removeItem();
    }
  }

  /**
   * Allows the player to knock on the door for the current location and have an interaction.
   */
  public void knockOnDoor() {
    String knock = "/door-knock.wav";
    playSound(knock);
    House house = getNeighborhood().getNeighborhood().get(getPlayer().getPosition());
    house.setKnocked(true);
  }

  /**
   * Allows the player to knock at the Saw House location.
   *
   * @param playerItems A list of items that the player has (inventory).
   * @return Returns a boolean representing if player has correct items to escape Saw or not.
   */
  public boolean playerHasCorrectSawItem(ArrayList<String> playerItems) {
    // check for "thing" in not in our items then we lose the game
    if (!playerItems.contains("thing")) {
      setState(State.LOSE);
      return false;
    } // otherwise, thing will free us from the trap, and be removed from the inventory
    else {
      return true;
    }
  }

  /**
   * Allows the player to knock at the Karen's House location.
   *
   * @param playerItems A list of items that the player has (inventory).
   * @return Returns a boolean representing if player has correct items to win Karen or not.
   */
  public boolean playerHasCorrectKarenItem(ArrayList<String> playerItems) {
    // if we have a badge, potion, or ruby, then do nothing
    if (playerItems.contains("badge") || playerItems.contains("potion") || playerItems.contains(
        "ruby")) {
      return true;
    }
    // if we don't have a badge, potion, or ruby we lose the game
    else {
      playSound("/evil-shreik.wav");
      setState(State.LOSE);
      return false;
    }
  }

  /**
   * Allows the player to save the current game.
   */
  public void saveGame() {
    storeGame.saveGame(state, player, neighborhood);
  }

  /**
   * Allows the player to use items at the Witch's Den location.
   *
   * @param item  The item to be used at Witch's Den.
   * @param house An instance of {@link House}, representing the Witch's Den.
   */
  public void witchUseItem(String item, House house) {
    if (item.equals("cat-hair") || item.equals("beer") || item.equals("dentures")) {
      playSound("/bubbles.wav");
      house.addItem(item);
    } else {
    }
    ArrayList<String> witchHouseItems = house.getHouseItems();
    if (witchHouseItems.contains("cat-hair") && witchHouseItems.contains("beer")
        && witchHouseItems.contains("dentures")) {
      // NOTE: potion is a hidden item, so we don't store it in the house
      getPlayer().addItem("potion");
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
      setState(State.WIN);
      playSound("/girl_scream.wav");
    }
  }

  public void draculaUseItem(String item, House house) {
    if (item.equals("tooth")) {
      house.addItem(item);
      getPlayer().addItem("ruby");
    }
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

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Neighborhood getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(Neighborhood neighborhood) {
    this.neighborhood = neighborhood;
  }

}
