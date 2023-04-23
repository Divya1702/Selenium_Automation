package com.pages;

import org.openqa.selenium.By;
import infrastructure.Setup;
import org.openqa.selenium.WebElement;

/*****
 *
 * Base page
 *
 *****/
public class BasePage extends Setup {
    public BasePage() {
        super();
    }


    public WebElement findElement(String element) {
        return driver.findElement(By.cssSelector(element));
    }

    public By returnCSS(String element) {
        return By.cssSelector(element);
    }

}
