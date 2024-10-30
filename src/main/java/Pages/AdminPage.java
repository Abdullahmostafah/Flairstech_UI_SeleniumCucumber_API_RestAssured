package Pages;

import TestBases.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends TestBase {

    WebDriver driver;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "menu_admin_viewAdminModule")
    WebElement adminTab;

    @FindBy(id = "btnAdd")
    WebElement addButton;

    @FindBy(id = "systemUser_userName")
    WebElement usernameField;

    @FindBy(id = "btnSave")
    WebElement saveButton;

    // Other required fields

    public void navigateToAdminTab() {
        adminTab.click();
    }

    public void addUser(String username) {
        addButton.click();
        usernameField.sendKeys(username);
        // fill other details as needed
        saveButton.click();
    }
}


