package org.wahlzeit.model;

public class Location {
	public Coordinate coordinate = new CartesianCoordinate(0.0, 0.0, 0.0);
	
	public Location(Coordinate coord) {
		if(coord != null) {
			this.coordinate = coord;
		}
	}
	
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj instanceof Location) {
			Location loc = (Location) obj;
			return coordinate.equals(loc.getCoordinate());
		}
		return false;
	}
}
