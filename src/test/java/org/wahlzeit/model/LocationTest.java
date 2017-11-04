package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LocationTest {
	@Test
	public void compareLocations() {
		Location loc = new Location(new Coordinate(0.0, 0.0, 0.0));
		Location loc2 = new Location(null);
		Location loc3 = new Location(new Coordinate(1.0, 2.0, 3.0));
		assertTrue(loc.equals(loc2));
		assertFalse(loc.equals(null));
		assertFalse(loc.equals(loc3));
	}
}
