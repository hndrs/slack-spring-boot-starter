package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationOpenResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationOpenResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationOpenResponse constructor(@JsonProperty("ok") open val ok: Boolean) {
    companion object
}

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
data class SuccessfulConversationOpenResponse constructor(override val ok: Boolean,
                                                          @JsonProperty("channel") val channel: Channel)
    : ConversationOpenResponse(ok) {

    companion object


    @JacksonDataClass
    data class Channel(@JsonProperty("id") val id: String) {
        companion object
    }
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorConversationOpenResponse constructor(override val ok: Boolean, @JsonProperty("error") val error: String)
    : ConversationOpenResponse(ok) {
    companion object
}

/**
 * Opens or resumes a direct message or multi-person direct message.
 *
 * @property channelId Resume a conversation by supplying an im or mpim's ID. Or provide the users field instead.
 * @property shouldReturnIm Boolean, indicates you want the full IM channel definition in the response.
 * @property users Comma separated lists of users. If only one user is included, this creates a 1:1 DM. The ordering of the users is preserved whenever a multi-person direct message is returned. Supply a channel when not supplying users.
 */
@JacksonDataClass
data class ConversationsOpenRequest constructor(@JsonProperty("channel") val channelId: String? = null,
                                                @JsonProperty("return_im") val shouldReturnIm: Boolean? = null,
                                                @JsonProperty("users") val users: List<String>? = null) {
    companion object
}


