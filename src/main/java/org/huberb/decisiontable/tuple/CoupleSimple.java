/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.decisiontable.tuple;

import java.io.Serializable;
import java.util.Objects;

/**
 * Tuple with 2 elements having the same type.
 * 
 * @author berni
 */
public class CoupleSimple<T> implements Serializable {

    private static final long serialVersionUID = 20161011L;

    final T t1;
    final T t2;

    private CoupleSimple() {
        this(null, null);
    }

    public CoupleSimple(T t1, T t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public T getT1() {
        return t1;
    }

    public T getT2() {
        return t2;
    }

    //---
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.t1);
        hash = 17 * hash + Objects.hashCode(this.t2);
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
        final CoupleSimple<?> other = (CoupleSimple<?>) obj;
        if (!Objects.equals(this.t1, other.t1)) {
            return false;
        }
        if (!Objects.equals(this.t2, other.t2)) {
            return false;
        }
        return true;
    }

}
