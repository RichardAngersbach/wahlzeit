package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate {
	private double latitude; //in radians
	private double longitude; // in radians
	private double radius;
	
	//default constructor
	public SphericCoordinate() {
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.radius = 0.0;
	}
		
	//parameterized constructor
	public SphericCoordinate(double latitude, double longitude, double radius) {
		setLatitude(latitude);
		setLongitude(longitude);
		setRadius(radius);
	}
	
	//@throws 
	@Override
	public CartesianCoordinate asCartesianCoordinate() throws IllegalArgumentException {
		//see: https://vvvv.org/blog/polar-spherical-and-geographic-coordinates
		double x = this.radius * Math.cos(this.latitude) * Math.cos(this.longitude);
		double y = this.radius * Math.cos(this.latitude) * Math.sin(this.longitude);
		double z = this.radius * Math.sin(this.latitude);
		CartesianCoordinate ret = new CartesianCoordinate(x, y, z);
		return ret;
	}
	
	//@throws IllegalArgumentException when argument is null
	//@throws 
	@Override
	public double getCartesianDistance(Coordinate coord) throws IllegalArgumentException, ClassInvariantsException {
		assertNotNullArg(coord);
		assertClassInvariants(coord);
		
		CartesianCoordinate cartThis = this.asCartesianCoordinate();
		return cartThis.getCartesianDistance(coord);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}
	
	//@throws IllegalArgumentException when argument is null
	//@throws 
	@Override
	public double getSphericDistance(Coordinate coord) throws IllegalArgumentException, ClassInvariantsException {
		assertNotNullArg(coord);
		assertClassInvariants(coord);
		
		SphericCoordinate tmp = coord.asSphericCoordinate();
		
		double diffLat = Math.abs(this.latitude - tmp.latitude);
		double diffLong = Math.abs(this.longitude - tmp.longitude);
		double centralAngle = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(diffLat/2.0), 2.0) 
											   		+ Math.cos(this.latitude) * Math.cos(tmp.latitude) * Math.pow(Math.sin(diffLong/2.0), 2.0)));
		return this.radius * centralAngle;
	}
	
	@Override
	public boolean isEqual(Coordinate coord) {
		SphericCoordinate tmp = coord.asSphericCoordinate();
		return	Math.abs(this.latitude - tmp.latitude) <= eps 
			 && Math.abs(this.longitude - tmp.longitude) <= eps 
			 && Math.abs(this.radius - tmp.radius) <= eps;
	}

	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		try {
			assertValidValue(longitude);
			if(Math.abs(longitude) <= Math.PI) {
				this.longitude = longitude;
			} else {
				int n = (int) (longitude / Math.PI);
				this.longitude = longitude - n * Math.PI; 
			}
		} catch(IllegalArgumentException e) {
			this.longitude = 0.0;
		}
	}

	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		try {
			assertValidValue(latitude);
			if(Math.abs(latitude) <= Math.PI/2.0) {
				this.latitude = latitude;
			} else {
				this.latitude = 0.0;
			}
		} catch(IllegalArgumentException e) {
			this.latitude = 0.0;
		}
	}

	public double getRadius() {
		return radius;
	}
	
	public void setRadius(double radius) {
		try {
			assertValidValue(radius);
			if(radius >= 0.0) {
				this.radius = radius;
			} else {
				this.radius = 0.0;
			}
		} catch(IllegalArgumentException e) {
			this.radius = 0.0;
		}
	}
}
