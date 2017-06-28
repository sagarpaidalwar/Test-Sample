package com.jawbone.utility;

import java.io.File;


public interface Constants{

	String CLASSICPAGEPATH=System.getProperty("user.dir") +File.separator+""+File.separator+"src"+File.separator+""+File.separator+"main"+File.separator+""+File.separator+"resources"+File.separator+""+File.separator+"locators"+File.separator+""+File.separator+"classiclocators"+File.separator+""+File.separator;
	String OPENPAGEPATH=System.getProperty("user.dir") +File.separator+""+File.separator+"src"+File.separator+""+File.separator+"main"+File.separator+""+File.separator+"resources"+File.separator+""+File.separator+"locators"+File.separator+""+File.separator+"openlocators"+File.separator+""+File.separator;
	String PAGEPATH=System.getProperty("user.dir") +File.separator+""+File.separator+"src"+File.separator+""+File.separator+"main"+File.separator+""+File.separator+"resources"+File.separator+""+File.separator+"locators"+File.separator+""+File.separator;
    String FILEPATH=System.getProperty("user.dir") +File.separator+""+File.separator+"src"+File.separator+""+File.separator+"main"+File.separator+""+File.separator+"resources"+File.separator+""+File.separator+"locators";
    String JSONFILEPATH=System.getProperty("user.dir") +File.separator+""+File.separator+"src"+File.separator+""+File.separator+"main"+File.separator+""+File.separator+"resources"+File.separator+""+File.separator+"testdata"+File.separator+""+File.separator;
    int ELEMENT_WAIT_TIME=60;
    String JAWBONECONFIGPATH=System.getProperty("user.dir") +File.separator+""+File.separator+"src"+File.separator+""+File.separator+"main"+File.separator+""+File.separator+"resources"+File.separator+""+File.separator;

}
