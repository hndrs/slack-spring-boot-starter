package io.olaph.slack.dto.jackson.group.respond

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.messaging.Attachment
import io.olaph.slack.dto.jackson.common.messaging.Block

@JacksonDataClass
data class SlackRespondMessageRequest constructor(@JsonProperty("text") val text: String? = null,
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
