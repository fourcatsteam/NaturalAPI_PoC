Feature: Design module behavior
Scenario: Python - no programming language adapter
Given there is a business application language
And there is a set of related BDD feature scenarios
When I generate an API from the business application language
Then I am asked to confirm each API suggestion (method names, argument types)
And finally I am given an API in the “Python” language with all suggestions I confirmed
And finally I am given a test API in the “behave” framework for the given feature scenarios