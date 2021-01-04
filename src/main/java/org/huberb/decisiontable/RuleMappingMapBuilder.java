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
