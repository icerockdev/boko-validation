/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.konformConstraints

import io.konform.validation.Constraint
import io.konform.validation.ValidationBuilder
import org.joda.time.format.DateTimeFormat

/**
 * Constraint for validate date field.
 *
 * Example of checking the date by format:
 * ```
 * Request::date required {
 *   date("dd-MM-yyyy") hint "date must be a {0} format"
 * }
 * ```
 */
fun ValidationBuilder<String>.date(pattern: String): Constraint<String> {
    val dateTimeFormatter = try {
        DateTimeFormat.forPattern(pattern)
    } catch (_: IllegalArgumentException) {
        null
    }

    require(dateTimeFormatter != null) { "date requires valid pattern" }

    return addConstraint(
        "date must be a {0} format",
        pattern,
    ) {
        try {
            DateTimeFormat.forPattern(pattern).parseDateTime(it)
            true
        } catch (_: IllegalArgumentException) {
            false
        }
    }
}
