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
        JsonSubTypes.Type(value = SuccessfulGetChannelListResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorGetChannelListResponse::class, name = "false")
)
@JacksonDataClass
abstract class SlackGetChannelListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class ErrorGetChannelListResponse constructor(override val ok: Boolean,
                                                   @JsonProperty("error") val error: String)
    : SlackGetChannelListResponse(ok) {
    companion object
}

@JacksonDataClass
data class SuccessfulGetChannelListResponse(
        override val ok: Boolean,
        @JsonProperty("channels") val channels: List<Channel>) : SlackGetChannelListResponse(ok) {
    companion object
}

@JacksonDataClass
data class Channel(
        @JsonProperty("id") val id: String,
        @JsonProperty("name") val name: String,
        @JsonProperty("is_channel") val isChannel: Boolean,
        @JsonProperty("created") val created: Int,
        @JsonProperty("is_archived") val isArchived: Boolean,
        @JsonProperty("is_general") val isGeneral: Boolean,
        @JsonProperty("unlinked") val unlinked: Int,
        @JsonProperty("creator") val creator: String,
        @JsonProperty("name_normalized") val nameNormalized: String,
        @JsonProperty("is_shared") val isShared: Boolean,
        @JsonProperty("is_org_shared") val isOrgShared: Boolean,
        @JsonProperty("is_member") val isMember: Boolean,
        @JsonProperty("is_private") val isPrivate: Boolean,
        @JsonProperty("is_mpim") val isMpim: Boolean,
        //TODO make this an INSTANT
        @JsonProperty("last_read") val lastRead: String,
        @JsonProperty("unread_count") val unreadCount: Int,
        @JsonProperty("unread_count_display") val unreadCountDisplay: Int,
        @JsonProperty("members") val members: List<String>,
        @JsonProperty("topic") val topic: Topic,
        @JsonProperty("purpose") val purpose: Purpose,
        @JsonProperty("previous_names") val previousNames: List<String>,
        @JsonProperty("num_members") val numMembers: Int
) {
    companion object
}

@JacksonDataClass
data class Purpose(
        @JsonProperty("value") val value: String,
        @JsonProperty("creator") val creator: String,
        @JsonProperty("last_set") val lastSet: Int
) {
    companion object
}

@JacksonDataClass
data class Topic(
        @JsonProperty("value") val value: String,
        @JsonProperty("creator") val creator: String,
        @JsonProperty("last_set") val lastSet: Int
) {
    companion object
}
