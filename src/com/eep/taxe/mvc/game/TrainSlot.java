package com.eep.taxe.mvc.game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.eep.taxe.models.Train;
import com.eep.taxe.models.Train;

public class TrainSlot extends JButton{
	private final int WIDTH;
	private final int HEIGHT;
	private Train train;
	private ImageIcon defaultIcon;
	private boolean selected;
	
	public TrainSlot (final int width, final int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.defaultIcon = new ImageIcon(getClass().getResource("/resources/gameview/EmptySlotIconTrains.png"));
		this.train = null;
		this.selected = false;
		this.setPreferredSize(new Dimension(WIDTH/24, WIDTH/24));
		this.setIcon(defaultIcon);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		this.setContentAreaFilled(false);
	}
	
	public Train getTrain() {
		return train;
	}
	
	public boolean isEmpty() {
		return train == null;
	}

	public void setTrain(Train train) {
		if(train != null){
			this.setIcon(new ImageIcon(train.getTrainImage()));
		}else{
			this.setIcon(defaultIcon);
		}
		this.train = train;

		
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