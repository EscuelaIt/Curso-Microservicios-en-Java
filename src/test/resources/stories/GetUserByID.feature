Feature: Verify get user by id
    
  Scenario: 
	Given users stored in the system
	When client makes call to GET users with id 1
	Then the client receives status code of 200 And the user id is 1