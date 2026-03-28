@tag
Feature: Purchase the oder from E-commerce website
	Background:
		Given I landed on E-commercer Page
	@tag2
	Scenario Outline: Nominal scenarios of submitting order feature
		Given Logged in with correct username <username> and  password <password>
		When add the product <productName> to home page
		And verify whether the product <productName> is into the cart page and checkout
		Then I redirect to the confirmation page and get the mesage "Thankyou for the order." on display.
	
		Examples: 
			| username 					  | password   | productName     | 
			| "abelngbandaman@gmail.com"  | "Mba@2026" | "iphone 13 pro" |
