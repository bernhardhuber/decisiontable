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
 * Tuple with 4 elements.
 * 
 * @author berni
 */
public class Quadruple<T, U, V, W> implements Serializable {

    private static final long serialVersionUID = 20161011L;

    final T t;
    final U u;
    final V v;
    final W w;

    Quadruple() {
        this(() -> null, () -> null, () -> null, () -> null);
    }

    public Quadruple(T t, U u, V v, W w) {
        this.t = t;
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public Quadruple(Supplier<T> t, Supplier<U> u, Supplier<V> v, Supplier<W> w) {
        this.t = t.get();
        this.u = u.get();
        this.v = v.get();
        this.w = w.get();
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
        if (!Objects.equals(this.t, other.t)) {
            return false;
        }
        if (!Objects.equals(this.u, other.u)) {
            return false;
        }
        if (!Objects.equals(this.v, other.v)) {
            return false;
        }
        if (!Objects.equals(this.w, other.w)) {
            return false;
        }
        return true;
    }
    
}
