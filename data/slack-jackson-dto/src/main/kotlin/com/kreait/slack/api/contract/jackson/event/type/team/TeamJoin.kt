package com.kreait.slack.api.contract.jackson.event.type.team

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.common.types.Member
import com.kreait.slack.api.contract.jackson.event.Event
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JacksonDataClass
data class TeamJoin(
    override val type: String,
    @JsonProperty("user") val member: Member
) : Event(type) {

    override fun slackTypeString() = TYPE

    companion object {
        /**
         * A new member has joined
         * https://api.slack.com/events/team_join
         */
        const val TYPE = "team_join"
    }
    
}
