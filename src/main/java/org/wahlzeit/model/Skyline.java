package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

public class Skyline extends DataObject {
	private SkylineType skylineType = null;
	
	public Skyline(SkylineType st) {
		assert(st != null) : "Invalid SkylineType (null)";
		this.skylineType = st;
	}

	public SkylineType getType() {
		return skylineType;
	}

	public void setType(SkylineType st) {
		assert(st != null) : "Invalid SkylineType (null)";
		this.skylineType = st;
	}
}
