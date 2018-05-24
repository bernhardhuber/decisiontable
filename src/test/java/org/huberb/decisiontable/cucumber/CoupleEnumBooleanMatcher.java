/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.decisiontable.cucumber;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.huberb.decisiontable.tuple.Couple;

/**
 *
 * @author berni3
 */
class CoupleEnumBooleanMatcher extends TypeSafeDiagnosingMatcher<Couple<Enum, Boolean>> {
    
    private final Enum e;
    private final boolean b;

    public CoupleEnumBooleanMatcher(Enum e, boolean b) {
        super(Couple.class);
        this.e = e;
        this.b = b;
    }

    @Override
    protected boolean matchesSafely(Couple<Enum, Boolean> item, Description mismatchDescription) {
        mismatchDescription.appendValueList("(", ",", ")", item);
        return item.getT() == e && item.getU() == b;
    }

    @Override
    public void describeTo(Description description) {
    }
    
}
