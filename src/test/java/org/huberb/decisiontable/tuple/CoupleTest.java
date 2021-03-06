/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.decisiontable.tuple;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author berni
 */
public class CoupleTest {

    @Test
    public void testConstructor() {
        Couple<Integer, String> is = new Couple<>(1, "A");
        assertEquals(1, is.getT().intValue());
        assertEquals("A", is.getU());
    }

    @Test
    public void testEquals() {
        final Couple<Integer, String> is1_1 = new Couple<>(1, "A");
        final Couple<Integer, String> is1_2 = new Couple<>(1, "A");
        final Couple<Integer, String> is2_1 = new Couple<>(2, "B");

        assertEquals(true, is1_1.equals(is1_1));
        assertEquals(true, is1_1.equals(is1_2));
        assertEquals(true, is2_1.equals(is2_1));
        assertEquals(true, is2_1.equals(is2_1));
        assertEquals(false, is2_1.equals(is1_1));
        assertEquals(false, is2_1.equals(is1_2));
    }

    @Test
    public void testHashCode() {
        final Couple<Integer, String> is1_1 = new Couple<>(1, "A");
        final Couple<Integer, String> is1_2 = new Couple<>(1, "A");
        final Couple<Integer, String> is2_1 = new Couple<>(2, "B");

        assertEquals(true, is1_1.hashCode() == is1_1.hashCode());
        assertEquals(true, is1_1.hashCode() == is1_2.hashCode());
        assertEquals(true, is2_1.hashCode() == is2_1.hashCode());
        assertEquals(true, is2_1.hashCode() == is2_1.hashCode());
        assertEquals(false, is2_1.hashCode() == is1_1.hashCode());
        assertEquals(false, is2_1.hashCode() == is1_2.hashCode());
    }

}
