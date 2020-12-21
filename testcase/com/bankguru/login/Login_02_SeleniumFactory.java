package com.bankguru.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.bankGuru.HomePageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.RegisterPageObject;

public class Login_02_SeleniumFactory {

	WebDriver driver;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	String loginPageUrl, userIDValue, passwordValue;
	String email = "automationx" + randomNumber() + "@gmail.com";

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", ".\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Mo app ra -> se den Login page
		driver.get("http://demo.guru99.com/v4/");

		// Khoi tao trang Login len
		loginPage = new LoginPageObject(driver);

		// Get login page Url
		loginPageUrl = loginPage.getLoginPageUrl();
	}

	@Test
	public void TC_01_Register() {

		System.out.println("Text size = " + loginPage.getTextboxSize());

		// B1: Click 'Here' link
		registerPage = loginPage.clickToHereLink();

		// B2: Nhap email vao emailTextbox
		registerPage.inputToEmailTextbox(email);

		// B3: Click 'submit' button
		registerPage.clickToSubmitButton();

		// B4: Lay ra gia tri UserID
		userIDValue = registerPage.getUserIDText();

		// B5: Lay ra gia tri Password
		passwordValue = registerPage.getPasswordText();

		// B6: Tu trang Register, mo lai trang Login page
		loginPage = registerPage.openLoginPage(loginPageUrl);

	}

	@Test
	public void TC_02_Login() {
		loginPage.inputToUserIDTextbox(userIDValue);

		loginPage.inputToPasswordTextbox(passwordValue);

		homePage = loginPage.clickToLoginButton();

		// Verify login successfully
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// random number
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
