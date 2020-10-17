package com.qa.orangehrm.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;



import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseBage2 {
	WebDriver driver;
	Properties prop;
	
	public WebDriver init_driver2(String browserName){
		System.out.println(browserName);
		
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			if(prop.getProperty("headless").equals("yes")){
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver=new ChromeDriver(co);
				
				}else{
					driver=new ChromeDriver();
				}
			
		}else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if(prop.getProperty("headless").equals("yes")){
				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--headless");
			driver=new FirefoxDriver(fo);
			}else{
				driver=new FirefoxDriver();
			}
		}else if (browserName.equals("opera")) {
			WebDriverManager.operadriver().setup();
			driver=new OperaDriver();
		}else if (browserName.equals("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver=new SafariDriver();
			
		}else {
			
			
		}
			System.out.println("browsername" + browserName + "is not found");
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
	
		return driver;
		
	}
	
	public Properties init_properties(){
		prop=new Properties();
		String path=prop.getProperty("./src/main/java/com/qa/orangehrm/config/config.properties");
		
		try {
			FileInputStream ip= new FileInputStream(path);
		} catch (FileNotFoundException e) {
			System.out.println("some propblems occured wile using config. Please check your config");
		}catch (IOException e) {
			// TODO: handle exception
		}
		return prop;
		
		
	}

}
