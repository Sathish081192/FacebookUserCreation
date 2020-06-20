Feature: Order Item and User Modification

Background:
		Given User Navigates to Application URL
		When User Enters Valid UserName and Password then click on SignIn
		Then User should be successfully logged into Application
  

  @OrderTshirt
  Scenario: Order Tshirts
    Given User is on the MyAccount Page
    When User Click on HomePage Button
    Then User successfully navigated to Shopping Website HomePage
    And User Selects the TShirt Category and list of Available Tshirts should be displayed
    And User Select the Item and Click on AddtoCart Button
    Then Item should be successfully added to Cart
    And Item Details in Cart Summary Page should matched with Item Selection Page
    And User Click on ProceedtoCheckOut Button on Cart Summary Page
    And User Selects Delivery Address and click ProceedtoCheckOut Button on Address Confirmation Page
    And User Selects Shipping Option and click ProceedtoCheckOut Button on Shipping Confirmation Page
    And User Selects the Payment Method and Click on ConfirmMyOrder Button
    Then Order Confirmation Page should be displayed and Order details should present in OrderHistory Page

  @UpdateFirstNameOfUser
  Scenario: Modify the FirstName of User
  Given User is on the MyAccount Page
  And User Click on MyPersonalInformation Menu
  And User Modify the FirstName and Click on Save
  Then User details update Confirmation should be displayed
  And Modified FirstName should updated in Right Top Corner of WebPage
  And Modified FirstName should updated in MyPersonalInformation Page