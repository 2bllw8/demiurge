/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */
package exe.bbllw8.demiurge.attempt;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class CheckedFunctionTest {

    private transient final CheckedFunction<Long, Long> function = n -> {
        if (n > 0) {
            return n + 1;
        } else {
            throw new IOException();
        }
    };

    @Test
    public void testApply() throws Throwable {
        Assert.assertEquals("Expected result: 3",
                3L, (long) function.apply(2L));
    }

    @Test(expected = IOException.class)
    public void testApplyThrow() throws Throwable {
        function.apply(-1L);
    }

    @Test
    public void testAndThen() throws Throwable {
        Assert.assertEquals("Expected result: \"4!\"",
                "4!", function.andThen(n -> n + "!").apply(3L));
    }

    @Test
    public void testCompose() throws Throwable {
        Assert.assertEquals("Expected result: 2",
                2L, (long) function.compose((Boolean b) -> b ? 1L : 0L).apply(true));
    }
}
