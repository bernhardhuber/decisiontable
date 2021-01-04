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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import org.huberb.decisiontable.tuple.Couple;

/**
 * Conditions: named via {@link Enum} and evaluated by {@link Predicate}.
 *
 * @param <C> the Context-type of the predicate evaluation.
 */
public class Conditions<C> {

    private final List<Couple<Enum, Predicate<C>>> conditionsList;

    public Conditions(List<Couple<Enum, Predicate<C>>> conditionsList) {
        this.conditionsList = conditionsList;
    }

    public static class ConditionResult {

        private final List<Couple<Enum, Boolean>> result;

        public ConditionResult(List<Couple<Enum, Boolean>> result) {
            this.result = result;
        }

        public List<Couple<Enum, Boolean>> result() {
            return result;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 61 * hash + Objects.hashCode(this.result);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ConditionResult other = (ConditionResult) obj;
            return Objects.equals(this.result, other.result);
        }

        @Override
        public String toString() {
            return "ConditionResult{" + "result=" + result + '}';
        }
                
    }

    /**
     * Evaluate conditions.
     *
     * @param ctx context for evaluating the predicates.
     * @return
     */
    public ConditionResult evaluateUsing(C ctx) {
        final List<Couple<Enum, Boolean>> result = new ArrayList<>();
        conditionsList
                .stream()
                .forEach((org.huberb.decisiontable.tuple.Couple<java.lang.Enum, java.util.function.Predicate<C>> t) -> {
            final Boolean f = t.getU().test(ctx);
            final Enum e = t.getT();
            result.add(new Couple(e, f));
        });
        return new ConditionResult(result);
    }

    /**
     * Get Boolean result of the Condition evaluation more easily, than
     * searching the result list.
     * @param conditionResult
     * @param conditionEnum
     * @return 
     */
    public Optional<Boolean> findByCondtionEnum(ConditionResult conditionResult, Enum conditionEnum) {
        final Optional<Boolean> optBooleanResult = conditionResult
                .result()
                .stream()
                .filter((Couple<Enum, Boolean> elem) -> elem.getT() == conditionEnum)
                .findFirst()
                .map((Couple<Enum, Boolean> elem) -> elem.getU());
        return optBooleanResult;
    }

}
