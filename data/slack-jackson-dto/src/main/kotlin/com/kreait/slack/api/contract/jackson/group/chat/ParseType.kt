package com.kreait.slack.api.contract.jackson.group.chat

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize

/**
 * They ParseType for messages
 * @property value the valueString
 * @see [Slack Formatting Guide](https://api.slack.com/docs/message-formatting)
 */
@JsonSerialize(using = ParseType.Serializer::class)
@JsonDeserialize(using = ParseType.Deserializer::class)
enum class ParseType(private val value: String) {
    /**
     * will ignore any markup formatting you added to your message
     */
    FULL("full"),

    /**
     * If you want Slack to treat your message as completely unformatted, pass parse=full. This will ignore any markup formatting you added to your message.
     */
    NONE("none"),
    CLIENT("client");

    class Serializer : JsonSerializer<ParseType>() {
        override fun serialize(parseType: ParseType, gen: JsonGenerator?, serializers: SerializerProvider?) {
            gen?.writeString(parseType.value)
        }
    }

    class Deserializer : JsonDeserializer<ParseType>() {
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): ParseType? {
            return ParseType.values().firstOrNull { it.value == p.text }
        }
    }
}
