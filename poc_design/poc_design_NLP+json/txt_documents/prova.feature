Feature: Bank withdraw
Scenario: Withdraw cash
Given the user is in front of an ATM
When the user inserts the card in the ATM
And the user enters the pin of the card
And the user enters the amount of cash he wants to withdraw
Then the ATM should check the pin
And the ATM should give the user the cash
But the ATM should not give the user the cash

