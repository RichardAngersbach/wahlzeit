package org.wahlzeit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.wahlzeit.services.ObjectManager;

public class SkylineManager {
	protected static final SkylineManager instance = new SkylineManager();
	
	List<Skyline> skylines = new ArrayList<Skyline>();
	HashMap<String, SkylineType> skylineTypes = new HashMap<String, SkylineType>();
	
	public SkylineManager() {
		
	}
	
	public Skyline createSkyline(String typeName) {
		assert(typeName != null) : "Invalid SkylineType name (null)";
		SkylineType st = getSkylineType(typeName);
		Skyline result = st.createInstance();
		skylines.add(result);
		return result;
	}
	
	public SkylineType getSkylineType(String typeName) {
		assert(typeName != null) : "Invalid SkylineType name (null)";
		SkylineType st = skylineTypes.get(typeName);
		if(st == null) {
			st = new SkylineType(typeName);
			skylineTypes.put(typeName, st);
		}
		return st;
	}
	
	public int getIndex(Skyline sl) {
		return skylines.indexOf(sl);
	}
	
	public Skyline getSkyline(int index) {
		return skylines.get(index);
	}
	
	public static final SkylineManager getInstance() {
		return instance;
	}
}
