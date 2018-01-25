package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	public static Properties prop;
	
	public static void init() {
		prop = new Properties();
		FileInputStream fs;
		try {
			fs = new FileInputStream("C:\\Users\\srilu\\IntroSelenium\\Week06\\Ex\\osTicketWorkSpace\\SriRestAssuredDataDrivenFramework\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(fs);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	

}
