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
