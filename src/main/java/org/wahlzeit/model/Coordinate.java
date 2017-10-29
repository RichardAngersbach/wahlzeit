package org.wahlzeit.model;

public class Coordinate {
	private double x;
	private double y;
	private double z;
	
	//default constructor
	public Coordinate() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	//parameterized constructor 
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//computes the euclidian distance between two points
	public double getDistance(Coordinate coord) {
		return Math.sqrt( Math.pow(this.x - coord.x, 2.0) 
						+ Math.pow(this.y - coord.y, 2.0) 
						+ Math.pow(this.z - coord.z, 2.0) );
	}
	
	public boolean isEqual(Coordinate coord) {
		return this.x == coord.x && this.y == coord.y && this.z == coord.z;
	}
	
	public boolean equals(Coordinate coord) {
		return isEqual(coord);
	}
}
