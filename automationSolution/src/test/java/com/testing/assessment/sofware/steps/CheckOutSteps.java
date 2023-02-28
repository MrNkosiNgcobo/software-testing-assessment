package com.testing.assessment.sofware.steps;

import com.testing.assessment.sofware.datasets.RunConfiguration;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckOutSteps {

    @Autowired
    RunConfiguration runConfiguration;

    private WebDriver driver;

    @Before
    public void setup() {
        // Set the path of the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Workspace\\Env\\chromedriver_win32\\chromedriver.exe");
        // Create a new instance of the ChromeDriver
        driver = new ChromeDriver();
    }

    @Given("^The items that the user selected before check out$")
    public void testThisCheckWorks(){
        System.out.println("Starting >>> " + runConfiguration.getTestType());
        System.out.println("Test running in >>> " + runConfiguration.getBrowser());
        System.out.println("This is working correctly...");

        // Navigate to the website
        driver.get("https://www.saucedemo.com/");

        // Find the first product and add it to the cart
        WebElement product1 = driver.findElement(By.xpath("//div[@class='product'][1]"));
        product1.findElement(By.xpath(".//button")).click();

        // Find the second product and add it to the cart
        WebElement product2 = driver.findElement(By.xpath("//div[@class='product'][2]"));
        product2.findElement(By.xpath(".//button")).click();

        // Go to the cart page
        driver.findElement(By.cssSelector(".cart")).click();

        // Confirm that the correct products are added to the cart
        assertTrue(driver.findElement(By.xpath("//td[text()='Product 1']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//td[text()='Product 2']")).isDisplayed());

        // Fill in the customer information and go to the checkout overview screen
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.cssSelector(".checkout_buttons .btn_primary")).click();

        // Confirm that the total price is correct
        String expectedTotalPrice = "$50.00"; // Assuming each product costs $25.00
        String actualTotalPrice = driver.findElement(By.cssSelector(".summary_total_label")).getText();
        assertEquals(expectedTotalPrice, actualTotalPrice);

        // Finish the order
        driver.findElement(By.cssSelector(".checkout_buttons .btn_primary")).click();

        // Confirm that the order confirmation screen is displayed
        assertTrue(driver.findElement(By.xpath("//h2[text()='THANK YOU FOR YOUR ORDER']")).isDisplayed());

    }
}
