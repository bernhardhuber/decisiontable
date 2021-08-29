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
import org.huberb.decisiontable.Conditions.ConditionResult;

/**
 * Map predicate evaluation results to some rules.
 *
 */
public class RuleMapping {

    private final Map<ConditionResult, List<Enum>> m;

    public RuleMapping(Map<ConditionResult, List<Enum>> m) {
        this.m = m;
    }

    /**
     * Lookup the list of enumerations associated with a condition result.
     *
     * @param conditionResult a key looking up a list of enumerations
     * @return looked up conditionResul
     */
    public List<Enum> mapToRules(ConditionResult conditionResult) {
        final List<Enum> result = m.get(conditionResult);
        return result;
    }

}
