package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate {
	private final double latitude; //in radians
	private final double longitude; // in radians
	private final double radius;
	
	//default constructor
	public SphericCoordinate() {
		this.longitude = 0.0;
		this.latitude = 0.0;
		this.radius = 0.0;
	}
		
	//parameterized constructor
	public SphericCoordinate(double latitude, double longitude, double radius) {
		double lati, longi, rad;
		lati = longi = rad = 0.0;
		
		try {
			assertValidValue(latitude);
			assertValidValue(longitude);
			assertValidValue(radius);
			
			if(Math.abs(latitude) <= Math.PI/2.0) {
				lati = latitude;
			}
			
			if(Math.abs(longitude) <= Math.PI) {
				longi = longitude;
			} else {
				int n = (int) (longitude / Math.PI);
				longi = longitude - n * Math.PI; 
			}
			
			if(radius >= 0.0) {
				rad = radius;
			}
			
		} catch(IllegalArgumentException e) {}
		
		this.longitude = longi;
		this.latitude = lati;
		this.radius = rad;
	}
	
	public static SphericCoordinate getSphericInstance(SphericCoordinate coord) {
		Integer integerCode = new Integer(coord.getCoordinateCode());
		SphericCoordinate instance = (SphericCoordinate) sharedInstances.get(integerCode);
		if(instance == null) {
			sharedInstances.put(coord.getCoordinateCode(), coord);
			return coord;
		}
		return instance;
	}
	
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		//see: https://vvvv.org/blog/polar-spherical-and-geographic-coordinates
		double x = this.radius * Math.cos(this.latitude) * Math.cos(this.longitude);
		double y = this.radius * Math.cos(this.latitude) * Math.sin(this.longitude);
		double z = this.radius * Math.sin(this.latitude);
		CartesianCoordinate ret = new CartesianCoordinate(x, y, z);
		return ret;
	}
	
	//@throws IllegalArgumentException when argument is null
	//@throws ClassInvariantsException when class invariants not valid
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
	//@throws ClassInvariantsException when class invariants not valid
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
	
	public SphericCoordinate setLongitude(double longitude) {
		double l = 0.0;
		try {
			assertValidValue(longitude);
			if(Math.abs(longitude) <= Math.PI) {
				l = longitude;
			} else {
				int n = (int) (longitude / Math.PI);
				l = longitude - n * Math.PI; 
			}
		} catch(IllegalArgumentException e) {}
		
		return getSphericInstance(new SphericCoordinate(this.getLatitude(), l, this.getRadius()));
	}

	public double getLatitude() {
		return latitude;
	}
	
	public SphericCoordinate setLatitude(double latitude) {
		double l = 0.0;
		try {
			assertValidValue(latitude);
			if(Math.abs(latitude) <= Math.PI/2.0) {
				l = latitude;
			}
		} catch(IllegalArgumentException e) {}
		
		return getSphericInstance(new SphericCoordinate(l, this.getLongitude(), this.getRadius()));
	}

	public double getRadius() {
		return radius;
	}
	
	public SphericCoordinate setRadius(double radius) {
		double r = 0.0;
		try {
			assertValidValue(radius);
			if(radius >= 0.0) {
				r = radius;
			}
		} catch(IllegalArgumentException e) {}
		
		return getSphericInstance(new SphericCoordinate(this.getLatitude(), this.getLongitude(), r));
	}
	
	public int getCoordinateCode() {
		return this.hashCode();
	}
}
