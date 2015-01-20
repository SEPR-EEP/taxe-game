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
	
	private final String EMPTY_TOOLTIP = "Empty slot";
	
	
	public InventorySlot (final int width, final int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.defaultIcon = new ImageIcon(getClass().getResource("/resources/gameview/EmptySlotIcon.png"));
		this.item = null;
		this.selected = false;
		this.setPreferredSize(new Dimension(WIDTH/24, WIDTH/24));
		this.setIcon(defaultIcon);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		this.setContentAreaFilled(false);
		this.setBackground(Color.DARK_GRAY);
//		this.setToolTipText(EMPTY_TOOLTIP);
	}


	public boolean isEmpty() {
		return item == null;
	}
	
	public Usable getItem() {
		return item;
	}

	public void setItem(Usable item) {
		if(item != null){
			this.setIcon(new ImageIcon(getClass().getResource(item.getImage())));
			this.setToolTipText(item.getName());
		}else{
			this.setIcon(defaultIcon);
			this.setToolTipText(EMPTY_TOOLTIP);
		}
		this.item = item; // You need to be able to set the item to null		
	}

	public void setSelected(boolean selected) {
		if(selected == false){
			this.selected = selected;
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		}else{
			this.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
		}
	}
	
	public boolean getSelected(){
		return selected;
	}
	

}
