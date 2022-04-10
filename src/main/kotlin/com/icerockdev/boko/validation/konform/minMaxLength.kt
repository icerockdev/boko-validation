/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.boko.validation.konform

import io.konform.validation.Constraint
import io.konform.validation.ValidationBuilder

/**
 * Constraint for validate string for minimum and maximum length.
 *
 * Example of checking string for minimum and maximum length:
 * ```
 * Request::date required {
 *   minMaxLength(2, 10) hint "name must be minimum {0} and maximum {1} characters"
 * }
 * ```
 */
fun ValidationBuilder<String>.minMaxLength(min: Int, max: Int): Constraint<String> {
    require(min >= 0 && max >= 0) { "minMaxLength requires the min and max to be >= 0" }
    require(min < max) { "minMaxLength requires the min should be less than max" }

    return addConstraint(
        "must have at least {0} and most {1} characters",
        min.toString(),
        max.toString()
    ) { it.length in min..max }
}
