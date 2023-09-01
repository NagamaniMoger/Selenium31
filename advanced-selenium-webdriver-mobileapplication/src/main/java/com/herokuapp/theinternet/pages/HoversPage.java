package com.herokuapp.theinternet.pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HoversPage extends BasePageObject {

	private String pageUrl = "http://the-internet.herokuapp.com/hovers";

	private By avatarLocator = By.xpath("//div[@class='figure']");
	private By viewProfileLinkLocator = By.xpath(".//a[contains(text(),'View profile')]");

	/*
	 * dot in the xpath is used because when we use the xpath without dot it takes first link but
	 * we need 2nd one so dot specifies don't take that xpath and below we have
	 * specifiedUserAvatar which carries 2nd avatar and tells click on this link
	 */
	public HoversPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/** Open HoversPage with it's url */
	public void openPage() {
		log.info("Opening page: " + pageUrl);
		openUrl(pageUrl);
		log.info("Page opened!");
	}

	/** Open specified user profile */
	public void openUserProfile(int i) {
		List<WebElement> avatars = findAll(avatarLocator);
		WebElement specifiedUserAvatar = avatars.get(i - 1);// for some starts from zero so we need 2nd avatar so -1
		hoverOverElement(specifiedUserAvatar);
		specifiedUserAvatar.findElement(viewProfileLinkLocator).click();
	}
}
