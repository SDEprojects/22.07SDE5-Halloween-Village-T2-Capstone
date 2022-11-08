package com.halloween;

import com.halloween.controller.Controller;
import com.halloween.controller.Game;
import com.halloween.view.gui.GuiView;

public class Main {

  private static Controller controller;

  public static void main(String[] args) {
    Game game = new Game(); // Legacy model / controller
    GuiView view = new GuiView(); // Newly implemented view (GUI)
    controller = new Controller(game, view); // Newly implemented controller (GUI)

    controller.startProgram();
  }

}
