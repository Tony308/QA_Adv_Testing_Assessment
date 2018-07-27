#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: As an Admin,
	I want to manage the details of owners in the system,
	So that I can keep up to date information about all owners.

 Scenario: Get List of Owners
  Given The website works/exists
   When I send a get request
    Then I should receive a list of owners
    
  Scenario: Get Specific Owner By Surname
   Given The website works/exists
   And An owner exists
    When I search a specific owner
     Then I should receive a record of that owner
  
  Scenario: Post a New Owner
   Given The website works/exists
   And An owner exists
    When I create a new owner
     Then See that new owner has been added
     
  Scenario: Update/put an owner
   Given The website works/exists
   And An owner exists
    When I update an owner
     Then the owner should update
     
  Scenario: Delete an Owner
   Given The website works/exists
   And An owner exists
    When I delete an owner
     Then Check the owner has been deleted
     
