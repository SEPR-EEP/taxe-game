package com.eep.taxe.mvc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.eep.taxe.models.Usable;

public class InventorySlot extends JButton{
	private final int WIDTH;
	private final int HEIGHT;
	private Usable item;
	private ImageIcon defaultIcon;
	private boolean selected;

	
	
	public void insertItem(Usable item) {
		this.item = item;
		this.setIcon(new ImageIcon(item.getImage()));
	}
	
	public void selectSlot(){
		if(!isEmpty()){
			this.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
			selected = true;
		}
	}
	
	public void deselectSlot(){
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		selected = false;
	}
	
	public Usable useItem(){
		deselectSlot();
		this.setIcon(defaultIcon);
		Usable temp = item;
		item = null;
		return temp;
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public boolean isEmpty(){
		return (this.item == null);
	}
	
	
	
	public InventorySlot (final int width, final int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.defaultIcon = new ImageIcon(getClass().getResource("RailroadTracks.jpg"));
		this.item = null;
		this.selected = false;
		this.setPreferredSize(new Dimension(WIDTH/24, WIDTH/24));
		this.setIcon(defaultIcon);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		this.setContentAreaFilled(false);
	}



	

}