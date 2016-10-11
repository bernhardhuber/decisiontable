/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.decisiontable;

import java.util.Arrays;
import java.util.List;
import org.huberb.decisiontable.tuple.Couple;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author berni
 */
public class DecisionTableTest {

    public DecisionTableTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Conditions of the person dt.
     */
    enum Conditions {

        c1, c2
    }

    /**
     * Rules of the person dt.
     */
    enum Rules {

        r1, r2, r3
    }

    @Test
    public void test_DecisionTableConditions() {
        final List<Person> persons = Person.tenPersons();
        final DecisionTable.Conditions<Person> dtConditionsPerson
                = new DecisionTable.Conditions<>(
                        new DecisionTable.ConditionsListBuilder<Person>().
                        enumPredicate(Conditions.c1, (p) -> p.age > 20).
                        enumPredicate(Conditions.c2, (p) -> p.weight % 2 == 0).
                        build()
                );
        List<Couple<Enum, Boolean>> result;
        result = dtConditionsPerson.evaluateUsing(persons.get(0));

        assertEquals(2, result.size());

        assertEquals(Conditions.c1, result.get(0).getT());
        assertEquals(false, result.get(0).getU());

        assertEquals(Conditions.c2, result.get(1).getT());
        assertEquals(true, result.get(1).getU());

        assertEquals(false, dtConditionsPerson.findByCondtionEnum(result, Conditions.c1).get());
        assertEquals(true, dtConditionsPerson.findByCondtionEnum(result, Conditions.c2).get());
    }

    @Test
    public void test_DecisionTableRuleMapping() {
        final List<Person> persons = Person.tenPersons();
        final DecisionTable.Conditions<Person> dtConditionsPerson
                = new DecisionTable.Conditions<>(
                        new DecisionTable.ConditionsListBuilder<Person>().
                        enumPredicate(Conditions.c1, (p) -> p.age > 20).
                        enumPredicate(Conditions.c2, (p) -> p.weight % 2 == 0).
                        build()
                );

        final DecisionTable.RuleMapping dtRuleMappingPerson
                = new DecisionTable.RuleMapping(
                        new DecisionTable.RuleMappingMapBuilder().
                        assocConditionWithPredicateResult(Conditions.c1, Boolean.FALSE).
                        assocConditionWithPredicateResult(Conditions.c2, Boolean.TRUE).
                        mapToRules(Arrays.asList(Rules.r1)).
                        assocConditionWithPredicateResult(Conditions.c1, Boolean.TRUE).
                        assocConditionWithPredicateResult(Conditions.c2, Boolean.FALSE).
                        mapToRules(Arrays.asList(Rules.r2)).
                        build()
                );
        {
            Person person = persons.get(0);
            final List<Couple<Enum, Boolean>> conditionsResult = dtConditionsPerson.evaluateUsing(person);
            assertEquals(false, dtConditionsPerson.findByCondtionEnum(conditionsResult, Conditions.c1).get());
            assertEquals(true, dtConditionsPerson.findByCondtionEnum(conditionsResult, Conditions.c2).get());

            final List<Enum> rulesResult = dtRuleMappingPerson.mapToRules(conditionsResult);
            assertEquals(1, rulesResult.size());
            assertEquals(Rules.r1, rulesResult.get(0));
        }
        {
            Person person = persons.get(1);
            final List<Couple<Enum, Boolean>> conditionsResult = dtConditionsPerson.evaluateUsing(person);
            assertEquals(true, dtConditionsPerson.findByCondtionEnum(conditionsResult, Conditions.c1).get());
            assertEquals(false, dtConditionsPerson.findByCondtionEnum(conditionsResult, Conditions.c2).get());
            final List<Enum> rulesResult = dtRuleMappingPerson.mapToRules(conditionsResult);
            assertEquals(1, rulesResult.size());
            assertEquals(Rules.r2, rulesResult.get(0));
        }
    }

