package com.kreait.slack.api.contract.jackson.common.types

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JacksonDataClass
data class ShareDetail(
    @JsonProperty("reply_users") val replyUsers: List<String>?,
    @JsonProperty("reply_users_count") val replyUsersCount: Int?,
    @JsonProperty("reply_count") val replyCount: Int?,
    @InstantToString @JsonProperty("ts") val ts: Instant?,
    @InstantToString @JsonProperty("thread_ts") val threadTs: Instant?,
    @InstantToString @JsonProperty("latest_reply") val latestReply: Instant?,
    @JsonProperty("channel_name") val channelName: String?,
    @JsonProperty("team_id") val teamId: String?
)
