package org.wahlzeit.model;

public class CartesianCoordinate implements Coordinate {
	private double x;
	private double y;
	private double z;
	
	private double eps = 0.001;
	
	//default constructor
	public CartesianCoordinate() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	//parameterized constructor 
	public CartesianCoordinate(double x, double y, double z) {
		if(x >= Double.MAX_VALUE || y >= Double.MAX_VALUE || z >= Double.MAX_VALUE) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		this.x = x;
		this.y = y;
		this.z = z;
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
		return this;
	}
	
	@Override
	public double getCartesianDistance(Coordinate coord) {
		if(coord == null) {
			return -Double.MAX_VALUE;
		}
		CartesianCoordinate tmp = coord.asCartesianCoordinate();		
		if(tmp.x >= Double.MAX_VALUE || tmp.y >= Double.MAX_VALUE || tmp.z >= Double.MAX_VALUE) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		
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
		return new SphericCoordinate(latitude, longitude, r);
	}

	@Override
	public double getSphericDistance(Coordinate coord) {
		SphericCoordinate sphericThis = this.asSphericCoordinate();
		return sphericThis.getSphericDistance(coord);
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
		CartesianCoordinate tmp = coord.asCartesianCoordinate();
		return	Math.abs(this.x - tmp.x) <= eps 
			 && Math.abs(this.y - tmp.y) <= eps 
			 && Math.abs(this.z - tmp.z) <= eps;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		if(Math.abs(x) >= Double.MAX_VALUE) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		if(Math.abs(y) >= Double.MAX_VALUE) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		this.y = y;
	}

	public double getZ() {
		if(Math.abs(z) >= Double.MAX_VALUE) {
			throw new IllegalArgumentException("Illegal Arguments");
		}
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
}
