package com.halloween.controller;

import com.halloween.model.Neighborhood;
import com.halloween.model.Player;
import com.halloween.view.GUI.GuiView;

public class Controller {

  Game game;
  GuiView view;
  Player player;
  String currentLocation;
  Neighborhood neighborhood;

  public Controller(Game game, GuiView view) {
    this.game = game;
    this.view = view;
  }

  public void startGame() {
    view.displayTitleScreen();
  }

}
