package com.herokuapp.theinternet.alertstests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.JavaScriptAlertsPage;
import com.herokuapp.theinternet.pages.WelcomePage;

public class AlertsTests extends TestUtilities {

	@Test
	public void jsAlertTest() {
		log.info("Starting jsAlertTest");
		SoftAssert softassert = new SoftAssert();

		// open main page
		WelcomePage welcomePage = new WelcomePage(driver, log);
		welcomePage.openPage();

		// Click on JavaScript Alerts link
		JavaScriptAlertsPage alertsPage = welcomePage.clickJavaScriptAlertsLink();

		// Click JS Alert button
		alertsPage.openJSAlert();
		sleep(1000);
		
		// Get alert text
		String alertMessage = alertsPage.getAlertText();

		// Click OK button
		alertsPage.acceptAlert();

		// Get Results text
		String result = alertsPage.getResultText();
		sleep(1000);
		// Verifications
		// 1 - Alert text is expected
		softassert.assertTrue(alertMessage.equals("I am a JS Alert"),
				"Alert message is not expected. \nShould be 'I am a JS Alert', but it is '" + alertMessage + "'");

		// 2 - Result text is expected
		softassert.assertTrue(result.equals("You successfuly clicked an alert"),
				"result is not expected. \nShould be 'You successfuly clicked an alert', but it is '" + result + "'");
		softassert.assertAll();
	}

	@Test
	public void jsDismissTest() {
		log.info("Starting jsDismissTest");
		SoftAssert softassert = new SoftAssert();

		// open main page
		WelcomePage welcomePage = new WelcomePage(driver, log);
		welcomePage.openPage();

		// Click on JavaScript Alerts link
		JavaScriptAlertsPage alertsPage = welcomePage.clickJavaScriptAlertsLink();

		// Click JS Confirm button
		alertsPage.openJSConfirm();
		sleep(1000);
		// Get alert text
		String alertMessage = alertsPage.getAlertText();

		// Click Cancel button
		alertsPage.dismissAlert();

		// Get Results text
		String result = alertsPage.getResultText();
		sleep(1000);
		// Verifications
		// 1 - Alert text is expected
		softassert.assertTrue(alertMessage.equals("I am a JS Confirm"),
				"Alert message is not expected. \nShould be 'I am a JS Confirm', but it is '" + alertMessage + "'");

		// 2 - Result text is expected
		softassert.assertTrue(result.equals("You clicked: Cancel"),
				"result is not expected. \nShould be 'You clicked: Cancel', but it is '" + result + "'");
		softassert.assertAll();
	}

	@Test
	public void jsPromptTest() {
		log.info("Starting jsDismissTest");
		SoftAssert softassert = new SoftAssert();

		// open main page
		WelcomePage welcomePage = new WelcomePage(driver, log);
		welcomePage.openPage();

		// Click on JavaScript Alerts link
		JavaScriptAlertsPage alertsPage = welcomePage.clickJavaScriptAlertsLink();

		// Click JS Prompt button
		alertsPage.openJSPrompt();
		sleep(1000);
		// Get alert text
		String alertMessage = alertsPage.getAlertText() + "[FAIL]";//Test should fail

		// Type text into alert
		alertsPage.typeTextIntoAlert("Hello Alert, it's Nagamani here");
		sleep(1000);
		// Get Results text
		String result = alertsPage.getResultText() + "[FAIL]";//Test should fail
		/*
		 * As alert message is not same as original and result message is not same as
		 * original so test should fail----as we are using soft assertion test won't fail
		 * at the beginning after executing everything test fails(assertAll)
		 */
		sleep(1000);
		// Verifications
		// 1 - Alert text is expected
		softassert.assertTrue(alertMessage.equals("I am a JS prompt"),
				"Alert message is not expected. \nShould be 'I am a JS prompt', but it is '" + alertMessage + "'");

		// 2 - Result text is expected
		softassert.assertTrue(result.equals("You entered: Hello Alert, it's Nagamani here"),
				"result is not expected. \nShould be 'You entered: Hello Alert, its Nagamani here', but it is '" + result
						+ "'");
		softassert.assertAll();
	}
}
