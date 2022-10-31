package com.halloween;

import static com.halloween.controller.Game.getDisplay;
import static com.halloween.view.SoundEffects.muteSoundEffects;
import static com.halloween.view.SoundEffects.playSound;
import static com.halloween.view.SoundEffects.unmuteSoundEffects;

import com.halloween.controller.Game;
import com.halloween.controller.TextParser;
import com.halloween.model.State;
import java.awt.Color;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Main {
  private static Game game;
  private static String[] input;
  private static TextParser textParser;
  public static Game getGame(){
    return game;
  }

  public static void main(String[] args) throws IOException {

    //JFrame = a GUI window to add components to
    MyFrame frame = new MyFrame(); //Creates a frame
    frame.setTitle("HALLOWEEN VILLAGE"); //Sets the title of the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of the application
    frame.setResizable(false); //present frame from being resized
    frame.setSize(600,600); //sets the x-dimensions, and y-dimensions of the frame
    frame.setVisible(true); //makes frame visible

    ImageIcon image = new ImageIcon("./resource/Halloween_Village_Logo.png");
    frame.setIconImage(image.getImage());  //Change icon of the frame
    frame.getContentPane().setBackground(new Color(0, 51, 153)); //change color of the background



    Game game = new Game(); // an instance of the Game model (MVC)
    textParser = new TextParser(); // text parser used to parse the user input (command)
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
    // play sound effect (this to prevent NullPointerException from happening when the mute fx
    // or the unmute fx command is used before any sound clip object is created)
    playSound("/howl.wav");

    // allow the user to continue playing the game while the game's state is not terminal
    while (!game.getState().isTerminal()) {
      // show the current game's status
      game.showStatus();
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
      } else if (input[0].equals("go")) {
        game.movePlayer(input[1]);
      } else if (input[0].equals("knock")) {
        game.knockOnDoor();
      } else if (input[0].equals("get")) {
        game.getItem();
      } else if (input[0].equals("god")) {
        game.godModeGetItem(input[1]);
      } else if (input[0].equals("use") && input[1] != null) {
        game.useItem(input[1]);
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
    getDisplay().printPlayNewGame();
    playNewGame();
  }

  public static void playNewGame(){
    String[] NewInput = textParser.userInput();
    if(!NewInput[0].equals("n")) {
    playGame(new Game(), textParser);
    }
  }

}
