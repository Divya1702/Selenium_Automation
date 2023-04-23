package com.steps;

import com.pages.HomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

/*****
 *
 * Home Page Steps
 *
 * ****/
public class HomePageSteps {

    private final HomePage homePage;

    public HomePageSteps(HomePage homePage) {
        this.homePage = homePage;
    }

    /**
     * Navigates to the website homepage via url and wait until the page is loaded
     */
    @Given("The user is on the home page")
    public void navigateToApp() throws Exception {
        homePage.goToHomePage();
    }

    /**
     * Method to perform search action by clicking search icon or select value from the view listed
     *
     * @param itemName - The text for enter in the search box
     */

    @And("^the user enter '([\\w\\s\\S]+)' text on search box and clickSearchIcon$")
    public void searchItem(String itemName) throws InterruptedException {
        homePage.searchItem(itemName);
        homePage.selectItemByLink(itemName);
    }

    /**
     * Method to click add to cart button on a specific item by hover over
     *
     * @param itemName - Name of the product
     */
    @And("the user select '([\\w\\s\\S]+)' by mousehover and add to cart")
    public void selectItemByHoverOver(String itemName) throws InterruptedException {
        homePage.searchItem(itemName);
        if (itemName.contains("™")) {
            homePage.selectItemByHoverOver(itemName.replaceAll("™", " trade"));
        } else homePage.selectItemByHoverOver(itemName);
    }
}


