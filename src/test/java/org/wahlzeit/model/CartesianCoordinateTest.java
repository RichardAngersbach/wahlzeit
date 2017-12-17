package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;

public class CartesianCoordinateTest {
	CartesianCoordinate a = null;
	CartesianCoordinate b = null;
	CartesianCoordinate c = null;
	CartesianCoordinate d = null;
	CartesianCoordinate e = null;
	CartesianCoordinate f = null;
	
	@Before
	public void setUpCoordinates() {
		a = CartesianCoordinate.getCartesianInstance(new CartesianCoordinate(0.0, 1.0, 2.0));
		b = CartesianCoordinate.getCartesianInstance(new CartesianCoordinate(0.0, 1.0, 2.0));
		c = CartesianCoordinate.getCartesianInstance(new CartesianCoordinate(1.0, 1.0, 1.0));
		d = CartesianCoordinate.getCartesianInstance(new CartesianCoordinate(0.0, 0.0, 0.0));
		e = CartesianCoordinate.getCartesianInstance(new CartesianCoordinate(0.5, 0.5, 1/Math.sqrt(2.0)));
		f = CartesianCoordinate.getCartesianInstance(new CartesianCoordinate(0.0, 0.0, 0.0));
	}
	
	@Test
	public void testCtor() {
		CartesianCoordinate tmp = CartesianCoordinate.getCartesianInstance(new CartesianCoordinate(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NaN));
		assertTrue(tmp.equals(new CartesianCoordinate()));
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
		assertTrue(d.equals(d.asSphericCoordinate().asCartesianCoordinate())); //setters map values to 0.0 for invalid values now
	}
	
	@Test 
	public void testSetAndGet() {
		f = f.setX(1.0);
		f = f.setY(0.0);
		f = f.setZ(-1.0);
		
		assertEquals(f.getX(), 1.0, 0.001);
		assertEquals(f.getY(), 0.0, 0.001);
		assertEquals(f.getZ(), -1.0, 0.001);
	}
	
	@Test
	public void testSetAndGet2() {
		a = a.setX(Double.NaN);
		a = a.setY(Double.POSITIVE_INFINITY);
		a = a.setZ(Double.NEGATIVE_INFINITY);
		
		assertEquals(a.getX(), 0.0, 0.001);
		assertEquals(a.getY(), 0.0, 0.001);
		assertEquals(a.getZ(), 0.0, 0.001);
	}
	
	@Test
	public void testGetDistances() {
		assertEquals(a.getDistance(a), 0.0, 0.001);
		assertEquals(a.asSphericCoordinate().getDistance(b.asSphericCoordinate()), a.getDistance(b), 0.001);
		
		assertEquals(a.getDistance(b), 0.0, 0.001);
		assertEquals(a.getDistance(c), Math.sqrt(2), 0.001);
		assertEquals(a.getDistance(d), Math.sqrt(5), 0.001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetDistanceNull() {
		a.asSphericCoordinate().getDistance(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetDistanceNull2() {
		a.getDistance(null);
	}
}
