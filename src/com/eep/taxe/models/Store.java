package com.eep.taxe.models;

import java.io.Serializable;
import java.util.ArrayList;

import com.eep.taxe.models.Age.Ages;

public class Store implements Serializable {
	
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
		}
		return items;
	}
}
