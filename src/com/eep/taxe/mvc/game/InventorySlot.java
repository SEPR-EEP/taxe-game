package com.eep.taxe.mvc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.eep.taxe.models.Usable;

public class InventorySlot extends JPanel{
	private final int WIDTH;
	private final int HEIGHT;
	private Usable item;
	private ImageIcon defaultIcon;
	private boolean selected;
	private JLabel itemLabel;
	private JPanel selectedPanel;
	
	
	public Usable getItem() {
		return item;
	}

	public void setItem(Usable item) {
		this.item = item;
		itemLabel.setIcon(new ImageIcon(item.getImage()));
	}
	
	public void selectItem(){
		if(!isEmpty()){
//		selectedPanel.setOpaque(true);
		selected =true;
		}
	}
	
	public void deselectItem(){
//		selectedPanel.setOpaque(false);
		selected = false;
	}
	
	public Usable useItem(){
		deselectItem();
		itemLabel.setIcon(defaultIcon);
		return item;
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public boolean isEmpty(){
		return (this.item == null);
	}
	
	
	
	public InventorySlot(final int width, final int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.item = null;
		this.setPreferredSize(new Dimension(WIDTH/24, WIDTH/24));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.selected = false;
		
		this.itemLabel = new JLabel();
		itemLabel.setSize(new Dimension(WIDTH/24, WIDTH/24));
		itemLabel.setBackground(Color.RED);
		itemLabel.setText("sdfdsfg");
//		itemLabel.setIcon(new ImageIcon(getClass().getResource("/resources/RailroadTracks.jpg")));
		
		
//		this.selectedPanel = new JPanel();
//		selectedPanel.setBackground(new Color(122, 207, 109, 128));
//		selectedPanel.setPreferredSize(new Dimension(WIDTH/24, WIDTH/24));
//		selectedPanel.setOpaque(false);
		
		this.add(itemLabel);
//		this.add(selectedPanel);
	}



	

}