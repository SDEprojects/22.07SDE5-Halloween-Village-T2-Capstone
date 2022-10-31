package com.halloween;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFrame extends JFrame implements ActionListener {

  JButton button1;  //Global component of button
  JButton button2;
  JButton button3;
  JButton button4;
  JButton button5;
  MyFrame() {
    //JFrame = a GUI window to add components to

    button1 = new JButton("NEW GAME");
    button2 = new JButton("LOAD GAME");
    button3 = new JButton("HELP");
    button4 = new JButton("QUIT");
    button5 = new JButton("SAVE GAME");

    button1.setBounds(200,100,100,50);
    button2.setBounds(200,100,100,50);
    button3.setBounds(200,100,100,50);
    button4.setBounds(200,100,100,50);
    button5.setBounds(200, 100,100, 50);

    button1.addActionListener(this);

    this.setTitle("HALLOWEEN VILLAGE"); //Sets the title of the frame
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of the application
    //this.setResizable(false); //present frame from being resized
    this.setSize(600, 600); //sets the x-dimensions, and y-dimensions of the frame
    this.setVisible(true); //makes frame visible
    this.add(button1);
    this.add(button2);
    this.add(button3);
    this.add(button4);

    ImageIcon image = new ImageIcon("./resource/Halloween_Village_Logo.png");
    this.setIconImage(image.getImage());  //Change icon of the frame
//    this.getContentPane().setBackground(new Color(0, 51, 153)); //change color of the background

    JPanel blackPanel = new JPanel();
    blackPanel.setBackground(Color.black);
    blackPanel.setBounds(0, 0, 250, 250);

    JLabel label = new JLabel(); //Creates a label
    label.setIcon(image);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource()==button1) {
      System.out.println("Check Up");
    }
  }
}
