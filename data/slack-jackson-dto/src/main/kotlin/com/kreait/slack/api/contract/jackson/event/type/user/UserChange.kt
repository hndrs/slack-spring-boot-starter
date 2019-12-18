package com.kreait.slack.api.contract.jackson.event.type.user

import com.fasterxml.jackson.annotation.JsonProperty
import com.kreait.slack.api.contract.jackson.common.types.Member
import com.kreait.slack.api.contract.jackson.event.Event
import com.kreait.slack.api.contract.jackson.util.JacksonDataClass

@JacksonDataClass
data class UserChange(override val type: String,
                      @JsonProperty("user") val member: Member) : Event(type) {
    companion object {
        /**
         * A member's data has changed
         * https://api.slack.com/events/user_change
         */
        const val TYPE = "user_change"
    }

    override fun slackTypeString() = TYPE
}
