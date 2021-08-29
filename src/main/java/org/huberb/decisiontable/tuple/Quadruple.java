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
 * Tuple with 4 elements.
 *
 * @author berni
 */
public class Quadruple<T, U, V, W> implements Serializable {

    private static final long serialVersionUID = 20161011L;

    private final T t;
    private final U u;
    private final V v;
    private final W w;

    Quadruple() {
        this(null, null, null, null);
    }

    public Quadruple(T t, U u, V v, W w) {
        this.t = t;
        this.u = u;
        this.v = v;
        this.w = w;
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

    public W getW() {
        return w;
    }

    //---
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.t);
        hash = 89 * hash + Objects.hashCode(this.u);
        hash = 89 * hash + Objects.hashCode(this.v);
        hash = 89 * hash + Objects.hashCode(this.w);
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
        final Quadruple<?, ?, ?, ?> other = (Quadruple<?, ?, ?, ?>) obj;
        boolean equalsResult = true;
        equalsResult = equalsResult && Objects.equals(this.t, other.t);
        equalsResult = equalsResult && Objects.equals(this.u, other.u);
        equalsResult = equalsResult && Objects.equals(this.v, other.v);
        equalsResult = equalsResult && Objects.equals(this.w, other.w);
        return equalsResult;
    }

    @Override
    public String toString() {
        return "Quadruple{" + "t=" + t + ", u=" + u + ", v=" + v + ", w=" + w + '}';
    }

}
