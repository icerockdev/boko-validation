/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.icerockdev.boko.validation.konform

import com.icerockdev.boko.validation.getHint
import io.konform.validation.Valid
import io.konform.validation.Validation
import io.konform.validation.ValidationResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class ConstraintsTest {

    @Test
    fun `Date constraint test`() {
        val validation = Validation<String> { date("dd-MM-yyyy") }

        assertEquals(Valid("09-04-2022"), validation("09-04-2022"))

        assertEquals(
            Valid("2022-04-09T12:08:56.235+0700"),
            Validation<String> { date("yyyy-MM-dd'T'HH:mm:ss.SSSZ") }("2022-04-09T12:08:56.235+0700")
        )

        assertFailsWith(IllegalArgumentException::class) { Validation<String> { date("") } }

        assertEquals(1, validation("09.04.2022").errors.size)

        assertEquals("date must be a dd-MM-yyyy format", validation("09.04.2022").getHint())
        assertEquals(
            "date must be valid",
            Validation<String> { date("dd-MM-yyyy") hint "date must be valid" }("09.04.2022").getHint()
        )
    }

    @Test
    fun `Email constraint test`() {
        val validation = Validation<String> { email() }

        assertEquals(Valid("test@test.com"), validation("test@test.com"))
        assertEquals(Valid("t.test@test.com"), validation("t.test@test.com"))
        assertEquals(Valid("t-test@test.com"), validation("t-test@test.com"))
        assertEquals(Valid("test@test.test.com"), validation("test@test.test.com"))

        assertEquals(1, validation("test@test").errors.size)

        assertEquals("must be valid email address", validation("test@test").getHint())
        assertEquals(
            "email must be valid",
            Validation<String> { email() hint "email must be valid" }("test@test").getHint()
        )
    }

    @Test
    fun `In list constraint test`() {
        val validation = Validation<Int> { inList(listOf(1, 3, 5)) }

        assertEquals(Valid(1), validation(1))
        assertEquals(Valid(3), validation(3))
        assertEquals(Valid(5), validation(5))

        assertEquals(
            Valid("FOO"),
            Validation<String> { inList(listOf("FOO", "BAR", "BAZ")) }("FOO")
        )

        assertFailsWith(IllegalArgumentException::class) { Validation<String> { inList(emptyList()) } }

        assertEquals(1, validation(2).errors.size)
        assertEquals(1, validation(-1).errors.size)
        assertEquals(1, validation(Int.MIN_VALUE).errors.size)
        assertEquals(1, validation(Int.MAX_VALUE).errors.size)

        assertEquals("value must be one of 1, 3, 5 values", validation(0).getHint())
    }

    @Test
    fun `In range constraint test`() {
        val validation = Validation<Number> { inRange(1.1..999.9) }

        assertEquals<ValidationResult<Number>>(Valid(999.9), validation(999.9))
        assertEquals<ValidationResult<Number>>(Valid(20), validation(20))
        assertEquals<ValidationResult<Number>>(Valid(10), validation(10))
        assertEquals<ValidationResult<Number>>(Valid(1.1), validation(1.1))

        assertEquals(1, validation(0).errors.size)
        assertEquals(1, validation(0.1).errors.size)
        assertEquals(1, validation(-2).errors.size)
        assertEquals(1, validation(-6.7).errors.size)
        assertEquals(1, validation(1000).errors.size)
        assertEquals(1, validation(9999.9999).errors.size)
        assertEquals(1, validation(Double.NEGATIVE_INFINITY).errors.size)
        assertEquals(1, validation(Double.POSITIVE_INFINITY).errors.size)

        assertEquals("must be in 1.1..999.9 range", validation(1000).getHint())
    }

    @Test
    fun `Match constraint test`() {
        val validation = Validation<String> { match { this == "changeit" } }

        assertEquals(Valid("changeit"), validation("changeit"))

        assertEquals(
            Valid(listOf("FOO", "BAR", "FOO")),
            Validation<List<String>> { match { this[0] == this[2] } }(listOf("FOO", "BAR", "FOO"))
        )

        assertEquals("values must match", validation("").getHint())
    }

    @Test
    fun `Min-max length constraint test`() {
        val validation = Validation<String> { minMaxLength(5, 10) }

        assertEquals(Valid("bla-bla"), validation("bla-bla"))

        assertFailsWith(IllegalArgumentException::class) { Validation<String> { minMaxLength(-5, -10) } }
        assertFailsWith(IllegalArgumentException::class) { Validation<String> { minMaxLength(10, 5) } }

        assertEquals(1, validation("bla").errors.size)

        assertEquals("must have at least 5 and most 10 characters", validation("bla").getHint())
        assertEquals(
            "should be valid",
            Validation<String> { minMaxLength(5, 10) hint "should be valid" }("bla").getHint()
        )
    }
}
