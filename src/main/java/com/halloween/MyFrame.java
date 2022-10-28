package com.halloween;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame {

  MyFrame() {
    //JFrame = a GUI window to add components to

    this.setTitle("HALLOWEEN VILLAGE"); //Sets the title of the frame
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of the application
    this.setResizable(false); //present frame from being resized
    this.setSize(600,600); //sets the x-dimensions, and y-dimensions of the frame
    this.setVisible(true); //makes frame visible

    ImageIcon image = new ImageIcon("./resource/Halloween_Village_Logo.png");
    this.setIconImage(image.getImage());  //Change icon of the frame
    this.getContentPane().setBackground(new Color(0, 51, 153)); //change color of the background
  }

}
