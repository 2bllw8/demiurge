/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */

/**
 * This package provides the Try type.
 *
 * <p>The {@link exe.bbllw8.demiurge.attempt.Try} type represents a computation that may either
 * result in an exception, or return a successfully computed value. It's similar to, but
 * semantically different from the {@link exe.bbllw8.demiurge.either.Either} type. Instances of
 * {@link exe.bbllw8.demiurge.attempt.Try}, are either an instance of
 * {@link exe.bbllw8.demiurge.attempt.Success} or {@link exe.bbllw8.demiurge.attempt.Failure}.
 *
 * @author 2bllw8
 * @since 1.0.0
 */
package exe.bbllw8.demiurge.attempt;
