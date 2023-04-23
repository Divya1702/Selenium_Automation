package com.steps;

import com.pages.ItemPage;
import com.pages.CartPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

/*****
 *
 * Add item to Cart Steps
 *
 * ****/
public class AddItemToCartSteps {

    private final CartPage cartPage;
    private final ItemPage itemPage;

    public AddItemToCartSteps(CartPage cartPage, ItemPage itemPage) {
        this.cartPage = cartPage;
        this.itemPage = itemPage;

    }

    /**
     * User select item size,color,qty of the product and add to cart
     *
     * @param qty - Quantity of selected item
     */

    @When("^the user selects '([\\w\\s]+)' color, '([\\w\\s]+)' Size and enter '([\\d]+)' quantity$")
    public void selectPropertiesItemAddtoCart(String color, String size, int qty) throws InterruptedException {
        itemPage.addToCart(color, size, qty);
    }

    /**
     * User verifies the cart page
     *
     * @param value - User is on the shopping cart page or not
     *              true or false
     */
    @And("User is on the view cart page:(true|false)$")
    public void onCartPage(boolean value) {
        itemPage.onCartPage(value);
    }

    /**
     * User adds the shipping address
     *
     * @param table the datatable
     *              Set field given a list of{Input,Label,Value}
     *              E.x -  | dropdown | shippingAddress.country_id | GB    |
     */
    @And("^the user adds the shipping address with the fields :$")
    public void setFieldsOnPage(DataTable table) {
        table.asMaps(String.class, String.class).forEach((input -> {
            try {
                cartPage.setField(input.get("Input"),
                        input.get("Value"), input.get("Action"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }


    /**
     * user verifies the price details on on the Shopping Cart Page
     *
     * @param label name of the field
     * @param value expected  price value
     */
    @And("^the user verifies the '([\\w\\s]+)' price on the cart is '(\\d+(?:\\.\\d+)?)'$")
    public void verifyPrice(String label, String value) {
        cartPage.verifyPrice(label, '$' + value);
    }

    /**
     * User updates the quantity of the product on cart
     *
     * @param qty - Quantity of selected product
     */
    @And("^the user updates the qty to '([\\d]+)' on the cart$")
    public void updateQty(int qty) throws InterruptedException {
        itemPage.updateQty(qty);
    }

    /**
     * User verifies the message on cart after adding the product
     *
     * @param expectedMessage - After added item into add to cart
     * @param value           - expected message displayed or not
     *                        true or false
     */
    @And("the user verifies the message '([\\w\\s\\S]+)':(true|false)$")
    public void verifyMessage(String expectedMessage, boolean value) throws InterruptedException {
        itemPage.verifyMessage(expectedMessage, value);
    }
}