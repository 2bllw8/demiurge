/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */
package exe.bbllw8.demiurge.tuple;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * A tuple containing 6 (six) elements.
 *
 * <p>This class is not serializable.
 *
 * @author 2bllw8
 * @see exe.bbllw8.demiurge.tuple
 * @since 1.0.0
 */
public final class Tuple6<A, B, C, D, E, F> {

    private transient final A first;
    private transient final B second;
    private transient final C third;
    private transient final D fourth;
    private transient final E fifth;
    private final F sixth;

    /**
     * Default constructor.
     */
    public Tuple6(A first, B second, C third, D fourth, E fifth, F sixth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
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
     * Returns the sixth element.
     */
    public F getSixth() {
        return sixth;
    }

    /**
     * Returns a {@link Tuple7} containing the six elements contained in this tuple plus a seventh
     * given element.
     *
     * @see Tuple7
     */
    public <G> Tuple7<A, B, C, D, E, F, G> with(G seventh) {
        return new Tuple7<>(first, second, third, fourth, fifth, sixth, seventh);
    }

    /**
     * Returns a {@link Stream} containing all the elements contained in this tuple.
     */
    public Stream<Object> stream() {
        return Stream.of(first, second, third, fourth, fifth, sixth);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tuple6)) {
            return false;
        }
        final Tuple6<?, ?, ?, ?, ?, ?> other = (Tuple6<?, ?, ?, ?, ?, ?>) o;
        return Objects.equals(first, other.first)
                && Objects.equals(second, other.second)
                && Objects.equals(third, other.third)
                && Objects.equals(fourth, other.fourth)
                && Objects.equals(fifth, other.fifth)
                && Objects.equals(sixth, other.sixth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third, fourth, fifth, sixth);
    }

    @Override
    public String toString() {
        return "("
                + first + ", "
                + second + ", "
                + third + ", "
                + fourth + ", "
                + fifth + ", "
                + sixth
                + ")";
    }
}
