# language: en
Feature: Testing person Manuela

  Background: 
    Given a Person "Manuela" (Gender.female, age 21, weight 55, height 185)

  Scenario: Matching single condition
    Given the condition "c1" is associated with predicate age = "21"
    And the condition "c2" is associated with predicate weight = "60"
    Then Condition "c1" matches

  Scenario: Matching single condition
    Given the condition "c2" is associated with predicate age = "21"
    And the condition "c1" is associated with predicate weight = "60"
    Then Condition "c2" matches

  Scenario: Matching multi condition
    Given the condition "c1" is associated with predicate age = "21"
    And the condition "c2" is associated with predicate weight = "55"
    Then Condition "c1" matches
    And Condition "c2" matches
