/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.decisiontable;

import java.util.List;
import org.huberb.decisiontable.Conditions.ConditionResult;
import org.huberb.decisiontable.Person.ConditionsEnum;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author berni
 */
public class ConditionsTest {

    public ConditionsTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test_DecisionTableConditions() {
        final List<Person> persons = Person.tenPersons();
        final Conditions<Person> dtConditionsPerson = new Conditions<Person>(
                new ConditionsListBuilder<Person>().
                        enumPredicate(ConditionsEnum.c1, (p) -> p.age > 20).
                        enumPredicate(ConditionsEnum.c2, (p) -> p.weight % 2 == 0).
                        build()
        );
        final ConditionResult conditionResult = dtConditionsPerson.evaluateUsing(persons.get(0));

        assertEquals(2, conditionResult.result().size());

        assertEquals(ConditionsEnum.c1, conditionResult.result().get(0).getT());
        assertEquals(false, conditionResult.result().get(0).getU());

        assertEquals(ConditionsEnum.c2, conditionResult.result().get(1).getT());
        assertEquals(true, conditionResult.result().get(1).getU());

        assertEquals(false, dtConditionsPerson.findByCondtionEnum(conditionResult, ConditionsEnum.c1).get());
        assertEquals(true, dtConditionsPerson.findByCondtionEnum(conditionResult, ConditionsEnum.c2).get());
    }

}
