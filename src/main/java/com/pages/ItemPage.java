package com.pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*****
 *
 * Elements and Methods for Item Select Page
 *
 *****/
public class ItemPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(driver, 3);

    /**
     * Method to update qty on the car page
     * pre-req : Add Product to the cart
     *
     * @param qty - Quantity of selected item
     */
    public void updateQty(int qty) throws InterruptedException {
        WebElement element = findElement(getProperty("quantity"));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        element.clear();
        element.sendKeys(String.valueOf(qty));
        findElement(getProperty("cart")).click();
        Thread.sleep(5000);
    }

    /**
     * Method to select size,color,qty for the product and add to cart
     */
    public void addToCart(String color, String size, int qty) throws InterruptedException {
        findElement(getProperty("size").replace("{0}", size)).click();
        wait.until(ExpectedConditions.visibilityOf(findElement(getProperty("color").replace("{0}", "size"))));
        Assert.assertEquals(String.format("Expected size:  %s, Actual size : %s", size, (findElement(element.getProperty("color").replace("{0}", "size")).getText())), size, (findElement(element.getProperty("color").replace("{0}", "size"))).getText());
        findElement(getProperty("size").replace("{0}", color)).click();
        wait.until(ExpectedConditions.visibilityOf(findElement(getProperty("color").replace("{0}", "color"))));
        Assert.assertEquals(String.format("Expected color:  %s, Actual color : %s", color, (findElement(element.getProperty("color").replace("{0}", "color")).getText())), color, (findElement(element.getProperty("color").replace("{0}", "color")).getText()));
        WebElement element = findElement(getProperty("quantity"));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        element.clear();
        element.sendKeys(String.valueOf(qty));
        WebElement cart = findElement(getProperty("addToCart"));
        cart.click();
        Thread.sleep(2000);
    }

    /**
     * Method to verify the success message after adding item to cart
     *
     * @param expectedMessage - Message
     * @param value           - result
     */
    public void verifyMessage(String expectedMessage, boolean value) throws InterruptedException {
        Thread.sleep(3000);
        WebElement element = findElement(getProperty("message"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        String message = element.getText();
        Assert.assertEquals(String.format("Expected Message is displayed %s", message), expectedMessage.equalsIgnoreCase(message), value);
    }

    /**
     * Method to go to cart after adding item from search page
     *
     * @param value - User is on the shopping cart page or not
     *              true or false
     */
    public CartPage onCartPage(boolean value) {
        wait.until(ExpectedConditions.elementToBeClickable(returnCSS(element.getProperty("gotoCart")))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(returnCSS(element.getProperty("summary"))));
        cartPage("Shopping Cart", value);
        return new CartPage();
    }

    /**
     * Method to verify is the user is on the view cart page
     *
     * @param value - true/false
     */
    public void cartPage(String text, boolean value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(returnCSS(element.getProperty("Title"))));
        String header = driver.findElement(returnCSS(element.getProperty("Title"))).getText();
        if (header.equalsIgnoreCase(text)) {
            Assert.assertTrue("User is on the view cart page", text.equalsIgnoreCase(header));
        } else {
            Assert.assertFalse("User is not on the view cart page", text.equalsIgnoreCase(header));
        }
    }
}