package com.atmecs.falcon.testfunction;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.codehaus.jettison.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.falcon.automation.util.parser.JsonParser;
import com.atmecs.falcon.automation.verifyresult.VerificationManager;
import com.jawbone.utility.Constants;
import com.sun.javafx.scene.control.behavior.ListCellBehavior;

public class CommonMethods implements Constants
{

	JsonParser par=new JsonParser();
	Properties locators=null;
	public AppiumDriver<MobileElement> driver;
	
	/*
	 * Constructor for getting driver and property object from main script
	 */
	public CommonMethods(AppiumDriver<MobileElement> driver, Properties prop)
	{
		locators=prop;
		this.driver=driver;
	}

	/*
	 * Method to log in to application
	 */
	public void	testLogin(org.codehaus.jettison.json.JSONObject tesdata,String emailid,String pass) throws Exception
     {
		    String emailAddress=emailid;
		    String password=pass;
		    waitForVisible(By.id(locators.getProperty("loginscreen.opensignin")));
    		driver.findElementById(locators.getProperty("loginscreen.opensignin")).click();
    		driver.findElementById(locators.getProperty("loginscreen.emailtextbox")).sendKeys((String)tesdata.get(emailAddress));
    		driver.findElementById(locators.getProperty("loginscreen.passwordtextbox")).sendKeys((String)tesdata.get(password));
    		driver.hideKeyboard();
    		driver.findElementById(locators.getProperty("loginscreen.signinbutton")).click();
    		try
    		{
    			driver.findElementById(locators.getProperty("homescreen.welcomehome")).click();
    			System.out.println("Welcome screen found for the for the first time login");
    		}catch(NoSuchElementException e)
    		{
    			System.out.println("No welcome screen found for the second time");
    		}
	 }

	/*
	 * Method used to wait until the element is visible
	 */
	public void waitForVisible(By by) throws IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, ELEMENT_WAIT_TIME);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/*
	 * Method for log out from the Application
	 */
	public void logout() throws IOException, InterruptedException
	{
		waitForVisible(By.id(locators.getProperty("homescreen.todaysdate")));
		driver.findElementByAccessibilityId(locators.getProperty("homescreen.leftmenubutton")).click();
		waitForVisible(By.id(locators.getProperty("homescreen.hometext")));
		driver.swipe(300, 900, 300,700,500);
		driver.findElementById(locators.getProperty("leftmenu.helpsettingsbutton")).click();
		driver.findElementById(locators.getProperty("leftmenu.signoutbutton")).click();
		driver.findElementById(locators.getProperty("leftmenu.signoutpopupokbutton")).click();
		waitForVisible(By.id(locators.getProperty("loginscreen.opensignin")));
	}
	public void goToLeftMenu(String menuName) throws IOException
	{
		waitForVisible(By.id(locators.getProperty("homescreen.todaysdate")));
		driver.findElementByAccessibilityId(locators.getProperty("homescreen.leftmenubutton")).click();
		waitForVisible(By.id(locators.getProperty("homescreen.hometext")));
		driver.findElementByName(menuName).click();
	    
	}
	
	public void goToFriendsProfile(String friendName)
	{
		boolean status;
		while(true)
		{
			try{
			status=driver.findElementByName(friendName).isDisplayed();
			}
			catch(NoSuchElementException e)
			{
				status=false;
				System.out.println("No friends of such name is available in Friends List"); 
			}
			if(status)
			{
				//Here just select medicine if found
				driver.findElementByName(friendName).click();
				break;
			}
			else
			{ 
				scrollupToElementInFriendsList();
			}
		}
		
	}
	
	public void scrollupToElementInFriendsList()
	{
		List<MobileElement> noOfFriendsList=driver.findElementsByXPath("//android.widget.ListView/android.widget.LinearLayout");
		int index=noOfFriendsList.size();
		int lastIndex=index-2;
		System.out.println("Name of the friends on last "+noOfFriendsList.get(lastIndex).findElementById("com.jawbone.up:id/teammate_name").getText());
		String bottomElementIndex="'"+String.valueOf(lastIndex)+"']"; 
		WebElement top= driver.findElementByXPath(locators.getProperty("leftmenu.friends.firstfriendxpath"));
		WebElement bottom= driver.findElementByXPath(locators.getProperty("leftmenu.friends.xpath")+bottomElementIndex);
		int topX=top.getLocation().x+25;
		int topY=top.getLocation().y+150;
	   int bottomX=bottom.getLocation().x+700;
	   int bottomY=bottom.getLocation().y; 
	   driver.swipe(bottomX, bottomY, topX, topY, 2000);
	}
	
	
}


