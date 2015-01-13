package com.eep.taxe.mvc.game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class RightPanel extends JPanel {
	private final int WIDTH;
	private final int HEIGHT;
	
	public RightPanel(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		
		this.setPreferredSize(new Dimension(WIDTH/6, HEIGHT));
		this.setBackground(Color.RED);
	}
}
