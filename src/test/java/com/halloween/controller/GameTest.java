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

    player.addItem("ruby");
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

  /*
    DONE: ITEM USE TESTS
   */
  @Test
  void player_should_be_able_to_use_an_item_they_have_in_inventory() {
    player.addItem("ruby");
    player.addItem("candy");
    player.addItem("thing");
    // knock door first to interact with the location
    game.knockOnDoor();

    // use ruby
    game.useItem("ruby");
    // there should be 2 items in the inventory now
    assertEquals(2, player.getItems().size());
    // first item should be candy now
    assertEquals("candy", player.getItems().get(0));
    // second item should be thing now
    assertEquals("thing", player.getItems().get(1));

    // use candy
    game.useItem("candy");
    // there should be 1 item in the inventory now
    assertEquals(1, player.getItems().size());
    // the only item left is thing
    assertEquals("thing", player.getItems().get(0));

    // use thing
    game.useItem("thing");
    // there should be no item in the inventory now
    assertEquals(0, player.getItems().size());
  }

  @Test
  void player_should_NOT_be_able_to_use_an_item_they_do_not_have() {
    player.addItem("ruby");
    player.addItem("candy");
    player.addItem("thing");
    // knock door first to interact with the location
    game.knockOnDoor();

    // use badge
    game.useItem("badge");
    // there should be 3 items in the inventory now
    assertEquals(3, player.getItems().size());
    // first item should still be ruby
    assertEquals("ruby", player.getItems().get(0));
    // second item should still be candy
    assertEquals("candy", player.getItems().get(1));
    // third item should still be thing
    assertEquals("thing", player.getItems().get(2));
  }

  @Test
  void item_should_NOT_be_used_if_player_did_not_knock_before() {
    player.addItem("ruby");
    player.addItem("candy");
    player.addItem("thing");

    // use ruby
    game.useItem("ruby");
    // there should be 3 items in the inventory now
    assertEquals(3, player.getItems().size());
    // first item should still be ruby
    assertEquals("ruby", player.getItems().get(0));
    // second item should still be candy
    assertEquals("candy", player.getItems().get(1));
    // third item should still be thing
    assertEquals("thing", player.getItems().get(2));

    // use candy
    game.useItem("candy");
    // there should be 3 items in the inventory now
    assertEquals(3, player.getItems().size());
    // first item should still be ruby
    assertEquals("ruby", player.getItems().get(0));
    // second item should still be candy
    assertEquals("candy", player.getItems().get(1));
    // third item should still be thing
    assertEquals("thing", player.getItems().get(2));

    // use thing
    game.useItem("thing");
    // there should be 3 items in the inventory now
    assertEquals(3, player.getItems().size());
    // first item should still be ruby
    assertEquals("ruby", player.getItems().get(0));
    // second item should still be candy
    assertEquals("candy", player.getItems().get(1));
    // third item should still be thing
    assertEquals("thing", player.getItems().get(2));
  }

}