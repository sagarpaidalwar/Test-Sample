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

public class SignInUsingAccountCreatedInAnotherPlatform extends TestSuiteBase{
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
	public void signInUsingAccountCreatedInAnotherPlatform() throws Exception
	{
		
		commonmethods= new CommonMethods(driver,prop);
		commonmethods.testLogin(testData,"iosusername","iospassword");
        commonmethods.waitForVisible(driver, By.id(prop.getProperty("homescreen.todaysdate")));   
		driver.findElementById(prop.getProperty("homescreen.todaysdate")).getText();
		VerificationManager.verifyString(driver.findElementById(prop.getProperty("homescreen.todaysdate")).getText(),"TODAY", "Logged in successfully using account created in IOSS app");
		commonmethods.logout();
	}

    @AfterTest
	public void afterTest()
	{
    	
	}

}
