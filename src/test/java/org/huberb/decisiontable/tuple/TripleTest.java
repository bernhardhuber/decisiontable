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
public class TripleTest {

    @Test
    public void testConstructor() {
        Triple<Integer, String, Double> is = new Triple<>(1, "A", -2.0);
        assertEquals(1, is.getT().intValue());
        assertEquals("A", is.getU());
        assertEquals(-2.0, is.getV(), 0.0);
    }

    @Test
    public void testEquals() {
        final Triple<Integer, String, Double> is1_1 = new Triple<>(1, "A", 1.0);
        final Triple<Integer, String, Double> is1_2 = new Triple<>(1, "A", 1.0);
        final Triple<Integer, String, Double> is2_1 = new Triple<>(2, "B", 2.0);

        assertEquals(true, is1_1.equals(is1_1));
        assertEquals(true, is1_1.equals(is1_2));
        assertEquals(true, is2_1.equals(is2_1));
        assertEquals(true, is2_1.equals(is2_1));
        assertEquals(false, is2_1.equals(is1_1));
        assertEquals(false, is2_1.equals(is1_2));
    }

    @Test
    public void testHashCode() {
        final Triple<Integer, String, Double> is1_1 = new Triple<>(1, "A", 1.0);
        final Triple<Integer, String, Double> is1_2 = new Triple<>(1, "A", 1.0);
        final Triple<Integer, String, Double> is2_1 = new Triple<>(2, "B", 2.0);

        assertEquals(true, is1_1.hashCode() == is1_1.hashCode());
        assertEquals(true, is1_1.hashCode() == is1_2.hashCode());
        assertEquals(true, is2_1.hashCode() == is2_1.hashCode());
        assertEquals(true, is2_1.hashCode() == is2_1.hashCode());
        assertEquals(false, is2_1.hashCode() == is1_1.hashCode());
        assertEquals(false, is2_1.hashCode() == is1_2.hashCode());
    }

}
