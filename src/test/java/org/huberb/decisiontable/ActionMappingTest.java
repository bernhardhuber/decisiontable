/*
 * Copyright 2021 berni3.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.huberb.decisiontable;

import java.util.Arrays;
import java.util.List;
import org.huberb.decisiontable.Conditions.ConditionResult;
import org.huberb.decisiontable.Person.RulesEnum;
import org.huberb.decisiontable.tuple.Couple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author berni
 */
public class ActionMappingTest {

    List<Person> persons;
    Conditions<Person> dtConditionsPerson;
    RuleMapping dtRuleMappingPerson;
    ActionMapping<Person, String> dtActionMappingPerson;

    static enum ConditionsE {
        c1_Age_Gt_20, c2_Weight_even
    }

    @BeforeEach
    public void setUp() {
        persons = Person.tenPersons();
        dtConditionsPerson = new Conditions<>(
                new ConditionsListBuilder<Person>().
                        enumPredicate(ConditionsE.c1_Age_Gt_20, (p) -> p.age > 20).
                        enumPredicate(ConditionsE.c2_Weight_even, (p) -> p.weight % 2 == 0).
                        build()
        );

        dtRuleMappingPerson = new RuleMapping(
                new RuleMappingMapBuilder().
                        assocConditionWithPredicateResult(ConditionsE.c1_Age_Gt_20, Boolean.FALSE).
                        assocConditionWithPredicateResult(ConditionsE.c2_Weight_even, Boolean.TRUE).
                        mapToRules(Arrays.asList(RulesEnum.r1)).
                        assocConditionWithPredicateResult(ConditionsE.c1_Age_Gt_20, Boolean.TRUE).
                        assocConditionWithPredicateResult(ConditionsE.c2_Weight_even, Boolean.FALSE).
                        mapToRules(Arrays.asList(RulesEnum.r2)).
                        build()
        );
        dtActionMappingPerson = new ActionMapping<>(
                new ActionMappingMapBuilder<Person, String>().
                        action((Person p) -> p.name).rule(RulesEnum.r1).
                        action((Person p) -> p.name.toLowerCase()).rule(RulesEnum.r2).
                        build()
        );
    }

    @Test
    public void test_DecisionTableAction_Peter() {
        final Person person = persons.get(0);
        final ConditionResult conditionsResult = dtConditionsPerson.evaluateUsing(person);
        assertEquals(false, conditionsResult.findByConditionEnum(ConditionsE.c1_Age_Gt_20).get());
        assertEquals(true, conditionsResult.findByConditionEnum(ConditionsE.c2_Weight_even).get());

        final List<Enum> rulesResult = dtRuleMappingPerson.mapToRules(conditionsResult);
        assertEquals(1, rulesResult.size());
        assertEquals(RulesEnum.r1, rulesResult.get(0));

        List<Couple<Enum, String>> actionResult = dtActionMappingPerson.applyActions(person, rulesResult);
        assertEquals(1, actionResult.size());
        assertEquals(RulesEnum.r1, actionResult.get(0).getT());
        assertEquals("Peter", actionResult.get(0).getU());
    }

    @Test
    public void test_DecisionTableAction_Manuela() {
        final Person person = persons.get(1);
        final ConditionResult conditionsResult = dtConditionsPerson.evaluateUsing(person);
        assertEquals(true, conditionsResult.findByConditionEnum(ConditionsE.c1_Age_Gt_20).get());
        assertEquals(false, conditionsResult.findByConditionEnum(ConditionsE.c2_Weight_even).get());
        final List<Enum> rulesResult = dtRuleMappingPerson.mapToRules(conditionsResult);
        assertEquals(1, rulesResult.size());
        assertEquals(RulesEnum.r2, rulesResult.get(0));

        List<Couple<Enum, String>> actionResult = dtActionMappingPerson.applyActions(person, rulesResult);
        assertEquals(1, actionResult.size());
        assertEquals(RulesEnum.r2, actionResult.get(0).getT());
        assertEquals("manuela", actionResult.get(0).getU());
    }

}
