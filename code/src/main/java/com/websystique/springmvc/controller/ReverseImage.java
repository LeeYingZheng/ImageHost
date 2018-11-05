package com.websystique.springmvc.controller;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.websystique.springmvc.model.UserDocument;


@Component
public class ReverseImage {
    private static String SLOC;
    private static String CLOC;

    @Value("${selenium.location}")
    private void setSLoc(String privateSLoc) {
        ReverseImage.SLOC = privateSLoc;
    }

    @Value("${chrome.location}")
    private void setCLoc(String privateCLoc) {
        ReverseImage.CLOC = privateCLoc;
    }

    public static void run(UserDocument document) {
        //declaration and instantiation of objects/variables
        System.out.println("Test case is running.");
        System.setProperty("webdriver.chrome.driver",CLOC);
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        String baseUrl = "https://images.google.com/";
        String expectedText = "No other sizes of this image found.";

        // launch Chrome and direct it to the Base URL
        driver.get(baseUrl);

        // get the actual value
        driver.findElement(By.id("qbi")).click();
        driver.findElement(By.linkText("Upload an image")).click();
        driver.findElement(By.id("qbfile")).click();
        driver.findElement(By.id("qbfile")).clear();
        driver.findElement(By.id("qbfile")).sendKeys(SLOC+document.getId()+"\\"+document.getName());

        if (expectedText.equals(driver.findElement(By.cssSelector("div.O1id0e")).getText())){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }

        //close Chrome
        driver.close();

    }

}
