package com.atmecs.falcon.testsuite;

import java.io.IOException;
import java.util.Properties;

import org.codehaus.jettison.json.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.atmecs.falcon.automation.appium.manager.UserBaseTest;
import com.jawbone.utility.Constants;
import com.jawbone.utility.LoadPages;
import com.jawbone.utility.Readjson;

public class TestSuiteBase extends UserBaseTest implements Constants{
	LoadPages page=new LoadPages();
	Properties configprop=new Properties();
	public static Properties prop=new Properties();

	@BeforeSuite
	public void preSetup() throws IOException {
		String appName="Classic";
		prop=page.loadAllFiles(appName);
		configprop=page.getObjectRepository("jawboneconfig.properties",JAWBONECONFIGPATH);
	}
	
	

	@AfterSuite
	public void teardown() {
		
	}
}
