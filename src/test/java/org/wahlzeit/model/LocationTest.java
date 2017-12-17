package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LocationTest {
	@Test
	public void compareLocations() {
		Location loc = new Location(new CartesianCoordinate());
		Location loc2 = new Location(null);
		Location loc3 = new Location(new CartesianCoordinate(1.0, 2.0, 3.0));
		assertTrue(loc.equals(loc2));
		assertFalse(loc.equals(loc3));
		
		Location loc4 = new Location(new SphericCoordinate());
		Location loc5 = new Location(new SphericCoordinate(-2 * Math.PI, -2 * Math.PI, -1.0));
		assertTrue(loc4.equals(loc5));
		assertFalse(loc.equals(null));
	}
}
