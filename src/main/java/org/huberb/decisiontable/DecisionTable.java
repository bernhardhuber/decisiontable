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

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A simple decision table.
 *
 * <ol>
 * <li>Using enum to name conditions. Conditions are evaluated using
 * {@link Predicate}.
 * </li>
 * <li>
 * Result of applying predicates are mapped to Rules again denoted via some
 * enum.
 * </li>
 * <li>
 * Finally rules are evaluated using {@link  Function}.
 * </li>
 * </ul>
 * <p>
 * This implementation is quite simple, and uses Builders for setting up the
 * datastructure.
 *
 * @author berni
 */
public class DecisionTable {
}
