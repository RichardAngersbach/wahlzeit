package org.wahlzeit.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccessRightsTest.class, CartesianCoordinateTest.class, SphericCoordinateTest.class, FlagReasonTest.class, GenderTest.class, GuestTest.class,
		LocationTest.class, PhotoFilterTest.class, SkylinePhotoFactoryTest.class, SkylinePhotoManagerTest.class,
		SkylinePhotoTest.class, TagsTest.class, UserStatusTest.class, TypeObjectTest.class, ValueTest.class })
public class AllTestsModel {

}
