package com.halloween.model;

/**
 * This class acts as a model for the current state of a game (a game can be either won, lost, or
 * still playing).
 */
public enum State {

  WIN,
  LOSE,
  PLAY {
    /**
     * Changes the state of current game to be not terminal.
     *
     * @return Returns a boolean (false), indicating that the state of current game is not terminal.
     */
    @Override
    public boolean isTerminal() {
      return false;
    }
  };

  // tells game whether the state causes the game to end

  /**
   * Changes the state of current game to be terminal.
   *
   * @return Returns a boolean (true), indicating that the state of current game is terminal.
   */
  public boolean isTerminal() {
    return true;
  }

}
