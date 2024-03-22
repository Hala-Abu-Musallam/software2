package org.test;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.User;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Login {
    @When("username and password are true {string} {string}")
public void username_and_password_are_true(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
User user = new User();
user.login(string,string2);
}
    @Then("user succesfull loged in")
    public void user_succesfull_loged_in() {
        // Write code here that turns the phrase above into concrete actions
assertTrue(User.user_type>0 && User.user_type<4);
    }

    @When("username or password is false {string} {string}")
    public void usernameOrPasswordIsFalse(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
User user= new User();
user.login(string,string2);
    }
    @Then("user failed log in")
    public void userFailedLogIn() {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(User.user_type>4 && User.user_type<1);
    }





}







