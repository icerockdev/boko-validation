/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.konformConstraints

import io.konform.validation.Constraint
import io.konform.validation.ValidationBuilder

/**
 * Constraint for validate that the value is in list.
 *
 * Example of checking if the value is in list:
 * ```
 * Request::num required {
 *   inList(listOf(1, 3, 5)) hint "value must be one of {0} values"
 * }
 * ```
 */
fun <T> ValidationBuilder<T>.inList(list: List<T>): Constraint<T> {
    require(list.isNotEmpty()) { "inList requires not empty list" }

    return addConstraint(
        "value must be one of {0} values",
        list.joinToString(", ") { it.toString() },
    ) { it in list }
}
