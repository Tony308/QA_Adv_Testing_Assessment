package com.qa.Main;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Landing {
	
	@FindBy(xpath = "/html/body/app-root/div[1]/nav/div/ul/li[2]/a")
	private WebElement ownerDrop;
	
	@FindBy(xpath = "/html/body/app-root/div[1]/nav/div/ul/li[2]/ul/li[2]/a")
	private WebElement ownerAdd;
	
	@FindBy(xpath = "//*[@id=\"firstName\"]")
	private WebElement firstname;
	
	@FindBy(xpath = "/html/body/app-root/app-owner-add/div/div/form/div[7]/div/button[2]")
	private WebElement addSubmit;
	
	
	public String addOwner(ChromeDriver driver, Actions action) {
		ownerDrop.click();
		ownerAdd.click();
		firstname.sendKeys("firstname");
		
		action = new Actions(driver);
		action.sendKeys(Keys.TAB, "surname").perform();
		action.sendKeys(Keys.TAB, "address").perform();
		action.sendKeys(Keys.TAB, "city").perform();
		action.sendKeys(Keys.TAB, "789456123").perform();
		addSubmit.click();
		return "";
	}

}
