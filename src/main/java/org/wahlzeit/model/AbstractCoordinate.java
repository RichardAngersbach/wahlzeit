package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
	protected double eps = 0.001;
	
	//@throws NullPointerException when the argument is null 
	//@throws IllegalArgumentException when conversion results to illegal arguments for the new object
	@Override
	public boolean equals(Object obj) throws NullPointerException, IllegalArgumentException {
		assertNotNullArg((Coordinate) obj);
		if(obj instanceof Coordinate && obj != null) {
			return isEqual((Coordinate) obj);
		}
		return false;
	}
	
	//@throws NullPointerException when the argument is null 
	//@throws IllegalArgumentException when conversion results to illegal arguments for the new object
	@Override
	public double getDistance(Coordinate coord) throws NullPointerException, IllegalArgumentException {
		assertNotNullArg(coord);
		assertClassInvariants(coord);
		if(coord instanceof CartesianCoordinate) {
			return getCartesianDistance(coord);
		}
		return getSphericDistance(coord);
	}
	
	//@throws NullPointerException when the argument is null 
	public void assertNotNullArg(Coordinate c) throws NullPointerException {
		if(c == null) {
			throw new NullPointerException("Coordinate is null");
		}
	}
	
	//@throws IllegalArgumentException when invalid coordinate values are used
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
	
	//@throws IllegalArgumentException when invalid double value is used
	public void assertValidValue(double val) throws IllegalArgumentException {
		if(Math.abs(val) >= Double.MAX_VALUE || Double.isNaN(val)) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
	}
}
