package com.halloween;

import com.halloween.controller.Controller;
import com.halloween.controller.Game;
import com.halloween.view.gui.GuiView;
import com.halloween.view.View;
import java.io.IOException;

public class Main {

  // TODO: BELOW ARE FIELDS FROM THE TEXT-BASED VERSION.
  //  REMOVE THEM BEFORE RELEASE V2.3.
  //  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> REMOVE BEFORE FLIGHT <<<<<
//  private static Game game;
//  private static String[] input;
//  private static TextParser textParser;
//  static boolean gameStarted;
//
//  public static Game getGame() {
//    return game;
//  }

  public static void main(String[] args) {
    Game game = new Game();
    GuiView view = new GuiView();
    Controller controller = new Controller(game, view);

    controller.startProgram();
  }

  // TODO: BELOW IS THE TEXT-BASED VERSION'S MAIN METHOD. IT'S LEFT HERE FOR REFERENCE.
  //  REMOVE IT BEFORE RELEASE V2.3.
  //  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> REMOVE BEFORE FLIGHT <<<<<
//  public static void main(String[] args) throws IOException {
//    game = new Game(); // an instance of the Game model (MVC)
//    textParser = new TextParser(); // text parser used to parse the user input (command)
//    gameStarted = false; // variable used to decide whether a game has started or not
//    // check if a game has started or not
//    while (!gameStarted) {
//      // if a game has not started yet, show the game menu and prompt the user to enter a command
//      // and parse their command and save the parsed command as a variable
//      game.showMenu();
//      input = textParser.userInput();
//
//      // check if the parsed user input matches a valid command and if so, invoke an appropriate
//      // method related to the command to either terminate the program or to start a game,
//      // whether it be a new game or a saved game.
//      if (input[0].equals("quit")) {
//        game.getDisplay().printPlayNewGame();
//        playNewGame();
//      } else if (input[0].equals("new") && input[1].equals("game")) {
//        gameStarted = true;
//      } else if (input[0].equals("load") && input[1].equals("game")) {
//        gameStarted = true;
//        game = game.loadGame();
//      }
//    }
//
//    // Initialize the game object and get username
//    game.showTitle();
//    game.showBackstory();
//    game.showInstructions();
//    game.greetPlayer();
//
//    // Play game until user wins, loses, or quits
//    playGame(game, textParser);
//    playNewGame();
//  }
//
//  public static void playGame(Game game, TextParser textParser) throws IOException {
//    game.setState(State.PLAY);
//    // start the music
//    game.startMusic();
//    // play sound effect (this to prevent NullPointerException from happening when the mute fx
//    // or the unmute fx command is used before any sound clip object is created)
//    playSound("/howl.wav");
//
//    // allow the user to continue playing the game while the game's state is not terminal
//    while (!game.getState().isTerminal()) {
//      // show the current game's status
//      game.showStatus();
//      // parse the user input into an array of words
//      input = textParser.userInput();
//
//      // check if the parsed user input matches a valid command and if so, invoke an appropriate
//      // method related to the command
//      if (input[0].equals("quit")) {
//        game.getDisplay().printPlayNewGame();
//        playNewGame();
//      } else if (input[0].equals("save")) {
//        game.saveGame();
//      } else if (input[0].equals("help")) {
//        game.showHelp();
//      } else if (input[0].equals("inventory")) {
//        game.showInventory();
//      } else if (input[0].equals("map")) {
//        game.showMap();
//      } else if (input[0].equals("start") && input[1].equals("music")) {
//        game.startMusic();
//      } else if (input[0].equals("stop") && input[1].equals("music")) {
//        game.stopMusic();
//      } else if (input[0].equals("increase") && input[1].equals("volume")) {
//        game.increaseVolume();
//      } else if (input[0].equals("decrease") && input[1].equals("volume")) {
//        game.decreaseVolume();
//      } else if (input[0].equals("mute") && input[1].equals("fx")) {
//        muteSoundEffects();
//      } else if (input[0].equals("unmute") && input[1].equals("fx")) {
//        unmuteSoundEffects();
//      } else if (input[0].equals("go")) {
//        game.movePlayer(input[1]);
//      } else if (input[0].equals("knock")) {
//        game.knockOnDoor();
//      } else if (input[0].equals("get")) {
//        game.getItem();
//      } else if (input[0].equals("god")) {
//        game.godModeGetItem(input[1]);
//      } else if (input[0].equals("use") && input[1] != null) {
//        game.useItem(input[1]);
//      } else if (input[0].equals("n") || input[0].equals("y")) {
//        game.getDisplay().printHowToStartNewGame();
//      }
//    }
//    // at this point, the game's state is terminal so check the win/lose status of the game
//    if (game.getState().equals(State.WIN)) {
//      game.showWin();
//    } else {
//      game.showLose();
//    }
//    game.removeFiles();
//    game.getDisplay().printPlayNewGame();
//    playNewGame();
//  }
//
//  public static void playNewGame() throws IOException {
//    String[] newInput = textParser.userInput();
//    if (newInput[0].equals("n")) {
//      game.quitGame();
//    } else if (newInput[0].equals("y")) {
//      gameStarted = true;
//      game.setState(State.PLAY);
//      main(newInput);
//    }
//  }

}
