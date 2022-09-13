/*
 * Copyright (c) 2021-2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */
package exe.bbllw8.demiurge.either;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Projects an Either into a {@link Left}.
 *
 * <p>All instances of this class are not serializable.
 *
 * @author 2bllw8
 * @see Either#left()
 * @see Left
 * @since 1.0.0
 */
public abstract class LeftProjection<A, B> {

    /**
     * Package-private default constructor.
     */
    /* package */ LeftProjection() {
    }

    /**
     * Returns false if {@link Right} or returns the result of the application of the given function
     * to the {@link Left} value.
     */
    public abstract boolean exists(Function<A, Boolean> predicate);

    /**
     * Returns {@link Optional#empty()} if this is a {@link Right} or if the given predicate p does
     * not hold for the left value, otherwise, returns a {@link Left}.
     */
    public abstract Optional<Either<A, B>> filterToOptional(Function<A, Boolean> predicate);

    /**
     * Binds the given function across {@link Left}.
     */
    public abstract <A1> Either<A1, B> flatMap(Function<A, Either<A1, B>> function);

    /**
     * Returns true if {@link Right} or returns the result of the application of the given function
     * to the Right value.
     */
    public abstract boolean forAll(Function<A, Boolean> function);

    /**
     * Executes the given side-effecting function if this is a {@link Left}.
     */
    public abstract void forEach(Consumer<A> consumer);

    /**
     * Returns the value from this {@link Left} or the given argument if this is a {@link Right}.
     */
    public abstract A getOrElse(A fallback);

    /**
     * The given function is applied if this is a {@link Left}.
     */
    public abstract <A1> Either<A1, B> map(Function<A, A1> function);

    /**
     * Returns a stream containing the {@link Left} value if it exists or {@link Stream#empty()} if
     * this is a {@link Right}.
     */
    public abstract Stream<A> stream();

    /**
     * Returns an {@link Optional} containing the {@link Left} value if it exists or
     * {@link Optional#empty()} if this is a {@link Right}.
     */
    public abstract Optional<A> toOptional();
}
