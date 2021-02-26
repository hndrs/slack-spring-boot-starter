package io.hndrs.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.common.types.Channel
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulConversationCreateResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorConversationCreateResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationCreateResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 * @property channel the new channel object you created
 */
data class SuccessfulConversationCreateResponse(
    override val ok: Boolean,
    @JsonProperty("channel") val channel: Channel
) : ConversationCreateResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorConversationCreateResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ConversationCreateResponse(ok) {
    companion object
}

/**
 * Initiates a public or private channel-based conversation
 *
 * @property name the channel name for the channel you want to create
 * @property isPrivate true if you want to create a private channel
 */
data class ConversationCreateRequest(
    @JsonProperty("name") val name: String,
    @JsonProperty("is_private") val isPrivate: Boolean? = null
) {
    companion object
}
