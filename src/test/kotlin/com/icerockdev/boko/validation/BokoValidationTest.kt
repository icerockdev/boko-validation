/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.boko.validation

import com.icerockdev.boko.validation.konform.match
import com.icerockdev.boko.validation.konform.minMaxLength
import io.konform.validation.Valid
import io.konform.validation.Validation
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.assertDoesNotThrow

internal class BokoValidationTest {

    @Test
    fun `Base BOKO validation test`() {
        val validation = BokoValidation<ChangePasswordRequest> {
            Validation {
                ChangePasswordRequest::firstName {
                    minMaxLength(5, 10) hint "First name must be minimum {0} and maximum {1} characters"
                }
                ChangePasswordRequest::password {
                    match { password == passwordRepeat } hint "Passwords must match"
                }
            }
        }

        assertFailsWith<BokoValidationException> {
            validation.valid()
        }

        assertFailsWith<BokoValidationException> {
            validation.getErrors()
        }

        assertEquals(Valid(ChangePasswordRequest()), validation(ChangePasswordRequest()))

        assertDoesNotThrow {
            validation.valid()
            validation.getErrors()
        }

        assertEquals(
            "First name must be minimum 5 and maximum 10 characters",
            validation(ChangePasswordRequest(firstName = "bla-bla-bla")).getHint("firstName")
        )
        assertEquals(
            "Passwords must match",
            validation(ChangePasswordRequest(passwordRepeat = "change")).getHint("password")
        )
    }
}

private data class ChangePasswordRequest(
    val firstName: String = "bla-bla",
    val password: String = "changeit",
    val passwordRepeat: String = "changeit"
)
