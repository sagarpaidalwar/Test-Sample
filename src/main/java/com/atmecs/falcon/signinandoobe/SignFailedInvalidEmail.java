package com.atmecs.falcon.signinandoobe;

import java.io.IOException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.falcon.automation.verifyresult.VerificationManager;
import com.atmecs.falcon.testfunction.CommonMethods;
import com.atmecs.falcon.testsuite.TestSuiteBase;
import com.jawbone.utility.Readjson;

public class SignFailedInvalidEmail extends TestSuiteBase{
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
	public void signInFailedInvalidEmail() throws Exception
	{
		commonmethods= new CommonMethods(driver,prop);
		commonmethods.testLogin(testData, "InvalidUserName", "Validpassword");
		commonmethods.waitForVisible(driver, By.id(prop.getProperty("loginscreen.signfailedalerttitle")));
		VerificationManager.verifyString(driver.findElementById(prop.getProperty("loginscreen.signfailedmessage")).getText(), "Sorry, the email address you entered is invalid. Please try again.", "Showing Correct Error Message For Sign In With Invalid Email");
	}

}
