package testing;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.*;

import static org.junit.Assert.*;

public class Login {
    Admin admin = new Admin();
    Customer customer = new Customer();
    Chef chef = new Chef();
    ChefManager cm = new ChefManager();

    boolean loginResult;
    String loginMessage;



    @Given("the Customer is not logged in")
    public void the_customer_is_not_logged_in() {
        customer.clear();
    }

    @Given("the Customer username is {string}")
    public void the_customer_username_is(String username) {
        customer.setUsername(username);
    }

    @Given("the Customer password is {string}")
    public void the_customer_password_is(String password) {
        customer.setPassword(password);
        loginResult = customer.login();
        loginMessage = customer.getLoginMessage();
    }

    @Then("the login should succeed")
    public void the_login_should_succeed() {
        assertTrue(loginResult);
    }



    @Given("the Admin is not logged in")
    public void the_admin_is_not_logged_in() {
        admin.clear();
    }

    @Given("the Admin username is {string}")
    public void the_admin_username_is(String username) {
        admin.setUsername(username);
    }

    @Given("the Admin password is {string}")
    public void the_admin_password_is(String password) {
        admin.setPassword(password);
        loginResult = admin.login();
        loginMessage = admin.getLoginMessage();
    }



    @Given("the Chef is not logged in")
    public void the_chef_is_not_logged_in() {
        chef.clear();
    }

    @Given("the Chef username is {string}")
    public void the_chef_username_is(String username) {
        chef.setUsername(username);
    }

    @Given("the Chef password is {string}")
    public void the_chef_password_is(String password) {
        chef.setPassword(password);
        loginResult = chef.login();
        loginMessage = chef.getLoginMessage();
    }



    @Given("the Chef Manager is not logged in")
    public void the_chef_manager_is_not_logged_in() {
        cm.clear();
    }

    @Given("the Chef Manager username is {string}")
    public void the_chef_manager_username_is(String username) {
        cm.setUsername(username);
    }

    @Given("the Chef Manager password is {string}")
    public void the_chef_manager_password_is(String password) {
        cm.setPassword(password);
        loginResult = cm.login();
        loginMessage = cm.getLoginMessage();
    }
    private void assertLoginFailureWithMessage(String expectedMessage) {
        assertFalse(loginResult);
        assertEquals(expectedMessage, loginMessage);
    }

    @Then("the login should fail  {string} message")
    public void the_login_should_fail_with_a_message(String expectedMessage) {
        assertLoginFailureWithMessage(expectedMessage);
    }
    @Then("the login should fail with an with {string} message")
    public void the_login_should_fail_with_an_message(String expectedMessage) {
        assertLoginFailureWithMessage(expectedMessage);
    }


}
