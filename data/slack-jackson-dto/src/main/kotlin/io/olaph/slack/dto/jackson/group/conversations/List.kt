package io.olaph.slack.dto.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.ChannelType
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.ResponseMetadata
import io.olaph.slack.dto.jackson.common.types.Conversation

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulConversationListResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorConversationListResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

data class SuccessfulConversationListResponse(
        override val ok: Boolean,
        @JsonProperty("channels") val channels: List<Conversation>,
        @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata
) : ConversationListResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorConversationListResponse constructor(override val ok: Boolean,
                                                     @JsonProperty("error") val error: String)
    : ConversationListResponse(ok) {
    companion object
}

/**
 * DataClass that represents arguments as defined here https://api.slack.com/methods/conversations.list
 */
data class ConversationsListRequest(private val cursor: String? = null,
                                    private val excludeArchived: Boolean? = null,
                                    private val limit: Int? = null,
                                    private val types: Set<ChannelType>? = null) {

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        cursor?.let { requestMap.put("cursor", it) }
        excludeArchived?.let { requestMap.put("exclude_archived", it.toString()) }
        limit?.let { requestMap.put("limit", it.toString()) }
        types?.let { requestMap.put("types", it.map(ChannelType::value).joinToString(",")) }
        return requestMap
    }

    companion object

}
