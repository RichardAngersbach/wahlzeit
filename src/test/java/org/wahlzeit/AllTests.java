package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.wahlzeit.handlers.AllTestsHandlers;
import org.wahlzeit.model.AllTestsModel;
import org.wahlzeit.model.persistence.AllTestsModelPersistence;
import org.wahlzeit.services.AllTestsServices;
import org.wahlzeit.services.mailing.AllTestsServicesMailing;
import org.wahlzeit.utils.AllTestsUtils;

@RunWith(Suite.class)
@SuiteClasses({ AllTestsHandlers.class,
				AllTestsModel.class,
				AllTestsModelPersistence.class,
				AllTestsServices.class,
				AllTestsServicesMailing.class,
				AllTestsUtils.class
})

public class AllTests {
	
}
