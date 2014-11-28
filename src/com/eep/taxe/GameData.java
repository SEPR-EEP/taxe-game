package com.eep.taxe;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;


/**
 * Represents a collection of Game Data.
 */
public class GameData implements Serializable {

	private static final long serialVersionUID = -8678570713750881431L;
	
	/**
	 * Serialised object 
	 * @param 		The Game data
	 * @return		Bytes
	 */
	public byte[] serialise() {
		return SerializationUtils.serialize(this);
	}
	
	/**
	 * Return a Game Data object from an array of bytes 
	 * @param 		The serialised object
	 * @return 		Game data
	 */
	public static GameData deserialise(byte[] bytes) {
		return (GameData) SerializationUtils.deserialize(bytes);
	}


}
