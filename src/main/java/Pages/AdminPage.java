package Pages;

public class AdminPage {

    WebDriver driver;

    @FindBy(id = "menu_admin_viewAdminModule")
    WebElement adminTab;

    @FindBy(id = "btnAdd")
    WebElement addButton;

    @FindBy(id = "systemUser_userName")
    WebElement usernameField;

    @FindBy(id = "btnSave")
    WebElement saveButton;

    // Other required fields

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

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


}
