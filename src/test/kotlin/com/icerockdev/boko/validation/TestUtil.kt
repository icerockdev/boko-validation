/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.boko.validation

import io.konform.validation.ValidationResult

internal fun <T> ValidationResult<T>.getHint(): String = get()!![0]

internal fun <T> ValidationResult<T>.getHint(property: String): String = get(property)!![0]
