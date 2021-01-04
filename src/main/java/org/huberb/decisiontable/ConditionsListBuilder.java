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
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.huberb.decisiontable.tuple.Couple;

/**
 *
 * @author berni3
 * @param <C>
 */
public class ConditionsListBuilder<C> {

    private final List<Couple<Enum, Predicate<C>>> l = new ArrayList<>();

    public ConditionsListBuilder<C> enumPredicate(Enum e, Predicate<C> p) {
        l.add(new Couple(e, p));
        return this;
    }

    public ConditionsListBuilder<C> enumPredicateSupplier(Supplier<Couple<Enum, Predicate<C>>> supp) {
        final Couple<Enum, Predicate<C>> couple = supp.get();
        l.add(couple);
        return this;
    }

    public List<Couple<Enum, Predicate<C>>> build() {
        return l;
    }

}
