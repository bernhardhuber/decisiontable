/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.decisiontable;

import java.util.Arrays;
import java.util.List;
import org.huberb.decisiontable.Conditions.ConditionResult;
import org.huberb.decisiontable.Person.ConditionsEnum;
import org.huberb.decisiontable.Person.RulesEnum;
import org.huberb.decisiontable.tuple.Couple;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author berni
 */
public class ActionMappingTest {

    public ActionMappingTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test_DecisionTableAction() {
        final List<Person> persons = Person.tenPersons();
        final Conditions<Person> dtConditionsPerson = new Conditions<>(
                new ConditionsListBuilder<Person>().
                        enumPredicate(ConditionsEnum.c1, (p) -> p.age > 20).
                        enumPredicate(ConditionsEnum.c2, (p) -> p.weight % 2 == 0).
                        build()
        );

        final RuleMapping dtRuleMappingPerson
                = new RuleMapping(
                        new RuleMappingMapBuilder().
                                assocConditionWithPredicateResult(ConditionsEnum.c1, Boolean.FALSE).
                                assocConditionWithPredicateResult(ConditionsEnum.c2, Boolean.TRUE).
                                mapToRules(Arrays.asList(RulesEnum.r1)).
                                assocConditionWithPredicateResult(ConditionsEnum.c1, Boolean.TRUE).
                                assocConditionWithPredicateResult(ConditionsEnum.c2, Boolean.FALSE).
                                mapToRules(Arrays.asList(RulesEnum.r2)).
                                build()
                );
        final ActionMapping<Person, String> dtActionMappingPerson
                = new ActionMapping<>(
                        new ActionMappingMapBuilder<Person, String>().
                                action((Person p) -> p.name).rule(RulesEnum.r1).
                                action((Person p) -> p.name.toLowerCase()).rule(RulesEnum.r2).
                                build()
                );
        {
            Person person = persons.get(0);
            final ConditionResult conditionsResult = dtConditionsPerson.evaluateUsing(person);
            assertEquals(false, dtConditionsPerson.findByCondtionEnum(conditionsResult, ConditionsEnum.c1).get());
            assertEquals(true, dtConditionsPerson.findByCondtionEnum(conditionsResult, ConditionsEnum.c2).get());

            final List<Enum> rulesResult = dtRuleMappingPerson.mapToRules(conditionsResult);
            assertEquals(1, rulesResult.size());
            assertEquals(RulesEnum.r1, rulesResult.get(0));

            List<Couple<Enum, String>> actionResult = dtActionMappingPerson.applyActions(person, rulesResult);
            assertEquals(1, actionResult.size());
            assertEquals(RulesEnum.r1, actionResult.get(0).getT());
            assertEquals("Peter", actionResult.get(0).getU());
        }

        {
            Person person = persons.get(1);
            final ConditionResult conditionsResult = dtConditionsPerson.evaluateUsing(person);
            assertEquals(true, dtConditionsPerson.findByCondtionEnum(conditionsResult, ConditionsEnum.c1).get());
            assertEquals(false, dtConditionsPerson.findByCondtionEnum(conditionsResult, ConditionsEnum.c2).get());
            final List<Enum> rulesResult = dtRuleMappingPerson.mapToRules(conditionsResult);
            assertEquals(1, rulesResult.size());
            assertEquals(RulesEnum.r2, rulesResult.get(0));

            List<Couple<Enum, String>> actionResult = dtActionMappingPerson.applyActions(person, rulesResult);
            assertEquals(1, actionResult.size());
            assertEquals(RulesEnum.r2, actionResult.get(0).getT());
            assertEquals("manuela", actionResult.get(0).getU());
        }
    }

}
