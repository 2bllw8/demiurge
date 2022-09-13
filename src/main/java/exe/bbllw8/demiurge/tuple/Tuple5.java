/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */
package exe.bbllw8.demiurge.tuple;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * A tuple containing 5 (five) elements.
 *
 * <p>This class is not serializable.
 *
 * @author 2bllw8
 * @see exe.bbllw8.demiurge.tuple
 * @since 1.0.0
 */
public final class Tuple5<A, B, C, D, E> {

    private transient final A first;
    private transient final B second;
    private transient final C third;
    private transient final D fourth;
    private transient final E fifth;

    /**
     * Default constructor.
     */
    public Tuple5(A first, B second, C third, D fourth, E fifth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
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
     * Returns the fourth element.
     */
    public D getFourth() {
        return fourth;
    }

    /**
     * Returns the fifth element.
     */
    public E getFifth() {
        return fifth;
    }

    /**
     * Returns a {@link Tuple6} containing the five elements contained in this tuple plus a sixth
     * given element.
     *
     * @see Tuple6
     */
    public <F> Tuple6<A, B, C, D, E, F> with(F sixth) {
        return new Tuple6<>(first, second, third, fourth, fifth, sixth);
    }

    /**
     * Returns a {@link Stream} containing all the elements contained in this tuple.
     */
    public Stream<Object> stream() {
        return Stream.of(first, second, third, fourth, fifth);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple5)) {
            return false;
        }
        final Tuple5<?, ?, ?, ?, ?> other = (Tuple5<?, ?, ?, ?, ?>) o;
        return Objects.equals(first, other.first)
                && Objects.equals(second, other.second)
                && Objects.equals(third, other.third)
                && Objects.equals(fourth, other.fourth)
                && Objects.equals(fifth, other.fifth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third, fourth, fifth);
    }

    @Override
    public String toString() {
        return "("
                + first + ", "
                + second + ", "
                + third + ", "
                + fourth + ", "
                + fifth
                + ")";
    }
}
