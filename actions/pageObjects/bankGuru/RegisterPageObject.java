package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPage;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;

	public RegisterPageObject(WebDriver mapDriver) {
		driver = mapDriver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.NAME, using = "emailid")
	private WebElement emailTextbox;

	@FindBy(how = How.NAME, using = "btnLogin")
	private WebElement submitButton;

	@FindBy(how = How.XPATH, using = "//td[text()='User ID :']/following-sibling::td")
	private WebElement userIDText;

	@FindBy(how = How.XPATH, using = "//td[text()='Password :']/following-sibling::td")
	private WebElement passwordText;

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, email);
	}

	public String getUserIDText() {
		waitForElementVisible(driver, userIDText);
		return getElementText(driver, userIDText);
	}

	public String getPasswordText() {
		waitForElementVisible(driver, passwordText);
		return getElementText(driver, passwordText);
	}

	public LoginPageObject openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);
		return PageGeneratorManager.getLoginPage(driver);
		
	}

	public void clickToSubmitButton() {
		waitForElementClickable(driver, submitButton);
		clickToElement(driver, submitButton);
	}

}
