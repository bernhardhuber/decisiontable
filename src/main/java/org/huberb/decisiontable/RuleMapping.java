/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.decisiontable;

import java.util.List;
import java.util.Map;

/**
 * Map predicate evaluation results to rules.
 *
 */
public class RuleMapping {

    private final Map<Conditions.ConditionResult, List<Enum>> m;

    public RuleMapping(Map<Conditions.ConditionResult, List<Enum>> m) {
        this.m = m;
    }

    /**
     *
     * @param l
     * @return
     */
    public List<Enum> mapToRules(Conditions.ConditionResult conditionResult) {
        //final List<Couple<Enum, Boolean>> l = conditionResult.result;
        final List<Enum> result = m.get(conditionResult);
        //            if (result == null) {
        //                for (Map.Entry<ConditionResult, List<Enum>> entry : m.entrySet()) {
        //                    ConditionResult entryKey = entry.getKey();
        //
        //                    boolean matched = false;
        //                    if (l.size() == entryKey.result().size()) {
        //                        for (int i = 0; i < l.size(); i++) {
        //                            Couple<Enum, Boolean> lcouple = l.get(i);
        //                            Couple<Enum, Boolean> entryKeycouple = entryKey.result().get(i);
        //
        //                            if (lcouple.getT() == entryKeycouple.getT()
        //                                    && lcouple.getU().equals(entryKeycouple.getU())) {
        //                                matched = true;
        //                            } else {
        //                                matched = false;
        //                                break;
        //                            }
        //                        }
        //                    }
        //                    if (matched) {
        //                        result = entry.getValue();
        //                    }
        //                }
        //            }
        return result;
    }

}
