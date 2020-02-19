/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.decisiontable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author berni3
 * @param <C>
 * @param <R>
 */
public class ActionMappingMapBuilder<C, R> {

    private List<Function<C, R>> l = new ArrayList<>();
    private final Map<Enum, List<Function<C, R>>> m = new HashMap<>();

    public ActionMappingMapBuilder<C, R> action(Function<C, R> f) {
        l.add(f);
        return this;
    }

    public ActionMappingMapBuilder<C, R> rule(Enum e) {
        m.put(e, l);
        l = new ArrayList<>();
        return this;
    }

    public Map<Enum, List<Function<C, R>>> build() {
        return m;
    }

}
