package org.automation.suits;

import org.automation.tests.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by SSarker on 9/9/2018.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {InvalidAuthTests.class,
                ValidAuthTests.class,
                ViewAllBugsTests.class
        }
)
public class ReleaseSepetemberRegressionTests {
}
