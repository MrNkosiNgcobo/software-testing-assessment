package com.testing.assessment.sofware.steps;

import com.testing.assessment.sofware.datasets.RunConfiguration;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckOutSteps {

    @Autowired
    RunConfiguration runConfiguration;

    @Autowired
    private WebDriver driver;

    @Given("^The items that the user selected before check out$")
    public void testThisCheckWorks(){
        System.out.println("Starting >>> " + runConfiguration.getTestType());
        System.out.println("Test running in >>> " + runConfiguration.getBrowser());
        System.out.println("This is working correctly...");

        // Navigate to the website
        driver.get("https://www.saucedemo.com/");

        // Enter the username and password in the appropriate fields
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        // Click the login button
        driver.findElement(By.id("login-button")).click();

        // Find the first product and add it to the cart
        WebElement product1 = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        product1.click();

        // Find the second product and add it to the cart
        WebElement product2 = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        product2.click();

        // Go to the cart page
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        // Confirm that the correct products are added to the cart
        String backpackDescription = driver.findElement
                (By.xpath("//span[text()='Your Cart']//following::div[@class='inventory_item_name'][1]"))
                .getText();
        String bikeDescription = driver.findElement
                        (By.xpath("//span[text()='Your Cart']//following::div[@class='inventory_item_name'][2]"))
                .getText();
        assertEquals(backpackDescription, "Sauce Labs Backpack");
        assertEquals(bikeDescription, "Sauce Labs Bike Light");

        driver.findElement(By.xpath("//button[@id='checkout']")).click();

        // Fill in the customer information and go to the checkout overview screen
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        // Confirm that the total price is correct
        String price1= driver.findElement
                (By.xpath("//span[text()='Checkout: Overview']//following::div[@class='inventory_item_price'][1]")).getText();
        String price2 = driver.findElement
                (By.xpath("//span[text()='Checkout: Overview']//following::div[@class='inventory_item_price'][2]")).getText();
        double backpackPrice = Double.parseDouble(price1.replace("$", ""));
        double bikePrice = Double.parseDouble(price2.replace("$", ""));
        double expectedTotalPrice = backpackPrice + bikePrice;

        String stringTotalPrice = driver.findElement
                (By.xpath("//div[@class='summary_subtotal_label']")).getText();
        double actualTotalPrice = Double.parseDouble(stringTotalPrice.replaceAll("[^\\d.]+", ""));
        assertEquals(expectedTotalPrice, actualTotalPrice, 0);

        // Finish the order
        driver.findElement(By.xpath("//button[@id='finish']")).click();

        // Confirm that the order confirmation screen is displayed
        assertTrue(driver.findElement(By.xpath("//h2[text()='Thank you for your order!']")).isDisplayed());

    }
}
