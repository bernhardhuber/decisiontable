/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

}
