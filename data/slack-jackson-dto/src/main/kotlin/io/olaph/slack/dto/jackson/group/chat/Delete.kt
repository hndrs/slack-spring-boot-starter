package io.olaph.slack.dto.jackson.group.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulChatDeleteResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorChatDeleteResponse::class, name = "false")
)
@JacksonDataClass
abstract class SlackDeleteResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulChatDeleteResponse constructor(override val ok: Boolean,
                                                    @JsonProperty("channel") val channel: String,
                                                    @JsonProperty("ts") val team: String)
    : SlackDeleteResponse(ok) {
    companion object
}


@JacksonDataClass
data class ErrorChatDeleteResponse constructor(override val ok: Boolean,
                                               @JsonProperty("error") val error: String)
    : SlackDeleteResponse(ok) {
    companion object
}


@JacksonDataClass
data class SlackChatDeleteRequest constructor(@JsonProperty("channel") val channel: String,
                                              @JsonProperty("ts") val timestamp: String,
                                              @JsonProperty("as_user") val asUser: Boolean = false) {
    companion object
}
