package pageObjects.bankGuru;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPage;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;

	public LoginPageObject(WebDriver mapDriver) {
		driver = mapDriver;
		PageFactory.initElements(driver, this);
	}

	// Dinh nghia het Locator
	// @FindBy(how = How.XPATH, using = "//a[text()='here']")
	@FindBy(xpath = "//a[text()='here']")
	private WebElement hereLink;
	// ==> tuong duong cach viet: private WebElement hereLinks = driver.findElement(By.xpath("//a[text()='here']"));

	@FindBy(how = How.NAME, using = "uid")
	private WebElement userIDTextbox;

	@FindBy(how = How.NAME, using = "password")
	private WebElement passwordTextbox;

	@FindBy(how = How.NAME, using = "btnLogin")
	private WebElement loginButton;

	@FindBy(how = How.CSS, using = "input[name='btnLogin']")
	private List<WebElement> loginButtons;

	@FindBys({ // AND
			@FindBy(how = How.NAME, using = "uid"), @FindBy(how = How.ID, using = "password") })
	private List<WebElement> bothCriteria;

	@FindAll({ // OR
			@FindBy(how = How.NAME, using = "uid"), @FindBy(how = How.NAME, using = "password") })
	private List<WebElement> eitherCriterion;

	public String getLoginPageUrl() {
		return getCurrentUrl(driver);
	}

	public int getTextboxSize() {
		return countElement(driver, eitherCriterion);
	}

	public RegisterPageObject clickToHereLink() {
		waitForElementClickable(driver, hereLink);
		clickToElement(driver, hereLink);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public void inputToUserIDTextbox(String userIDValue) {
		waitForElementVisible(driver, userIDTextbox);
		sendkeyToElement(driver, userIDTextbox, userIDValue);
	}

	public void inputToPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, passwordValue);
	}

	public HomePageObject clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
		return PageGeneratorManager.getHomePage(driver);
	}
}
