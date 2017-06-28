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

public class SignInPromtAccountCreatedInJawboneSite extends TestSuiteBase{
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
	public void loginTest() throws Exception
	{
		
		commonmethods= new CommonMethods(driver,prop);
		commonmethods.testLogin(testData,"jawboneusername","jawbonepassword");
		commonmethods.waitForVisible(driver, By.id(prop.getProperty("loginscreen.chooseyouruppage"))); 
		VerificationManager.verifyString(driver.findElementById(prop.getProperty("loginscreen.chooseyouruppage")).getText(), "Choose Your UP", "Choose your page is displayed after logged in with jawbone account ");
	}

    @AfterTest
	public void afterTest()
	{
	}
}
