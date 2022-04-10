/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.boko.validation

import io.konform.validation.Validation
import io.konform.validation.ValidationError
import io.konform.validation.ValidationResult

class BokoValidation<T>(private val init: T.() -> Validation<T>) {
    private var executed = false

    private val errors = mutableSetOf<ValidationError>()

    fun getErrors(): Map<String, String> {
        if (!executed) throw RuntimeException("For get error list run validate method first.")

        return errors.associate {
            it.dataPath to it.message
        }
    }

    fun valid() : Boolean {
        if (!executed) throw RuntimeException("For get validation result run validate method first.")

        return errors.isEmpty()
    }

    private fun validate(target: T): ValidationResult<T> {
        val validation = target.init()
        val validationResult = validation(target)

        executed = true
        errors.clear()
        errors.addAll(validationResult.errors)

        return validationResult
    }

    operator fun invoke(value: T) = validate(value)
}
