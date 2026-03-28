@tag
Feature: 
	@errorsvalidations 
	Scenario Outline: Errors Connexion
		Given being on the landing page
		When I try to log in with incorrect username <username> and  password <password>
		Then I get the mesage "Incorrect email or password." on display.
	
		Examples: 
			| username 					   | password    | 
			| "abelngbandamans@gmail.com"  | "Mbas@2026" |	
		