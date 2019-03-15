package io.olaph.slack.dto.jackson.group.conversations

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.ChannelType
import io.olaph.slack.dto.jackson.JacksonDataClass
import org.jetbrains.annotations.Nullable

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccesfulGetConversationListResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGetConversationListResponse::class, name = "false")
)
@JacksonDataClass
abstract class SlackGetConversationListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

data class SuccesfulGetConversationListResponse(
        override val ok: Boolean,
        @JsonProperty("channels") val channels: List<ConversationListChannel>,
        @JsonProperty("response_metadata") val responseMetadata: ResponseMetadata
) : SlackGetConversationListResponse(ok)

@JacksonDataClass
data class ErrorGetConversationListResponse constructor(override val ok: Boolean,
                                                        @JsonProperty("error") val error: String)
    : SlackGetConversationListResponse(ok)

data class ConversationListChannel(
        @JsonProperty("id") val id: String,
        @JsonProperty("name") val name: String,
        @JsonProperty("is_channel") val isChannel: Boolean,
        @JsonProperty("is_group") val isGroup: Boolean,
        @JsonProperty("is_im") val isIm: Boolean,
        @JsonProperty("created") val created: Int,
        @JsonProperty("is_archived") val isArchived: Boolean,
        @JsonProperty("is_general") val isGeneral: Boolean,
        @JsonProperty("unlinked") val unlinked: Int,
        @JsonProperty("name_normalized") val nameNormalized: String,
        @JsonProperty("is_shared") val isShared: Boolean?,
        @JsonProperty("creator") val creator: String?,
        @JsonProperty("is_ext_shared") val isExtShared: Boolean?,
        @JsonProperty("is_org_shared") val isOrgShared: Boolean?,
        @JsonProperty("shared_team_ids") val sharedTeamIds: List<String>?,
        @JsonProperty("pending_shared") val pendingShared: List<Any>?,
        @JsonProperty("is_pending_ext_shared") val isPendingExtShared: Boolean?,
        @JsonProperty("is_member") val isMember: Boolean?,
        @JsonProperty("is_private") val isPrivate: Boolean,
        @JsonProperty("is_mpim") val isMpim: Boolean?,
        @JsonProperty("topic") val topic: Topic?,
        @JsonProperty("purpose") val purpose: Purpose?,
        @JsonProperty("previous_names") @Nullable val previousNames: List<Any>?,
        @JsonProperty("num_members") val numMembers: Int?,
        @JsonProperty("last_read") @Nullable val lastRead: String?,
        @JsonProperty("is_open") val isOpen: Boolean,
        @JsonProperty("priority") val priority: Int
)

data class Purpose(
        @JsonProperty("value") val value: String,
        @JsonProperty("creator") val creator: String,
        @JsonProperty("last_set") val lastSet: Int
)

data class Topic(
        @JsonProperty("value") val value: String,
        @JsonProperty("creator") val creator: String,
        @JsonProperty("last_set") val lastSet: Int
)

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
}