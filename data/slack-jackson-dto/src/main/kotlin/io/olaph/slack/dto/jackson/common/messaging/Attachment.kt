package io.olaph.slack.dto.jackson.common.messaging

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.Action

@JacksonDataClass
data class Attachment(
        @JsonProperty("title") val title: String? = null,
        @JsonProperty("fallback") val fallback: String,
        @JsonProperty("pretext") val pretext: String? = null,
        @JsonProperty("color") val color: Color? = null,
        @JsonProperty("attachment_type") val attachmentType: String? = null,
        @JsonProperty("callback_id") val callbackId: String? = null,
        @JsonProperty("actions") val actions: List<Action>? = listOf(),
        @JsonProperty("text") val text: String? = null,
        @JsonProperty("author_name") val authorName: String? = null)

@JsonSerialize(using = Color.Serializer::class)
data class Color(val code: String?) {

    companion object {
        val GOOD = Color(code = "good")
        val WARNING = Color(code = "warning")
        val DANGER = Color(code = "danger")
        val NEUTRAL = Color(code = "neutral")

        fun ofHex(hexCode: String): Color {
            //TODO validation
            return Color(hexCode)
        }
    }

    class Serializer : JsonSerializer<Color>() {
        override fun serialize(value: Color, gen: JsonGenerator?, serializers: SerializerProvider?) {
            gen?.writeString(value.code)
        }
    }

}

@JacksonDataClass
data class UpdateAttachment(
        @JsonProperty("pretext") val pretext: String? = null,
        @JsonProperty("text") val text: String,
        @JsonProperty("title") val title: String? = null,
        @JsonProperty("color") val color: String? = null,
        @JsonProperty("attachment_type") val attachmentType: String? = "default",
        @JsonProperty("callback_id") val callbackId: String? = null,
        @JsonProperty("actions") val actions: List<Action>? = listOf(),
        @JsonProperty("author_name") val authorName: String? = null,
        @JsonProperty("fallback") val fallback: String)
