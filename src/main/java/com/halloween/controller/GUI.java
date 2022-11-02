package com.halloween.controller;

import com.halloween.model.Neighborhood;
import com.halloween.model.Player;
import com.halloween.view.GUI.TitleScreen;

public class GUI {
  TitleScreen titleScreen;
  Game game;
  Player player;
  String currentLocation;
  Neighborhood neighborhood;

  public GUI(Game game) {
    titleScreen = new TitleScreen(game);
  }

  public void startNewGame() {

  }

}
