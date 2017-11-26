package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
	protected double eps = 0.001;
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coordinate && obj != null) {
			return isEqual((Coordinate) obj);
		}
		return false;
	}
	
	@Override
	public double getDistance(Coordinate coord) {
		if(coord == null) {
			return -Double.MAX_VALUE;
		}
		if(coord instanceof CartesianCoordinate) {
			return getCartesianDistance(coord);
		}
		return getSphericDistance(coord);
	}
}
