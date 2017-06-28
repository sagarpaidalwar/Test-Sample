package com.jawbone.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadPages implements Constants{

	Properties page = new Properties();
	Properties allPages=new Properties();
	File fileObj;  
	String filePath=null;

	// Method for reading data from object.proporties
	public Properties getObjectRepository(String fileName,String Filepath) throws IOException {	
		String locatorsFileName=fileName;
		 final InputStream stream = new FileInputStream(new
				 File(Filepath+ locatorsFileName));
		 page.load(stream);
		return page; 
	}
	
	public Properties loadAllFiles(String appName) throws IOException
	{
		if(appName.equalsIgnoreCase("Classic"))
		{
		    fileObj = new File(CLASSICPAGEPATH);
		    filePath=CLASSICPAGEPATH;
		}
		if(appName.equalsIgnoreCase("Open")){
			fileObj = new File(OPENPAGEPATH);
			filePath=OPENPAGEPATH;
		}
		File[] files = fileObj.listFiles();
		    for (int i =0; i <files.length; i++)
		    {
		    	String FileName=files[i].getName();
		    	allPages=getObjectRepository(FileName,filePath);
		    }
		return allPages;
	}
	
	 
	
}