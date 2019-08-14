package com.kreait.slack.api.spring.group

import com.fasterxml.jackson.core.JsonGenerator
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.Instant

@DisplayName("Instant Converter Tests")
internal class InstantConverterTests {


    @Test
    @DisplayName("Serialization")
    fun serializationTests() {
        val jsonGenerator = mock<JsonGenerator> {}
        val instance = RestTemplateFactory.InstantToUnixTimestampStringSerializer.INSTANCE
        val now = Instant.now()
        instance.serialize(now, jsonGenerator, mock {})
        verify(jsonGenerator, times(1)).writeString("${now.epochSecond}.${now.nano / 1000}")

        instance.serialize(now.minusNanos(now.nano.toLong()), jsonGenerator, mock {})
        verify(jsonGenerator, times(1)).writeString("${now.epochSecond}")
    }

    @Test
    @DisplayName("Deserialization")
    fun deserializationTests() {
        val instance = RestTemplateFactory.UnixTimestampStringToInstantDeserializer.INSTANCE
        val deserializedInstance = instance.deserialize(mock { on { text } doReturn "1234567890.123456" }, mock { })

        Assertions.assertEquals(Instant.ofEpochSecond(1234567890, 123456 * 1000), deserializedInstance)

        val deserializedInstanceNoFractions = instance.deserialize(mock { on { text } doReturn "1234567890" }, mock { })
        Assertions.assertEquals(Instant.ofEpochSecond(1234567890), deserializedInstanceNoFractions)
    }
}
