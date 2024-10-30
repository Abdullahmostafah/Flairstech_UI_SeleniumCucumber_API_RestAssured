package StepDefinitions;

import Pages.AdminPage;
import Pages.LoginPage;
import TestBases.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AdminPanelSteps extends TestBase {

    WebDriver driver;
    LoginPage loginPage;
    AdminPage adminPage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        openWebSite();
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
    }

    @When("I log in with username {string} and password {string}")
    public void i_log_in(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I navigate to the Admin tab")
    public void i_navigate_to_the_admin_tab() {
        adminPage.navigateToAdminTab();
    }

    // Steps to note initial count, add user, verify count increase, delete user, verify count decrease
}

