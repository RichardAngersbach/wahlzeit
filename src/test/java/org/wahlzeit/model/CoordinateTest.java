package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {
	@Test
	public void compareCoordinateValues() {
		Coordinate a = new Coordinate(2.0, 3.0, 4.0);
		Coordinate b = new Coordinate(2.0, 3.0, 4.0);
		Coordinate c = new Coordinate(0.0, 0.0, 0.0);
		assertTrue(a.equals(b));
		assertFalse(c.equals(a));
		assertFalse(c.equals(null));
	}
	
	@Test
	public void computeDistances() {
		Coordinate a = new Coordinate(0.0, 0.0, 0.0);
		Coordinate b = new Coordinate(1.0, 1.0, 1.0);
		assertEquals(a.getDistance(b), Math.sqrt(3.0), 0.001);
		assertEquals(a.getDistance(a),  0.0, 0.001);
		assertEquals(a.getDistance(null), -Double.MAX_VALUE, 0.001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void computeDistances2() {
		Coordinate a = new Coordinate(0.0, 0.0, 0.0);
		Coordinate b = new Coordinate(Double.MAX_VALUE+1, Double.MAX_VALUE+1, Double.MAX_VALUE+1);
		a.getDistance(b);
	}
	
}
