package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate {
	private double x;
	private double y;
	private double z;
	
	//default constructor
	public CartesianCoordinate() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	//parameterized constructor 
	public CartesianCoordinate(double x, double y, double z) {
		assertValidValue(x);
		assertValidValue(y);
		assertValidValue(z);
		
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}
	
	@Override
	public double getCartesianDistance(Coordinate coord) {
		assertNotNullArg(coord);
		CartesianCoordinate tmp = coord.asCartesianCoordinate();
		
		return Math.sqrt( Math.pow(this.x - tmp.x, 2.0) 
						+ Math.pow(this.y - tmp.y, 2.0) 
						+ Math.pow(this.z - tmp.z, 2.0) );
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		//see: https://vvvv.org/blog/polar-spherical-and-geographic-coordinates
		double r = Math.sqrt( Math.pow(this.x, 2.0) 
				  			+ Math.pow(this.y, 2.0) 
				  			+ Math.pow(this.z, 2.0) );
		double latitude =  Math.asin(this.z/r);
		double longitude = Math.atan2(y, x);
		
		//different results for atan2, see: https://en.wikipedia.org/wiki/Atan2 
		if(x < 0 && y>= 0) {
			longitude += Math.PI;
		} else if(x < 0 && y < 0) {
			longitude -= Math.PI;
		} else if(Math.abs(x) <= eps && y > 0) {
			longitude = Math.PI/2.0;
		} else if(Math.abs(x) <= eps && y < 0) {
			longitude = -Math.PI/2.0;
		}
		SphericCoordinate ret = new SphericCoordinate(latitude, longitude, r);
		assertClassInvariants(ret);
		return ret;
	}

	@Override
	public double getSphericDistance(Coordinate coord) {
		assertNotNullArg(coord);
		SphericCoordinate sphericThis = this.asSphericCoordinate();
		return sphericThis.getSphericDistance(coord);
	}

	@Override
	public boolean isEqual(Coordinate coord) {
		assertNotNullArg(coord);
		CartesianCoordinate tmp = coord.asCartesianCoordinate();
		return	Math.abs(this.x - tmp.x) <= eps 
			 && Math.abs(this.y - tmp.y) <= eps 
			 && Math.abs(this.z - tmp.z) <= eps;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		assertValidValue(x);
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		assertValidValue(y);
		this.y = y;
	}

	public double getZ() {
		assertValidValue(z);
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
}
