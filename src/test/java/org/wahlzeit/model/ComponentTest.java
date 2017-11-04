package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ComponentTest {
	@Test
	public void compareCoordinateValues() {
		Coordinate a = new Coordinate(2.0, 3.0, 4.0);
		Coordinate b = new Coordinate(2.0, 3.0, 4.0);
		Coordinate c = new Coordinate(0.0, 0.0, 0.0);
		assertTrue(a.equals(b));
		assertTrue(b.equals(a));
		assertFalse(c.equals(a) && c.equals(b));
	}
	
	@Test
	public void computeDistances() {
		Coordinate a = new Coordinate(0.0, 0.0, 0.0);
		Coordinate b = new Coordinate(1.0, 1.0, 1.0);
		assertTrue(a.getDistance(b) == Math.sqrt(3.0));
		assertTrue(a.getDistance(a) == 0.0);
	}
	
}
