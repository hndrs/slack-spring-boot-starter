package com.kreait.slack.api.contract.jackson.group.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kreait.slack.api.contract.jackson.ChannelType
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.types.Purpose
import com.kreait.slack.api.contract.jackson.common.types.Topic
import com.kreait.slack.api.contract.jackson.util.InstantToInt
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = SuccessfulConversationsResponse::class, name = "true"),
    JsonSubTypes.Type(value = ErrorConversationsResponse::class, name = "false")
)
@JacksonDataClass
sealed class ConversationsResponse constructor(@JsonProperty("ok") open val ok: Boolean)


data class ErrorConversationsResponse(
    override var ok: Boolean,
    @JsonProperty("error") val error: String
) : ConversationsResponse(ok) {
    companion object
}

data class SuccessfulConversationsResponse(
    override var ok: Boolean,
    @JsonProperty("channels") val channels: List<Channel>,
    @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata
) : ConversationsResponse(ok) {
    companion object
}


data class Channel(
    @JsonProperty("id") val id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("is_channel") val isChannel: Boolean?,
    @JsonProperty("is_group") val isGroup: Boolean?,
    @JsonProperty("is_im") val isIm: Boolean?,
    @InstantToInt @JsonProperty("created") val createdAt: Instant,
    @JsonProperty("creator") val creator: String = "",
    @JsonProperty("is_archived") val isArchived: Boolean?,
    @JsonProperty("is_general") val isGeneral: Boolean?,
    @JsonProperty("unlinked") val unlinked: Int?,
    @JsonProperty("name_normalized") val nameNormalized: String?,
    @JsonProperty("is_shared") val isShared: Boolean?,
    @JsonProperty("is_ext_shared") val isExtShared: Boolean?,
    @JsonProperty("is_org_shared") val isOrgShared: Boolean?,
    @JsonProperty("pending_shared") val pendingShared: List<Any>?,
    @JsonProperty("is_pending_ext_shared") val isPendingExtShared: Boolean?,
    @JsonProperty("is_private") val isPrivate: Boolean,
    @JsonProperty("is_mpim") val isMpim: Boolean?,
    @JsonProperty("is_open") val isOpen: Boolean?,
    @JsonProperty("topic") val topic: Topic?,
    @JsonProperty("purpose") val purpose: Purpose?,
    @JsonProperty("priority") val priority: Int?,
    @JsonProperty("user") val user: String?,
    @JsonProperty("is_user_deleted") val isUserDeleted: Boolean?
)

/**
 * DataClass that represents arguments as defined here https://api.slack.com/methods/users.conversations
 */
data class ConversationsRequest(
    private val cursor: String? = null,
    private val excludeArchived: Boolean? = null,
    private val limit: Int? = null,
    private val types: Set<ChannelType>? = null,
    private val userId: String? = null
) {

    fun toRequestMap(): MutableMap<String, String> {
        val requestMap = mutableMapOf<String, String>()
        cursor?.let { requestMap.put("cursor", it) }
        excludeArchived?.let { requestMap.put("exclude_archived", it.toString()) }
        limit?.let { requestMap.put("limit", it.toString()) }
        types?.let { requestMap.put("types", it.map(ChannelType::value).joinToString(",")) }
        userId?.let { requestMap.put("user", it) }
        return requestMap
    }

    companion object
}
