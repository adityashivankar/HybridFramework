package com.hooks;

import com.base.PreDefinedMethods;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class hooks {
	PreDefinedMethods predefMe = new PreDefinedMethods();

	@After
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed())
			scenario.attach(predefMe.capatureScreenshot(), "image/png", scenario.getName());
	}
}
