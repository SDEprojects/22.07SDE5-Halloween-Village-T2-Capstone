package com.halloween.view.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class GameScreen {

  private static final Font TOP_BUTTON_FONT = new Font("Serif", Font.PLAIN, 40);
  private static final Font GAME_TEXT_FONT = new Font("Serif", Font.PLAIN, 25);
  private JPanel topPanel;
  private JPanel sidePanel;
  private JPanel mainPanel;
  private JPanel firstPanelBelowMain;
  private JPanel bottomPanel;
  private JLabel locationLabel;
  private JLabel inventoryLabel;
  private JLabel remainingMovesLabel;
  private JLabel npcLabel;
  private JLabel gameTextLabel;
  private JButton helpButton;
  private JButton mapButton;
  private JButton musicButton;
  private JButton fxButton;
  private JButton saveGameButton;
  private JButton quitButton;
  private JButton goNorthButton;
  private JButton goEastButton;
  private JButton goSouthButton;
  private JButton goWestButton;
  private JButton knockButton;
  private JButton getItemButton;
  private JButton useItemButton;

  public GameScreen() {
    inventoryLabel = createInventoryLabel();
    remainingMovesLabel = createRemainingMovesLabel();
    locationLabel = createLocationLabel();
    npcLabel = createNpcLabel();
    gameTextLabel = createGameTextLabel();
    topPanel = createTopPanel();
    sidePanel = createSidePanel();
    mainPanel = createMainPanel();
    bottomPanel = createBottomPanel();
    firstPanelBelowMain = createFirstPanelBelowMain();
  }

  public JPanel createTopPanel() {
    helpButton = new JButton("HELP");
    helpButton.setFont(TOP_BUTTON_FONT);

    mapButton = new JButton("MAP");
    mapButton.setFont(TOP_BUTTON_FONT);

    musicButton = new JButton("MUSIC");
    musicButton.setFont(TOP_BUTTON_FONT);

    fxButton = new JButton("FX");
    fxButton.setFont(TOP_BUTTON_FONT);

    saveGameButton = new JButton("SAVE GAME");
    saveGameButton.setFont(TOP_BUTTON_FONT);

    quitButton = new JButton("QUIT GAME");
    quitButton.setBackground(Color.RED);
    quitButton.setOpaque(true);
    quitButton.setFont(TOP_BUTTON_FONT);

    JPanel panel = new JPanel(); //Creates the top panel
    panel.add(helpButton);
    panel.add(mapButton);
    panel.add(musicButton);
    panel.add(fxButton);
    panel.add(saveGameButton);
    panel.add(quitButton);
    panel.setBorder(new LineBorder(Color.WHITE, 3)); //Sets the border
    panel.setBackground(Color.ORANGE); //Sets the background
    panel.setLayout(new FlowLayout(10)); //Aligns the layout
    panel.setBounds(0, 0, 1400, 100);
    return panel;
  }

  public JPanel createSidePanel() {
    JPanel panel = new JPanel();
    panel.setBorder(new LineBorder(Color.WHITE, 3));
    panel.setBounds(0, 100, 300, 700);
    panel.setBackground(Color.RED);
//    panel.add(new JLabel(""));
    panel.add(getInventoryLabel());
    panel.add(getRemainingMovesLabel());
    return panel;

  }

  public JPanel createMainPanel() {
    JPanel panel = new JPanel();
    panel.setBorder(new LineBorder(Color.WHITE, 3));
    panel.setBounds(300, 100, 1100, 80);
    panel.setBackground(Color.CYAN);
    panel.add(getLocationLabel());
    return panel;
  }

  public JPanel createFirstPanelBelowMain() {
    JPanel panel = new JPanel();

//    GridLayout layout = new GridLayout(3, 2);
//    panel.setLayout(layout);
//    layout.addLayoutComponent("game text ",getGameTextLabel());
//    layout.addLayoutComponent("npc ",getNpcLabel());

    panel.setBorder(new LineBorder(Color.WHITE, 3));
    panel.setBounds(300, 180, 1100, 620);
    panel.setBackground(Color.GRAY);

    panel.add(getGameTextLabel());
    panel.add(getNpcLabel());
    return panel;
  }

  public JPanel createBottomPanel() {
    JPanel panel = new JPanel();

    goNorthButton = new JButton("GO NORTH");
    goNorthButton.setFont(TOP_BUTTON_FONT);
    goNorthButton.setVisible(false);

    goEastButton = new JButton("GO EAST");
    goEastButton.setFont(TOP_BUTTON_FONT);
    goEastButton.setVisible(false);

    goSouthButton = new JButton("GO SOUTH");
    goSouthButton.setFont(TOP_BUTTON_FONT);
    goSouthButton.setVisible(false);

    goWestButton = new JButton("GO WEST");
    goWestButton.setFont(TOP_BUTTON_FONT);
    goWestButton.setVisible(false);

    knockButton = new JButton("KNOCK");
    knockButton.setFont(TOP_BUTTON_FONT);

    getItemButton = new JButton("GET ITEM");
    getItemButton.setFont(TOP_BUTTON_FONT);

    useItemButton = new JButton("USE ITEM");
    useItemButton.setFont(TOP_BUTTON_FONT);

    panel.add(goNorthButton);
    panel.add(goEastButton);
    panel.add(goSouthButton);
    panel.add(goWestButton);
    panel.add(knockButton);
    panel.add(getItemButton);
    panel.add(useItemButton);
    panel.setBorder(new LineBorder(Color.WHITE, 3)); //Sets the border
    panel.setBounds(0, 800, 1400, 200);
    panel.setBackground(Color.WHITE); //Sets the background
    return panel;
  }

  public JLabel createLocationLabel() {
    JLabel label = new JLabel("", SwingConstants.CENTER);
    label.setFont(GAME_TEXT_FONT);
    label.setOpaque(true);
    label.setBorder(new LineBorder(Color.WHITE, 3));
    label.setBounds(0, 100, 200, 100);
    return label;
  }

  public JLabel createInventoryLabel() {
    JLabel label = new JLabel("", SwingConstants.LEFT);
    label.setFont(GAME_TEXT_FONT);
    label.setOpaque(true);
    label.setBorder(new LineBorder(Color.WHITE, 3));
//    label.setBounds(0, 550, 200, 300);
    return label;
  }

  public JLabel createRemainingMovesLabel() {
    JLabel label = new JLabel("", SwingConstants.LEADING);
    label.setFont(GAME_TEXT_FONT);
    label.setOpaque(true);
    label.setBorder(new LineBorder(Color.WHITE, 3));
    label.setBounds(0, 10, 900, 100);
    return label;
  }

  public JLabel createNpcLabel() {
    JLabel label = new JLabel("");
    label.setFont(GAME_TEXT_FONT);
    label.setOpaque(true);
    label.setBorder(new LineBorder(Color.WHITE, 3));
//    label.setBounds(0, 10, 900, 100);
    return label;
  }

  public JLabel createGameTextLabel() {
    JLabel label = new JLabel("");
    label.setFont(GAME_TEXT_FONT);
    label.setOpaque(true);
    label.setBorder(new LineBorder(Color.WHITE, 3));
//    label.setBounds(10, 100, 100, 300);
    return label;
  }

  public JPanel getTopPanel() {
    return topPanel;
  }

  public JPanel getSidePanel() {
    return sidePanel;
  }

  public JPanel getMainPanel() {
    return mainPanel;
  }

  public JPanel getFirstPanelBelowMain() {
    return firstPanelBelowMain;
  }


  public JPanel getBottomPanel() {
    return bottomPanel;
  }

  public JLabel getLocationLabel() {
    return locationLabel;
  }

  public JLabel getInventoryLabel() {
    return inventoryLabel;
  }

  public JLabel getRemainingMovesLabel() {
    return remainingMovesLabel;
  }

  public JLabel getNpcLabel() {
    return npcLabel;
  }

  public JLabel getGameTextLabel() {
    return gameTextLabel;
  }

  public JButton getHelpButton() {
    return helpButton;
  }

  public JButton getMapButton() {
    return mapButton;
  }

  public JButton getMusicButton() {
    return musicButton;
  }

  public JButton getFxButton() {
    return fxButton;
  }

  public JButton getSaveGameButton() {
    return saveGameButton;
  }

  public JButton getQuitButton() {
    return quitButton;
  }

  public JButton getGoNorthButton() {
    return goNorthButton;
  }

  public JButton getGoEastButton() {
    return goEastButton;
  }

  public JButton getGoSouthButton() {
    return goSouthButton;
  }

  public JButton getGoWestButton() {
    return goWestButton;
  }

  public JButton getKnockButton() {
    return knockButton;
  }

  public JButton getGetItemButton() {
    return getItemButton;
  }

  public JButton getUseItemButton() {
    return useItemButton;
  }

}
