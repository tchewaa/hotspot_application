package com.application.hotspotapplication.steps;

import com.application.hotspotapplication.configs.SpringIntergrationTests;
import com.application.hotspotapplication.requests.users.Users;
import com.application.hotspotapplication.requests.users.UsersService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javax.inject.Inject;
import lombok.Data;
import org.junit.Assert;

@Data
public class UserSteps extends SpringIntergrationTests {

  @Inject
  private UsersService usersService;
  private String result;
  private Users user;

  @When("^The client sends a request for all users$")
  public void allUsers() throws Throwable {
   result =  usersService.allUsers().toString();
  }

  @Then("Assert that there is a result$")
  public void assertResult() throws Throwable {
    Assert.assertTrue(!result.isEmpty());
  }

  @When("^The client sends a request for a specific user by email \"(.*)\"$")
  public void findUserByEmail(String email) throws Throwable {
    result =  usersService.findUserByEmail(email).toString();
  }

  @When("^The client sends a request for a specific user by id \"(.*)\"$")
  public void findUserByID(Long id) throws Throwable {
    user =  usersService.findUserById(id);
  }

  @And("^I assert the email is \"(.*)\"$")
  public void verifyCorrectEmail(String email) throws Throwable {
    Assert.assertTrue(email.equalsIgnoreCase(user.getEmail()));
  }







}
