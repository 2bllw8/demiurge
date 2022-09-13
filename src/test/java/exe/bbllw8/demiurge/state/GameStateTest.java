/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */
package exe.bbllw8.demiurge.state;

import exe.bbllw8.demiurge.tuple.Tuple2;
import java.util.function.Function;
import org.junit.Assert;
import org.junit.Test;

public class GameStateTest {

    @Test
    public void test0() {
        final State<GameState, GameValue> state = playGame("abcaaacbbcabbab");
        final GameState initialState = new GameState(false, 0);
        final GameValue value = state.evalState(initialState);
        Assert.assertEquals("Expected score is zero",
                0, value.score);
    }

    @Test
    public void testPos3() {
        final State<GameState, GameValue> state = playGame("caaac");
        final GameState initialState = new GameState(false, 0);
        final GameValue value = state.evalState(initialState);
        Assert.assertEquals("Score should increase by 3",
                initialState.score + 3, value.score);
    }

    @Test
    public void testNeg3() {
        final State<GameState, GameValue> state = playGame("cbbb");
        final GameState initialState = new GameState(false, 0);
        final GameValue value = state.evalState(initialState);
        Assert.assertEquals("Score should decrease by 3",
                initialState.score - 3, value.score);
    }

    private State<GameState, GameValue> playGame(
            String str) {
        if (str.isEmpty()) {
            return State.of(s -> new Tuple2<>(new GameValue(s.score), s));
        } else {
            return State.of((GameState s) -> new Tuple2<>((GameValue) null,
                            getStateTransformation(str.charAt(0)).apply(s)))
                    .bind(s -> playGame(str.substring(1)));
        }
    }

    private static Function<GameState, GameState> getStateTransformation(char c) {
        switch (c) {
            case 'a':
                return s -> new GameState(s.isOn, s.score + 1);
            case 'b':
                return s -> new GameState(s.isOn, s.score - 1);
            case 'c':
                return s -> new GameState(!s.isOn, s.score);
            default:
                return Function.identity();
        }
    }

    private static class GameValue {

        public transient final int score;

        public GameValue(int score) {
            this.score = score;
        }
    }

    private static class GameState {

        public transient final boolean isOn;
        public transient final int score;

        public GameState(boolean isOn, int score) {
            this.isOn = isOn;
            this.score = score;
        }
    }
}
