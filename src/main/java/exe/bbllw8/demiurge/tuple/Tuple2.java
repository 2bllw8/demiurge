/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */
package exe.bbllw8.demiurge.tuple;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * A tuple containing 2 (two) elements.
 *
 * <p>This class is not serializable.
 *
 * @author 2bllw8
 * @see exe.bbllw8.demiurge.tuple
 * @since 1.0.0
 */
public final class Tuple2<A, B> {

    private transient final A first;
    private transient final B second;

    /**
     * Default constructor.
     */
    public Tuple2(A first, B second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first element.
     */
    public A getFirst() {
        return first;
    }

    /**
     * Returns the second element.
     */
    public B getSecond() {
        return second;
    }

    /**
     * Returns a {@link Tuple3} containing the two elements contained in this tuple plus a third
     * given element.
     *
     * @see Tuple3
     */
    public <C> Tuple3<A, B, C> with(C third) {
        return new Tuple3<>(first, second, third);
    }

    /**
     * Returns a {@link Stream} containing all the elements contained in this tuple.
     */
    public Stream<Object> stream() {
        return Stream.of(first, second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple2)) {
            return false;
        }
        final Tuple2<?, ?> other = (Tuple2<?, ?>) o;
        return Objects.equals(first, other.first)
                && Objects.equals(second, other.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
