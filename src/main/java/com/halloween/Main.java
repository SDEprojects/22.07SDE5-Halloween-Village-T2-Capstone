package com.halloween;

import static com.halloween.view.SoundEffects.muteSoundEffects;
import static com.halloween.view.SoundEffects.unmuteSoundEffects;

import com.halloween.controller.Game;
import com.halloween.controller.TextParser;
import com.halloween.model.State;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    Game game = new Game(); // an instance of the Game model (MVC)
    TextParser textParser = new TextParser(); // text parser used to parse the user input (command)
    boolean gameStarted = false; // variable used to decide whether a game has started or not
    // check if a game has started or not
    while (!gameStarted) {
      // if a game has not started yet, show the game menu and prompt the user to enter a command
      // and parse their command and save the parsed command as a variable
      game.showMenu();
      String[] userInput = textParser.userInput();

      // check if the parsed user input matches a valid command and if so, invoke an appropriate
      // method related to the command to either terminate the program or to start a game,
      // whether it be a new game or a saved game.
      if (userInput[0].equals("quit")) {
        game.quitGame();
      } else if (userInput[0].equals("new") && userInput[1].equals("game")) {
        gameStarted = true;
        game.setState(State.PLAY);
      } else if (userInput[0].equals("load") && userInput[1].equals("game")) {
        gameStarted = true;
        game = game.loadGame();
        game.setState(State.PLAY);
      }
    }

    // Initialize the game object and get username
    game.showTitle();
    game.showBackstory();
    game.showInstructions();
    game.greetPlayer();

    // Play game until user wins, loses, or quits
    playGame(game, textParser);
  }

  private static void playGame(Game game, TextParser textParser) {
    // start the music
    game.startMusic();
    // placeholder for user input (command)
    String[] input;
    // show the current game's status
    game.showStatus();
    // allow the user to continue playing the game while the game's state is not terminal
    while (!game.getState().isTerminal()) {
      // parse the user input into an array of words
      input = textParser.userInput();

      // check if the parsed user input matches a valid command and if so, invoke an appropriate
      // method related to the command
      if (input[0].equals("quit")) {
        game.quitGame();
      } else if (input[0].equals("save")) {
        game.saveGame();
      } else if (input[0].equals("help")) {
        game.showHelp();
      } else if (input[0].equals("inventory")) {
        game.showInventory();
      } else if (input[0].equals("map")) {
        game.showMap();
      } else if (input[0].equals("start") && input[1].equals("music")) {
        game.startMusic();
      } else if (input[0].equals("stop") && input[1].equals("music")) {
        game.stopMusic();
      } else if (input[0].equals("increase") && input[1].equals("volume")) {
        game.increaseVolume();
      } else if (input[0].equals("decrease") && input[1].equals("volume")) {
        game.decreaseVolume();
      } else if (input[0].equals("mute") && input[1].equals("fx")) {
        muteSoundEffects();
      } else if (input[0].equals("unmute") && input[1].equals("fx")) {
        unmuteSoundEffects();
      } else {
        if (input[0].equals("go")) {
          game.movePlayer(input[1]);
        } else if (input[0].equals("knock")) {
          game.knockOnDoor();
        } else if (input[0].equals("get")) {
          game.getItem();
        } else if (input[0].equals("use") && input[1] != null) {
          game.useItem(input[1]);
        }
        game.showStatus();
      }
    }
    // at this point, the game's state is terminal so check the win/lose status of the game
    if (game.getState().equals(State.WIN)) {
      game.showWin();
      game.removeFiles();
    } else {
      game.showLose();
      game.removeFiles();
    }
  }

}
