package com.halloween.view;

import static org.junit.jupiter.api.Assertions.*;

import com.halloween.controller.Game;
import com.halloween.model.Player;
import com.halloween.model.State;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViewTest {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  Game game;

  @BeforeEach
  void setUp() {
    game = new Game();
    game.setState(State.PLAY);
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

  /*
    DONE: GOD MODE CHEAT VIEW TESTS
   */
  @Test
  void godModeGetItem_should_display_correct_message_when_invoked_with_VALID_item_badge() {
    game.godModeGetItem("badge");
    assertEquals("You added a badge to your items!", outputStreamCaptor.toString().trim());
  }

  @Test
  void godModeGetItem_should_display_correct_message_when_invoked_with_VALID_item_potion() {
    game.godModeGetItem("potion");
    assertEquals("You added a potion to your items!", outputStreamCaptor.toString().trim());
  }

  @Test
  void godModeGetItem_should_display_correct_message_when_invoked_with_VALID_item_ruby() {
    game.godModeGetItem("ruby");
    assertEquals("You added a ruby to your items!", outputStreamCaptor.toString().trim());
  }

  @Test
  void godModeGetItem_should_display_alert_when_invoked_with_INVALID_item() {
    game.godModeGetItem("cake");

    assertEquals(
        "Error: invalid item name was entered. You can get badge, potion, or ruby with this command.",
        outputStreamCaptor.toString().trim());
  }

  /*
    DONE: PLAYER MOVES COUNTER VIEW TESTS
   */
  @Test
  void correct_num_of_remaining_moves_are_displayed_when_user_moves_to_a_new_location() {
    game.showStatus();
    String[] outputArray = outputStreamCaptor.toString().trim().split(" ");
    String remainingMoves = outputArray[outputArray.length - 1];
    assertEquals("10", remainingMoves);

    game.movePlayer("north");
    game.showStatus();
    outputArray = outputStreamCaptor.toString().trim().split(" ");
    remainingMoves = outputArray[outputArray.length - 1];
    assertEquals("9", remainingMoves);

    game.movePlayer("south");
    game.showStatus();
    outputArray = outputStreamCaptor.toString().trim().split(" ");
    remainingMoves = outputArray[outputArray.length - 1];
    assertEquals("8", remainingMoves);
  }

  @Test
  void correct_num_of_remaining_moves_are_displayed_when_user_tries_to_move_to_INVALID_direction() {
    game.showStatus();
    String[] outputArray = outputStreamCaptor.toString().trim().split(" ");
    String remainingMoves = outputArray[outputArray.length - 1];
    assertEquals("10", remainingMoves);

    game.movePlayer("candy");
    game.showStatus();
    outputArray = outputStreamCaptor.toString().trim().split(" ");
    remainingMoves = outputArray[outputArray.length - 1];
    assertEquals("10", remainingMoves);

    game.movePlayer("far");
    game.showStatus();
    outputArray = outputStreamCaptor.toString().trim().split(" ");
    remainingMoves = outputArray[outputArray.length - 1];
    assertEquals("10", remainingMoves);
  }

}