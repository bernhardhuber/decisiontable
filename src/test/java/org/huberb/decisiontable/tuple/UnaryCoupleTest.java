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
public class UnaryCoupleTest {

    @Test
    public void testConstructor() {
        UnaryCouple<String> is = new UnaryCouple<>("A", "B");
        assertEquals("A", is.getT());
        assertEquals("B", is.getU());
    }

    @Test
    public void testEquals() {
        final UnaryCouple<String> is1_1 = new UnaryCouple<>("A", "B");
        final UnaryCouple<String> is1_2 = new UnaryCouple<>("A", "B");
        final UnaryCouple<String> is2_1 = new UnaryCouple<>("B", "C");

        assertEquals(true, is1_1.equals(is1_1));
        assertEquals(true, is1_1.equals(is1_2));
        assertEquals(true, is2_1.equals(is2_1));
        assertEquals(true, is2_1.equals(is2_1));
        assertEquals(false, is2_1.equals(is1_1));
        assertEquals(false, is2_1.equals(is1_2));
    }

    @Test
    public void testHashCode() {
        final UnaryCouple<String> is1_1 = new UnaryCouple<>("A", "B");
        final UnaryCouple<String> is1_2 = new UnaryCouple<>("A", "B");
        final UnaryCouple<String> is2_1 = new UnaryCouple<>("B", "C");

        assertEquals(true, is1_1.hashCode() == is1_1.hashCode());
        assertEquals(true, is1_1.hashCode() == is1_2.hashCode());
        assertEquals(true, is2_1.hashCode() == is2_1.hashCode());
        assertEquals(true, is2_1.hashCode() == is2_1.hashCode());
        assertEquals(false, is2_1.hashCode() == is1_1.hashCode());
        assertEquals(false, is2_1.hashCode() == is1_2.hashCode());
    }

}
