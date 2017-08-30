/*
 * Copyright 2015-2017 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.junit.jupiter.api

import org.junit.jupiter.api.function.Executable
import org.junit.platform.commons.meta.API
import org.junit.platform.commons.meta.API.Usage.Experimental
import java.util.stream.Stream

/**
 * [Stream] of functions to be executed.
 */
internal typealias ExecutableStream = Stream<() -> Unit>
internal fun ExecutableStream.convert() = map { Executable(it) }

/**
 * @see Assertions.assertAll
 * @since 5.0
 */
@API(Experimental)
fun assertAll(executables: ExecutableStream) =
    Assertions.assertAll(executables.convert())

/**
 * @see Assertions.assertAll
 * @since 5.0
 */
@API(Experimental)
fun assertAll(heading : String?, executables: ExecutableStream) =
    Assertions.assertAll(heading, executables.convert())

/**
 * @see Assertions.assertAll
 * @since 5.0
 */
@API(Experimental)
fun assertAll(vararg executables: () -> Unit) =
    assertAll(executables.toList().stream())

/**
 * @see Assertions.assertAll
 * @since 5.0
 */
@API(Experimental)
fun assertAll(heading: String?, vararg executables: () -> Unit) =
    assertAll(heading, executables.toList().stream())

/**
 * Example usage:
 * ```kotlin
 * val exception = assertThrows<IllegalArgumentException> {
 *     throw IllegalArgumentException("Talk to a 🦆")
 * }
 * assertEquals("Talk to a 🦆", exception.message)
 * ```
 * @see Assertions.assertThrows
 * @since 5.0
 */
@API(Experimental)
inline fun <reified T : Throwable> assertThrows(noinline executable : () -> Unit) : T =
    Assertions.assertThrows(T::class.java, Executable(executable))
