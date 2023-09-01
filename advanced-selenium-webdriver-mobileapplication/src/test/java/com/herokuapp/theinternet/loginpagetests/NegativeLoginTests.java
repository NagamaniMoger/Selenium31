package com.herokuapp.theinternet.loginpagetests;

import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.CsvDataProviders;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePage;

public class NegativeLoginTests extends TestUtilities {

	
	@Test(priority = 2, dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)//since we don't have dataprovider class in the TestUtilities class which we extend so we need to mention it here with class name and extension
	public void negativeLoginTest(Map<String, String> testData) {
		//Creating data variables(copy from csv file)
		String no = testData.get("no");
		String username = testData.get("username");
		String password = testData.get("password");
		String expectedErrorMessage = testData.get("expectedMessage");
		String description = testData.get("description");
		
		log.info("Starting negativeLoginTest #" + no + "for" + description);
		
		//open main page
		WelcomePage welcomePage = new WelcomePage(driver, log);
		welcomePage.openPage();
		
		// Click on Form Authentication link
		LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
		
		// enter username and password
		//execute negative login
		loginPage.negativeLogIn(username, password);
		
		//wait for error message
		loginPage.waitForErrorMessage();
		String message = loginPage.getErrorMessageText();
		
		//Verification
		Assert.assertTrue(message.contains(expectedErrorMessage), "Message doesn't contain expexted text.. ");

		// Verification
		String actualErrorMessage = driver.findElement(By.id("flash")).getText();
		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"actualErrorMessage does not contain expectedErrorMessage\nexpectedErrorMessage: "
						+ expectedErrorMessage + "\nactualErrorMessage: " + actualErrorMessage);


	}


}
