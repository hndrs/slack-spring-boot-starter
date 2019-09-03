package com.kreait.slack.api.contract.jackson

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.common.Action
import com.kreait.slack.api.contract.jackson.common.messaging.Block
import com.kreait.slack.api.contract.jackson.group.chat.Message
import com.kreait.slack.api.contract.jackson.util.InstantToString
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass
import java.time.Instant

@JacksonDataClass
@JsonIgnoreProperties(ignoreUnknown = true)
data class InteractiveComponentResponse(
        //TODO can this really be optional?
        @JsonProperty("type") val type: String?,
        @JsonProperty("token") val token: String?,
        @InstantToString @JsonProperty("action_ts") val actionTimestamp: Instant?,
        @InstantToString @JsonProperty("message_ts") val timestamp: Instant?,
        @JsonProperty("team") val team: Team,
        @JsonProperty("user") val user: User,
        @JsonProperty("state") val state: String? = "",
        @JsonProperty("channel") val channel: Channel,
        @JsonProperty("submission") val submission: Map<String, Any>?,
        @JsonProperty("callback_id") val callbackId: String?,
        @JsonProperty("response_url") val responseUrl: String?,
        @JsonProperty("actions") val actions: List<Action>? = listOf(),
        @JsonProperty("blocks") val blocks: List<Block>? = listOf(),
        @JsonProperty("container") val container: Container? = null,
        @JsonProperty("trigger_id") val triggerId: String?,
        @JsonProperty("message") val message: Message?) {

    companion object

    @JacksonDataClass
    data class Channel(@JsonProperty("id") val id: String,
                       @JsonProperty("name") val name: String) {
        companion object
    }

    @JacksonDataClass
    data class User(@JsonProperty("id") val id: String,
                    @JsonProperty("name") val name: String,
                    @JsonProperty("username") val username: String,
            //TODO can this really be optional?
                    @JsonProperty("team_id") val teamId: String?) {
        companion object
    }

    @JacksonDataClass
    data class Team(@JsonProperty("id") val id: String,
                    @JsonProperty("domain") val domain: String,
                    @JsonProperty("enterprise_id") val enterpriseId: String?,
                    @JsonProperty("enterprise_name") val enterpriseName: String?) {
        companion object
    }

    data class Container(
            @JsonProperty("channel_id") val channelId: String?,
            @JsonProperty("is_ephemeral") val isEphemeral: Boolean?,
            @JsonProperty("message_ts") val messageTs: String?,
            @JsonProperty("type") val type: String?
    )
}






