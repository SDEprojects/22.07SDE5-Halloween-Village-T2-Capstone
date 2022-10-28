package com.halloween.controller;

import com.halloween.view.View;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Set;

/**
 * This class is responsible for parsing a user input and checking whether the input is a valid game
 * command.
 */
public class TextParser {

  private static Set<String> DIRECTIONS = Set.of(
      "north", "east", "south", "west"
  );
  private Reader reader;
  private BufferedReader buffer;

  /**
   * Initializes an instance of {@link TextParser}.
   */
  public TextParser() {
    this.reader = new InputStreamReader(System.in);
    this.buffer = new BufferedReader(reader);
  }

  /**
   * Prompts the user to enter a command, then sanitizes and separates the user input into an array
   * of words.
   *
   * @return Returns an array of words representing the user input separated by whitespace(s).
   */
  public String[] userInput() {
    String input = "";
    String[] inputArray;
    do {
      View.printUserCommandPrompt();
      try {
        input = buffer.readLine().trim().toLowerCase();
      } catch (Exception e) {
        System.out.println("Error Exception: " + e);
      }
      inputArray = input.split("\\s+");
    } while (!isInputValid(inputArray));
    return inputArray;
  }

  /**
   * Checks the validity of the user's command and returns the result.
   * <p>If the user input is invalid, an alert will be displayed to the user.</p>
   *
   * @param input User command (user input split into words).
   * @return Returns a boolean representing the validity of user command.
   */
  public boolean isInputValid(String[] input) {
    boolean valid = false;

    // if input array is greater than two it is not value
    if (input.length > 2) {
      View.printInvalidInputWarning(true);
      return false;
      // if input has one word it can either be help or quit otherwise false
    } else if (input.length == 1) {
      if (input[0].equals("quit") || input[0].equals("help") || input[0].equals("knock")
          || input[0].equals("inventory") || input[0].equals("map") || input[0].equals("save")) {
        return true;
      }
    } else {
      if (input[0].equals("go") && DIRECTIONS.contains(input[1])) {
        return true;
      } else if (input[0].equals("god") && (input[1].equals("ruby") || input[1].equals("potion")
          || input[1].equals("badge"))) {
        return true;
      } else if (input[0].equals("get") && input[1].equals("item")) {
        return true;
      } else if (input[0].equals("knock")) {
        return true;
      } else if (input[0].equals("new") && input[1].equals("game")) {
        return true;
      } else if (input[0].equals("load") && input[1].equals("game")) {
        return true;
      } else if (input[0].equals("help")) {
        return true;
      } else if (input[0].equals("quit")) {
        return true;
      } else if (input[0].equals("inventory")) {
        return true;
      } else if (input[0].equals("use")) {
        return true;
      } else if (input[0].equals("map")) {
        return true;
      } else if (input[0].equals("save")) {
        return true;
      } else if (input[0].equals("start") && input[1].equals("music")) {
        return true;
      } else if (input[0].equals("stop") && input[1].equals("music")) {
        return true;
      } else if (input[0].equals("increase") && input[1].equals("volume")) {
        return true;
      } else if (input[0].equals("decrease") && input[1].equals("volume")) {
        return true;
      } else if (input[0].equals("mute") && input[1].equals("fx")) {
        return true;
      } else if (input[0].equals("unmute") && input[1].equals("fx")) {
        return true;
      }
    }
    View.printInvalidInputWarning(false);
    return valid;
  }

}
