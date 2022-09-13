/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */
package exe.bbllw8.demiurge.tuple;

import org.junit.Assert;
import org.junit.Test;

public class Tuple6Test {

    private transient final Tuple6<Long, Long, Long, Long, Long, Long> tuple =
            new Tuple6<>(1L, 2L, 3L, 4L, 5L, 6L);

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
        Assert.assertEquals("Expected fifth value",
                5L, (long) tuple.getFifth());
        Assert.assertEquals("Expected sixth value",
                6L, (long) tuple.getSixth());
    }

    @Test
    public void testEquality() {
        Assert.assertEquals("Equality check between two Tuple6 instances",
                new Tuple6<>(1L, 2L, 3L, 4L, 5L, 6L),
                tuple);
        Assert.assertEquals("Expected hashcode equality between two Tuple6 instances",
                new Tuple6<>(1L, 2L, 3L, 4L, 5L, 6L).hashCode(),
                tuple.hashCode());
    }

    @Test
    public void testWith() {
        Assert.assertEquals("Expected equality check between Tuple7 and Tuple6#with",
                new Tuple7<>(1L, 2L, 3L, 4L, 5L, 6L, 7L),
                tuple.with(7L));
        Assert.assertEquals("Expected hashcode equality between Tuple7 and Tuple6#with",
                new Tuple7<>(1L, 2L, 3L, 4L, 5L, 6L, 7L).hashCode(),
                tuple.with(7L).hashCode());
    }

    @Test
    public void testStream() {
        Assert.assertEquals("Expected stream size of 6",
                6, tuple.stream().count());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("Expected (_, _) string representation",
                "(1, 2, 3, 4, 5, 6)", tuple.toString());
    }
}
