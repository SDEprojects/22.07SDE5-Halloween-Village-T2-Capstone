package com.halloween.view.GUI;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;

public class GuiView {

  JFrame window;
  private Container container;

  public GuiView() {
    window = new JFrame("Halloween Village");
    window.setSize(800, 600); //sets the x-dimensions, and y-dimensions of the frame
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of the application
    //this.setResizable(false); //present frame from being resized
    window.setLayout(null);
    window.getContentPane().setBackground(Color.black);
    container = window.getContentPane();
    window.setVisible(true); //makes frame visible
  }

  public void displayTitleScreen() {
    TitleScreen titleScreen = new TitleScreen();
    container.add(titleScreen.getTitlePanel());
    container.add(titleScreen.getButtonsPanel());
    container.repaint();
    container.revalidate();
  }

}
