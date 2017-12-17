package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate {
	private final double x;
	private final double y;
	private final double z;
	
	//default constructor
	public CartesianCoordinate() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	//parameterized constructor
	public CartesianCoordinate(double x, double y, double z) {
		double _x, _y, _z;
		_x = _y = _z = 0.0;
		
		try {
			assertValidValue(x);
			_x = x;
		} catch(IllegalArgumentException e) {}
		
		try {
			assertValidValue(y);
			_y = y;
		} catch(IllegalArgumentException e) {}
		
		try {
			assertValidValue(z);
			_z = z;
		} catch(IllegalArgumentException e) {}
		
		this.x = _x;
		this.y = _y;
		this.z = _z;
	}
	
	public static CartesianCoordinate getCartesianInstance(CartesianCoordinate coord) {
		Integer integerCode = new Integer(coord.getCoordinateCode());
		CartesianCoordinate instance = (CartesianCoordinate) sharedInstances.get(integerCode);
		if(instance == null) {
			sharedInstances.put(coord.getCoordinateCode(), coord);
			return coord;
		}
		return instance;
	}
	
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}
	//@throws IllegalArgumentException when argument is null
	//@throws ClassInvariantsException when class invariants not valid
	@Override
	public double getCartesianDistance(Coordinate coord) throws IllegalArgumentException, ClassInvariantsException {
		assertNotNullArg(coord);
		assertClassInvariants(coord);
		
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
		if(x < 0 && y >= 0) {
			longitude += Math.PI;
		} else if(x < 0 && y < 0) {
			longitude -= Math.PI;
		} else if(Math.abs(x) <= eps && y > 0) {
			longitude = Math.PI/2.0;
		} else if(Math.abs(x) <= eps && y < 0) {
			longitude = -Math.PI/2.0;
		}
		SphericCoordinate ret = new SphericCoordinate(latitude, longitude, r);
		return ret;
	}
	
	//@throws IllegalArgumentException when argument is null
	//@throws ClassInvariantsException when class invariants not valid
	@Override
	public double getSphericDistance(Coordinate coord) throws IllegalArgumentException, ClassInvariantsException {
		assertNotNullArg(coord);
		assertClassInvariants(coord);
		
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
	
	public CartesianCoordinate setX(double x) {
		double _x = 0.0;
		try {
			assertValidValue(x);
			_x = x;
		} catch(IllegalArgumentException e) {}
		
		return getCartesianInstance(new CartesianCoordinate(_x, this.getY(), this.getZ()));
	}

	public double getY() {
		return y;
	}
	
	public CartesianCoordinate setY(double y) {
		double _y = 0.0;
		try {
			assertValidValue(y);
			_y = y;
		} catch(IllegalArgumentException e) {}
		
		return getCartesianInstance(new CartesianCoordinate(this.getX(), _y, this.getZ()));
	}

	public double getZ() {
		return z;
	}
	
	public CartesianCoordinate setZ(double z) {
		double _z = 0.0;
		try {
			assertValidValue(z);
			_z = z;
		} catch(IllegalArgumentException e) {}
		
		return getCartesianInstance(new CartesianCoordinate(this.getX(), this.getY(), _z));
	}
	
	public int getCoordinateCode() {
		return this.hashCode();
	}
}
