package com.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/*****
 *
 * Elements and methods for Cart page
 *
 *****/
public class CartPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(driver, 7);

    /**
     * Method to add shipping address
     *
     * @param input type of input
     * @param label the name of the field
     * @param value the expected value of the field
     */
    public void setField(String input, String label, String value) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.findElement(By.cssSelector("[id='block-shipping']")).click();
        switch (input) {
            case "textarea":
                wait.until(ExpectedConditions.visibilityOfElementLocated(returnCSS(element.getProperty("name").replace("{0}", label))));
                js.executeScript("document.querySelector('[name=\"" + label + "\"]>div>input').value=\"" + value + "\";");
                break;
            case "dropdown":
                wait.until(ExpectedConditions.visibilityOfElementLocated(returnCSS(element.getProperty("name").replace("{0}", label))));
                WebElement drop = findElement((element.getProperty("name").replace("{0}", label)) + ">div>select");
                Select data = new Select(drop);
                data.selectByValue(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid field Name " + input + " provided.");
        }
        Thread.sleep(3000);
    }

    /**
     * Method to Verify Price on Cart
     *
     * @param label name of the field
     * @param value expected  price value
     **/
    public void verifyPrice(String label, String value) {
        if (label.equals("Order Total")) {
            label = "grand totals";
            wait.until(ExpectedConditions.visibilityOfElementLocated(returnCSS(element.getProperty("priceSummary").replace("{0}", label))));
            String price = findElement((element.getProperty("priceSummary").replace("{0}", label)) + ">td>strong>span").getText();
            Assert.assertEquals(String.format("Expected Price and Actual Price "), value, price);
        } else {
            System.out.println("Cart Total not found");
        }

    }
}