package org.wahlzeit.model;

public class Location {
	public Coordinate coordinate;
	
	public Location(Coordinate coord) {
		this.coordinate = coord;
	}
	
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
}
