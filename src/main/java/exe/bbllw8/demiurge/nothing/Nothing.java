/*
 * Copyright (c) 2022 2bllw8
 * SPDX-License-Identifier: Apache-2.0
 */

package exe.bbllw8.demiurge.nothing;

/**
 * Nothing has no instances. You can use it to represent a value that never exists: for example, if
 * a given function has return type Nothing, it means that it will always throw an exception (it
 * never returns).
 */
@SuppressWarnings("PMD.MissingStaticMethodInNonInstantiatableClass")
public final class Nothing {

    private Nothing() {
    }
}
