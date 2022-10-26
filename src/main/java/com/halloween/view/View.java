package com.halloween.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
  private HashMap<String, HashMap<String, String>> dialogue =
      (HashMap<String, HashMap<String, String>>)
          dialogueList
              .stream()
              .collect(Collectors
                  .toMap(map -> String.join("", map.keySet()),
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

}
