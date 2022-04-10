/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.boko.validation.konform

import io.konform.validation.Constraint
import io.konform.validation.ValidationBuilder

/**
 * Constraint for validate by any matching rule.
 *
 * Example of password matching:
 * ```
 * Request::password required {
 *   match { password == passwordRepeat } hint "passwords must match"
 * }
 * ```
 */
fun <T> ValidationBuilder<T>.match(matcher: T.() -> Boolean): Constraint<T> {
    return addConstraint("values must match") { it.matcher() }
}
