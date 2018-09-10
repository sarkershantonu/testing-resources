SET REG_SEP=org.automation.suits.ReleaseSepetemberRegressionTests.class
SET REG_ALL=org.automation.RegressionTests.class
SET TEST_CLASS=%REG_SEP%
mvn -Dtest.suit=%TEST_CLASS% clean test