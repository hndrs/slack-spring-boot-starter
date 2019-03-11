package io.olaph.slack.dto.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.olaph.slack.dto.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulPostMessageResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorPostMessageResponse::class, name = "false")
)
@JacksonDataClass
abstract class SlackPostMessageResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulPostMessageResponse constructor(override val ok: Boolean,
                                                     @JsonProperty("channel") val channel: String,
                                                     @JsonProperty("ts") val timestamp: String,
                                                     @JsonProperty("message") val message: Message? = Message()
) : SlackPostMessageResponse(ok)


data class Message(
        @JsonProperty("type") val type: String? = null,
        @JsonProperty("subtype") val subtype: String? = null,
        @JsonProperty("text") val text: String? = null,
        @JsonProperty("ts") val ts: String? = null,
        @JsonProperty("username") val username: String? = null,
        @JsonProperty("bot_id") val botId: String? = null
)

@JacksonDataClass
data class ErrorPostMessageResponse constructor(override val ok: Boolean,
                                                @JsonProperty("error") val error: String
) : SlackPostMessageResponse(ok)

@JacksonDataClass
data class SlackPostMessageRequest constructor(@JsonProperty("text") val text: String? = null,
                                               @JsonProperty("attachments") val attachments: List<Attachment>? = listOf(),
                                               @JsonProperty("channel") val channel: String,
                                               @JsonProperty("as_user") val asUser: Boolean? = false,
                                               @JsonProperty("username") val username: String? = null,
                                               @JsonProperty("icon_emoji") val iconEmoji: String? = null,
                                               @JsonProperty("icon_url") val iconUrl: String? = null,
                                               @JsonProperty("link_names") val linkNames: Boolean = true,
                                               @JsonProperty("mrkdwn") val markDown: Boolean = true,
                                               @JsonProperty("parse") val parse: String = "none",
                                               @JsonProperty("reply_broadcast") val replyBroadcast: Boolean = false,
                                               @JsonProperty("thread_ts") val threadTs: String? = null,
                                               @JsonProperty("unfurl_links") val unfurlLinks: Boolean = false,
                                               @JsonProperty("unfurl_media") val unfurlMedia: Boolean = true)

@JsonSerialize(using = ResponseType.Serializer::class)
enum class ResponseType {
    IN_CHANNEL, EPHEMERAL;

    class Serializer : JsonSerializer<ResponseType>() {
        override fun serialize(value: ResponseType?, gen: JsonGenerator?, provider: SerializerProvider?) {
            gen?.writeString(value.toString().toLowerCase())
        }
    }
}


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
data class Action(
        @JsonProperty("name") val name: String? = null,
        @JsonProperty("text") val text: String? = null,
        @JsonProperty("style") val style: Style? = null,
        @JsonProperty("type") val type: ActionType,
        @JsonProperty("value") val value: String? = null,
        @JsonProperty("options") val options: List<Option>? = listOf(),
        @JsonProperty("selected_options") val selectedOptions: List<Option>? = listOf()
)

@JsonDeserialize(using = ActionType.Deserializer::class)
@JsonSerialize(using = ActionType.Serializer::class)
enum class ActionType {
    BUTTON,
    SELECT;

    class Serializer : JsonSerializer<ActionType>() {
        override fun serialize(value: ActionType?, gen: JsonGenerator?, provider: SerializerProvider?) {
            gen?.writeString(value.toString().toLowerCase())
        }
    }

    class Deserializer : JsonDeserializer<ActionType>() {
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): ActionType {
            return ActionType.valueOf(p.text.toUpperCase())
        }
    }
}

@JsonDeserialize(using = Style.Deserializer::class)
@JsonSerialize(using = Style.Serializer::class)
enum class Style {
    DEFAULT,
    PRIMARY,
    DANGER;

    class Serializer : JsonSerializer<Style>() {
        override fun serialize(value: Style?, gen: JsonGenerator?, provider: SerializerProvider?) {
            gen?.writeString(value.toString().toLowerCase())
        }
    }

    class Deserializer : JsonDeserializer<Style>() {
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Style {
            return Style.valueOf(p.text.toUpperCase())
        }
    }
}

@JacksonDataClass
data class Option(
        @JsonProperty("text") val text: String? = null,
        @JsonProperty("value") val value: String
)
