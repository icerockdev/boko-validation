/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.boko.validation

import com.icerockdev.boko.validation.konform.match
import io.konform.validation.Valid
import io.konform.validation.Validation
import kotlin.test.Test
import kotlin.test.assertEquals

internal class BokoValidationTest {

    @Test
    fun `Base BOKO validation test`() {
        val validation = BokoValidation<ChangePasswordRequest> {
            Validation {
                ChangePasswordRequest::password {
                    match { password == passwordRepeat } hint "passwords must match"
                }
            }
        }

        assertEquals(Valid(ChangePasswordRequest()), validation(ChangePasswordRequest()))

        assertEquals(
            "passwords must match",
            validation(ChangePasswordRequest(passwordRepeat = "change")).getHint("password")
        )
    }
}

private data class ChangePasswordRequest(val password: String = "changeit", val passwordRepeat: String = "changeit")
