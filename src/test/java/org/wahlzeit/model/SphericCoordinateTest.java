package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SphericCoordinateTest {
	SphericCoordinate a = null;
	SphericCoordinate b = null;
	SphericCoordinate c = null;
	SphericCoordinate d = null;
	SphericCoordinate e = null;
	
	@Before
	public void setUpCoordinates() {
		a = SphericCoordinate.getSphericInstance(new SphericCoordinate(Math.PI/2.0, Math.PI/2.0, 1.0));
		b = SphericCoordinate.getSphericInstance(new SphericCoordinate(Math.PI/2.0, Math.PI/2.0, 1.0));
		c = SphericCoordinate.getSphericInstance(new SphericCoordinate(2.0 * Math.PI, 2.0 * Math.PI, -1.0));
		d = SphericCoordinate.getSphericInstance(new SphericCoordinate(Math.PI/4.0, Math.PI/4.0, 1.0));
		e = SphericCoordinate.getSphericInstance(new SphericCoordinate(0.0, 0.0, 0.0));
	}
	
	@Test
	public void testCtor() {
		SphericCoordinate tmp = new SphericCoordinate(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NaN);
		assertTrue(tmp.equals(new SphericCoordinate()));
	}
	
	@Test
	public void testSetUpAndEquals() {
		assertTrue(Math.abs(a.getLatitude()) <= Math.PI/2.0);
		assertTrue(Math.abs(a.getLongitude()) <= Math.PI);
		assertEquals(Math.abs(c.getLatitude()), 0.0, 0.001);
		assertEquals(Math.abs(c.getLongitude()), 0.0, 0.001);
		
		assertTrue(d.equals(new CartesianCoordinate(0.5, 0.5, 1/Math.sqrt(2.0))));
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
		assertFalse(a.equals(null));
	}
	
	@Test
	public void testConversions() {
		assertTrue(a.equals(a.asCartesianCoordinate().asSphericCoordinate()));
		assertTrue(b.equals(b.asCartesianCoordinate().asSphericCoordinate()));
		assertTrue(d.equals(d.asCartesianCoordinate().asSphericCoordinate()));
		assertTrue(c.equals(c.asCartesianCoordinate().asSphericCoordinate())); //setters map values to 0.0 for invalid values now
	}
	
	@Test 
	public void testSetAndGet() {
		e = e.setLatitude(Math.PI/4.0);
		e = e.setLongitude(Math.PI);
		e = e.setRadius(-100.0);
		
		assertEquals(e.getLatitude(), Math.PI/4.0, 0.001);
		assertEquals(e.getLongitude(), Math.PI, 0.001);
		assertEquals(e.getRadius(), 0.0, 0.001);
	}
	
	@Test
	public void testSetAndGet2() {
		a = a.setLatitude(Double.POSITIVE_INFINITY);
		a = a.setLongitude(Double.NEGATIVE_INFINITY);
		a = a.setRadius(Double.NaN);
		
		assertEquals(a.getLatitude(), 0.0, 0.001);
		assertEquals(a.getLongitude(), 0.0, 0.001);
		assertEquals(a.getRadius(), 0.0, 0.001);
	}
	
	@Test
	public void testGetDistances() {
		assertEquals(a.getDistance(a), 0.0, 0.001);
		assertEquals(a.asCartesianCoordinate().getDistance(b.asCartesianCoordinate()), a.getDistance(b), 0.001);
		assertEquals(c.getDistance(a), 0.0, 0.001);
		
		a = a.setRadius(1737.4); //radius of the moon in km
		d = d.setRadius(1737.4);
		c = c.setRadius(1737.4);
		
		//pre-calculated with https://www.lpi.usra.edu/lunar/tools/lunardistancecalc/
		assertEquals(a.getDistance(d), 1364.5507690867269, 0.001);
		assertEquals(c.getDistance(d), 1819.4010254489688, 0.001);
		assertEquals(a.asCartesianCoordinate().getDistance(d), 1364.5507690867269, 0.001);
		assertEquals(c.asCartesianCoordinate().getDistance(d), 1819.4010254489688, 0.001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetDistanceNull() {
		a.asCartesianCoordinate().getDistance(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetDistanceNull2() {
		a.getDistance(null);
	}
}
