/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */
package exe.bbllw8.demiurge.tuple;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * A tuple containing 3 (three) elements.
 *
 * <p>This class is not serializable.
 *
 * @author 2bllw8
 * @see exe.bbllw8.demiurge.tuple
 * @since 1.0.0
 */
public final class Tuple3<A, B, C> {

    private transient final A first;
    private transient final B second;
    private transient final C third;

    /**
     * Default constructor.
     */
    public Tuple3(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
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
     * Returns the third element.
     */
    public C getThird() {
        return third;
    }

    /**
     * Returns a {@link Tuple4} containing the three elements contained in this tuple plus a fourth
     * given element.
     *
     * @see Tuple4
     */
    public <D> Tuple4<A, B, C, D> with(D fourth) {
        return new Tuple4<>(first, second, third, fourth);
    }

    /**
     * Returns a {@link Stream} containing all the elements contained in this tuple.
     */
    public Stream<Object> stream() {
        return Stream.of(first, second, third);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple3)) {
            return false;
        }
        final Tuple3<?, ?, ?> other = (Tuple3<?, ?, ?>) o;
        return Objects.equals(first, other.first)
                && Objects.equals(second, other.second)
                && Objects.equals(third, other.third);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }

    @Override
    public String toString() {
        return "("
                + first + ", "
                + second + ", "
                + third
                + ")";
    }
}
