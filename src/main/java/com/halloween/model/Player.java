package com.halloween.model;

import java.util.ArrayList;

/**
 * This class acts as a model for the Player's character throughout a game.
 */
public class Player {

  private String name;
  private String position;
  private ArrayList<String> items;

  /**
   * Initializes an instance of {@link Player} with empty items.
   */
  public Player() {
    this.items = new ArrayList<>();
  }

  /**
   * Initializes an instance of {@link Player}.
   *
   * @param name     Name of the player.
   * @param position Current position of the player.
   * @param items    Items currently in the player's inventory.
   */
  public Player(String name, String position, ArrayList<String> items) {
    this.name = name;
    this.position = position;
    this.items = new ArrayList<>();
  }

  /**
   * Adds an item to the player's current inventory.
   *
   * @param item An item to be added to the player's inventory.
   */
  public void addItem(String item) {
    items.add(item);
  }

  /**
   * Removes an item from the player's current inventory when the item is used and returns the
   * result of using (removing) the item.
   *
   * @param item The item the players wishes to use.
   * @return Returns the result (whether successful or not) of removing an item from the player's
   * inventory.
   */
  public boolean removeItem(String item) {
    if (items.contains(item)) {
      // remove the item
      items.remove(item);
      // return true if the item was successfully used
      return true;
    } else {
      // return false if the item use was unsuccessful
      return false;
    }
  }

  /*
    Getter and Setter Methods
   */

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public ArrayList<String> getItems() {
    return items;
  }

  public void setItems(ArrayList<String> items) {
    this.items = items;
  }

}
