package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class SkylinePhoto extends Photo {
	
	private String date = "";

	public SkylinePhoto() {
		super();
	}
	
	public SkylinePhoto(Location loc) {
		super(loc);
	}

	public SkylinePhoto(PhotoId myId) {
		super(myId);
	}
	
	/**
	 * @methodtype get
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * @methodtype set
	 * @throws IllegalArgumentException if argument string is null
	 */
	public void setDate(String d) throws NullPointerException {
		if(d == null) {
			throw new IllegalArgumentException("Argument string is null");
		}
		date = d;
	}
}
