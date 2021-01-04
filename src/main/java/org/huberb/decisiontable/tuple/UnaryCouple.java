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
package org.huberb.decisiontable.tuple;

import java.io.Serializable;

/**
 * Tuple with 2 elements having the same type.
 *
 * @author berni
 * @param <T>
 */
public class UnaryCouple<T> extends Couple<T, T> implements Serializable {

    private static final long serialVersionUID = 20161011L;

    private UnaryCouple() {
        this(null, null);
    }

    public UnaryCouple(T t1, T t2) {
        super(t1, t2);
    }

}
