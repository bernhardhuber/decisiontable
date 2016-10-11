/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.decisiontable.tuple;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author berni
 */
public class CoupleTest {

    public CoupleTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        Couple<Integer, String> is = new Couple<>(1, "A");
        assertEquals(1, is.getT().intValue());
        assertEquals("A", is.getU());
    }

}
