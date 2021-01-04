/*
 * Copyright 2021 berni3.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.huberb.decisiontable.tuple;

import static org.junit.Assert.*;
import org.junit.Test;

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
