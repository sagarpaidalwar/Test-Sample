package com.atmecs.falcon.signinandoobe;

import org.codehaus.jettison.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.falcon.automation.verifyresult.VerificationManager;
import com.atmecs.falcon.testfunction.CommonMethods;
import com.atmecs.falcon.testsuite.TestSuiteBase;
import com.jawbone.utility.Readjson;

public class SignFailedInvalidPassword extends TestSuiteBase
{

	CommonMethods commonmethods;
	Readjson readjsonobj;
	public JSONObject testData;
	@BeforeTest
	public void beforetest()
	{
		readjsonobj=new Readjson();
		testData=readjsonobj.readJsonData("login.js");
	}
	
	@Test
	public void signinFailedInvalidPassword() throws Exception
	{
		
		commonmethods= new CommonMethods(driver,prop);
		commonmethods.waitForVisible(driver, By.id(prop.getProperty("loginscreen.opensignin"))); 
		commonmethods.testLogin(testData,"ValidUserName","Invalidpass");
		commonmethods.waitForVisible(driver, By.id(prop.getProperty("loginscreen.signfailedalerttitle")));
		VerificationManager.verifyString(driver.findElementById(prop.getProperty("loginscreen.signfailedmessage")).getText(), "Email or Password cannot be validated. Please try again", "Showing Correct Error Message For Sign In Failed With Invalid passwords");
	}

    @AfterTest
	public void afterTest()
	{
	}
}
