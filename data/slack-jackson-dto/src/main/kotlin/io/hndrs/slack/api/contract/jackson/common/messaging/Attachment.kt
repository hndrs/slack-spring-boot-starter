package io.hndrs.slack.api.contract.jackson.common.messaging

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.hndrs.slack.api.contract.jackson.common.Action
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

/**
 * Attachments are used to add secondary content to messages.
 *
 * @property title Title text that appears at the top of the attachment
 * @property fallback A fallback summary in plain text for clients that don't show formatted texts (IRC, Mobile Notifications, etc.)
 * @property pretext Text that appears above the attachment block.
 * @property color The color of the left border of the attachment defined by the [Color] object.
 * @property attachmentType Indicates the type of the attachment.
 * @property callbackId A callback reference to that attachment.
 * @property actions [Action]s that can be used within an attachment, such as Buttons and Selectors.
 * @property text The main body text of the attachment.
 * @property authorName The name of the author that is displayed at the attachment.
 * @see [Action]
 * @see [Slack API Documentation](https://api.slack.com/reference/messaging/attachments)
 */
@JacksonDataClass
data class Attachment(
    @JsonProperty("id") val id: String? = null,
    @JsonProperty("title") val title: String? = null,
    @JsonProperty("fallback") val fallback: String? = null,
    @JsonProperty("pretext") val pretext: String? = null,
    @JsonProperty("color") val color: Color? = null,
    @JsonProperty("attachment_type") val attachmentType: String? = null,
    @JsonProperty("callback_id") val callbackId: String? = null,
    @JsonProperty("actions") val actions: List<Action>? = listOf(),
    @JsonProperty("text") val text: String? = null,
    @JsonProperty("author_name") val authorName: String? = null,
    @JsonProperty("blocks") val blocks: List<Block>? = null
) {
    companion object
}

/**
 * A helper data-class for the standard-colors used in Slack
 *
 * @property code Choses one of the standard colors depending on the identifier.
 * @see [Slack API Documentation](https://api.slack.com/docs/message-guidelines)
 */
@JsonSerialize(using = Color.Serializer::class)
@JsonDeserialize(using = Color.Deserializer::class)
@JacksonDataClass
data class Color(val code: String?) {

    /**
     * @property GOOD Translates to a green color tone.
     * @property WARNING Translates to a orange color tone.
     * @property DANGER Translates to a red color tone.
     * @property NEUTRAL Translates to a grey tone.
     */
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

    class Deserializer : JsonDeserializer<Color>() {
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): Color {
            return ofHex(p.text)
        }
    }
}

/**
 * Data class with the same properties as [Attachment] but used to update predecessing attachments.
 *
 * @property pretext Text that appears above the attachment block.
 * @property text The main body text of the attachment.
 * @property title Title text that appears at the top of the attachment.
 * @property color The color of the left border of the attachment.
 * @property attachmentType Indicates the type of the attachment.
 * @property callbackId A callback reference to that attachment.
 * @property actions [Action]s that can be used within an attachment, such as Buttons and Selectors.
 * @property authorName The name of the author that is displayed at the attachment.
 * @property fallback A fallback summary in plain text for clients that don't show formatted texts (IRC, Mobile Notifications, etc.)
 */
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
    @JsonProperty("fallback") val fallback: String,
    @JsonProperty("blocks") val blocks: List<Block>? = null
)
