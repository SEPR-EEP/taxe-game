package com.eep.taxe.mvc.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JImagePanel extends JPanel {

  private Image img;

  public JImagePanel() {

    
    Dimension size = new Dimension(1300, 720);
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
    


  }
  
  public void paintComponent(Graphics g){
		try {
			img = ImageIO.read(getClass().getResource("/resources/MainMap.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, 0, 0, null);
  }
  
  public void paintMap(Graphics g) {
		try {
			img = ImageIO.read(getClass().getResource("/resources/MainMap.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	  g.drawImage(img, 0, 0, null);
  } 
  

}
