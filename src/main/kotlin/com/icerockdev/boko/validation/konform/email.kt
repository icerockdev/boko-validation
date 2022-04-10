/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.boko.validation.konform

import io.konform.validation.Constraint
import io.konform.validation.ValidationBuilder
import io.konform.validation.jsonschema.pattern

internal const val DEFAULT_EMAIL_PATTERN = ".+@.+\\..+"

/**
 * Constraint for validate email address.
 *
 * Example validate email address:
 * ```
 * Request::email required {
 *   email() hint "must be valid email address"
 * }
 * ```
 */
fun ValidationBuilder<String>.email(pattern: String = DEFAULT_EMAIL_PATTERN): Constraint<String> =
    pattern(pattern.toRegex()) hint "must be valid email address"
