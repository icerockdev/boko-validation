/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.konformConstraints

import io.konform.validation.Constraint
import io.konform.validation.ValidationBuilder

/**
 * Constraint for validate by any matching rule.
 *
 * Example of password matching:
 * ```
 * // NOTE: Using match in object ValidationBuilder context
 * val passwordsMatchConstraint = match {
 *    password == passwordRepeat
 * } hint "values must match"
 *
 * Request::password required {
 *   rule { passwordsMatchConstraint }
 * }
 * ```
 *
 * @see [com.icerockdev.konformConstraints.extension.rule]
 */
fun <T> ValidationBuilder<T>.match(matcher: T.() -> Boolean): Constraint<T> {
    return addConstraint("values must match") { it.matcher() }
}
