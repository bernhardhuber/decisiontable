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
package org.huberb.decisiontable.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.hamcrest.MatcherAssert;
import org.huberb.decisiontable.Conditions;
import org.huberb.decisiontable.Conditions.ConditionResult;
import org.huberb.decisiontable.ConditionsListBuilder;
import org.huberb.decisiontable.Person;
import org.huberb.decisiontable.tuple.Couple;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author berni3
 */
public class PersonStepdefs {

    Person person;
    List<Couple<Enum, Predicate<Person>>> l;

    @Given("^a Person \"([^\"]*)\".*")
    public void a_Person(String arg1) throws Throwable {
        this.person = Person.tenPersons().stream().filter((p) -> p.getName().equals(arg1)).findFirst().orElseThrow(() -> {
            throw new AssertionError("Missing person " + arg1);
        });
        this.l = new ArrayList<>();
    }

    @Given("^the condition \"([^\"]*)\" is associated with predicate age = \"([^\"]*)\"$")
    public void the_condition_is_associated_with_predicate_age(String arg1, int arg2) throws Throwable {
        Enum e = Person.ConditionsEnum.valueOf(arg1);
        Predicate<Person> agePred = (p) -> p.getAge() == arg2;
        l.addAll(
                new ConditionsListBuilder<Person>().
                        enumPredicate(e, agePred).build()
        );
    }

    @Given("^the condition \"([^\"]*)\" is associated with predicate weight = \"([^\"]*)\"$")
    public void the_condition_is_associated_with_prediage_weight(String arg1, int arg2) throws Throwable {
        Enum e = Person.ConditionsEnum.valueOf(arg1);
        Predicate<Person> weightPred = (p) -> p.getWeight() == arg2;
        l.addAll(
                new ConditionsListBuilder<Person>().
                        enumPredicate(e, weightPred).build()
        );
    }

    @Then("^Condition \"([^\"]*)\" matches$")
    public void condition_matches(String arg1) throws Throwable {
        String m = "" + arg1;
        Enum e = Person.ConditionsEnum.valueOf(arg1);
        final Conditions<Person> dtConditionsPerson = new Conditions<>(this.l);
        ConditionResult conditionResult = dtConditionsPerson.evaluateUsing(person);
        Couple<Enum, Boolean> rArg1 = conditionResult.result().stream().filter((c) -> c.getT() == e).findFirst().orElseThrow(() -> {
            throw new AssertionError("Missing enum " + arg1 + ", " + conditionResult);
        });

        MatcherAssert.assertThat(rArg1, new CoupleEnumBooleanMatcher(e, true));
        assertEquals(e, rArg1.getT());
        assertEquals(true, rArg1.getU().booleanValue());
    }

}
