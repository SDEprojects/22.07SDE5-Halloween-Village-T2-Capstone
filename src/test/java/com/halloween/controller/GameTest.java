package com.halloween.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.halloween.model.Player;
import com.halloween.model.State;
import java.util.ArrayList;
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
    DONE: GOD MODE CHEAT TESTS
   */

  @Test
  void godModeGetItem_should_add_valid_items_to_inventory() {
    // ArrayLists maintain insertion order. For more info, refer to the link below.
    // (https://docs.oracle.com/javase/7/docs/api/java/util/List.html).
    ArrayList<String> items = player.getItems();

    game.godModeGetItem("ruby");
    assertEquals(1, items.size());
    assertEquals("ruby", items.get(0));

    game.godModeGetItem("badge");
    assertEquals(2, items.size());
    assertEquals("ruby", items.get(0));
    assertEquals("badge", items.get(1));

    game.godModeGetItem("potion");
    assertEquals(3, items.size());
    assertEquals("ruby", items.get(0));
    assertEquals("badge", items.get(1));
    assertEquals("potion", items.get(2));
  }

  @Test
  void godModeGetItem_should_NOT_add_invalid_items_to_inventory() {
    // this test only checks the correct behavior of the godModeGetItem method.
    // test for the correctness of messages being displayed is in ViewTest.
    ArrayList<String> items = player.getItems();

    game.godModeGetItem("candy");
    assertEquals(0, items.size());

    game.godModeGetItem("cake");
    assertEquals(0, items.size());
  }

  /*
    DONE: PLAYER MOVES COUNTER TESTS
   */
  @Test
  void movePlayer_should_decrement_remaining_moves_by_one_when_given_valid_direction() {
    // users get 10 moves initially
    assertEquals(10, game.getUserMovesCounter());

    game.movePlayer("north");
    assertEquals(9, game.getUserMovesCounter());

    game.movePlayer("east");
    assertEquals(8, game.getUserMovesCounter());

    game.movePlayer("south");
    assertEquals(7, game.getUserMovesCounter());

    game.movePlayer("west");
    assertEquals(6, game.getUserMovesCounter());
  }

  @Test
  void movePlayer_should_NOT_decrement_remaining_moves_when_given_invalid_direction() {
    // user gets 10 moves initially
    assertEquals(10, game.getUserMovesCounter());

    game.movePlayer("far"); // invalid direction
    assertEquals(10, game.getUserMovesCounter());

    game.movePlayer("away"); // invalid direction
    assertEquals(10, game.getUserMovesCounter());
  }

  @Test
  void userMovesCounter_should_remain_the_same_if_player_did_not_move() {
    // user gets 10 moves initially
    assertEquals(10, game.getUserMovesCounter());

    game.getPlayer().addItem("ruby");
    assertEquals(10, game.getUserMovesCounter());

    game.knockOnDoor();
    assertEquals(10, game.getUserMovesCounter());

    game.useItem("ruby");
    assertEquals(10, game.getUserMovesCounter());
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