package org.test;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class signup {

    @When("user provides valid information {string} {string}")
    public void user_provides_valid_information(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        User user = new User();
        user.adduser(string,string2);

    }
    @Then("user succesfully signs up")
    public void user_succesfully_signs_up() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(User.loginFlag);
    }

    @When("user provides invalid information {string} {string}")
    public void user_provides_invalid_information(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        User user = new User();
        user.adduser(string,string2);

    }
    @Then("user failed to sign up")
    public void user_failed_to_sign_up() {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(User.loginFlag);

    }
}
