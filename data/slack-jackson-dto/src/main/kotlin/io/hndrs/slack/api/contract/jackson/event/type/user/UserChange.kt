package io.hndrs.slack.api.contract.jackson.event.type.user

import com.fasterxml.jackson.annotation.JsonProperty
import io.hndrs.slack.api.contract.jackson.common.types.Member
import io.hndrs.slack.api.contract.jackson.event.Event
import io.hndrs.slack.api.contract.jackson.util.JacksonDataClass

@JacksonDataClass
data class UserChange(
    override val type: String,
    @JsonProperty("user") val member: Member
) : Event(type) {

    override fun slackTypeString() = TYPE

    companion object {
        /**
         * A member's data has changed
         * https://api.slack.com/events/user_change
         */
        const val TYPE = "user_change"
    }
}
