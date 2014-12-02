package com.eep.taxe.models;

import java.io.Serializable;
import java.util.Vector;

public class Path extends Vector<Edge> implements Serializable {

	private static final long serialVersionUID = 7893406019421382154L;

	/**
	 * Calculate the total length of the Path
	 * @return	The total length of the path
	 */
	public int getTotalLength() {
		int t = 0;
		for ( Edge x: this ) {
			t += x.getLength();
		}
		return t;
	}
	
	/**
	 * Estimate total duration given a speed
	 * @param	speed	Speed of the train
	 * @return 	The duration of the journey
	 */
	public double getDuration(double speed) {
		return this.getTotalLength() / speed;
	}
	
	
	/**
	 * Estimate total duration at normal speed (1)
	 * @return 	The duration of the journey
	 */
	public double getDuration() {
		return this.getDuration(1.0);
	}
	
	
	
}
