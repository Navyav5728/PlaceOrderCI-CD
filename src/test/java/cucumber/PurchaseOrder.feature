Feature: Purchase Order from Ecommerce site

Backgorund: 
Given I landed on Ecommerce page

Scenario Outline: postive test of submitting order

Given Logged in with username<name> and password<password>
When I add product<productName> to cart
And Checkout <productName>and submit the order
Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page


Examples:
 name                       | password|   productName  |
 
 navya.veerlanka@gmail.com  | Test@123  | ZARA COAT 3 |