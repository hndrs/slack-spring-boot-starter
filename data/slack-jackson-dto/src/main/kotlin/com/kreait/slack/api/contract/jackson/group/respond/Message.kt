package com.kreait.slack.api.contract.jackson.group.respond

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.kreait.slack.api.contract.jackson.common.messaging.Attachment
import com.kreait.slack.api.contract.jackson.common.messaging.Block
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass


/**
 * Responds to an Action
 * You can respond to actions (slashcommands, actions & interactive components, by posting to the contained response-url
 *
 * @property text the text of the message that should be posted
 * @property attachments the attachments of the message
 * @property blocks the blocks you want to include in the message
 * @property responseType the responsetype of your message, can be [ResponseType.IN_CHANNEL] or [ResponseType.EPHEMERAL]
 */
@JacksonDataClass
data class RespondMessageRequest constructor(@JsonProperty("text") val text: String? = null,
                                             @JsonProperty("attachments") val attachments: List<Attachment>? = null,
                                             @JsonProperty("blocks") val blocks: List<Block>? = null,
                                             @JsonProperty("response_type") val responseType: ResponseType) {
    companion object
}

@JsonSerialize(using = ResponseType.Serializer::class)
enum class ResponseType {
    IN_CHANNEL, EPHEMERAL;

    class Serializer : JsonSerializer<ResponseType>() {
        override fun serialize(value: ResponseType?, gen: JsonGenerator?, provider: SerializerProvider?) {
            gen?.writeString(value.toString().toLowerCase())
        }
    }
}