    @Test
    public void test_DecisionTableAction() {
        final List<Person> persons = Person.tenPersons();
        final DecisionTable.Conditions<Person> dtConditionsPerson
                = new DecisionTable.Conditions<>(
                        new DecisionTable.ConditionsListBuilder<Person>().
                        enumPredicate(Conditions.c1, (p) -> p.age > 20).
                        enumPredicate(Conditions.c2, (p) -> p.weight % 2 == 0).
                        build()
                );

        final DecisionTable.RuleMapping dtRuleMappingPerson
                = new DecisionTable.RuleMapping(
                        new DecisionTable.RuleMappingMapBuilder().
                        assocConditionWithPredicateResult(Conditions.c1, Boolean.FALSE).
                        assocConditionWithPredicateResult(Conditions.c2, Boolean.TRUE).
                        mapToRules(Arrays.asList(Rules.r1)).
                        assocConditionWithPredicateResult(Conditions.c1, Boolean.TRUE).
                        assocConditionWithPredicateResult(Conditions.c2, Boolean.FALSE).
                        mapToRules(Arrays.asList(Rules.r2)).
                        build()
                );
        final DecisionTable.ActionMapping<Person, String> dtActionMappingPerson
                = new DecisionTable.ActionMapping<>(
                        new DecisionTable.ActionMappingMapBuilder<Person, String>().
                        action((Person p) -> p.name).rule(Rules.r1).
                        action((Person p) -> p.name.toLowerCase()).rule(Rules.r2).
                        build()
                );
        {
            Person person = persons.get(0);
            final List<Couple<Enum, Boolean>> conditionsResult = dtConditionsPerson.evaluateUsing(person);
            assertEquals(false, dtConditionsPerson.findByCondtionEnum(conditionsResult, Conditions.c1).get());
            assertEquals(true, dtConditionsPerson.findByCondtionEnum(conditionsResult, Conditions.c2).get());

            final List<Enum> rulesResult = dtRuleMappingPerson.mapToRules(conditionsResult);
            assertEquals(1, rulesResult.size());
            assertEquals(Rules.r1, rulesResult.get(0));

            List<Couple<Enum, String>> actionResult = dtActionMappingPerson.applyActions(person, rulesResult);
            assertEquals(1, actionResult.size());
            assertEquals(Rules.r1, actionResult.get(0).getT());
            assertEquals("Peter", actionResult.get(0).getU());
        }

        {
            Person person = persons.get(1);
            final List<Couple<Enum, Boolean>> conditionsResult = dtConditionsPerson.evaluateUsing(person);
            assertEquals(true, dtConditionsPerson.findByCondtionEnum(conditionsResult, Conditions.c1).get());
            assertEquals(false, dtConditionsPerson.findByCondtionEnum(conditionsResult, Conditions.c2).get());
            final List<Enum> rulesResult = dtRuleMappingPerson.mapToRules(conditionsResult);
            assertEquals(1, rulesResult.size());
            assertEquals(Rules.r2, rulesResult.get(0));

            List<Couple<Enum, String>> actionResult = dtActionMappingPerson.applyActions(person, rulesResult);
            assertEquals(1, actionResult.size());
            assertEquals(Rules.r2, actionResult.get(0).getT());
            assertEquals("manuela", actionResult.get(0).getU());
        }
    }

    //----
    static class Person {

        enum Gender {

            female, male
        }
        String name;
        Gender gender;
        int age;
        int weight;
        int height;

        public Person(String name, Gender gender, int age, int weight, int height) {
            this.name = name;
            this.gender = gender;
            this.age = age;
            this.weight = weight;
            this.height = height;
        }

        static List<Person> tenPersons() {
            List<Person> persons = Arrays.asList(
                    new Person("Peter", Gender.male, 15, 50, 170),
                    new Person("Manuela", Gender.female, 21, 55, 185),
                    new Person("Thomas", Gender.male, 26, 70, 160),
                    new Person("Maria", Gender.female, 30, 90, 172),
                    new Person("Hermann", Gender.male, 34, 80, 196),
                    new Person("Daniela", Gender.female, 38, 60, 165),
                    new Person("Gustav", Gender.male, 42, 70, 179),
                    new Person("Viktoria", Gender.female, 55, 83, 184),
                    new Person("Klaus", Gender.male, 62, 62, 166),
                    new Person("Eva", Gender.female, 73, 70, 183)
            );
            return persons;
        }
    }

}
