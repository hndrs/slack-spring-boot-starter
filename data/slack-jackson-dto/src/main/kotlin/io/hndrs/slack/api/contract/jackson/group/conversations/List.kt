package io.hndrs.slack.api.contract.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.hndrs.slack.api.contract.jackson.ChannelType
import io.hndrs.slack.api.contract.jackson.common.ResponseMetadata
import io.hndrs.slack.api.contract.jackson.common.types.Conversation
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "ok",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulConversationListResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorConversationListResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

/**
 * Success-response of this request.
 *
 * @property ok will be true
 */
data class SuccessfulConversationListResponse(
    override val ok: Boolean,
    @JsonProperty("channels") val channels: List<Conversation>,
    @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata
) : ConversationListResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorConversationListResponse constructor(
    override val ok: Boolean,
    @JsonProperty("error") val error: String
) : ConversationListResponse(ok) {
    companion object
}

/**
 * Lists all channels
 *
 * @property cursor Paginate through collections of data by setting the cursor parameter to a next_cursor attribute returned by a previous request's response_metadata. Default value fetches the first "page" of the collection.
 * @property excludeArchived Set to true to exclude archived channels from the list
 * @property limit The maximum number of items to return. Fewer than the requested number of items may be returned, even if the end of the list hasn't been reached. Must be an integer no larger than 1000.
 * @property Mix and match channel types by providing a comma-separated list of any combination of public_channel, private_channel, mpim, im
 */
data class ConversationsListRequest(
    private val cursor: String? = null,
    private val excludeArchived: Boolean? = null,
    private val limit: Int? = null,
    private val types: Set<ChannelType>? = null
) {

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
