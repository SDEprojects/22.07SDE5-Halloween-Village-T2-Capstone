package com.halloween.controller;

import com.halloween.model.Neighborhood;
import com.halloween.model.Player;
import com.halloween.view.GUI.TitleScreen;
import com.halloween.view.GUI.mapScreen;
import java.io.IOException;

public class GUI {

  TitleScreen titleScreen;
  mapScreen mapScreen;
  Game game;
  Player player;
  String currentLocation;
  Neighborhood neighborhood;

  public GUI(Game game) throws IOException {
    titleScreen = new TitleScreen(game);
    mapScreen = new mapScreen(game);
  }

  public void startNewGame() {

  }

}
