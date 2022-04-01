/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.konformConstraints.extension

import io.konform.validation.Constraint
import io.konform.validation.Validation
import io.konform.validation.ValidationBuilder

/**
 * Apply externally created constraint.
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
 * @see [com.icerockdev.konformConstraints.match]
 */
fun <T, R> ValidationBuilder<T>.rule(init: () -> Constraint<R>) {
    val validation = Validation<T> { init() }
    run(validation)
}
