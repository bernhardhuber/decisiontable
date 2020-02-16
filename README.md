# decisiontable

A simple project realizing a decision table in java.

As I was browsing some websites for organizing business logic, I stumbled
over the *decision table* topic.

Reading the wiki page about decision table (https://en.wikipedia.org/wiki/Decision_table),
I decided to realize some simple decision table using

* Java 8
* FunctionalInterfaces

I read about some decision table implementation using Excel or some other tools,
I decided to stick to a simple pure Java implementation.

The basic idea is to replace long-and-nested if/else by some more simpler

* org.huberb.decisiontable.DecisionTable.Conditions
* org.huberb.decisiontable.DecisionTable.RuleMapping
* org.huberb.decisiontable.DecisionTable.ActionMapping

constructs.

Maybe to get some idea of this decision-table implementation look at the
unit-test
 https://github.com/bernhardhuber/decisiontable/blob/master/src/test/java/org/huberb/decisiontable/DecisionTableTest.java

[![Build Status](https://travis-ci.org/bernhardhuber/decisiontable.svg?branch=master)](https://travis-ci.org/bernhardhuber/decisiontable)

