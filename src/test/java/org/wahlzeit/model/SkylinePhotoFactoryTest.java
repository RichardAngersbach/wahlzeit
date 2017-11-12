package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

public class SkylinePhotoFactoryTest {
	
	SkylinePhotoFactory factory = null;
	PhotoId pId = null;
	SkylinePhoto p = null;
	SkylinePhoto p2 = null;
	
	@ClassRule
	public static TestRule chain = RuleChain.
			   					   outerRule(new LocalDatastoreServiceTestConfigProvider()).
			   					   around(new RegisteredOfyEnvironmentProvider());
	
	@Before
	public void setUpFactory() {
		factory = SkylinePhotoFactory.getInstance();
		pId = new PhotoId(0);
		p = factory.createPhoto(pId);
		p2 = factory.createPhoto(pId);
	}

	@Test
	public void testSetUp() {
		assertTrue(factory != null);
		assertTrue(p != null);
		assertTrue(p2 != null);
		assertTrue(p.getId().equals(p2.getId()));
	}

}
