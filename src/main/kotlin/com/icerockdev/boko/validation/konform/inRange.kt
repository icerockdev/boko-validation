/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.boko.validation.konform

import io.konform.validation.Constraint
import io.konform.validation.ValidationBuilder

/**
 * Constraint for validate that the value is in the range.
 *
 * Example of checking if the value is in the range:
 * ```
 * Request::num required {
 *   inRange(1.1..999.9) hint "must be in {0}..{1} range"
 * }
 * ```
 */
fun <T, R> ValidationBuilder<T>.inRange(range: ClosedRange<R>): Constraint<T> where T : Number, R : Number, R : Comparable<R> {
    return addConstraint(
        "must be in {0}..{1} range",
        range.start.toString(),
        range.endInclusive.toString()
    ) { it.toDouble() in range.start.toDouble()..range.endInclusive.toDouble() }
}
