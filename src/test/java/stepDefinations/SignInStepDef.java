package stepDefinations;

import com.base.PreDefinedMethods;
import com.constant.constant;
import com.util.PropertyFileReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignInStepDef {

	PreDefinedMethods preDefMe = new PreDefinedMethods();

	@Given("User navigates to the Ironclad home page")
	public void user_navigates_to_the_ironclad_home_page() {
		preDefMe.navigateToURL();
	}

	@When("User click on the Sign In button")
	public void user_click_on_the_sign_in_button() {
		preDefMe.clickOnElement("homePagesigninButton_xpath", "HomePage");
	}

	@Then("Verify that {string} is displayed on the Sign In page")
	public void verify_that_is_displayed_on_the_sign_in_page(String string) {
		preDefMe.waitForElementToBeVisible("signInText_xpath", "SignInPage");
		preDefMe.verifyText("signInText_xpath", string, "SignInPage");
	}

	@When("User enters invalid {string}")
	public void user_enters_invalid(String string) {
		preDefMe.waitForElementToBeVisible("signInText_xpath", "SignInPage");
		preDefMe.enterText("enterEmailTextBox_name", "SignInPage", string);
	}

	@When("User click on the continue button")
	public void user_click_on_the_continue_button() {
		preDefMe.clickOnElement("continueButton_xpath", "SignInPage");
	}

	@Then("Verify that {string} is displayed")
	public void verify_that_is_displayed(String string) {
		preDefMe.waitForElementToBeVisible("invalidEmailErrorText_xpath", "SignInPage");
		preDefMe.verifyText("invalidEmailErrorText_xpath", string, "SignInPage");
	}
}
