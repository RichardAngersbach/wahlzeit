package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Set;

import org.wahlzeit.services.DataObject;

import com.googlecode.objectify.annotation.Entity;

public class SkylineType extends DataObject {
	private static final long serialVersionUID = 1L;
	
	private final String typeName;
	protected Set<SkylineType> subTypes = new HashSet<SkylineType>();
	
	public SkylineType(String tn) {
		assert(tn != null) : "Invalid SkylineType name (null)";
		this.typeName = tn;
	}
	
	public Skyline createInstance() {
		return new Skyline(this);
	}

	public void addSubType(SkylineType st) {
		assert(st != null) : "Invalid SkylineType (null)";
		subTypes.add(st);
	}
	
	public boolean isSubType(SkylineType st) {
		if(st == null) {
			return false;
		}
		return subTypes.contains(st);
	}
	
	public boolean hasInstance(Skyline sl) {
		assert(sl != null) : "Invalid Skyline object (null)";
		if(this.equals(sl.getType())) {
			return true;
		}
		for(SkylineType st : subTypes) {
			return st.hasInstance(sl);
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if(obj instanceof SkylineType) {
			SkylineType st = (SkylineType) obj;
			return this.getTypeName().equals(st.getTypeName());
		}
		return false;
	}

	public String getTypeName() {
		return typeName;
	}

	public Set<SkylineType> getSubTypes() {
		return subTypes;
	}

	public void setSubTypes(Set<SkylineType> subTypes) {
		assert(subTypes != null) : "Invalid SkylineType Set (null)";
		for(SkylineType st : subTypes) {
			addSubType(st);
		}
	}
}
