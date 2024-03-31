package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.DisplayB;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.example.DisplayB.BudgCalen;
public class DisplayBudget {
    String username;
    @Given("user name is {string}")
    public void user_name_is(String string) {
        // Write code here that turns the phrase above into concrete actions
       username=string;
    }
    @When("admin choose display budget planner {string}")
    public void admin_choose_display_budget_planner(String string) {
        // Write code here that turns the phrase above into concrete actions
       DisplayB display=new DisplayB();
       display.BudgetDisplay(BudgCalen);
    }
    @Then("display budget file")
    public void display_budget_file() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(DisplayB.addToBudget);
    }

}
