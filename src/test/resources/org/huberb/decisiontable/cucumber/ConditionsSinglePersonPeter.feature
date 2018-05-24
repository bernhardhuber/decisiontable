# language: en
Feature: Testing person Peter

  Background: 
    Given a Person "Peter" (Gender.male, age 15, weight 50, height 170)

  Scenario: Matching single condition
    Given the condition "c1" is associated with predicate age = "15"
    And the condition "c2" is associated with predicate weight = "49"
    Then Condition "c1" matches

  Scenario: Matching single condition
    Given the condition "c1" is associated with predicate age = "16"
    And the condition "c2" is associated with predicate weight = "50"
    Then Condition "c2" matches

  Scenario: Matching multi condition
    Given the condition "c1" is associated with predicate age = "15"
    And the condition "c2" is associated with predicate weight = "50"
    Then Condition "c1" matches
    And Condition "c2" matches
