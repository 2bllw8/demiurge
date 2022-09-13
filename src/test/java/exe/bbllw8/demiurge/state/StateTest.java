/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */
package exe.bbllw8.demiurge.state;

import exe.bbllw8.demiurge.nothing.Nothing;
import exe.bbllw8.demiurge.tuple.Tuple2;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.Test;

public class StateTest {

    @Test
    public void testOf() {
        final State<Long, Long> next = State.of(s -> new Tuple2<>(s + 1L, s));

        Assert.assertEquals("Next of 0 should be 1", 1L, (long) next.evalState(0L));
        Assert.assertEquals("Next of 1 should be 2", 2L, (long) next.evalState(1L));
        Assert.assertEquals("Next of 2 should be 3", 3L, (long) next.evalState(2L));
    }

    @Test
    public void testGet() {
        final State<Long, Long> zero = State.get(0L);

        Assert.assertEquals("Should always return 0", 0L, (long) zero.evalState(0L));
        Assert.assertEquals("Should always return 0", 0L, (long) zero.evalState(1L));
        Assert.assertEquals("Should always return 0", 0L, (long) zero.evalState(2L));
    }

    @Test
    public void testPut() {
        final State<String, Nothing> state = State.put("hi");

        Assert.assertNull("Should have null result", state.evalState(""));
        Assert.assertEquals("Should have expected state", "hi", state.execState("hi"));
    }


    @Test
    public void testConsistent() {
        final State<Random, Integer> nextRandom = State.of(r -> new Tuple2<>(r.nextInt(), r));

        Assert.assertEquals("Should have equal results for equal inputs",
                (long) nextRandom.evalState(new Random(0)),
                (long) nextRandom.evalState(new Random(0)));
        Assert.assertNotEquals("Should have different results for different inputs",
                (long) nextRandom.evalState(new Random(1)),
                (long) nextRandom.evalState(new Random(0)));
    }

    // Need to suppress these and write anon classes for Java 8 support
    // since its type system is not smart enough to infer all the types.
    // Java 11+ works fine though
    @SuppressWarnings({
            "Convert2Lambda",
            "PMD.DataflowAnomalyAnalysis"
    })
    @Test
    public void testBind() {
        final State<Integer, Integer> getNext = State.of(s -> new Tuple2<>(1 + s, 1 + s));
        final State<Integer, Integer> inc3 = getNext.bind(
                new Function<Integer, State<Integer, Integer>>() {
                    @Override
                    public State<Integer, Integer> apply(Integer x) {
                        return getNext.bind(new Function<Integer, State<Integer, Integer>>() {
                            @SuppressWarnings("Anonymous2MethodRef")
                            @Override
                            public State<Integer, Integer> apply(Integer y) {
                                return getNext.bind(
                                        new Function<Integer, State<Integer, Integer>>() {
                                            @Override
                                            public State<Integer, Integer> apply(Integer z) {
                                                return State.ret(z);
                                            }
                                        });
                            }
                        });
                    }
                });
        final int val = new Random().nextInt(Integer.MAX_VALUE - 3);
        final int result = inc3.evalState(val);
        Assert.assertEquals("Should increase by 3", val + 3, result);
    }

    @Test
    public void testModify() {
        final State<Stream<String>, String> addBird = State.of(s -> new Tuple2<>(
                "added bird",
                Stream.concat(s, Stream.of("bird"))));
        Assert.assertEquals("Should add fish",
                Arrays.asList("cat", "bird", "fish"),
                addBird.modify(s -> Stream.concat(s, Stream.of("fish")))
                        .execState(Stream.of("cat"))
                        .collect(Collectors.toList()));
        Assert.assertNull("Result should be null after modify",
                addBird.modify(s -> Stream.concat(s, Stream.of("fish")))
                        .evalState(Stream.of("cat")));
    }

    @Test
    public void testMap() {
        final State<String, String> state = State.get("cookie");

        Assert.assertEquals("Should have expected value",
                6L, (long) state.map(String::length)
                        .evalState(""));
    }

    @Test
    public void testPutAndRet() {
        final State<Long, Character> state = State.put(5L).bind($ -> State.ret('X'));

        Assert.assertEquals("Should have expected state",
                5L, (long) state.execState(0L));
        Assert.assertEquals("Should have expected state",
                5L, (long) state.execState(1L));
        Assert.assertEquals("Should have expected result",
                'X', (long) state.evalState(0L));
        Assert.assertEquals("Should have expected result",
                'X', (long) state.evalState(1L));
    }
}
