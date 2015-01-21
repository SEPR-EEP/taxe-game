package com.eep.taxe.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This represents the Store for a game. It is used as a container for 
 * the resources that can be bought in-game.
 * 
 * It has not been implemented yet.
 */
public class Store implements Serializable {
	
	private static final long serialVersionUID = 3398896335262150587L;

	public ArrayList getItems(Age.Ages age){
		
		ArrayList items = new ArrayList();
		
		ArrayList firstItems = new ArrayList();               // Needs items
		
		ArrayList secondItems = new ArrayList(firstItems);    // Needs items
		
		ArrayList thirdItems = new ArrayList(secondItems);    // Needs items
	
		ArrayList fourthItems = new ArrayList(thirdItems);    // Needs items
		
		switch (age) {
			case FIRST:
				items.addAll(firstItems);
			case SECOND:
				items.addAll(secondItems);
			case THIRD:
				items.addAll(thirdItems);
			case FOURTH:
				items.addAll(fourthItems);
			default:
				break;
		}
		return items;
	}
}
