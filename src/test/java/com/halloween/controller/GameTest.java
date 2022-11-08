package com.halloween.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.halloween.model.Player;
import com.halloween.model.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {

  Game game;
  Player player;

  @BeforeEach
  void setUp() {
    game = new Game();
    game.setState(State.PLAY);
    player = game.getPlayer();
  }

  /*
    DONE: ITEM GET TESTS
   */
  @Test
  void player_should_be_able_to_get_correct_items_from_the_location() {
    // initially, player should not have any item in their inventory
    assertEquals(0, player.getItems().size());

    // move player to neighbor's house
    game.movePlayer("north");
    assertEquals("neighbor's house", player.getPosition());
    // player should be able to get candy from here
    game.knockOnDoor();
    game.getItem();
    assertEquals(1, player.getItems().size()); // there should be 1 item in inventory now
    assertEquals("candy", player.getItems().get(0)); // the item should be candy

    // move player to grandma's house
    game.movePlayer("east");
    game.movePlayer("east");
    game.movePlayer("east");
    assertEquals("grandma's house", player.getPosition());
    // player should be able to get dentures from here
    game.knockOnDoor();
    game.getItem();
    assertEquals(2, player.getItems().size()); // there should be 2 items in inventory now
    assertEquals("candy", player.getItems().get(0)); // the first item should be candy
    assertEquals("dentures", player.getItems().get(1)); // the second item should be dentures
  }

  @Test
  void player_should_NOT_be_able_to_get_item_if_the_location_does_not_have_any_item() {
    // initially, player should not have any item in their inventory
    assertEquals(0, player.getItems().size());

    // must knock first before being able to interact with the location
    game.knockOnDoor();
    // current location is "your house" and there is no item
    // so player should not be able to get any item from the current location
    game.getItem();
    assertEquals(0, player.getItems().size());

    // move player to dracula's house
    game.movePlayer("north");
    game.movePlayer("east");
    game.movePlayer("east");
    assertEquals("dracula's mansion", player.getPosition());
    // player does not have an item to exchange at the moment
    // so player should not be able to get any item from the current location
    game.knockOnDoor();
    game.getItem();
    assertEquals(0, player.getItems().size());

    // move player to witch's den
    game.movePlayer("south");
    assertEquals("witch's den", player.getPosition());
    // player does not have an item to exchange at the moment
    // so player should not be able to get any item from the current location
    game.knockOnDoor();
    game.getItem();
  }

  /*
    DONE: PLAYER MOVES COUNTER TESTS
   */
  @Test
  void movePlayer_should_decrement_remaining_moves_by_one_when_given_valid_direction() {
    // users get 15 moves initially
    assertEquals(15, game.getPlayer().getUserMovesCounter());

    game.movePlayer("north");
    assertEquals(14, game.getPlayer().getUserMovesCounter());

    game.movePlayer("east");
    assertEquals(13, game.getPlayer().getUserMovesCounter());

    game.movePlayer("south");
    assertEquals(12, game.getPlayer().getUserMovesCounter());

    game.movePlayer("west");
    assertEquals(11, game.getPlayer().getUserMovesCounter());
  }

  @Test
  void movePlayer_should_NOT_decrement_remaining_moves_when_given_invalid_direction() {
    // user gets 15 moves initially
    assertEquals(15, game.getPlayer().getUserMovesCounter());

    game.movePlayer("far"); // invalid direction
    assertEquals(15, game.getPlayer().getUserMovesCounter());

    game.movePlayer("away"); // invalid direction
    assertEquals(15, game.getPlayer().getUserMovesCounter());
  }

  @Test
  void game_ends_and_user_loses_when_remaining_number_of_moves_is_zero() {
    // initially game is not terminal
    assertFalse(game.getState().isTerminal());
    // initially number of moves is 10
    game.movePlayer("north"); // 9
    game.movePlayer("east"); // 8
    game.movePlayer("south"); // 7
    game.movePlayer("west"); // 6
    game.movePlayer("north"); // 5
    game.movePlayer("east"); // 4
    game.movePlayer("south"); // 3
    game.movePlayer("west"); // 2
    game.movePlayer("north"); // 1
    game.movePlayer("east"); // 0
    game.movePlayer("east"); // remaining number of moves is less than 0, game should end
    assertEquals(State.LOSE, game.getState()); // ensure that the game's state is set to be lose
    assertTrue(game.getState().isTerminal()); // ensure that the game's state is now terminal
  }

}