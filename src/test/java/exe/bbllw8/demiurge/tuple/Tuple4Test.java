/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */
package exe.bbllw8.demiurge.tuple;

import org.junit.Assert;
import org.junit.Test;

public class Tuple4Test {

    private transient final Tuple4<Long, Long, Long, Long> tuple = new Tuple4<>(1L, 2L, 3L, 4L);

    @Test
    public void testGetters() {
        Assert.assertEquals("Expected first value",
                1L, (long) tuple.getFirst());
        Assert.assertEquals("Expected second value",
                2L, (long) tuple.getSecond());
        Assert.assertEquals("Expected third value",
                3L, (long) tuple.getThird());
        Assert.assertEquals("Expected fourth value",
                4L, (long) tuple.getFourth());
    }

    @Test
    public void testEquality() {
        Assert.assertEquals("Equality check between two Tuple4 instances",
                new Tuple4<>(1L, 2L, 3L, 4L),
                tuple);
        Assert.assertEquals("Expected hashcode equality between two Tuple4 instances",
                new Tuple4<>(1L, 2L, 3L, 4L).hashCode(),
                tuple.hashCode());
    }

    @Test
    public void testWith() {
        Assert.assertEquals("Expected equality check between Tuple5 and Tuple4#with",
                new Tuple5<>(1L, 2L, 3L, 4L, 5L),
                tuple.with(5L));
        Assert.assertEquals("Expected hashcode equality between Tuple5 and Tuple4#with",
                new Tuple5<>(1L, 2L, 3L, 4L, 5L).hashCode(),
                tuple.with(5L).hashCode());
    }

    @Test
    public void testStream() {
        Assert.assertEquals("Expected stream size of 4",
                4, tuple.stream().count());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("Expected (_, _) string representation",
                "(1, 2, 3, 4)", tuple.toString());
    }
}
