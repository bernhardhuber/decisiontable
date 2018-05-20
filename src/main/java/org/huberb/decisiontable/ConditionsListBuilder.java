/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
