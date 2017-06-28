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

public class EmailFieldPopulatedWithPreviouslySignedInUserEmailId extends TestSuiteBase
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
	public void emailFiledPopulatedWithPreviousSignedInEmailId() throws Exception
	{
		commonmethods=new CommonMethods(driver, prop);
		commonmethods.testLogin(testData, "ValidUserName", "Validpassword");
        commonmethods.logout();
    	driver.findElementById(prop.getProperty("loginscreen.opensignin")).click();
		String Text=driver.findElementById(prop.getProperty("loginscreen.emailtextbox")).getText();
		VerificationManager.verifyString(Text, (String) testData.get("ValidUserName"), "Email Feild is populated with previously logged in email id");
	}
	@AfterTest
	public void afterTest()
	{
		
	}
}
