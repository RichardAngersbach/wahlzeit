package org.wahlzeit.model;

public class Coordinate {
	private double x;
	private double y;
	private double z;
	
	private double eps = 0.001;
	
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
		if(coord == null) {
			return -Double.MAX_VALUE;
		}
		
		if(coord.x >= Double.MAX_VALUE || coord.y >= Double.MAX_VALUE || coord.z >= Double.MAX_VALUE) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		
		return Math.sqrt( Math.pow(this.x - coord.x, 2.0) 
						+ Math.pow(this.y - coord.y, 2.0) 
						+ Math.pow(this.z - coord.z, 2.0) );
	}
	
	public boolean isEqual(Coordinate coord) {
		if(coord == null) {
			return false;
		}
		return	Math.abs(this.x - coord.x) <= eps 
			 && Math.abs(this.y - coord.y) <= eps 
			 && Math.abs(this.z - coord.z) <= eps;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coordinate && obj != null) {
			return isEqual((Coordinate) obj);
		}
		return false;
	}
}
