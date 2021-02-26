package com.kreait.slack.api.contract.jackson.common.messaging.composition

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.kreait.slack.api.contract.jackson.common.messaging.composition.Text.Type
import java.lang.IllegalArgumentException


/**
 * An object containing text with formatting (either Markdown or Plain-Text)
 *
 * @property type Specifies the formatting
 * @property text The text to display
 * @property escapeEmojis Indicates if emojis should be escaped into the colon emoji format. Only usable when type is plain_text.
 * @property verbatim Converts URLS, conversations namens and certian mentions into links when set to false. Skips pre-processing when true.
 *
 * @see [Type]
 * @see [Slack API Documentation](https://api.slack.com/reference/messaging/composition-objects#text)
 */
data class Text (
    @JsonProperty("type") val type: Type,
    @JsonProperty("text") val text: String,
    @JsonProperty("emoji") val escapeEmojis: Boolean? = null,
    @JsonProperty("verbatim") val verbatim: Boolean? = null
) {

    @JsonSerialize(using = Type.Serializer::class)
    @JsonDeserialize(using = Type.Deserializer::class)
    enum class Type(val type: String) {
        MARKDOWN("mrkdwn"),
        PLAIN_TEXT("plain_text");

        companion object {
            fun findByType(type: String): Type = values().find { it.type == type }
                    ?: throw IllegalArgumentException("No enum constant with type: $type")
        }

        class Serializer : JsonSerializer<Type>() {
            override fun serialize(value: Type, gen: JsonGenerator?, serializers: SerializerProvider?) {
                gen?.writeString(value.type)
            }
        }

        class Deserializer : JsonDeserializer<Type>() {
            override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Type {
                return findByType(p.text)
            }
        }
    }

    companion object
}
