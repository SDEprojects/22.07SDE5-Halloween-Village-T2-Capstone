package com.halloween.model;

import java.util.ArrayList;

/**
 * This class acts as a model for a house in the game that users can visit and interact with.
 */
public class House {

  private String houseName;
  private ArrayList<String> houseItems;
  private String[] residents;
  private String north;
  private String east;
  private String south;
  private String west;
  private boolean knocked;

  /**
   * Initializes an instance of {@link House}.
   *
   * @param houseName House's name.
   * @param houseItems Items stored in the house.
   * @param residents People associated with the house.
   * @param north Name of the house located to the north of this house.
   * @param east Name of the house located to the east of this house.
   * @param south Name of the house located to the south of this house.
   * @param west Name of the house located to the west of this house.
   */
  public House(String houseName, ArrayList<String> houseItems, String[] residents, String north,
      String east, String south, String west) {
    this.houseName = houseName;
    this.houseItems = houseItems;
    this.residents = residents;
    this.north = north;
    this.east = east;
    this.south = south;
    this.west = west;
    this.knocked = false;
  }

  /*
    Getter and Setter Methods
   */
  public String getHouseName() {
    return houseName;
  }

  public ArrayList<String> getHouseItems() {
    return houseItems;
  }

  public String[] getResidents() {
    return residents;
  }

  public String getNorth() {
    return north;
  }

  public String getEast() {
    return east;
  }

  public String getSouth() {
    return south;
  }

  public String getWest() {
    return west;
  }

  public void setNorth(String north) {
    this.north = north;
  }

  public void setEast(String east) {
    this.east = east;
  }

  public void setSouth(String south) {
    this.south = south;
  }

  public void setWest(String west) {
    this.west = west;
  }

  public void removeItem() {
    houseItems.remove(0);
  }

  public void addItem(String item) {
    houseItems.add(item);
  }

  public boolean isKnocked() {
    return knocked;
  }

  public void setKnocked(boolean knocked) {
    this.knocked = knocked;
  }

}
