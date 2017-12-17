package org.wahlzeit.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

public class SkylinePhotoManagerTest {
	
	PhotoManager m = null;
	PhotoId pId = null;
	Photo p = null;

	@ClassRule
	public static TestRule chain = RuleChain.
								   outerRule(new LocalDatastoreServiceTestConfigProvider()).
								   around(new RegisteredOfyEnvironmentProvider());
	
	@Before
	public void setUpManager() {
		m = SkylinePhotoManager.getInstance();
		pId = new PhotoId(0);
		p = new SkylinePhoto(pId);
	}
	
	@Test
	public void testSetUp() {
		assertTrue(m != null);
		try {
			m.addPhoto(p);
		} catch (IOException e) {
			Assert.fail();
		}
		assertTrue(m.getPhotoFromId(pId) != null);
	}
	
}
