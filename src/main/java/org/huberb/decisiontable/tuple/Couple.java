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
 * Tuple with 2 elements.
 *
 * @author berni
 */
public class Couple<T, U> implements Serializable {

    private static final long serialVersionUID = 20161011L;

    final T t;
    final U u;

    private Couple() {
        this(null, null);
    }

    public Couple(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public T getT() {
        return t;
    }

    public U getU() {
        return u;
    }

    //---
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.t);
        hash = 59 * hash + Objects.hashCode(this.u);
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
        final Couple<?, ?> other = (Couple<?, ?>) obj;
        boolean tAndUEquals = true;
        tAndUEquals = tAndUEquals && Objects.equals(this.t, other.t);
        tAndUEquals = tAndUEquals && Objects.equals(this.u, other.u);
        return tAndUEquals;
    }

    @Override
    public String toString() {
        return "Couple{" + "t=" + t + ", u=" + u + '}';
    }

}
