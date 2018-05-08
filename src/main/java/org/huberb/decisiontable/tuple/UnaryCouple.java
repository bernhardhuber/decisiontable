/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
