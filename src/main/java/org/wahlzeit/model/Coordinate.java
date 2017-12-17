package org.wahlzeit.model;

public interface Coordinate {
	public CartesianCoordinate asCartesianCoordinate();
	public double getCartesianDistance(Coordinate coord) throws IllegalArgumentException, ClassInvariantsException;
	public SphericCoordinate asSphericCoordinate();
	public double getSphericDistance(Coordinate coord) throws IllegalArgumentException, ClassInvariantsException;
	public double getDistance(Coordinate coord);
	public boolean isEqual(Coordinate coord);
	public int getCoordinateCode();
}
