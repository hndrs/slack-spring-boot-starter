package com.kreait.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass


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

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
@JacksonDataClass
data class SuccessfulConversationMembersResponse constructor(override val ok: Boolean,
                                                             @JsonProperty("members") val memberIds: List<String>,
                                                             @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata)
    : ConversationMembersResponse(ok) {
    companion object
}

/**
 * Failure-response of this request
 *
 * @property ok will be false
 * @property error contains the error description
 */
@JacksonDataClass
data class ErrorConversationMembersResponse constructor(override val ok: Boolean, @JsonProperty("error") val error: String)
    : ConversationMembersResponse(ok) {
    companion object
}

/**
 * Retrieve members of a conversation.
 *
 * @property channelId channel-id of the channel you want to fetch the users from
 * @property cursor Paginate through collections of data by setting the cursor parameter to a next_cursor attribute returned by a previous request's response_metadata. Default value fetches the first "page" of the collection.
 * @property limit The maximum number of items to return. Fewer than the requested number of items may be returned, even if the end of the users list hasn't been reached.
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
