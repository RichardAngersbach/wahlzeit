package org.wahlzeit.model;

import java.util.HashMap;

public abstract class AbstractCoordinate implements Coordinate {
	protected double eps = 0.001;
	
	public static HashMap<Integer, Coordinate> sharedInstances = new HashMap<>();
	
	//@throws IllegalArgumentException when argument is null
	@Override
	public boolean equals(Object obj) throws IllegalArgumentException {
		if(obj instanceof Coordinate && obj != null) {
			return isEqual((Coordinate) obj);
		}
		return false;
	}
	
	//@throws IllegalArgumentException when argument is null
	@Override
	public double getDistance(Coordinate coord) throws IllegalArgumentException {
		if(coord instanceof CartesianCoordinate) {
			try {
				return getCartesianDistance(coord);
			} catch (ClassInvariantsException e) {
				return Double.NEGATIVE_INFINITY;
			}
		}
		try {
			return getSphericDistance(coord);
		} catch (ClassInvariantsException e) {
			return Double.NEGATIVE_INFINITY;
		}
	}
	
	//@throws IllegalArgumentException when argument is null
	public void assertNotNullArg(Coordinate c) throws IllegalArgumentException {
		if(c == null) {
			throw new IllegalArgumentException("Coordinate is null");
		}
	}
	
	//@throws IllegalArgumentException when invalid coordinate values are used
	public void assertClassInvariants(Coordinate c) throws IllegalArgumentException, ClassInvariantsException {
		if(c instanceof CartesianCoordinate) {
			assertValidValue(c.asCartesianCoordinate().getX());
			assertValidValue(c.asCartesianCoordinate().getY());
			assertValidValue(c.asCartesianCoordinate().getX());
		} else {
			assertValidValue(c.asSphericCoordinate().getRadius());
			assertValidValue(c.asSphericCoordinate().getLongitude());
			assertValidValue(c.asSphericCoordinate().getLatitude());
			
			if(c.asSphericCoordinate().getRadius() < 0.0 || Math.abs(c.asSphericCoordinate().getLongitude()) > Math.PI || Math.abs(c.asSphericCoordinate().getLatitude()) > Math.PI/2.0) {
				throw new ClassInvariantsException("Invalid class invariants");
			}
		}
	}
	
	//@throws IllegalArgumentException when invalid double value is used
	public void assertValidValue(double val) throws IllegalArgumentException {
		if(Double.isInfinite(val) || Double.isNaN(val)) {
			throw new IllegalArgumentException("Illegal Arguments, value is: " + val);
		}
	}
}
