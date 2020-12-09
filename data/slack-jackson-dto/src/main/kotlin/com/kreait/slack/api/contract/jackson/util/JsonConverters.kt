package com.kreait.slack.api.contract.jackson.util

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.Instant

object JsonConverters {

    class InstantToUnixTimestampStringSerializer : JsonSerializer<Instant>() {

        override fun serialize(value: Instant, gen: JsonGenerator, serializers: SerializerProvider?) {
            val microSeconds = (value.nano / 1e3).toLong()

            if (microSeconds == 0L) {
                gen.writeString("${value.epochSecond}")
            } else {
                gen.writeString("${value.epochSecond}.${value.nano / 1000}")
            }
        }

    }

    class UnixTimestampStringToInstantDeserializer : JsonDeserializer<Instant>() {

        override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Instant {
            val split = p.text.split(".").map { it.toLong() }
            return if (split.size == 2) {
                Instant.ofEpochSecond(split[0], split[1] * 1000)
            } else {
                Instant.ofEpochSecond(split[0])
            }
        }
    }

    class InstantToUnixTimestampIntSerializer : JsonSerializer<Instant>() {

        override fun serialize(value: Instant, gen: JsonGenerator, serializers: SerializerProvider?) =
            gen.writeNumber(value.epochSecond)

    }

    class UnixTimestampIntToInstantDeserializer : JsonDeserializer<Instant>() {

        override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Instant =
            Instant.ofEpochSecond(p.longValue)
    }
}
