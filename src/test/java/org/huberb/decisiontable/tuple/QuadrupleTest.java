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
public class QuadrupleTest {

    @Test
    public void testConstructor() {
        Quadruple<Integer, String, Integer, String> is = new Quadruple<>(1, "A", 2, "B");
        assertEquals(1, is.getT().intValue());
        assertEquals("A", is.getU());
    }

    @Test
    public void testEquals() {
        final Quadruple<Integer, String, Integer, String> is1_1 = new Quadruple<>(1, "A", 2, "AA");
        final Quadruple<Integer, String, Integer, String> is1_2 = new Quadruple<>(1, "A", 2, "AA");
        final Quadruple<Integer, String, Integer, String> is2_1 = new Quadruple<>(2, "B", 3, "BBB");

        assertEquals(true, is1_1.equals(is1_1));
        assertEquals(true, is1_1.equals(is1_2));
        assertEquals(true, is2_1.equals(is2_1));
        assertEquals(true, is2_1.equals(is2_1));
        assertEquals(false, is2_1.equals(is1_1));
        assertEquals(false, is2_1.equals(is1_2));
    }

    @Test
    public void testHashCode() {
        final Quadruple<Integer, String, Integer, String> is1_1 = new Quadruple<>(1, "A", 2, "AA");
        final Quadruple<Integer, String, Integer, String> is1_2 = new Quadruple<>(1, "A", 2, "AA");
        final Quadruple<Integer, String, Integer, String> is2_1 = new Quadruple<>(2, "B", 3, "BBB");

        assertEquals(true, is1_1.hashCode() == is1_1.hashCode());
        assertEquals(true, is1_1.hashCode() == is1_2.hashCode());
        assertEquals(true, is2_1.hashCode() == is2_1.hashCode());
        assertEquals(true, is2_1.hashCode() == is2_1.hashCode());
        assertEquals(false, is2_1.hashCode() == is1_1.hashCode());
        assertEquals(false, is2_1.hashCode() == is1_2.hashCode());
    }

}
