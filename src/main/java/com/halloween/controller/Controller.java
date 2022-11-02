package com.halloween.controller;

import com.halloween.model.Neighborhood;
import com.halloween.model.Player;
import com.halloween.view.GUI.GuiView;
import com.halloween.view.GUI.TitleScreen;
import com.halloween.view.GUI.mapScreen;
import java.io.IOException;

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
