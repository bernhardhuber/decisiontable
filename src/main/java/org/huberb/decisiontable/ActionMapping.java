/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @param <C> Context-Type
 * @param <R> Action-Result
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
     * @param l list of rules denoted via enum.
     * @return
     */
    public List<Couple<Enum, R>> applyActions(C ctx, List<Enum> l) {
        List<Couple<Enum, R>> result = new ArrayList<>();
        Set<Enum> s = new LinkedHashSet<>(l);
        for (Enum e : s) {
            List<Function<C, R>> fl = m.get(e);
            for (Function<C, R> f : fl) {
                R r = f.apply(ctx);
                result.add(new Couple(e, r));
            }
        }
        return result;
    }

}
