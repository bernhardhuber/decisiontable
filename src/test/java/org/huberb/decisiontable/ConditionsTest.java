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

import java.util.List;
import org.huberb.decisiontable.Conditions.ConditionResult;
import org.huberb.decisiontable.Person.ConditionsEnum;
import org.huberb.decisiontable.tuple.Couple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author berni
 */
public class ConditionsTest {

    @Test
    public void test_DecisionTableConditions() {
        final List<Person> persons = Person.tenPersons();
        final Conditions<Person> dtConditionsPerson = new Conditions<>(
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

    @Test
    public void test_DecisionTableConditions_Supplier() {
        final List<Person> persons = Person.tenPersons();

        final Conditions<Person> dtConditionsPerson = new Conditions<>(
                new ConditionsListBuilder<Person>().
                        enumPredicateSupplier(() -> new Couple<>(ConditionsEnum.c1, (p) -> p.age > 20)).
                        enumPredicateSupplier(() -> new Couple<>(ConditionsEnum.c2, (p) -> p.weight % 2 == 0)).
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
