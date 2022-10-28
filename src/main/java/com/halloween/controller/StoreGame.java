package com.halloween.controller;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.halloween.model.Neighborhood;
import com.halloween.model.Player;
import com.halloween.model.State;
import com.halloween.view.View;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This class is responsible for saving the current game data and loading previously saved game
 * data.
 */
public class StoreGame {

  /**
   * Loads previously saved game data from a JSON file and returns .
   *
   * @param resourceFile Name of the resource file to read in.
   * @param type         The type of the model class that matches the data to be read.
   * @param gson         An instance of {@link Gson} used to convert JSON data to Java object and
   *                     vice versa.
   * @param <T>          Type of the data/model class.
   * @return Returns either an instance of model class if the load was successful. Otherwise,
   * returns null.
   */
  public <T> T loadGame(String resourceFile, Type type, Gson gson) {
    try {
      URL url = StoreGame.class.getProtectionDomain().getCodeSource().getLocation();
      File jar = new File(url.toURI());
      File f = new File(jar.getParent(), resourceFile);
      try {
        if (f.exists()) {
          Reader reader = new InputStreamReader(new FileInputStream(f));
          return gson.fromJson(reader, type);
        } else {
          View.printGameLoadFailed(true);
        }
      } catch (FileNotFoundException e) {
        View.printGameLoadFailed(false);
      }
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  /**
   * Saves the complete current game data into three separate JSON files.
   *
   * @param state        The state of the current game (e.g., terminal or still-playing).
   * @param player       The player's data (e.g., player's name).
   * @param neighborhood The neighborhood data (locations in the game).
   */
  public void saveGame(State state, Player player, Neighborhood neighborhood) {
    writeFile(state, "state.json");
    writeFile(player, "player.json");
    writeFile(neighborhood, "neighborhood.json");
    View.printGameSaveSuccess();
  }

  /**
   * Writes a JSON file that stores a part of a complete game data.
   *
   * @param gameObject An instance of a model class representing the state of current game.
   * @param name       Name of the file in which game data will be written.
   * @param <T>        Type of the data/model class.
   */
  private <T> void writeFile(T gameObject, String name) {
    Gson gson = new Gson();
    try {
      String filepath = new File(name).getAbsolutePath();
      FileWriter writer = new FileWriter(filepath, false);
      gson.toJson(gameObject, writer);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JsonIOException e) {
      throw new RuntimeException();
    }
  }

  /**
   * Removes three JSON files that comprise a complete game data.
   */
  public void removeJsonFiles() {
    removeFile("state.json");
    removeFile("player.json");
    removeFile("neighborhood.json");
  }

  /**
   * Removes a target file.
   *
   * @param resourceFile The name of the file to be deleted.
   */
  private void removeFile(String resourceFile) {
    try {
      URL url = StoreGame.class.getProtectionDomain().getCodeSource().getLocation();
      File jar = new File(url.toURI());
      File f = new File(jar.getParentFile().getParent(), resourceFile);
      Files.deleteIfExists(Path.of(f.getAbsolutePath()));
    } catch (URISyntaxException | IOException e) {
      throw new RuntimeException(e);
    }
  }

}
