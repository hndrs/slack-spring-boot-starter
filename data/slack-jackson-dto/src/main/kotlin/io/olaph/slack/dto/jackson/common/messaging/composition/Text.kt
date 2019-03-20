package io.olaph.slack.dto.jackson.common.messaging.composition

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize

/**
 * https://api.slack.com/reference/messaging/composition-objects#text
 */
data class Text(@JsonProperty("type") val type: Type,
                @JsonProperty("text") val text: String,
                @JsonProperty("emoji") val escapeEmojis: Boolean? = null,
                @JsonProperty("verbatim") val verbatim: Boolean? = null) {

    @JsonSerialize(using = Type.Serializer::class)
    enum class Type(val type: String) {
        MARKDOWN("mrkdwn"), PLAIN_TEXT("plain_text");

        class Serializer : JsonSerializer<Type>() {
            override fun serialize(value: Type, gen: JsonGenerator?, serializers: SerializerProvider?) {
                gen?.writeString(value.type)
            }
        }
    }

    companion object

}
