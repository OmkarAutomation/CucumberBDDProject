Feature: Cutomers
Background: Common steps for all sceanrioss
Given User Launch Chrome browser 
	When User opens URL "http://admin-demo.nopcommerce.com/login"
	And User enters Email as "admin@yourstore.com" and Password as "admin" 
	And Click on Login
	Then User can view Dashboard
	
@Sanity 	#this is tags
Scenario: Add new customer
	When User click on customer Menu
	And click on customer Menu Item
	And click on add new button
	Then user can view add new customer page
	When user enter customer info
	And click pn Save button
	Then user can view confirmation message "The new customer has been added successfully."
	And close browser
	
@Regression	#this is tags
Scenario: Search Customer by Email
	When User click on customers Menu 
	And click on customers Menu Item 
	And Enter customer EMail
	When Click on search button
	Then User should found Email in the Search table
	And close browser 
	
@Regression	#this is tags
Scenario: Search Customer by Name
	When User click on customers Menu 
	And click on customers Menu Item 
	And Enter customer FirstName
	And Enter customer LastName
	When Click on search button
	Then User should found Name in the Search table
	And close browser 	