package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

public class CartesianCoordinateTest {
	CartesianCoordinate a = null;
	CartesianCoordinate b = null;
	CartesianCoordinate c = null;
	CartesianCoordinate d = null;
	CartesianCoordinate e = null;
	
	@Before
	public void setUpCoordinates() {
		a = new CartesianCoordinate(0.0, 1.0, 2.0);
		b = new CartesianCoordinate(0.0, 1.0, 2.0);
		c = new CartesianCoordinate(1.0, 1.0, 1.0);
		d = new CartesianCoordinate(0.0, 0.0, 0.0);
		e = new CartesianCoordinate(0.5, 0.5, 1/Math.sqrt(2.0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCtor() {
		CartesianCoordinate tmp = new CartesianCoordinate(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
	}
	
	@Test
	public void testSetUpAndEquals() {
		assertEquals(a.getX(), 0.0, 0.001);
		assertEquals(a.getY(), 1.0, 0.001);
		assertEquals(a.getZ(), 2.0, 0.001);
		
		assertTrue(e.equals(new SphericCoordinate(Math.PI/4.0, Math.PI/4.0, 1.0)));
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(null));
	}
	
	@Test
	public void testConversions() {
		assertTrue(a.equals(a.asSphericCoordinate().asCartesianCoordinate()));
		assertTrue(b.equals(b.asSphericCoordinate().asCartesianCoordinate()));
		assertTrue(c.equals(c.asSphericCoordinate().asCartesianCoordinate()));
		assertTrue(d.equals(d.asSphericCoordinate().asCartesianCoordinate()));
	}
	
	@Test 
	public void testSetAndGet() {
		CartesianCoordinate tmp = new CartesianCoordinate(0.0, 0.0, 0.0);
		tmp.setX(Double.NaN);
		tmp.setY(0.0);
		tmp.setZ(-1.0);
		
		assertEquals(tmp.getX(), Double.NaN, 0.001);
		assertEquals(tmp.getY(), 0.0, 0.001);
		assertEquals(tmp.getZ(), -1.0, 0.001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetAndGet2() {
		a.setX(Double.MAX_VALUE);
		a.setY(Double.MAX_VALUE);
		a.setZ(Double.MAX_VALUE);
	}
	
	@Test
	public void testGetDistances() {
		assertEquals(a.getDistance(a), 0.0, 0.001);
		assertEquals(a.asSphericCoordinate().getDistance(b.asSphericCoordinate()), a.getDistance(b), 0.001);
		
		assertEquals(a.getDistance(b), 0.0, 0.001);
		assertEquals(a.getDistance(c), Math.sqrt(2), 0.001);
		assertEquals(a.getDistance(d), Math.sqrt(5), 0.001);
		
		assertEquals(a.getCartesianDistance(null), -Double.MAX_VALUE, 0.001);
		assertEquals(a.getSphericDistance(null), -Double.MAX_VALUE, 0.001);
	}
}
