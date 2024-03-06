package org.prakash.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.BeforeClass;

public class Config {
	
	public static Properties properties;
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		Config.properties = properties;
	}
	

	public static void setUpconfig() throws Exception {
		properties = new Properties();
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream("D:\\Working Directory\\RestAssuredPractice\\config.properties");
			properties.load(fileInputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(properties.getProperty("BaseURL"));
		System.out.println(System.getProperty("BaseURL"));
	}
}
