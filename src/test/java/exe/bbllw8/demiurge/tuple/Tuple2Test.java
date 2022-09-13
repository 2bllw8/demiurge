/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */
package exe.bbllw8.demiurge.tuple;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

public class Tuple2Test {

    private transient final Tuple2<Long, Long> tuple = new Tuple2<>(1L, 2L);

    @Test
    public void testGetters() {
        Assert.assertEquals("Expected first value",
                1L, (long) tuple.getFirst());
        Assert.assertEquals("Expected second value",
                2L, (long) tuple.getSecond());
    }

    @Test
    public void testEquality() {
        Assert.assertEquals("Expected equality check between two Tuple2 instances",
                new Tuple2<>(1L, 2L), tuple);
        Assert.assertEquals("Expected hashcode equality between two Tuple2 instances",
                new Tuple2<>(1L, 2L).hashCode(),
                tuple.hashCode());
    }

    @Test
    public void testWith() {
        Assert.assertEquals("Expected equality check between Tuple3 and Tuple2#with",
                new Tuple3<>(1L, 2L, 3L),
                tuple.with(3L));
        Assert.assertEquals("Expected hashcode equality between Tuple3 and Tuple2#with",
                new Tuple3<>(1L, 2L, 3L).hashCode(),
                tuple.with(3L).hashCode());
    }

    @Test
    public void testStream() {
        Assert.assertEquals("Expected stream size of 2",
                2, tuple.stream().count());
        Assert.assertEquals("Expected contents [1, 2]",
                Arrays.asList(1L, 2L),
                tuple.stream().collect(Collectors.toList()));
    }

    @Test
    public void testToString() {
        Assert.assertEquals("Expected (_, _) string representation",
                "(1, 2)", tuple.toString());
    }
}
