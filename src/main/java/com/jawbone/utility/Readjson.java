package com.jawbone.utility;

import java.io.File;




import com.atmecs.falcon.automation.dataprovider.TestDataProvider;

public class Readjson implements Constants{
	  org.codehaus.jettison.json.JSONObject jsonObject;
	  
	  public org.codehaus.jettison.json.JSONObject readJsonData(String fileName){
          File file = new File(JSONFILEPATH+ fileName);
          TestDataProvider dataProvider = TestDataProvider.getInstance();
          try {
                  jsonObject = dataProvider.getJSONObject(file);
                  }
           catch (Exception e) {
                  e.printStackTrace();
          }
		return jsonObject;
    }
}