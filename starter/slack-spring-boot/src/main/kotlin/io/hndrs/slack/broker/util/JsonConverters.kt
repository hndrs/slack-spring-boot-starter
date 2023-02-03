package io.hndrs.slack.broker.util

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.Instant

object JsonConverters {

    private const val THOUSAND_MILLI_SECONDS = 1000

    class InstantToUnixTimestampStringSerializer : JsonSerializer<Instant>() {

        override fun serialize(value: Instant, gen: JsonGenerator, serializers: SerializerProvider?) {
            val microSeconds = (value.nano / THOUSAND_MILLI_SECONDS).toLong()

            if (microSeconds == 0L) {
                gen.writeString("${value.epochSecond}")
            } else {
                gen.writeString("${value.epochSecond}.${value.nano / THOUSAND_MILLI_SECONDS}")
            }
        }
    }

    class UnixTimestampStringToInstantDeserializer : JsonDeserializer<Instant>() {

        override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Instant {
            val split = p.text.split(".").map { it.toLong() }
            return if (split.size == 2) {
                Instant.ofEpochSecond(split[0], split[1] * THOUSAND_MILLI_SECONDS)
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
