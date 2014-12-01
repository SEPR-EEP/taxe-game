package com.eep.taxe.models;

public class Gold extends Spendable {

	private static final long serialVersionUID = 5922318445995842094L;
	
	private final static String GOLD_IMG = "images/gold.png";
	
	/**
	 * Instantiates a Gold Spendable Resource
	 */
	public Gold() {
		super("Gold", GOLD_IMG);
	}

}
