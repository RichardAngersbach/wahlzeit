package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class SkylinePhoto extends Photo {
	private static final long serialVersionUID = 1L;
	private String date = "";
	private Skyline skyline = null;

	public SkylinePhoto() {
		super();
	}
	
	public SkylinePhoto(Location loc) {
		super(loc);
	}
	
	public SkylinePhoto(PhotoId myId) {
		super(myId);
	}
	
	public Skyline getSkyline() {
		return this.skyline;
	}

	public void setSkyline(Skyline sl) {
		assert(sl != null) : "Illegal Skyline object (null)";
		this.skyline = sl;
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
