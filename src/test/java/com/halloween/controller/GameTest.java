package com.halloween.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.halloween.model.Player;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {

  Game game;
  Player player;

  @BeforeEach
  void setUp() {
     game = new Game();
     player = game.getPlayer();
  }

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

}