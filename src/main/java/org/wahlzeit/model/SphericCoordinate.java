package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate {
	private double latitude; //in radians
	private double longitude; // in radians
	private double radius;
	
	private double eps = 0.001;
	
	//default constructor
	public SphericCoordinate() {
		this.latitude = 0.0;
		this.longitude = 0.0;
		this.radius = 0.0;
	}
		
		//parameterized constructor 
	public SphericCoordinate(double latitude, double longitude, double radius) {
		if(Math.abs(radius) >= Double.MAX_VALUE || Math.abs(longitude) >= Double.MAX_VALUE || Math.abs(latitude) >= Double.MAX_VALUE) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		//range of latitude is -90° to 90°
		if(Math.abs(latitude) <= Math.PI/2.0) {
			this.latitude = latitude;
		}
		else {
			this.latitude = 0.0;
		}
		//range of longitude is -180° to 180°
		if(Math.abs(longitude) <= Math.PI) {
			this.longitude = longitude;
		} else {
			int n = (int) (longitude / Math.PI);
			this.longitude = longitude - n * Math.PI; 
		}
		//radius is non-negative
		if(radius >= 0) {
			this.radius = radius;
		} else {
			this.radius = 0.0;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coordinate && obj != null) {
			return isEqual((Coordinate) obj);
		}
		return false;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		//see: https://vvvv.org/blog/polar-spherical-and-geographic-coordinates
		double x = this.radius * Math.cos(this.latitude) * Math.cos(this.longitude);
		double y = this.radius * Math.cos(this.latitude) * Math.sin(this.longitude);
		double z = this.radius * Math.sin(this.latitude);
		return new CartesianCoordinate(x,y,z);
	}

	@Override
	public double getCartesianDistance(Coordinate coord) {
		CartesianCoordinate cartThis = this.asCartesianCoordinate();
		return cartThis.getCartesianDistance(coord);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}

	@Override
	public double getSphericDistance(Coordinate coord) {
		if(coord == null) {
			return -Double.MAX_VALUE;
		}
		SphericCoordinate tmp = coord.asSphericCoordinate();
		if(Math.abs(tmp.getRadius()) >= Double.MAX_VALUE || Math.abs(tmp.getLongitude()) >= Double.MAX_VALUE || Math.abs(tmp.getLatitude()) >= Double.MAX_VALUE) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		double diffLat = Math.abs(this.latitude - tmp.latitude);
		double diffLong = Math.abs(this.longitude - tmp.longitude);
		double centralAngle = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(diffLat/2.0), 2.0) 
											   		+ Math.cos(this.latitude) * Math.cos(tmp.latitude) * Math.pow(Math.sin(diffLong/2.0), 2.0)));
		return this.radius * centralAngle;
	}

	@Override
	public double getDistance(Coordinate coord) {
		if(coord == null) {
			return -Double.MAX_VALUE;
		}
		if(coord instanceof CartesianCoordinate) {
			return getCartesianDistance(coord);
		}
		return getSphericDistance(coord);
	}

	@Override
	public boolean isEqual(Coordinate coord) {
		if(coord == null) {
			return false;
		}
		SphericCoordinate tmp = coord.asSphericCoordinate();
		return	Math.abs(this.latitude - tmp.latitude) <= eps 
			 && Math.abs(this.longitude - tmp.longitude) <= eps 
			 && Math.abs(this.radius - tmp.radius) <= eps;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		if(Math.abs(longitude) >= Double.MAX_VALUE) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		if(Math.abs(longitude) <= Math.PI) {
			this.longitude = longitude;
		} else {
			int n = (int) (longitude / Math.PI);
			this.longitude = longitude - n * Math.PI; 
		}
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		if(Math.abs(latitude) >= Double.MAX_VALUE) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		if(Math.abs(latitude) <= Math.PI/2.0) {
			this.latitude = latitude;
		}
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		if(Math.abs(radius) >= Double.MAX_VALUE) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		if(radius >= 0.0) {
			this.radius = radius;
		}
	}

}
