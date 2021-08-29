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
import java.util.Objects;

/**
 * Tuple with 3 elements.
 *
 * @author berni
 */
public class Triple<T, U, V> implements Serializable {

    private static final long serialVersionUID = 20161011L;

    private final T t;
    private final U u;
    private final V v;

    Triple() {
        this(null, null, null);
    }

    public Triple(T t, U u, V v) {
        this.t = t;
        this.u = u;
        this.v = v;
    }

    public T getT() {
        return t;
    }

    public U getU() {
        return u;
    }

    public V getV() {
        return v;
    }

    //---
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.t);
        hash = 97 * hash + Objects.hashCode(this.u);
        hash = 97 * hash + Objects.hashCode(this.v);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Triple<?, ?, ?> other = (Triple<?, ?, ?>) obj;
        boolean equalsResult = true;
        equalsResult = equalsResult && Objects.equals(this.t, other.t);
        equalsResult = equalsResult && Objects.equals(this.u, other.u);
        equalsResult = equalsResult && Objects.equals(this.v, other.v);
        return equalsResult;
    }

    @Override
    public String toString() {
        return "Triple{" + "t=" + t + ", u=" + u + ", v=" + v + '}';
    }

}
