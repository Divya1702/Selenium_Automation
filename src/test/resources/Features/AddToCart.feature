Feature: Order items from magento softwaretesting board site

  Background:
    Given The user is on the home page

  Scenario: Add items to cart and verify the total
    When the user enter 'Gwyn Endurance Tee' text on search box and clickSearchIcon
    And the user selects 'Green' color, 'M' Size and enter '4' quantity
    Then the user verifies the message 'You added Gwyn Endurance Tee to your shopping cart.':true
    And User is on the view cart page:true
    When the user adds the shipping address with the fields :
      | Input    | Value                      | Action |
      | dropdown | shippingAddress.country_id | GB     |
      | textarea | shippingAddress.region     | Oxford |
      | textarea | shippingAddress.postcode   | OX1345 |
    Then the user verifies the 'Order Total' price on the cart is '92.00'
    When the user updates the qty to '3' on the cart
    When the user enter 'Gwyn Endurance Tee' text on search box and clickSearchIcon
    And the user selects 'Yellow' color, 'S' Size and enter '1' quantity
    Then the user verifies the message 'You added Gwyn Endurance Tee to your shopping cart.':true
    And the user select 'Quest Lumaflex™ Band' by mousehover and add to cart
    Then the user verifies the message 'You added Quest Lumaflex™ Band to your shopping cart.':true
    And User is on the view cart page:true
    Then the user verifies the 'Order Total' price on the cart is '116'