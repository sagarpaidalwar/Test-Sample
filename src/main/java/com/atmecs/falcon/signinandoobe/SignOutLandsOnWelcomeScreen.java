package com.atmecs.falcon.signinandoobe;

import java.io.IOException;

import org.codehaus.jettison.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.falcon.automation.verifyresult.VerificationManager;
import com.atmecs.falcon.testfunction.CommonMethods;
import com.atmecs.falcon.testsuite.TestSuiteBase;
import com.jawbone.utility.Readjson;

public class SignOutLandsOnWelcomeScreen extends TestSuiteBase
{
	CommonMethods commonmethods;
	public JSONObject testData;
	Readjson readjsonobj;
	@BeforeTest
	public void beforeTest()
	{
		readjsonobj=new Readjson();
		testData=readjsonobj.readJsonData("login.js");
	}
	
	@Test
	public void signOutLandsOnWelcomeScreen() throws Exception
	{
		commonmethods= new CommonMethods(driver,prop);
		commonmethods.testLogin(testData,"ValidUserName","Validpassword");
		commonmethods.logout();
		VerificationManager.verifyString(driver.findElementById(prop.getProperty("loginscreen.welcometext")).getText(), "Welcome to the new you.", "Successfully lands on welcome screen");
		
	}
	
	@AfterTest
	public void afterTest()
	{
		
	}
	

}
