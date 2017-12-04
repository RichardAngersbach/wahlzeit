package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
	protected double eps = 0.001;
	
	@Override
	public boolean equals(Object obj) {
		assertNotNullArg((Coordinate) obj);
		if(obj instanceof Coordinate && obj != null) {
			return isEqual((Coordinate) obj);
		}
		return false;
	}
	
	@Override
	public double getDistance(Coordinate coord) {
		assertNotNullArg(coord);
		assertClassInvariants(coord);
		if(coord instanceof CartesianCoordinate) {
			return getCartesianDistance(coord);
		}
		return getSphericDistance(coord);
	}
	
	public void assertNotNullArg(Coordinate c) throws NullPointerException {
		if(c == null) {
			throw new NullPointerException();
		}
	}
	
	public void assertClassInvariants(Coordinate c) throws IllegalArgumentException {
		if(c instanceof CartesianCoordinate) {
			assertValidValue(c.asCartesianCoordinate().getX());
			assertValidValue(c.asCartesianCoordinate().getY());
			assertValidValue(c.asCartesianCoordinate().getX());
		} else {
			assertValidValue(c.asSphericCoordinate().getRadius());
			assertValidValue(c.asSphericCoordinate().getLongitude());
			assertValidValue(c.asSphericCoordinate().getLatitude());
			
			if(c.asSphericCoordinate().getRadius() < 0.0 || Math.abs(c.asSphericCoordinate().getLongitude()) > Math.PI || Math.abs(c.asSphericCoordinate().getLatitude()) > Math.PI/2.0) {
				throw new IllegalArgumentException("Illegal Arguments");
			}
		}
	}
	
	public void assertValidValue(double val) throws IllegalArgumentException {
		if(Math.abs(val) >= Double.MAX_VALUE || Double.isNaN(val)) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
	}
}
