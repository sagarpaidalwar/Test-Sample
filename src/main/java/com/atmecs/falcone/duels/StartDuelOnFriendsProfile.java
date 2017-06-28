package com.atmecs.falcone.duels;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.atmecs.falcon.automation.verifyresult.VerificationManager;
import com.atmecs.falcon.testfunction.CommonMethods;
import com.atmecs.falcon.testsuite.TestSuiteBase;

public class StartDuelOnFriendsProfile extends TestSuiteBase
{
//Go to Friends---Done
//Go to Friends Profile---Done
//Check For Duel Image---Done
//Start Duel---
//Send 3days Duel
//Go Back Navigate to Friends Profile
CommonMethods commonmethods;	
@Test	
public void sendDuelsFromFriendsProfile() throws IOException
{
	
	commonmethods= new CommonMethods(driver,prop);
	commonmethods.goToLeftMenu("Friends");
	commonmethods.waitForVisible(By.id(prop.getProperty("leftmenu.friends.teammate"))); 
	commonmethods.goToFriendsProfile("sagar paidalwar");
	VerificationManager.verifyBoolean(driver.findElementById(prop.getProperty("leftmenu.friends.profile.duels")).isDisplayed(), true, "Duels icon is displayed on Friends Profile screen");

}
} 