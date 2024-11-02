# language: en
Feature: Testing person Manuela

  Background: 
    Given a Person "Manuela" (Gender.female, age 21, weight 55, height 185)

  Scenario: Matching single condition age
    Given the condition "c1_age" is associated with predicate age = "21"
    And the condition "c2_weight" is associated with predicate weight = "60"
    Then Condition "c1_age" matches
    And Condition "c2_weight" does not match

  Scenario: Matching single condition weight
    Given the condition "c1_age" is associated with predicate age = "20"
    And the condition "c2_weight" is associated with predicate weight = "55"
    Then Condition "c1_age" does not match
    And Condition "c2_weight" matches

  Scenario: Matching multi condition age, and weight
    Given the condition "c1_age" is associated with predicate age = "21"
    And the condition "c2_weight" is associated with predicate weight = "55"
    Then Condition "c1_age" matches
    And Condition "c2_weight" matches
