# decisiontable

A simple project realizing a decision table in java.

[![Java CI](https://github.com/bernhardhuber/decisiontable/actions/workflows/maven.yml/badge.svg)](https://github.com/bernhardhuber/decisiontable/actions/workflows/maven.yml)

As I was browsing some websites for organizing business logic, I stumbled
over the *decision table* topic.

Reading the wiki page about decision table (https://en.wikipedia.org/wiki/Decision_table),
I decided to realize some simple decision table using

* Java 8
* FunctionalInterfaces

I read about some decision table implementation using Excel or some other tools,
I decided to stick to a simple pure Java implementation.

The basic idea is to replace long-and-nested if/else statements by some more simpler

* org.huberb.decisiontable.Conditions
* org.huberb.decisiontable.RuleMapping
* org.huberb.decisiontable.ActionMapping

constructs.

Maybe to get some idea of this decision-table implementation look at the
unit-tests:

* src/test/java/org/huberb/decisiontable/ActionMappingTest.java
* src/test/java/org/huberb/decisiontable/ConditionsTest.java
* src/test/java/org/huberb/decisiontable/RuleMappingTest.java

