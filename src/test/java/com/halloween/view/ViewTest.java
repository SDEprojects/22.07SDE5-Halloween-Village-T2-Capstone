package com.halloween.view;

import static org.junit.jupiter.api.Assertions.*;

import com.halloween.controller.Game;
import com.halloween.model.Player;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

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

}