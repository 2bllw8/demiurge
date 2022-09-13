/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */
package exe.bbllw8.demiurge.tuple;

import org.junit.Assert;
import org.junit.Test;

public class Tuple3Test {

    private transient final Tuple3<Long, Long, Long> tuple = new Tuple3<>(1L, 2L, 3L);

    @Test
    public void testGetters() {
        Assert.assertEquals("Expected first value",
                1L, (long) tuple.getFirst());
        Assert.assertEquals("Expected second value",
                2L, (long) tuple.getSecond());
        Assert.assertEquals("Expected second value",
                3L, (long) tuple.getThird());
    }

    @Test
    public void testEquality() {
        Assert.assertEquals("Equality check between two Tuple3 instances",
                new Tuple3<>(1L, 2L, 3L),
                tuple);
        Assert.assertEquals("Expected hashcode equality between two Tuple3 instances",
                new Tuple3<>(1L, 2L, 3L).hashCode(),
                tuple.hashCode());
    }

    @Test
    public void testWith() {
        Assert.assertEquals("Expected equality check between Tuple4 and Tuple3#with",
                new Tuple4<>(1L, 2L, 3L, 4L),
                tuple.with(4L));
        Assert.assertEquals("Expected hashcode equality between Tuple4 and Tuple3#with",
                new Tuple4<>(1L, 2L, 3L, 4L).hashCode(),
                tuple.with(4L).hashCode());
    }

    @Test
    public void testStream() {
        Assert.assertEquals("Expected stream size of 3",
                3, tuple.stream().count());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("Expected (_, _) string representation",
                "(1, 2, 3)", tuple.toString());
    }
}
