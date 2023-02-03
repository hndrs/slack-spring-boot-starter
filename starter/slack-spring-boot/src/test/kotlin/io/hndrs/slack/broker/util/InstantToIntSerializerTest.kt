package io.hndrs.slack.broker.util

import com.fasterxml.jackson.core.JsonGenerator
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.temporal.ChronoUnit

class InstantToIntSerializerTest {

    @Test
    fun serialize() {
        val jsonGenerator = mockk<JsonGenerator>(relaxed = true)
        val instant = Instant.now()
        val underTest = InstantToUnixTimestampIntSerializer()

        underTest.serialize(instant, jsonGenerator, null)

        verify {
            jsonGenerator.writeNumber(instant.epochSecond)
        }
    }

    @Test
    fun deserialize() {
        val instant = Instant.now().truncatedTo(ChronoUnit.SECONDS)

        val underTest = UnixTimestampIntToInstantDeserializer()

        val deserializeInstant = underTest.deserialize(
            mockk {
                every { longValue } returns instant.epochSecond
            },
            null
        )

        deserializeInstant shouldBe instant
    }
}
