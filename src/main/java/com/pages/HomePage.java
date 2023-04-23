package com.pages;

import cucumber.api.java.After;
import infrastructure.Setup;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

/*****
 *
 * Elements and Methods for home Page
 *
 * ****/

public class HomePage extends BasePage {

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    /**
     * Method to navigate to Home page via URL
     */
    public void goToHomePage() throws Exception {
        initialization();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(returnCSS(element.getProperty("welcomemessage"))));
        Thread.sleep(5000);
    }

    /**
     *
     * Method to search item from searchbar
     *
     * @param itemName - Name of the product
     */
    public void searchItem(String itemName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(returnCSS(element.getProperty("searchBar"))));
        WebElement search_Item = findElement(element.getProperty("searchItem"));
        search_Item.click();
        search_Item.clear();
        search_Item.sendKeys(itemName);
        wait.until(ExpectedConditions.elementToBeClickable(returnCSS(element.getProperty("searchIcon")))).click();
        Thread.sleep(2000);
    }

    /**
     * Method to select a item by hyperlink
     *
     * @param itemName - Name of the product
     */
    public String selectItemByLink(String itemName) throws InterruptedException {
        String url = element.getProperty("URL");
        findElement(element.getProperty("searchItemName").replace("{0}", url).replace("{1}", itemName).replaceAll(" ", "-").toLowerCase().trim()).click();
        Thread.sleep(2000);
        return String.valueOf(new ItemPage());
    }

    /**
     * Method to click add to cart button on a specific item by hover over
     *
     * @param itemName - Name of the product
     */
    public void selectItemByHoverOver(String itemName) throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement element = findElement(Setup.getProperty("searchItemName").replace("{0}", Setup.getProperty("URL")).replace("{1}", itemName).replaceAll(" ", "-").toLowerCase().trim());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        action.moveToElement(element).perform();
        WebElement clickAction = findElement(Setup.getProperty("searchItemList") + ":nth-child(1)>div>div>div:last-of-type>div>div:first-of-type>form>button" + Setup.getProperty("addToCart"));
        Thread.sleep(500);
        clickAction.click();
    }
}