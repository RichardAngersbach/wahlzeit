package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SphericCoordinateTest {
	SphericCoordinate a = null;
	SphericCoordinate b = null;
	SphericCoordinate c = null;
	SphericCoordinate d = null;
	
	@Before
	public void setUpCoordinates() {
		a = new SphericCoordinate(Math.PI/2.0, Math.PI/2.0, 1.0);
		b = new SphericCoordinate(Math.PI/2.0, Math.PI/2.0, 1.0);
		c = new SphericCoordinate(2.0 * Math.PI, 2.0 * Math.PI, -1.0);
		d = new SphericCoordinate(Math.PI/4.0, Math.PI/4.0, 1.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCtor() {
		SphericCoordinate tmp = new SphericCoordinate(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
	}
	
	@Test
	public void testSetUpAndEquals() {
		assertTrue(Math.abs(a.getLatitude()) <= Math.PI/2.0);
		assertTrue(Math.abs(a.getLongitude()) <= Math.PI);
		assertEquals(Math.abs(c.getLatitude()), 0.0, 0.001);
		assertEquals(Math.abs(c.getLongitude()), Math.PI, 0.001);
		
		assertTrue(d.equals(new CartesianCoordinate(0.5, 0.5, 1/Math.sqrt(2.0))));
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		
		assertFalse(a.equals(null));
	}
	
	@Test
	public void testConversions() {
		assertTrue(a.equals(a.asCartesianCoordinate().asSphericCoordinate()));
		assertTrue(b.equals(b.asCartesianCoordinate().asSphericCoordinate()));
		assertTrue(c.equals(c.asCartesianCoordinate().asSphericCoordinate()));
		assertTrue(d.equals(d.asCartesianCoordinate().asSphericCoordinate()));
	}
	
	@Test 
	public void testSetAndGet() {
		SphericCoordinate e = new SphericCoordinate(0.0, 0.0, 0.0);
		e.setLatitude(Math.PI/4.0);
		e.setLongitude(Math.PI);
		e.setRadius(-100.0);
		
		assertEquals(e.getLatitude(), Math.PI/4.0, 0.001);
		assertEquals(e.getLongitude(), Math.PI, 0.001);
		assertEquals(e.getRadius(), 0.0, 0.001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetAndGet2() {
		a.setLatitude(Double.MAX_VALUE);
		a.setLongitude(Double.MAX_VALUE);
		a.setRadius(Double.MAX_VALUE);
	}
	
	@Test
	public void testGetDistances() {
		assertEquals(a.getDistance(a), 0.0, 0.001);
		assertEquals(a.asCartesianCoordinate().getDistance(b.asCartesianCoordinate()), a.getDistance(b), 0.001);
		assertEquals(c.getDistance(a), 0.0, 0.001);
		
		a.setRadius(1737.4); //radius of the moon in km
		d.setRadius(1737.4);
		c.setRadius(1737.4);
		
		//pre-calculated with https://www.lpi.usra.edu/lunar/tools/lunardistancecalc/
		assertEquals(a.getDistance(d), 1364.5507690867269, 0.001);
		assertEquals(c.getDistance(d), 3638.8020508979384, 0.001);
		assertEquals(a.asCartesianCoordinate().getDistance(d), 1364.5507690867269, 0.001);
		assertEquals(c.asCartesianCoordinate().getDistance(d), 3638.8020508979384, 0.001);
	}
}