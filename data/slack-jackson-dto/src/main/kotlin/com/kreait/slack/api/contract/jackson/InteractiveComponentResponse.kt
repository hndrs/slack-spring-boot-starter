package com.kreait.slack.api.contract.jackson

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.common.Action
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JacksonDataClass
@JsonIgnoreProperties(ignoreUnknown = true)
data class InteractiveComponentResponse(
        //TODO can this really be optional?
        @JsonProperty("type") val type: String?,
        @JsonProperty("token") val token: String?,
        //TODO can this really be optional?
        @JsonProperty("action_ts") val actionTs: String?,
        //TODO can this really be optional?
        @JsonProperty("message_ts") val messageTs: String?,
        @JsonProperty("team") val team: Team,
        @JsonProperty("user") val user: User,
        @JsonProperty("state") val state: String? = "",
        @JsonProperty("channel") val channel: Channel,
        //TODO can this really be optional?
        @JsonProperty("submission") val submission: Map<String, Any>?,
        @JsonProperty("callback_id") val callbackId: String?,
        @JsonProperty("response_url") val responseUrl: String?,
        @JsonProperty("actions") val actions: List<Action>? = listOf(),
        @JsonProperty("trigger_id") val triggerId: String?) {

    companion object

    @JacksonDataClass
    data class Channel(@JsonProperty("id") val id: String,
                       @JsonProperty("name") val name: String) {
        companion object
    }

    @JacksonDataClass
    data class User(@JsonProperty("id") val id: String,
                    @JsonProperty("name") val name: String,
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
}






