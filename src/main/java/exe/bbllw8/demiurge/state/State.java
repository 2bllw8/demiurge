/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */

package exe.bbllw8.demiurge.state;

import exe.bbllw8.demiurge.nothing.Nothing;
import exe.bbllw8.demiurge.tuple.Tuple2;
import java.util.function.Function;

/**
 * A state is one or more variables that are required to perform some computation but are not among
 * the arguments of the relevant function.
 *
 * <p>Doing so will require mutable variables which would mean that
 * functions will have hidden dependencies. Often, it is possible to keep track of state in a
 * functionally pure way by passing the state information from one function to the next, thus making
 * the hidden dependencies explicit.
 *
 * @param <S> the type of the held state value.
 * @param <A> the type of the computed value.
 * @author 2bllw8
 * @since 1.0.0
 */
public final class State<S, A> {

    private transient final Function<S, Tuple2<A, S>> runState;

    private State(Function<S, Tuple2<A, S>> f) {
        this.runState = f;
    }

    /**
     * Default state constructor.
     */
    public static <S, A> State<S, A> of(Function<S, Tuple2<A, S>> f) {
        return new State<>(f);
    }

    /**
     * Set the result value but leave the state unchanged.
     */
    public static <S, A> State<S, A> ret(A x) {
        return new State<>(s -> new Tuple2<>(x, s));
    }

    /**
     * Set the result value to {@link Nothing} and set the state value.
     */
    public static <S> State<S, Nothing> put(S s) {
        return new State<>($ -> new Tuple2<>(null, s));
    }

    /**
     * Set the result value to the state and leave the state unchanged.
     */
    public static <S> State<S, S> get(S s) {
        return new State<>($ -> new Tuple2<>(s, s));
    }

    /**
     * Returns a new instance from the result of the application of the current state to a given
     * function.
     */
    public <B> State<S, B> bind(Function<A, State<S, B>> f) {
        return new State<>(s -> {
            final Tuple2<A, S> intermediate = runState.apply(s);
            return f.apply(intermediate.getFirst()).runState.apply(intermediate.getSecond());
        });
    }

    /**
     * Returns a new instance that lazily applies a given function to the current state.
     */
    public <B> State<S, B> map(Function<A, B> f) {
        return new State<>(s -> {
            final Tuple2<A, S> intermediate = runState.apply(s);
            return new Tuple2<>(f.apply(intermediate.getFirst()), intermediate.getSecond());
        });
    }

    /**
     * Modify applies a given function to the previous state and puts its result as the new state.
     */
    public State<S, A> modify(Function<S, S> f) {
        return new State<>(s -> new Tuple2<>(null, f.apply(execState(s))));
    }

    /**
     * Evaluate the final result starting from a given initial state value.
     */
    public A evalState(S s) {
        return runState.apply(s).getFirst();
    }

    /**
     * Evaluate the final state starting from a given initial state value.
     */
    public S execState(S s) {
        return runState.apply(s).getSecond();
    }
}
