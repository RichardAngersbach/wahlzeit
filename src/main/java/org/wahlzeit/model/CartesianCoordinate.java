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
		setX(x);
		setY(y);
		setZ(z);
	}
	
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}
	//@throws NullPointerException when the argument is null 
	//@throws IllegalArgumentException when conversion results to illegal arguments for the new object
	@Override
	public double getCartesianDistance(Coordinate coord) throws NullPointerException, IllegalArgumentException {
		assertNotNullArg(coord);
		CartesianCoordinate tmp = coord.asCartesianCoordinate();
		
		return Math.sqrt( Math.pow(this.x - tmp.x, 2.0) 
						+ Math.pow(this.y - tmp.y, 2.0) 
						+ Math.pow(this.z - tmp.z, 2.0) );
	}
	
	//@throws IllegalArgumentException when conversion results to illegal arguments for the new object
	@Override
	public SphericCoordinate asSphericCoordinate() throws IllegalArgumentException {
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
	
	//@throws NullPointerException when argument is null
	//@throws IllegalArgumentException when conversion results to illegal arguments for the new object
	@Override
	public double getSphericDistance(Coordinate coord) throws NullPointerException, IllegalArgumentException {
		assertNotNullArg(coord);
		SphericCoordinate sphericThis = this.asSphericCoordinate();
		return sphericThis.getSphericDistance(coord);
	}
	
	//@throws NullPointerException when argument is null
	//@throws IllegalArgumentException when conversion results to illegal arguments for the new object
	@Override
	public boolean isEqual(Coordinate coord) throws NullPointerException, IllegalArgumentException {
		assertNotNullArg(coord);
		CartesianCoordinate tmp = coord.asCartesianCoordinate();
		return	Math.abs(this.x - tmp.x) <= eps 
			 && Math.abs(this.y - tmp.y) <= eps 
			 && Math.abs(this.z - tmp.z) <= eps;
	}

	public double getX() {
		return x;
	}
	
	//@throws IllegalArgumentException when invalid coordinate value is used
	public void setX(double x) {
		try {
			assertValidValue(x);
			this.x = x;
		} catch(IllegalArgumentException e) {
			this.x = 0.0;
		}
	}

	public double getY() {
		return y;
	}
	
	//@throws IllegalArgumentException when invalid coordinate value is used
	public void setY(double y) {
		try {
			assertValidValue(y);
			this.y = y;
		} catch(IllegalArgumentException e) {
			this.y = 0.0;
		}
	}

	public double getZ() {
		return z;
	}
	
	//@throws IllegalArgumentException when invalid coordinate value is used
	public void setZ(double z) {
		try {
			assertValidValue(z);
			this.z = z;
		} catch(IllegalArgumentException e) {
			this.z = 0.0;
		}
	}
}
