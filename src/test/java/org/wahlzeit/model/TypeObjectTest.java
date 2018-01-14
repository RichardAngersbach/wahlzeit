package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

public class TypeObjectTest {
	Skyline a = null;
	Skyline aa = null;
	Skyline ab = null;
	Skyline b = null;
	Skyline c = null;
	Skyline d = null;
	
	SkylineManager sm;
	
	@Before
	public void setUp() {
		sm = SkylineManager.getInstance();
		
		a = sm.createSkyline("a");
		aa = sm.createSkyline("a");
		ab = sm.createSkyline("a");
		b = sm.createSkyline("b");
		c = sm.createSkyline("c");
		d = sm.createSkyline("d");
		
		a.getType().addSubType(c.getType());
		a.getType().addSubType(d.getType());
	}
	
	@Test
	public void testSetUp() {
		assertTrue(a != null);
		assertTrue(aa != null);
		assertTrue(sm != null);
		
		assertTrue(a.getType() != null);
		assertTrue(aa.getType() != null);
	}
	
	@Test
	public void testSkylineTypes() {
		assertTrue(a.getType().getTypeName().equals("a"));
		assertTrue(aa.getType().getTypeName().equals("a"));
		assertTrue(b.getType().getTypeName().equals("b"));
		assertTrue(c.getType().getTypeName().equals("c"));
		assertTrue(d.getType().getTypeName().equals("d"));
		assertFalse(a.getType().getTypeName().equals(null));
	}
	
	@Test(expected=AssertionError.class)
	public void testSkylineSubTypes() {
		assertTrue(a.getType().hasInstance(c));
		assertTrue(a.getType().hasInstance(d));
		
		assertTrue(aa.getType().hasInstance(a));
		assertTrue(aa.getType().hasInstance(aa));
		assertTrue(aa.getType().hasInstance(ab));
		assertFalse(aa.getType().hasInstance(b));
		
		aa.getType().hasInstance(null); //expected error
	}
}
