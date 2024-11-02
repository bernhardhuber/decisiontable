# language: en
Feature: Testing person Peter

  Background: 
    Given a Person "Peter" (Gender.male, age 15, weight 50, height 170)

  Scenario: Matching single condition
    Given the condition "c1" is associated with predicate age = "15"
    And the condition "c2" is associated with predicate weight = "49"
    And the condition "c3" is associated with predicate height = "71"
    Then Condition "c1" matches
    And Condition "c2" does not match
    And Condition "c3" does not match

  Scenario: Matching single condition
    Given the condition "c1" is associated with predicate age = "16"
    And the condition "c2" is associated with predicate weight = "50"
    And the condition "c3" is associated with predicate height = "71"
    Then Condition "c1" does not match
    And Condition "c2" matches
    And Condition "c3" does not match

  Scenario: Matching multi condition
    Given the condition "c1" is associated with predicate age = "15"
    And the condition "c2" is associated with predicate weight = "50"
    And the condition "c3" is associated with predicate height = "71"
    Then Condition "c1" matches
    And Condition "c2" matches
    And Condition "c3" does not match
