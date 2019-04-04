package io.olaph.slack.dto.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationMembersResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationMembersResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationMembersResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulConversationMembersResponse constructor(override val ok: Boolean,
                                                             @JsonProperty("members") val memberIds: List<String>,
                                                             @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata)
    : ConversationMembersResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorConversationMembersResponse constructor(override val ok: Boolean, @JsonProperty("error") val error: String)
    : ConversationMembersResponse(ok) {
    companion object
}

data class ResponseMetadata(@JsonProperty("next_cursor") val nextCursor: String) {
    companion object
}

/**
 * DataClass that represents arguments as defined here https://api.slack.com/methods/conversations.members
 */
data class ConversationMembersRequest(private val channelId: String,
                                      private val cursor: String? = null,
                                      private val limit: Int? = null) {

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        requestMap.put("channel", channelId)
        cursor?.let { requestMap.put("cursor", it) }
        limit?.let { requestMap.put("limit", it.toString()) }
        return requestMap
    }

    companion object
}
