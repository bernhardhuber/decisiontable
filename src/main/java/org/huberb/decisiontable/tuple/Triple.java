/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.decisiontable.tuple;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Tuple with 3 elements.
 * 
 * @author berni
 */
public class Triple<T, U, V> implements Serializable {

    private static final long serialVersionUID = 20161011L;

    final T t;
    final U u;
    final V v;

    Triple() {
        this(() -> null, () -> null, () -> null);
    }

    public Triple(T t, U u, V v) {
        this.t = t;
        this.u = u;
        this.v = v;
    }

    public Triple(Supplier<T> t, Supplier<U> u, Supplier<V> v) {
        this.t = t.get();
        this.u = u.get();
        this.v = v.get();
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
        if (!Objects.equals(this.t, other.t)) {
            return false;
        }
        if (!Objects.equals(this.u, other.u)) {
            return false;
        }
        if (!Objects.equals(this.v, other.v)) {
            return false;
        }
        return true;
    }
    

}
