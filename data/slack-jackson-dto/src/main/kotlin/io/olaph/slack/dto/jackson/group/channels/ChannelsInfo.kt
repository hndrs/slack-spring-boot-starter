package io.olaph.slack.dto.jackson.group.channels

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ok",
        visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulGetChannelInfoResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGetChannelInfoResponse::class, name = "false")
)
@JacksonDataClass
abstract class SlackGetChannelInfoResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulGetChannelInfoResponse constructor(override val ok: Boolean,
                                                        @JsonProperty("channel") val channel: ChannelInfoChannel)
    : SlackGetChannelInfoResponse(ok)

@JacksonDataClass
data class ErrorGetChannelInfoResponse constructor(override val ok: Boolean,
                                                   @JsonProperty("error") val error: String)
    : SlackGetChannelInfoResponse(ok)


data class ChannelInfoChannel(
        @JsonProperty("id") val id: String = "",
        @JsonProperty("name") val name: String = "",
        @JsonProperty("is_channel") val isChannel: Boolean = false,
        @JsonProperty("created") val created: Int = 0,
        @JsonProperty("creator") val creator: String = "",
        @JsonProperty("is_archived") val isArchived: Boolean = false,
        @JsonProperty("is_general") val isGeneral: Boolean = false,
        @JsonProperty("name_normalized") val nameNormalized: String = "",
        @JsonProperty("is_shared") val isShared: Boolean = false,
        @JsonProperty("is_org_shared") val isOrgShared: Boolean = false,
        @JsonProperty("is_member") val isMember: Boolean = false,
        @JsonProperty("is_private") val isPrivate: Boolean = false,
        @JsonProperty("is_mpim") val isMpim: Boolean = false,
        @JsonProperty("last_read") val lastRead: String = "",
        @JsonProperty("latest") val latest: ChannelInfoLatest = ChannelInfoLatest(),
        @JsonProperty("unread_count") val unreadCount: Int = 0,
        @JsonProperty("unread_count_display") val unreadCountDisplay: Int = 0,
        @JsonProperty("members") val members: List<String> = listOf(),
        @JsonProperty("topic") val topic: ChannelInfoTopic = ChannelInfoTopic(),
        @JsonProperty("purpose") val purpose: ChannelInfoPurpose = ChannelInfoPurpose(),
        @JsonProperty("previous_names") val previousNames: List<String> = listOf()
)

data class ChannelInfoLatest(
        @JsonProperty("text") val text: String = "",
        @JsonProperty("username") val username: String = "",
        @JsonProperty("bot_id") val botId: String = "",
        @JsonProperty("attachments") val attachments: List<ChannelInfoAttachment> = listOf(),
        @JsonProperty("type") val type: String = "",
        @JsonProperty("subtype") val subtype: String = "",
        @JsonProperty("ts") val ts: String = ""
)

data class ChannelInfoAttachment(
        @JsonProperty("text") val text: String = "",
        @JsonProperty("id") val id: Int = 0,
        @JsonProperty("fallback") val fallback: String = ""
)

data class ChannelInfoTopic(
        @JsonProperty("value") val value: String = "",
        @JsonProperty("creator") val creator: String = "",
        @JsonProperty("last_set") val lastSet: Int = 0
)

data class ChannelInfoPurpose(
        @JsonProperty("value") val value: String = "",
        @JsonProperty("creator") val creator: String = "",
        @JsonProperty("last_set") val lastSet: Int = 0
)


@JacksonDataClass
data class SlackChannelsInfoRequest constructor(@JsonProperty("channel") val channel: String,
                                                @JsonProperty("include_locale") val includeLocale: Boolean = false)
