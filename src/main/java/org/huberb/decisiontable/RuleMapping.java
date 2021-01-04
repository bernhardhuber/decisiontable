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
     * @param conditionResult
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
