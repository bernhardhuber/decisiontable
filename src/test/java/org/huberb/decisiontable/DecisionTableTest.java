/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.decisiontable;

import java.util.Arrays;
import java.util.List;
import org.huberb.decisiontable.DecisionTable.Conditions.ConditionResult;
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
        final ConditionResult conditionResult = dtConditionsPerson.evaluateUsing(persons.get(0));

        assertEquals(2, conditionResult.result().size());

        assertEquals(Conditions.c1, conditionResult.result().get(0).getT());
        assertEquals(false, conditionResult.result().get(0).getU());

        assertEquals(Conditions.c2, conditionResult.result().get(1).getT());
        assertEquals(true, conditionResult.result().get(1).getU());

        assertEquals(false, dtConditionsPerson.findByCondtionEnum(conditionResult, Conditions.c1).get());
        assertEquals(true, dtConditionsPerson.findByCondtionEnum(conditionResult, Conditions.c2).get());
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
            final ConditionResult conditionsResult = dtConditionsPerson.evaluateUsing(person);
            assertEquals(false, dtConditionsPerson.findByCondtionEnum(conditionsResult, Conditions.c1).get());
            assertEquals(true, dtConditionsPerson.findByCondtionEnum(conditionsResult, Conditions.c2).get());

            final List<Enum> rulesResult = dtRuleMappingPerson.mapToRules(conditionsResult);
            assertEquals(1, rulesResult.size());
            assertEquals(Rules.r1, rulesResult.get(0));
        }
        {
            Person person = persons.get(1);
            final ConditionResult conditionsResult = dtConditionsPerson.evaluateUsing(person);
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
            final ConditionResult conditionsResult = dtConditionsPerson.evaluateUsing(person);
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
            final ConditionResult conditionsResult = dtConditionsPerson.evaluateUsing(person);
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

}
