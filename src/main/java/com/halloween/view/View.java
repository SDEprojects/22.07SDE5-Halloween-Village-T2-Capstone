package com.halloween.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.halloween.Main;
import com.halloween.controller.Game;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Represents the data to be displayed to the user in a game. This class is a part of the View
 * component as this program follows the MVC model.
 */
public class View {

  private ResourceBundle instructions;
  private ResourceBundle npcResponse;
  private BufferedReader reader = new BufferedReader(
      new InputStreamReader(getClass().getClassLoader().getResourceAsStream("dialogue.json")));
  private Gson gson = new Gson();
  private Type collectionType = new TypeToken<ArrayList<HashMap<String, HashMap<String, String>>>>() {
  }.getType();
  private ArrayList<HashMap<String, HashMap<String, String>>> dialogueList = gson.fromJson(reader,
      collectionType);
  private HashMap<String, HashMap<String, String>> dialogue = (HashMap<String, HashMap<String, String>>) dialogueList.stream()
      .collect(Collectors.toMap(map -> String.join("", map.keySet()),
          map -> map.get(String.join("", map.keySet()))));

  /**
   * Initializes an instance of {@link View}, using JSON data in game-instructions- and
   * NPC-dialogue-related files.
   */
  public View() {
    instructions = ResourceBundle.getBundle("instructions");
    npcResponse = ResourceBundle.getBundle("npcResponse");
  }

  /**
   * Displays an appropriate greeting for the desired location.
   *
   * @param currentPosition The name of a location in the game.
   */
  public void greet(String currentPosition) {
    System.out.println(dialogue.get(currentPosition).get("greet"));
  }

  /**
   * Displays a no-item message, indicating that there is no item to acquire at the desired
   * location.
   *
   * @param currentPosition The name of a location in the game.
   */
  public void noItem(String currentPosition) {
    System.out.println(dialogue.get(currentPosition).get("no item"));
  }

  /**
   * Displays important information related to the game, such as the game title, background story,
   * win/lose messages, and other critical information about the game.
   *
   * @param key The name of the important information needed to be displayed.
   * @return Returns the actual content (text) of the important information.
   */
  public String getImportantDisplay(String key) {
    return instructions.getString(key) + "\n";
  }

  /**
   * Displays in-game alerts and messages to inform the user of the pre-requisite and/or the result
   * of their behaviors throughout the game, including alerts for entering invalid commands.
   *
   * @param key Name of the message/alert to be displayed.
   * @return Returns the actual content (text) of in-game messages/alerts.
   */
  public String getNpcResponse(String key) {
    return npcResponse.getString(key) + "\n";
  }

  public void printMovesCounter(){
    System.out.println("Remaining Moves: " + Game.getUserMovesCounter());
  }

  public void printTitle() {
    System.out.println(getImportantDisplay("title"));
  }

  public void printBackStory() {
    System.out.println(getImportantDisplay("backstory"));
  }

  public void printInstructions() {
    System.out.println(getImportantDisplay("instruction"));
  }

  public void printHelp() {
    System.out.println(getImportantDisplay("help"));
  }

  public void printMenu() {
    System.out.println(getImportantDisplay("menu"));
  }

  public void printMap() {
    System.out.println(getImportantDisplay("map"));
  }

  public void printWinMessage() {
    System.out.println(getImportantDisplay("win"));
  }

  public void printLoseMessage() {
    System.out.println(getImportantDisplay("lose"));
  }

  public static void printGameLoadFailed(boolean startNewGame) {
    System.out.println("There is no game to load");
    if (startNewGame) {
      System.out.println("Starting a new game soon...");
    }
  }

  public static void printGameSaveSuccess() {
    System.out.println("Your game has been saved!");
  }

  public static void printUserCommandPrompt() {
    System.out.println("Enter a command: ");
  }

  public static void printInvalidInputWarning(boolean isLengthError) {
    System.out.println("WARNING: Invalid input!");
    if (isLengthError) {
      System.out.println("Input CANNOT be more than two words!");
    }
  }

  public void printAskName() {
    System.out.println(getNpcResponse("ask_name"));
  }

  public void printWelcomeMessage(String playerName) {
    System.out.printf(getNpcResponse("welcome"), playerName);
  }

  public void printWelcomeBackMessage(String playerName) {
    // Print Welcome back + name
    System.out.printf(getNpcResponse("welcome_back") + "\n", playerName);
  }

  public void printHouseItem(String houseName, String houseItems) {
    System.out.printf(getNpcResponse("house_item"), houseName, houseItems);
  }

  public void printItemInInventory(String playerName, String playerPosition, String playerItems) {
    System.out.printf(getNpcResponse("item_in_inventory"), playerName, playerPosition, playerItems);
  }

  public void printInventoryMessage(ArrayList playerItems) {
    System.out.printf(getNpcResponse("show_inventory"), playerItems);
  }

  public void printValidMoves(String north, String east, String south, String west) {
    System.out.println(north + east + south + west);
  }

  public void printNoMovesLeftMessage(){
    System.out.println("Sorry, you don't have any moves remaining...");
  }
  public void printInvalidDirectionsMessage(String direction) {
    System.out.printf(getNpcResponse("invalid_direction"), direction);
  }

  public void printPlayersMove(String playerName, String direction, String playerPosition) {
    System.out.printf(getNpcResponse("players_move"), playerName, direction, playerPosition);
  }

  public void printGetItemMessage(String temp) {
    System.out.printf(getNpcResponse("get_items"), temp);
  }

  public void printNoItemError() {
    System.out.println(getNpcResponse("no_item_error"));
  }

  public void printGetItemFailed() {
    System.out.println(getNpcResponse("Could not find the item"));
  }

  public void knockDoorFirst() {
    System.out.println(getNpcResponse("knock_door_first"));
  }

  public void knockDoor() {
    System.out.println(getNpcResponse("knock_door"));
  }

  public void printKarenCallingCops() {
    System.out.println(getNpcResponse("karen_calling_cops"));
  }

  public void printPlayerArrested() {
    System.out.println(getNpcResponse("player_arrested"));
  }

  public void printExitGameMessage() {
    System.out.println(getNpcResponse("exit_game"));
  }

  public void printKnockToUseItem() {
    System.out.println(getNpcResponse("knock_to_use_item"));
  }

  public void printRemoveItem(String response, String item) {
    System.out.printf(getNpcResponse(response), item);
  }

  public void printDraculaTooth() {
    System.out.println(getNpcResponse("draculas_tooth"));
  }

  public void printGiveWitchItem(String item) {
    System.out.printf(getNpcResponse("give_witch_ingredient"), item);
  }

  public void printIncorrectWitchItem(String item) {
    System.out.printf(getNpcResponse("incorrect_witch_ingredient"), item);
  }

  public void printCompleteWitchPotion() {
    System.out.println(getNpcResponse("complete_witch_potion"));
  }

  public void printKarenDefeated(String item) {
    if (item.equals("badge")) {
      System.out.println(getNpcResponse("karen_defeated_badge"));
    } else if (item.equals("potion")) {
      System.out.println(getNpcResponse("karen_defeated_potion"));
    } else if (item.equals("ruby")) {
      System.out.println(getNpcResponse("karen_defeated_ruby"));
    }
  }

  public void printPlayNewGame(){
    System.out.println("Would you like to play a new game?\n"
        + "if not, enter:\n"
        + "n\n"
        + "enter anything else to play a new game");
  }


}
