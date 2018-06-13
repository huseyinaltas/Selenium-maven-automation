package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
	
	public static void main(String[] args) {
		
		//Set up chrome driver path
		WebDriverManager.chromedriver().setup();
		
		//invoke selenium webdriver
		WebDriver driver= new ChromeDriver();
		//fullscreen
		driver.manage().window().fullscreen();
		
		//set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String url="https://www.dice.com/";
		driver.get(url);
		String keyword="Java Developer";
		
		String actualTitle = driver.getTitle();
		String excpectedTitle = "Job Search for Technology Professionals | Dice.com";
		
		if (actualTitle.equals(excpectedTitle)) {
			System.out.println("Step PASS");	
		}
		else {
			System.out.println("Step FAIL");	
		}
		
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys("48015");
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count= driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		
		int countResult = Integer.parseInt(count.replaceAll(",", ""));
		
		if (countResult > 0) {
			System.out.println("Step PASS: Keyword : " + keyword + " search returned");	
		}
		else {
			System.out.println("step FAIL");
		}
		
		driver.close();
	}

}
