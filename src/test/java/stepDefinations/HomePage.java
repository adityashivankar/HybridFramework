package stepDefinations;

import com.base.PreDefinedMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePage extends PreDefinedMethods {

	@Given("User navigates to the linkedin home page")
	public void user_navigates_to_the_linkedin_home_page() {
		navigateToURL();
	}

	@When("User cliked on join now button")
	public void user_cliked_on_join_now_button() {
		clickOnElement("ContactButton_xpath", "HomePage");
	}

	@Then("Verify that user is on sign up page")
	public void verify_that_user_is_on_sign_up_page() {
		verifyText("LetsTalkText_xpath", "Let's talk technology, let's talk solutions", "HomePage");
	}

}
