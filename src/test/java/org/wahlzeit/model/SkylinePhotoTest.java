package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

public class SkylinePhotoTest {
	
	SkylinePhoto photo = null;
	SkylinePhoto photoLoc = null;
	SkylinePhoto photoID = null;
	
	@ClassRule
	public static TestRule chain = RuleChain.
								   outerRule(new LocalDatastoreServiceTestConfigProvider()).
								   around(new RegisteredOfyEnvironmentProvider());
	
	@Before
	public void setUpPhotos() {
		photo = new SkylinePhoto();
		photoLoc = new SkylinePhoto(new Location(new CartesianCoordinate(0.0, 0.0, 0.0)));
		photoID = new SkylinePhoto(new PhotoId(0));
	}
	
	@Test
	public void testSetUp() {
		assertTrue(photo != null);
		
		assertTrue(photoLoc != null);
		assertTrue(photoLoc.getLocation() != null);
		assertTrue(photoLoc.getLocation().getCoordinate() != null);
		
		assertTrue(photoID != null);
		assertTrue(photoID.getId() != null);
	}
	
	@Test
	public void testSetAndGet() {
		photo.setDate("1.1.2018");	
		assertTrue(photo.getDate().compareTo("1.1.2018") == 0);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSetAndGet2() {
		photoLoc.setDate(null);
		assertTrue(photoLoc.getDate().compareTo("") == 0);
	}
}
