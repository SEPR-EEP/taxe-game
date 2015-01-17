package com.eep.taxe.mvc.game;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.eep.taxe.models.Train;

public class TrainSlot extends JButton{
	private final int WIDTH;
	private final int HEIGHT;
	private Train train;
	private ImageIcon defaultIcon;
	private boolean selected;

	
	
	public void inserttrain(Train train) {
		this.train = train;
		this.setIcon(new ImageIcon(train.getTrainImage()));
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
	
	public Train usetrain(){
		deselectSlot();
		this.setIcon(defaultIcon);
		Train temp = train;
		train = null;
		return temp;
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public boolean isEmpty(){
		return (this.train == null);
	}
	
	
	public TrainSlot (final int width, final int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.defaultIcon = new ImageIcon(getClass().getResource("RailroadTracks.jpg"));
		this.train = null;
		this.selected = false;
		this.setPreferredSize(new Dimension(WIDTH/24, WIDTH/24));
		this.setIcon(defaultIcon);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		this.setContentAreaFilled(false);
	}



	

}