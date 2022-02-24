package testRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.base.PreDefinedMethods;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(features = "src/test/resources/Features", glue = { "stepDefinations", "com.hooks" }, plugin = {
		"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })

public class TestRunner extends PreDefinedMethods {

	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeTest
	public void tearUP() {
		browserInitialization();
	}

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable {
		// the 'featureWrapper' parameter solely exists to display the feature
		// file in a test report
		testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
	}

	@DataProvider
	public Object[][] scenarios() {
		if (testNGCucumberRunner == null) {
			return new Object[0][0];
		}
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		if (testNGCucumberRunner == null) {
			return;
		}
		testNGCucumberRunner.finish();
	}

	@AfterTest
	public void tearDown() {
		browserClose();
	}
}