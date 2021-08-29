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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import org.huberb.decisiontable.tuple.Couple;

/**
 * Encapsulate mapping from a enumeration context to an action result.
 *
 * @param <C> Context-Type an enumeration
 * @param <R> Action-Result a function mapping the enumeration to some value.
 */
public class ActionMapping<C, R> {

    private final Map<Enum, List<Function<C, R>>> m;

    public ActionMapping(Map<Enum, List<Function<C, R>>> m) {
        this.m = m;
    }

    /**
     * Apply rule actions.
     *
     * @param ctx context for applying some rule action.
     * @param l list of rules denoted via enumeration.
     * @return list of couples of enumeration, and action result.
     */
    public List<Couple<Enum, R>> applyActions(C ctx, List<Enum> l) {
        final List<Couple<Enum, R>> result = new ArrayList<>();
        final Set<Enum> s = new LinkedHashSet<>(l);
        for (Enum e : s) {
            final List<Function<C, R>> fl = m.get(e);
            for (Function<C, R> f : fl) {
                final R r = f.apply(ctx);
                result.add(new Couple(e, r));
            }
        }
        return result;
    }

}
