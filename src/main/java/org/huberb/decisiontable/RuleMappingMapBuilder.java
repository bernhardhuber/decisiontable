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
import org.huberb.decisiontable.tuple.Couple;

/**
 * Builder for the rule mapping map.
 */
public class RuleMappingMapBuilder {

    private Conditions.ConditionResult conditionResultKey = new Conditions.ConditionResult(new ArrayList<>());
    private final Map<Conditions.ConditionResult, List<Enum>> m = new HashMap<>();

    public RuleMappingMapBuilder assocConditionWithPredicateResult(Enum e, Boolean b) {
        conditionResultKey.result().add(new Couple(e, b));
        return this;
    }

    public RuleMappingMapBuilder mapToRules(List<Enum> v) {
        m.put(conditionResultKey, v);
        conditionResultKey = new Conditions.ConditionResult(new ArrayList<>());
        return this;
    }

    public Map<Conditions.ConditionResult, List<Enum>> build() {
        return m;
    }

}
